package com.yizhenwind.keeper.common

import com.yizhenwind.keeper.data.model.Internal
import com.yizhenwind.keeper.data.model.Sect
import com.yizhenwind.keeper.data.model.SectInternal
import com.yizhenwind.keeper.data.model.Server
import com.yizhenwind.keeper.data.model.Zone
import com.yizhenwind.keeper.data.model.ZoneServer

/**
 *
 *
 * @author WangZhiYao
 * @since 2023/6/22
 */
object Constant {

    val ZONE_SERVER = listOf(
        ZoneServer(
            zone = Zone(name = "电信一区"),
            serverList = listOf(
                Server(name = "蝶恋花"),
                Server(name = "龙争虎斗"),
                Server(name = "长安城")
            )
        ),
        ZoneServer(
            zone = Zone(name = "电信五区"),
            serverList = listOf(
                Server(name = "幽月轮"),
                Server(name = "斗转星移"),
                Server(name = "剑胆琴心"),
                Server(name = "乾坤一掷"),
                Server(name = "唯我独尊"),
                Server(name = "梦江南"),
                Server(name = "大唐万象"),
                Server(name = "横刀断浪")
            )
        ),
        ZoneServer(
            zone = Zone(name = "电信八区"),
            serverList = listOf(
                Server(name = "绝代天骄")
            )
        ),
        ZoneServer(
            zone = Zone(name = "双线一区"),
            serverList = listOf(
                Server(name = "天鹅坪"),
                Server(name = "破阵子")
            )
        ),
        ZoneServer(
            zone = Zone(name = "双线二区"),
            serverList = listOf(
                Server(name = "飞龙在天")
            )
        ),
        ZoneServer(
            zone = Zone(name = "双线四区"),
            serverList = listOf(
                Server(name = "青梅煮酒")
            )
        ),
        ZoneServer(
            zone = Zone(name = "缘起大区"),
            serverList = listOf(
                Server(name = "缘起稻香"),
                Server(name = "天宝盛世"),
                Server(name = "剑啸江湖")
            )
        )
    )

    val SECT_INTERNAL = listOf(
        SectInternal(
            sect = Sect(name = "万花"),
            internalList = listOf(
                Internal(name = "花间游"),
                Internal(name = "离经易道")
            )
        ),
        SectInternal(
            sect = Sect(name = "纯阳"),
            internalList = listOf(
                Internal(name = "太虚剑意"),
                Internal(name = "紫霞功")
            )
        ),
        SectInternal(
            sect = Sect(name = "七秀"),
            internalList = listOf(
                Internal(name = "冰心诀"),
                Internal(name = "云裳心经")
            )
        ),
        SectInternal(
            sect = Sect(name = "少林"),
            internalList = listOf(
                Internal(name = "易筋经"),
                Internal(name = "洗髓经")
            )
        ),
        SectInternal(
            sect = Sect(name = "天策"),
            internalList = listOf(
                Internal(name = "傲血战意"),
                Internal(name = "铁牢律")
            )
        ),
        SectInternal(
            sect = Sect(name = "藏剑"),
            internalList = listOf(
                Internal(name = "问水诀"),
                Internal(name = "山居剑意")
            )
        ),
        SectInternal(
            sect = Sect(name = "五毒"),
            internalList = listOf(
                Internal(name = "千蛛万毒手"),
                Internal(name = "补天心经")
            )
        ),
        SectInternal(
            sect = Sect(name = "唐门"),
            internalList = listOf(
                Internal(name = "惊羽诀"),
                Internal(name = "天罗诡道")
            )
        ),
        SectInternal(
            sect = Sect(name = "明教"),
            internalList = listOf(
                Internal(name = "焚影圣诀"),
                Internal(name = "明尊琉璃体")
            )
        ),
        SectInternal(
            sect = Sect(name = "丐帮"),
            internalList = listOf(
                Internal(name = "笑尘诀")
            )
        ),
        SectInternal(
            sect = Sect(name = "苍云"),
            internalList = listOf(
                Internal(name = "分山劲"),
                Internal(name = "铁骨衣")
            )
        ),
        SectInternal(
            sect = Sect(name = "霸刀"),
            internalList = listOf(
                Internal(name = "北傲诀")
            )
        ),
        SectInternal(
            sect = Sect(name = "长歌"),
            internalList = listOf(
                Internal(name = "莫问"),
                Internal(name = "相知")
            )
        ),
        SectInternal(
            sect = Sect(name = "蓬莱"),
            internalList = listOf(
                Internal(name = "凌海诀")
            )
        ),
        SectInternal(
            sect = Sect(name = "凌雪阁"),
            internalList = listOf(
                Internal(name = "隐龙诀")
            )
        ),
        SectInternal(
            sect = Sect(name = "衍天宗"),
            internalList = listOf(
                Internal(name = "太玄经")
            )
        ),
        SectInternal(
            sect = Sect(name = "北天药宗"),
            internalList = listOf(
                Internal(name = "无方"),
                Internal(name = "灵素")
            )
        ),
        SectInternal(
            sect = Sect(name = "刀宗"),
            internalList = listOf(
                Internal(name = "孤锋诀")
            )
        )
    )
}