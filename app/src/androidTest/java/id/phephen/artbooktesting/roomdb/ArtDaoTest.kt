package id.phephen.artbooktesting.roomdb

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.filters.SmallTest
import com.google.common.truth.Truth.assertThat
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import id.phephen.artbooktesting.HiltTestRunner
import id.phephen.artbooktesting.getOrAwaitValue
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import javax.inject.Inject
import javax.inject.Named

/**
 * Created by Phephen on 02/08/2021.
 */

@SmallTest
@ExperimentalCoroutinesApi
@HiltAndroidTest
class ArtDaoTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    var hiltTest = HiltAndroidRule(this)

    @Inject
    @Named("testDatabase")
    lateinit var database: ArtsDatabase

    private lateinit var dao: ArtsDao

    @Before
    fun setup() {
//        database = Room.inMemoryDatabaseBuilder(
//            ApplicationProvider.getApplicationContext(),
//            ArtsDatabase::class.java).allowMainThreadQueries().build()

        hiltTest.inject()

        dao = database.artDao()
    }

    @After
    fun teardown() {
        database.close()
    }

    @Test
    fun insertArtTesting() = runBlocking{
        val insertArtData = Art("fendy", "saputro", 1900, "test.com", 1)
        dao.insertArt(insertArtData)

        val list = dao.observeArts().getOrAwaitValue()
        assertThat(list).contains(insertArtData)
    }

    @Test
    fun deleteArtTesting() = runBlocking{
        val deleteArtData = Art("fendy", "saputro", 1900, "test.com", 1)
        dao.insertArt(deleteArtData)
        dao.deleteArt(deleteArtData)

        val list = dao.observeArts().getOrAwaitValue()
        assertThat(list).doesNotContain(deleteArtData)
    }

}