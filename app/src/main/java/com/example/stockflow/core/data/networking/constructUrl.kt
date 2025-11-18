package com.example.stockflow.core.data.networking

import com.example.stockflow.BuildConfig

fun constructUrl(url: String): String {

    return when {
        url.startsWith(BuildConfig.BASE_URL) -> url
        url.startsWith("/") -> BuildConfig.BASE_URL + url.drop(1)
        else -> BuildConfig.BASE_URL + url
    }

}