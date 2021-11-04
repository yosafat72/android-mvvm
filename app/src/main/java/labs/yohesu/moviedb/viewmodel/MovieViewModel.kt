package labs.yohesu.moviedb.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import labs.yohesu.moviedb.model.MovieModel
import labs.yohesu.moviedb.repository.MovieRepository
import labs.yohesu.mvvm2.networks.ResponseHelper
import javax.inject.Inject

@HiltViewModel
class MovieViewModel @Inject constructor(private val repository: MovieRepository) : ViewModel(){

    fun getPopularMovie() : LiveData<ResponseHelper<MovieModel>> {
        return repository.getPopularMovie()
    }

    fun getTopRate() : LiveData<ResponseHelper<MovieModel>>{
        return repository.getTopRate()
    }

    fun getUpcomming() : LiveData<ResponseHelper<MovieModel>>{
        return repository.getUpcomming()
    }
}