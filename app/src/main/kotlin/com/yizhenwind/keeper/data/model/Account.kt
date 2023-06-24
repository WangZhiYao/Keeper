package com.yizhenwind.keeper.data.model

import android.os.Parcelable
import androidx.recyclerview.widget.DiffUtil
import kotlinx.parcelize.Parcelize

/**
 *
 *
 * @author WangZhiYao
 * @since 2023/6/21
 */
@Parcelize
data class Account(
    val id: Long = 0,
    val username: String = "",
    val password: String = "",
    val createTime: Long = System.currentTimeMillis()
) : Parcelable {

    companion object {

        val DIFF_CALLBACK = object : DiffUtil.ItemCallback<Account>() {

            override fun areItemsTheSame(oldItem: Account, newItem: Account): Boolean =
                oldItem.id == newItem.id

            override fun areContentsTheSame(oldItem: Account, newItem: Account): Boolean =
                oldItem == newItem

        }
    }
}