package com.importantpeople.reportapp.util

import android.content.ContentResolver
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.util.Base64
import java.io.ByteArrayOutputStream

/** Utilidades para reducir las fotos y guardarlas como texto Base64. */
object ImageUtils {

    private const val MAX_SIZE = 800

    /** Carga una imagen de la galería ya reducida para no gastar memoria. */
    fun loadScaledBitmap(resolver: ContentResolver, uri: Uri): Bitmap? {
        val bounds = BitmapFactory.Options().apply { inJustDecodeBounds = true }
        resolver.openInputStream(uri)?.use { BitmapFactory.decodeStream(it, null, bounds) }

        var sample = 1
        while (bounds.outWidth / sample > MAX_SIZE * 2 || bounds.outHeight / sample > MAX_SIZE * 2) {
            sample *= 2
        }
        val options = BitmapFactory.Options().apply { inSampleSize = sample }
        val bitmap = resolver.openInputStream(uri)?.use {
            BitmapFactory.decodeStream(it, null, options)
        } ?: return null
        return scaleDown(bitmap)
    }

    fun scaleDown(bitmap: Bitmap, maxSize: Int = MAX_SIZE): Bitmap {
        val ratio = minOf(maxSize.toFloat() / bitmap.width, maxSize.toFloat() / bitmap.height)
        if (ratio >= 1f) return bitmap
        return Bitmap.createScaledBitmap(
            bitmap,
            (bitmap.width * ratio).toInt(),
            (bitmap.height * ratio).toInt(),
            true
        )
    }

    fun toBase64(bitmap: Bitmap, quality: Int = 60): String {
        val out = ByteArrayOutputStream()
        scaleDown(bitmap).compress(Bitmap.CompressFormat.JPEG, quality, out)
        return Base64.encodeToString(out.toByteArray(), Base64.NO_WRAP)
    }

    fun fromBase64(data: String?): Bitmap? {
        if (data.isNullOrBlank()) return null
        return runCatching {
            val bytes = Base64.decode(data, Base64.NO_WRAP)
            BitmapFactory.decodeByteArray(bytes, 0, bytes.size)
        }.getOrNull()
    }
}
