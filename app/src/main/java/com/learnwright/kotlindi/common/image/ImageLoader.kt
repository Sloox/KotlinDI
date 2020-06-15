package com.learnwright.kotlindi.common.image

import android.app.Activity
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions

class ImageLoader(private val mActivity: Activity) {
    private val mDefaultRequestOptions: RequestOptions = RequestOptions().centerCrop()

    fun loadImage(uri: String?, target: ImageView?) {
        Glide.with(mActivity).load(uri).apply(mDefaultRequestOptions).into(target)
    }
}