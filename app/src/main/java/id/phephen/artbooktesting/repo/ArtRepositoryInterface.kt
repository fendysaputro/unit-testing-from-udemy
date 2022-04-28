package id.phephen.artbooktesting.repo

import androidx.lifecycle.LiveData
import id.phephen.artbooktesting.model.ImageResponse
import id.phephen.artbooktesting.roomdb.Art
import id.phephen.artbooktesting.util.Resource

interface ArtRepositoryInterface {

    suspend fun insertArt(art : Art)

    suspend fun deleteArt(art: Art)

    fun getArt() : LiveData<List<Art>>

    suspend fun searchImage(imageString : String) : Resource<ImageResponse>

}