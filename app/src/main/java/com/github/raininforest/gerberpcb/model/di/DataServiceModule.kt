package com.github.raininforest.gerberpcb.model.di

import com.github.raininforest.gerberpcb.model.DataService
import com.github.raininforest.gerberpcb.model.IDataService
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

/**
 * Created by Sergey Velesko on 18.07.2021
 */
@InstallIn(SingletonComponent::class)
@Module
interface DataServiceModule {

    @Binds
    fun bindDataService(impl: DataService): IDataService
}