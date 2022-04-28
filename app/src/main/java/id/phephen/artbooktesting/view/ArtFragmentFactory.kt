package id.phephen.artbooktesting.view

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentFactory
import com.bumptech.glide.RequestManager
import id.phephen.artbooktesting.adapter.ArtRecyclerAdapter
import id.phephen.artbooktesting.adapter.ImageRecyclerAdapter
import javax.inject.Inject

class ArtFragmentFactory @Inject constructor(
    private val imageRvAdapter : ImageRecyclerAdapter,
    private val glide : RequestManager,
    private val rvArtAdapter: ArtRecyclerAdapter
) : FragmentFactory(){

    override fun instantiate(classLoader: ClassLoader, className: String): Fragment {
        return when(className) {
            ImageApiFragment::class.java.name -> ImageApiFragment(imageRvAdapter)
            ArtDetailsFragment::class.java.name -> ArtDetailsFragment(glide)
            ArtFragment::class.java.name -> ArtFragment(rvArtAdapter)
            else -> super.instantiate(classLoader, className)
        }
    }

}