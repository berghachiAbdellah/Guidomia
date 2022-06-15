package com.berghachi.guidomia.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.berghachi.guidomia.data.local.converters.CarTypeConverter
import com.berghachi.guidomia.data.local.dao.CarDao
import com.berghachi.guidomia.data.local.model.CarEntity

@TypeConverters(CarTypeConverter::class)
@Database(entities = [CarEntity::class], version = 1, exportSchema = false)
abstract class LocalDataSource : RoomDatabase() {

    abstract fun getCarDao(): CarDao

}
