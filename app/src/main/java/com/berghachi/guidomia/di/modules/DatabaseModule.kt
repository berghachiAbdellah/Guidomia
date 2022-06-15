package com.berghachi.guidomia.di.modules

import android.content.Context
import androidx.room.Room
import com.berghachi.guidomia.data.local.DbConstant
import com.berghachi.guidomia.data.local.LocalDataSource
import com.berghachi.guidomia.data.local.dao.CarDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DatabaseModule {

    @Singleton
    @Provides
    fun provideDatabase(@ApplicationContext appContext: Context): LocalDataSource {
        return Room.databaseBuilder(
            appContext,
            LocalDataSource::class.java, DbConstant.DB_NAME
        ).build()
    }

    @Singleton
    @Provides
    fun provideCarDao(appDatabase: LocalDataSource): CarDao {
        return appDatabase.getCarDao()
    }

}
