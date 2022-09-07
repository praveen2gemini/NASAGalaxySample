package com.cts.galaxy.utils

import android.graphics.Typeface.BOLD
import android.text.Spannable
import android.text.SpannableStringBuilder
import android.text.style.StyleSpan
import androidx.appcompat.widget.AppCompatTextView
import com.cts.galaxy.R
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

fun AppCompatTextView.loadFormattedDate(dateCreated: String) {
    val label = context.getString(R.string.label_date_creation)
    val content = getFormattedDate(dateCreated)
    text = getSemiBoldedSpan(label, content)
}

fun AppCompatTextView.loadCenter(center: String) {
    val label = context.getString(R.string.label_center)
    text = getSemiBoldedSpan(label, center)
}

fun getSemiBoldedSpan(label: String, content: String): SpannableStringBuilder {
    return SpannableStringBuilder(label.plus(" ").plus(content)).apply {
        setSpan(
            StyleSpan(BOLD),
            0, label.length,
            Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
        )
    }
}

fun getFormattedDate(dateCreated: String): String {
//    val dateFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss", Locale.ENGLISH)
    val dateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH)
    val date = try {
        val date = dateFormat.parse(dateCreated)
        dateFormat.format(date)
    } catch (pe: ParseException) {
        pe.printStackTrace()
        "N/A"
    }
    return date
}