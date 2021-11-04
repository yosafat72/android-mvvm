package labs.yohesu.mvvm2.utils

import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DecodeFormat
import com.bumptech.glide.request.RequestOptions
import labs.yohesu.moviedb.R

object UIHelpers {

    fun loadImage(imgView: ImageView, url: String){
        Glide.with(imgView.context)
            .load(url)
            .apply(RequestOptions()
                .placeholder(R.drawable.not_found)
                .error(R.drawable.not_found)
                .format(DecodeFormat.PREFER_ARGB_8888))
            .into(imgView)
    }

}