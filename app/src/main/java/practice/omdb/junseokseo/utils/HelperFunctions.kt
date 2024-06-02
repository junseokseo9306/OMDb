package practice.omdb.junseokseo.utils

import android.content.res.Resources

fun Float.fromDpToPx(): Int = (this * Resources.getSystem().displayMetrics.density).toInt()