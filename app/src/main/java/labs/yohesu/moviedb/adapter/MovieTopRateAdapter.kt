package labs.yohesu.moviedb.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import labs.yohesu.moviedb.databinding.ItemTopRateBinding
import labs.yohesu.moviedb.helper.Constanta
import labs.yohesu.moviedb.helper.ItemClickListener
import labs.yohesu.moviedb.model.MovieModel
import labs.yohesu.moviedb.model.ResultsItem
import labs.yohesu.mvvm2.utils.UIHelpers

class MovieTopRateAdapter : RecyclerView.Adapter<MovieTopRateAdapter.TopRateViewHolder>() {

    private var listData: MovieModel = MovieModel()
    private lateinit var listener: ItemClickListener

    class TopRateViewHolder(private val binding: ItemTopRateBinding, private val listener: ItemClickListener) : RecyclerView.ViewHolder(binding.root){

        fun bindData(data: ResultsItem){
            val urlToImage = Constanta.PATH_IMAGE + data.posterPath
            urlToImage.let { UIHelpers.loadImage(binding.imgPoster, it) }
            binding.txtTitle.text       = data.title
            binding.txtSubtitle.text    = data.overview
            binding.cardMovie.setOnClickListener {
                listener.onMovieClick(it, data)
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TopRateViewHolder {
        val binding : ItemTopRateBinding = ItemTopRateBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TopRateViewHolder(binding, listener)
    }

    override fun onBindViewHolder(holder: TopRateViewHolder, position: Int) {
        listData.results?.get(position)?.let {
            holder.bindData(it)
        }
    }

    override fun getItemCount(): Int {
        return if (listData.results == null) 0 else listData.results!!.size

    }

    fun setData(data: MovieModel){
        this.listData = data
    }

    fun setListener(listener: ItemClickListener){
        this.listener = listener
    }
}
