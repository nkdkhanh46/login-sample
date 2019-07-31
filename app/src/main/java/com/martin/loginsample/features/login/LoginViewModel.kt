package com.martin.loginsample.features.login

import android.content.Context
import androidx.databinding.ObservableField
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.gson.Gson
import com.martin.loginsample.networking.RetrofitClient
import com.martin.loginsample.networking.models.APIError
import com.martin.loginsample.networking.models.LoginRequest
import com.martin.loginsample.networking.models.LoginResponse
import com.martin.loginsample.session.SessionManager
import com.martin.loginsample.utils.NetworkUtil
import io.reactivex.SingleObserver
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import retrofit2.Response
import javax.inject.Inject

class LoginViewModel @Inject constructor(private val retrofitClient: RetrofitClient, private val sessionManager: SessionManager): ViewModel() {

    companion object {
        const val NOTIFICATION_EMPTY_EMAIL = 1
        const val NOTIFICATION_EMPTY_PASSWORD = 2
        const val NOTIFICATION_INVALID_ACCOUNT = 3
        const val NOTIFICATION_UNKNOWN_ERROR = 4
        const val NOTIFICATION_LOGIN_SUCCESS = 5
        const val NOTIFICATION_ALREADY_LOGIN = 6
        const val NOTIFICATION_NETWORK_NOT_AVAILABLE = 7
    }

    var notification = MutableLiveData<Int>()
    var loading = ObservableField(false)

    fun login(context: Context, email: String, password: String) {
        if (!verifyLoginInformation(email, password)) return

        if (!NetworkUtil.isInternetAvailable(context)) {
            notification.value = NOTIFICATION_NETWORK_NOT_AVAILABLE
            return
        }

        val body = LoginRequest(email, password)
        val loginObservable = retrofitClient.getService().login(body)
        loginObservable.subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object: SingleObserver<Response<LoginResponse>> {
                override fun onSubscribe(d: Disposable) {
                    loading.set(true)
                }

                override fun onSuccess(res: Response<LoginResponse>) {
                    loading.set(false)
                    handleLoginResponse(res)
                }

                override fun onError(e: Throwable) {
                    loading.set(false)
                    notification.value = NOTIFICATION_UNKNOWN_ERROR
                }
            })
    }

    private fun handleLoginResponse(res: Response<LoginResponse>) {
        if (res.isSuccessful && res.body() != null) {
            sessionManager.updateToken(res.body()!!.token)
            notification.value = NOTIFICATION_LOGIN_SUCCESS
            return
        }

        res.errorBody()?.let { it ->
            val error = Gson().fromJson(it.string(), APIError::class.java)
            if (error.error != null && error.error!!.equals("user not found", true)) {
                notification.value = NOTIFICATION_INVALID_ACCOUNT
                return
            }
        }

        notification.value = NOTIFICATION_UNKNOWN_ERROR
    }

    private fun verifyLoginInformation(email: String, password: String): Boolean {
        if (email.isEmpty()) {
            notification.value = NOTIFICATION_EMPTY_EMAIL
            return false
        }

        if (password.isEmpty()) {
            notification.value = NOTIFICATION_EMPTY_PASSWORD
            return false
        }

        return true
    }

    fun checkLoginState() {
        if (sessionManager.getToken().isNotEmpty()) {
            notification.value = NOTIFICATION_ALREADY_LOGIN
        }
    }
}