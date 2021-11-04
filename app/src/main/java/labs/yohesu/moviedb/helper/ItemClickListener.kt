package labs.yohesu.moviedb.helper

import android.view.View
import labs.yohesu.moviedb.model.ResultsItem

interface ItemClickListener {
    fun onMovieClick(view: View?, model: ResultsItem)
}