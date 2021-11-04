package labs.yohesu.moviedb.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import labs.yohesu.moviedb.helper.Constanta
import labs.yohesu.moviedb.model.MovieModel
import labs.yohesu.mvvm2.networks.ResponseHelper
import labs.yohesu.newapps.network.ApiService
import javax.inject.Inject

class MovieRepository @Inject constructor(private val service: ApiService) {

    fun getPopularMovie() : LiveData<ResponseHelper<MovieModel>> {
        return liveData {
            emit(ResponseHelper.Loading)
            try {
                val response = service.getPopularMovie(Constanta.API_KEY,"en-US", "1")
                if(response.isSuccessful){
                    response.body()?.let {
                        emit(ResponseHelper.Success(it))
                    }
                }
            }catch (ex : Exception){
                emit(ResponseHelper.Error(ex.message))
            }
        }
    }

    fun getTopRate() : LiveData<ResponseHelper<MovieModel>>{
        return liveData {
            emit(ResponseHelper.Loading)
            try {
               val response = service.getTopRate(Constanta.API_KEY, "en-US", "1")
               if(response.isSuccessful){
                   response.body()?.let {
                        emit(ResponseHelper.Success(it))
                   }
               }
            }catch (ex : Exception){
                emit(ResponseHelper.Error(ex.message))
            }
        }
    }

    fun getUpcomming() : LiveData<ResponseHelper<MovieModel>>{
        return liveData {
            emit(ResponseHelper.Loading)
            try {
                val response = service.getUpcomming(Constanta.API_KEY, "en-US", "1")
                if(response.isSuccessful){
                    response.body()?.let {
                        emit(ResponseHelper.Success(it))
                    }
                }
            }catch (ex : Exception){
                emit(ResponseHelper.Error(ex.message))
            }
        }
    }

}