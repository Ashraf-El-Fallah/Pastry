package com.example.pastry.utils

import android.widget.ImageView
import com.bumptech.glide.Glide

fun ImageView.loadImage(url: String?) {
    Glide.with(this.context)
        .load(url)
        .into(this)
}

//class PageDecoder(
//    private val bitmapPool: BitmapPool,
//) : ResourceDecoder<InputStream, Bitmap> {
//
//    companion object {
//        val PAGE_DECODER = Option.builder("abc")
//    }
//
//    override fun decode(
//        source: InputStream,
//        width: Int,
//        height: Int,
//        options: Options,
//    ): Resource<Bitmap>? {
//        return BitmapResource.obtain(BitmapFactory.decodeStream(source), bitmapPool)
//    }
//
//    override fun handles(source: InputStream, options: Options): Boolean =
//        options.get(PAGE_DECODER) ?: false
//
//}
//
//fun registerComponents(context: Context, glide: Glide, registry: Registry) {
//    registry.prepend(InputStream::class.java, Bitmap::class.java, PageDecoder(glide.bitmapPool))
//}