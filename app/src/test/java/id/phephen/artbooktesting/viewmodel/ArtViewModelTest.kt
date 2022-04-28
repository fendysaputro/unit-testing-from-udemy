package id.phephen.artbooktesting.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.google.common.truth.Truth.assertThat
import id.phephen.artbooktesting.MainCoroutineRule
import id.phephen.artbooktesting.getOrAwaitValueTest
import id.phephen.artbooktesting.repo.FakeArtRepository
import id.phephen.artbooktesting.util.Status
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class ArtViewModelTest {


    @get:Rule
    var instantTaskExecutorRule= InstantTaskExecutorRule()

    @get:Rule
    var mainCoroutineRule= MainCoroutineRule()

    private lateinit var artViewModel: ArtViewModel


    @Before
    fun setup () {
        artViewModel = ArtViewModel(FakeArtRepository())
    }

    @Test
    fun `insert art without year return error`() {
        artViewModel.makeArt("fendy", "saputro", "")
        val result = artViewModel.insertArtMessage.getOrAwaitValueTest()
        assertThat(result.status).isEqualTo(Status.ERROR)
    }

    @Test
    fun `insert art without name return error`() {
        artViewModel.makeArt("", "ana", "2020")
        val result = artViewModel.insertArtMessage.getOrAwaitValueTest()
        assertThat(result.status).isEqualTo(Status.ERROR)
    }

    @Test
    fun `insert art without artistName return error`() {
        artViewModel.makeArt("mely", "", "2020")
        val result = artViewModel.insertArtMessage.getOrAwaitValueTest()
        assertThat(result.status).isEqualTo(Status.ERROR)
    }

}