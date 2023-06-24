package com.yizhenwind.keeper.common.ext

import androidx.annotation.StringRes
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.Fragment
import androidx.navigation.NavDirections
import androidx.navigation.fragment.findNavController
import com.yizhenwind.keeper.ui.widget.AlertDialogFragment

/**
 *
 *
 * @author WangZhiYao
 * @since 2023/6/23
 */
fun Fragment.navigate(directions: NavDirections) {
    findNavController().navigate(directions)
}

fun Fragment.navigateUp() = findNavController().navigateUp()

fun Fragment.showDialog(
    @StringRes titleId: Int,
    @StringRes messageId: Int,
    @StringRes positiveButton: Int = 0,
    onPositiveButtonClickListener: ((DialogFragment) -> Unit)? = null,
    @StringRes negativeButton: Int = 0,
    onNegativeButtonClickListener: ((DialogFragment) -> Unit)? = null,
    cancelable: Boolean = true
) {
    AlertDialogFragment.Builder(requireContext())
        .setTitle(titleId)
        .setMessage(messageId)
        .apply {
            if (positiveButton != 0 && onPositiveButtonClickListener != null) {
                setPositiveButton(positiveButton, onPositiveButtonClickListener)
            }
            if (negativeButton != 0 && onNegativeButtonClickListener != null) {
                setNegativeButton(negativeButton, onNegativeButtonClickListener)
            }
        }
        .setCancelable(cancelable)
        .build()
        .show(childFragmentManager)
}