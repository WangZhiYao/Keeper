package com.yizhenwind.keeper.data.model

import androidx.recyclerview.widget.DiffUtil

/**
 *
 *
 * @author WangZhiYao
 * @since 2023/6/21
 */
data class Character(
    val id: Long = 0,
    val account: Account = Account(),
    val zone: Zone = Zone(),
    val server: Server = Server(),
    val name: String = "",
    val sect: Sect = Sect(),
    val internal: Internal = Internal(),
    val securityLock: String = "",
    val remark: String = "",
    val createTime: Long = System.currentTimeMillis()
) {

    companion object {

        val DIFF_CALLBACK = object : DiffUtil.ItemCallback<Character>() {

            override fun areItemsTheSame(oldItem: Character, newItem: Character): Boolean =
                oldItem.id == newItem.id

            override fun areContentsTheSame(oldItem: Character, newItem: Character): Boolean =
                oldItem == newItem

        }
    }
}