package com.martin.loginsample.features.login

import androidx.lifecycle.ViewModel
import com.martin.loginsample.networking.RetrofitClient
import javax.inject.Inject

class LoginViewModel @Inject constructor(private val retrofitClient: RetrofitClient): ViewModel() {
}