package id.phephen.artbooktesting.view

import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.test.espresso.Espresso
import androidx.test.espresso.ViewAction
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.filters.MediumTest
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import id.phephen.artbooktesting.R
import id.phephen.artbooktesting.launchFragmentInHiltContainer
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito
import javax.inject.Inject

/**
 * Created by Phephen on 02/08/2021.
 */

@MediumTest
@HiltAndroidTest

class ArtFragmentTest {

    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    @Inject
    lateinit var fragmentFactory: ArtFragmentFactory

    @Before
    fun setup() {
        hiltRule.inject()
    }

    @Test
    fun testNavigationFromArtToArtDetails () {

        val navController = Mockito.mock(NavController::class.java)

        launchFragmentInHiltContainer<ArtFragment>(
            factory = fragmentFactory
        ) {
            Navigation.setViewNavController(requireView(), navController)
        }

        Espresso.onView(ViewMatchers.withId(R.id.fab)).perform(ViewActions.click())

        Mockito.verify(navController).navigate(
            ArtFragmentDirections.actionArtFragmentToArtDetailsFragment()
        )
    }

}