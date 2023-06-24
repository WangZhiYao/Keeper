package com.yizhenwind.keeper.ui.widget

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import androidx.annotation.StringRes
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentManager

/**
 *
 *
 * @author WangZhiYao
 * @since 2023/6/22
 */
class AlertDialogFragment private constructor(private val builder: Builder) : DialogFragment() {

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog =
        builder.run {
            isCancelable = cancelable
            AlertDialog.Builder(context)
                .setTitle(title)
                .setMessage(message)
                .apply {
                    positiveButton?.apply {
                        setPositiveButton(this) { _, _ ->
                            positiveButtonClickListener?.invoke(this@AlertDialogFragment)
                        }
                    }
                    negativeButton?.apply {
                        setNegativeButton(this) { _, _ ->
                            negativeButtonClickListener?.invoke(this@AlertDialogFragment)
                        }
                    }
                }
                .create()
        }

    fun show(manager: FragmentManager) {
        super.show(manager, TAG)
    }

    class Builder(val context: Context) {

        var title: CharSequence? = null
            private set

        var message: CharSequence? = null
            private set

        var positiveButton: CharSequence? = null
            private set
        var positiveButtonClickListener: ((dialog: DialogFragment) -> Unit)? = null
            private set

        var negativeButton: CharSequence? = null
            private set
        var negativeButtonClickListener: ((dialog: DialogFragment) -> Unit)? = null
            private set

        var cancelable: Boolean = true
            private set

        fun setTitle(@StringRes titleId: Int) =
            apply { this.title = context.getString(titleId) }

        fun setTitle(title: CharSequence?) =
            apply { this.title = title }

        fun setMessage(@StringRes messageId: Int) =
            apply { this.message = context.getString(messageId) }

        fun setMessage(message: CharSequence?) =
            apply { this.message = message }

        fun setPositiveButton(
            @StringRes textId: Int,
            positiveButtonClickListener: ((dialog: DialogFragment) -> Unit)
        ) =
            apply { setPositiveButton(context.getString(textId), positiveButtonClickListener) }

        fun setPositiveButton(
            text: CharSequence?,
            positiveButtonClickListener: ((dialog: DialogFragment) -> Unit)
        ) =
            apply {
                this.positiveButton = text
                this.positiveButtonClickListener = positiveButtonClickListener
            }

        fun setNegativeButton(
            @StringRes textId: Int,
            negativeButtonClickListener: ((dialog: DialogFragment) -> Unit)
        ) =
            apply { setNegativeButton(context.getString(textId), negativeButtonClickListener) }

        fun setNegativeButton(
            text: CharSequence?,
            negativeButtonClickListener: ((dialog: DialogFragment) -> Unit)
        ) =
            apply {
                this.negativeButton = text
                this.negativeButtonClickListener = negativeButtonClickListener
            }

        fun setCancelable(cancelable: Boolean) =
            apply { this.cancelable = cancelable }

        fun build(): AlertDialogFragment = AlertDialogFragment(this)

    }

    companion object {

        private const val TAG = "AlertDialogFragment"

    }
}