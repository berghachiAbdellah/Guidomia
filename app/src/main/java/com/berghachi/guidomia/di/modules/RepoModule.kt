package com.berghachi.guidomia.di.modules

import com.berghachi.guidomia.data.local.dao.CarDao
import com.berghachi.guidomia.data.repositories.CarsRepositoryImpl
import com.berghachi.guidomia.domain.repositories.CarsRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module(includes = [DatabaseModule::class])
@InstallIn(SingletonComponent::class)
class RepoModule {

    @Singleton
    @Provides
    fun provideCarsRepository(
        carsDao: CarDao
    ): CarsRepository {
        return CarsRepositoryImpl(
            carsDao
        )
    }

}
