package id.phephen.artbooktesting.repo

import androidx.lifecycle.LiveData
import id.phephen.artbooktesting.api.RetrofitAPI
import id.phephen.artbooktesting.model.ImageResponse
import id.phephen.artbooktesting.roomdb.Art
import id.phephen.artbooktesting.roomdb.ArtsDao
import id.phephen.artbooktesting.util.Resource
import java.lang.Exception
import javax.inject.Inject

class ArtRepository @Inject constructor(
    private val artsDao: ArtsDao,
    private val retrofitApi: RetrofitAPI
) : ArtRepositoryInterface{
    override suspend fun insertArt(art: Art) {
        artsDao.insertArt(art)
    }

    override suspend fun deleteArt(art: Art) {
        artsDao.deleteArt(art)
    }

    override fun getArt(): LiveData<List<Art>> {
        return artsDao.observeArts()
    }

    override suspend fun searchImage(imageString: String): Resource<ImageResponse> {
        return try {
            val response = retrofitApi.imageSearch(imageString)
            if (response.isSuccessful) {
                response.body()?.let {
                    return@let Resource.success(it)
                } ?: Resource.error("Error", null)
            } else {
                Resource.error("Error", null)
            }
        } catch (e: Exception) {
            Resource.error("No data!", null)
        }
    }

}