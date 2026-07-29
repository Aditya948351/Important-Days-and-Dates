package com.importantdays.presentation.components

import android.app.Activity
import android.content.Context
import android.util.Log
import com.google.android.gms.ads.AdError
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.FullScreenContentCallback
import com.google.android.gms.ads.LoadAdError
import com.google.android.gms.ads.interstitial.InterstitialAd
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback

import com.importantdays.BuildConfig

class InterstitialAdManager(private val context: Context) {
    private var mInterstitialAd: InterstitialAd? = null
    private var clickCount = 0
    private val clicksBeforeAd = 1
    private var pendingAction: (() -> Unit)? = null

    init {
        loadAd()
    }

    private fun loadAd() {
        val adRequest = AdRequest.Builder().build()

        val adUnitId = BuildConfig.INTERSTITIAL_AD_ID
        InterstitialAd.load(context, adUnitId, adRequest,
            object : InterstitialAdLoadCallback() {
                override fun onAdFailedToLoad(adError: LoadAdError) {
                    Log.d("InterstitialAdManager", adError.toString())
                    mInterstitialAd = null
                }

                override fun onAdLoaded(interstitialAd: InterstitialAd) {
                    Log.d("InterstitialAdManager", "Ad was loaded.")
                    mInterstitialAd = interstitialAd

                    mInterstitialAd?.fullScreenContentCallback = object: FullScreenContentCallback() {
                        override fun onAdDismissedFullScreenContent() {
                            Log.d("InterstitialAdManager", "Ad was dismissed.")
                            pendingAction?.invoke()
                            pendingAction = null
                            mInterstitialAd = null
                            loadAd()
                        }

                        override fun onAdFailedToShowFullScreenContent(adError: AdError) {
                            Log.d("InterstitialAdManager", "Ad failed to show.")
                            pendingAction?.invoke()
                            pendingAction = null
                            mInterstitialAd = null
                        }

                        override fun onAdShowedFullScreenContent() {
                            Log.d("InterstitialAdManager", "Ad showed fullscreen content.")
                        }
                    }
                }
            })
    }

    fun showAdIfReadyAndExecute(activity: Activity, onActionCompleted: () -> Unit) {
        clickCount++
        if (clickCount >= clicksBeforeAd) {
            clickCount = 0
            if (mInterstitialAd != null) {
                pendingAction = onActionCompleted
                mInterstitialAd?.show(activity)
            } else {
                Log.d("InterstitialAdManager", "The interstitial ad wasn't ready yet.")
                onActionCompleted()
            }
        } else {
            onActionCompleted()
        }
    }
}
