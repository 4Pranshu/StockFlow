package com.example.stockflow.core.presentation.util

import android.content.Context
import com.example.stockflow.R
import com.example.stockflow.core.domain.util.NetworkError


fun NetworkError.toString(context: Context): String {
    val resID = when (this) {
        NetworkError.REQUEST_ERROR -> R.string.error_request_error
        NetworkError.REQUEST_TIMEOUT -> R.string.error_request_timeOut
        NetworkError.TOO_MANY_REQUESTS -> R.string.error_too_many_request
        NetworkError.SERVER_ERROR -> R.string.error_server
        NetworkError.SERIALIZATION_ERROR -> R.string.error_serialization
        NetworkError.NO_INTERNET -> R.string.error_no_internet
        NetworkError.UNKNOWN -> R.string.error_unknown
    }

    return context.getString(resID)
}