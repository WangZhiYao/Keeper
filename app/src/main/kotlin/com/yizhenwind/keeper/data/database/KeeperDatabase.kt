package com.yizhenwind.keeper.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.yizhenwind.keeper.data.database.dao.AccountDao
import com.yizhenwind.keeper.data.database.dao.CharacterDao
import com.yizhenwind.keeper.data.database.entity.AccountEntity
import com.yizhenwind.keeper.data.database.entity.CharacterEntity

/**
 *
 *
 * @author WangZhiYao
 * @since 2023/6/21
 */
@Database(
    entities = [
        AccountEntity::class,
        CharacterEntity::class
    ],
    version = 1,
    exportSchema = false
)
abstract class KeeperDatabase : RoomDatabase() {

    abstract fun accountDao(): AccountDao

    abstract fun characterDao(): CharacterDao

}