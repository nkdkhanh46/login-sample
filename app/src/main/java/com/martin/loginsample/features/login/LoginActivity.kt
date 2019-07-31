package com.martin.loginsample.features.login

import android.content.Intent
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.martin.loginsample.R
import com.martin.loginsample.application.MainApplication
import com.martin.loginsample.base.BaseActivity
import com.martin.loginsample.databinding.ActivityLoginBinding
import com.martin.loginsample.features.home.WelcomeActivity
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : BaseActivity() {

    private lateinit var binding: ActivityLoginBinding
    lateinit var viewModel: LoginViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupBinding()
        setupEvents()
        observeChanges()
    }

    private fun setupBinding() {
        (application as MainApplication).appComponent.inject(this)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_login)
        viewModel = ViewModelProvider(this, viewModelFactory)[LoginViewModel::class.java]
        binding.viewModel = viewModel
        binding.executePendingBindings()
    }

    private fun setupEvents() {
        btnLogin.setOnClickListener {
            val username = etEmail.text.toString()
            val password = etPassword.text.toString()
            viewModel.login(this, username, password)
        }
    }

    private fun observeChanges() {
        viewModel.notification.observe(this, Observer {
            it?.let { value ->
                when (value) {
                    LoginViewModel.NOTIFICATION_EMPTY_EMAIL -> showAlertErrorMessage(R.string.error_login_empty_email)
                    LoginViewModel.NOTIFICATION_EMPTY_PASSWORD -> showAlertErrorMessage(R.string.error_login_empty_password)
                    LoginViewModel.NOTIFICATION_UNKNOWN_ERROR -> showAlertErrorMessage(R.string.error_login_unknown_error)
                    LoginViewModel.NOTIFICATION_INVALID_ACCOUNT -> showAlertErrorMessage(R.string.error_login_invalid_account)
                    LoginViewModel.NOTIFICATION_LOGIN_SUCCESS,
                    LoginViewModel.NOTIFICATION_ALREADY_LOGIN -> openWelcomeScreen()
                    LoginViewModel.NOTIFICATION_NETWORK_NOT_AVAILABLE -> showAlertErrorMessage(R.string.error_network_unavailable)
                }
                viewModel.notification.value = null
            }
        })
    }

    private fun openWelcomeScreen() {
        startActivity(Intent(this, WelcomeActivity::class.java))
        finish()
    }

    override fun onStart() {
        super.onStart()
        viewModel.checkLoginState()
    }
}