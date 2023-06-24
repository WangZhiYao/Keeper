package com.yizhenwind.keeper.common.ext

import android.view.View
import androidx.annotation.StringRes
import androidx.core.content.ContextCompat
import com.google.android.material.snackbar.BaseTransientBottomBar
import com.google.android.material.snackbar.Snackbar
import com.yizhenwind.keeper.R

/**
 *
 *
 * @author WangZhiYao
 * @since 2023/3/30
 */
fun View.makeSnack(
    @StringRes resId: Int,
    @BaseTransientBottomBar.Duration duration: Int = Snackbar.LENGTH_SHORT
) = makeSnack(resources.getText(resId), duration)

fun View.makeSnack(
    text: CharSequence,
    @BaseTransientBottomBar.Duration duration: Int = Snackbar.LENGTH_SHORT
) = Snackbar.make(this, text, duration)

fun View.showSnack(
    @StringRes resId: Int,
    @BaseTransientBottomBar.Duration duration: Int = Snackbar.LENGTH_SHORT
) {
    showSnack(resources.getText(resId), duration)
}

fun View.showSnack(
    text: CharSequence,
    @BaseTransientBottomBar.Duration duration: Int = Snackbar.LENGTH_SHORT
) {
    makeSnack(text, duration).show()
}

fun View.showSnack(
    @StringRes resId: Int,
    @BaseTransientBottomBar.Duration duration: Int = Snackbar.LENGTH_SHORT,
    anchorView: View
) {
    showSnack(resources.getText(resId), duration, anchorView)
}

fun View.showSnack(
    text: CharSequence,
    @BaseTransientBottomBar.Duration duration: Int = Snackbar.LENGTH_SHORT,
    anchorView: View
) {
    makeSnack(text, duration).setAnchorView(anchorView).show()
}

fun View.showSnack(
    @StringRes resId: Int,
    @BaseTransientBottomBar.Duration duration: Int = Snackbar.LENGTH_SHORT,
    @StringRes actionResId: Int,
    listener: View.OnClickListener
) {
    showSnack(
        resources.getText(resId),
        duration,
        resources.getText(actionResId),
        listener
    )
}

fun View.showSnack(
    text: CharSequence,
    @BaseTransientBottomBar.Duration duration: Int = Snackbar.LENGTH_SHORT,
    actionText: CharSequence,
    listener: View.OnClickListener
) {
    makeSnack(text, duration)
        .setAction(actionText, listener)
        .setActionTextColor(ContextCompat.getColor(context, R.color.color_accent))
        .show()
}

fun View.showSnack(
    @StringRes resId: Int,
    @BaseTransientBottomBar.Duration duration: Int = Snackbar.LENGTH_SHORT,
    anchorView: View,
    @StringRes actionResId: Int,
    listener: View.OnClickListener
) {
    showSnack(
        resources.getText(resId),
        duration,
        anchorView,
        resources.getText(actionResId),
        listener
    )
}

fun View.showSnack(
    text: CharSequence,
    @BaseTransientBottomBar.Duration duration: Int = Snackbar.LENGTH_SHORT,
    anchorView: View,
    actionText: CharSequence,
    listener: View.OnClickListener
) {
    makeSnack(text, duration)
        .setAnchorView(anchorView)
        .setAction(actionText, listener)
        .setActionTextColor(ContextCompat.getColor(context, R.color.color_accent))
        .show()
}