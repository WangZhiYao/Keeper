package com.yizhenwind.keeper.data.model

/**
 *
 *
 * @author WangZhiYao
 * @since 2023/6/22
 */
data class SectInternal(
    val sect: Sect = Sect(),
    val internalList: List<Internal> = emptyList()
)