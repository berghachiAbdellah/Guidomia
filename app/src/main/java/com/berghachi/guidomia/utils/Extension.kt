package com.berghachi.guidomia.utils

import android.view.View
import android.widget.ImageView
import java.text.DecimalFormat
import kotlin.math.ln
import kotlin.math.pow

fun ImageView.loadImage(fileName: String) {
    val identifier = resources.getIdentifier(
        fileName.lowercase().replace(" ", ""),
        "drawable",
        context.packageName
    )
    this.setImageResource(identifier)
}



fun Long.coolNumberFormat(): String {
    if (this < 1000) return "" + this
    val exp = (ln(this.toDouble()) / ln(1000.0)).toInt()
    val format = DecimalFormat("0.#")
    val value: String = format.format(this / 1000.0.pow(exp.toDouble()))
    return String.format("%s%c", value, "kMBTPE"[exp - 1])
}