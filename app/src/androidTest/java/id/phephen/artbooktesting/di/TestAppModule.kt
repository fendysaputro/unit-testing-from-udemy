package id.phephen.artbooktesting.di

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import id.phephen.artbooktesting.roomdb.ArtsDatabase
import javax.inject.Named

/**
 * Created by Phephen on 02/08/2021.
 */

@Module
@InstallIn(ApplicationComponent::class)
object TestAppModule {

    @Provides
    @Named("testDatabase")
    fun injectInMemoryRoom(@ApplicationContext context: Context) =

        Room.inMemoryDatabaseBuilder(context, ArtsDatabase::class.java)
            .allowMainThreadQueries()
            .build()


}