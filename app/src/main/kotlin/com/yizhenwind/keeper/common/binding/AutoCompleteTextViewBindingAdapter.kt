package com.yizhenwind.keeper.common.binding

import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import androidx.databinding.BindingAdapter

/**
 *
 *
 * @author WangZhiYao
 * @since 2023/6/22
 */
@BindingAdapter("android:singleLineItem")
@Suppress("UNCHECKED_CAST")
fun AutoCompleteTextView.setSingleLineItem(itemList: List<String>?) {
    val realItemList = if (itemList == null) ArrayList() else ArrayList(itemList)
    if (adapter is ArrayAdapter<*>) {
        (adapter as ArrayAdapter<String>).apply {
            clear()
            addAll(realItemList)
        }
    } else {
        setAdapter(ArrayAdapter(context, android.R.layout.simple_dropdown_item_1line, realItemList))
    }
}

@BindingAdapter("android:textNoFiltering")
fun AutoCompleteTextView.setNoFilteringText(text: CharSequence?) {
    setText(text, false)
}