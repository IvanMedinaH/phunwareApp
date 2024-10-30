package com.kotlin.phunwareapp.framework

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import com.kotlin.phunwareapp.data.local.NetworkVerifier

class AndroidNetworkVeriifierImpl(private val context: Context): NetworkVerifier {
    override fun isNetworkAvailable(): Boolean {
        val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val network = connectivityManager.activeNetwork
        val capabilities = connectivityManager.getNetworkCapabilities(network)
        return capabilities != null &&
                (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) ||
                        capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR))
    }
}