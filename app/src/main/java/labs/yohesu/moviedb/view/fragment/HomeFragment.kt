package labs.yohesu.moviedb.view.fragment

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.synnapps.carouselview.ImageListener
import dagger.hilt.android.AndroidEntryPoint
import labs.yohesu.moviedb.adapter.MovieTopRateAdapter
import labs.yohesu.moviedb.adapter.MovieUpcommingAdapter
import labs.yohesu.moviedb.databinding.FragmentHomeBinding
import labs.yohesu.moviedb.helper.Constanta
import labs.yohesu.moviedb.model.ResultsItem
import labs.yohesu.moviedb.viewmodel.MovieViewModel
import labs.yohesu.mvvm2.networks.ResponseHelper

@AndroidEntryPoint
class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding
    private lateinit var viewModel: MovieViewModel

    private lateinit var topRateAdapter: MovieTopRateAdapter
    private lateinit var upcommingAdapter: MovieUpcommingAdapter
    private lateinit var imageArr: List<ResultsItem?>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        viewModel = ViewModelProvider(this)[MovieViewModel::class.java]

        setupRV()

        fetchPopularMovie()
        fetchTopRate()
        fetchUpComming()

        return binding.root
    }

    private fun setupRV(){
        topRateAdapter = MovieTopRateAdapter()
        binding.rvTopRate.layoutManager = LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false)
        binding.rvTopRate.adapter = topRateAdapter

        upcommingAdapter = MovieUpcommingAdapter()
        binding.rvUpcomming.layoutManager = LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false)
        binding.rvUpcomming.adapter = upcommingAdapter
    }

    var imageListener: ImageListener = object : ImageListener{
        override fun setImageForPosition(position: Int, imageView: ImageView?) {
            view?.let {
                Glide.with(it.context)
                    .load(Constanta.PATH_IMAGE + imageArr[position]?.posterPath)
                    .fitCenter()
                    .into(imageView!!)
            }
        }
    }

    private fun fetchPopularMovie(){

        viewModel.getPopularMovie().observe(viewLifecycleOwner, {
            when(it){
                is ResponseHelper.Loading -> {

                }
                is ResponseHelper.Success -> {
                    imageArr = it.body.results!!

                    if (imageArr.size > 5){
                        binding.carouselView.setImageListener(imageListener)
                        binding.carouselView.pageCount = 5
                    }else{
                        binding.carouselView.setImageListener(imageListener)
                        binding.carouselView.pageCount = it.body.results.size
                    }


                }
                is ResponseHelper.Error -> {
                    Toast.makeText(activity, it.message.toString(), Toast.LENGTH_LONG).show()
                }
            }
        })

    }

    @SuppressLint("NotifyDataSetChanged")
    private fun fetchTopRate(){
        viewModel.getTopRate().observe(viewLifecycleOwner, {
            when(it){
                is ResponseHelper.Loading -> {

                }
                is ResponseHelper.Success -> {
                    topRateAdapter.setData(it.body)
                    topRateAdapter.notifyDataSetChanged()
                }
                is ResponseHelper.Error -> {
                    Toast.makeText(activity, it.message.toString(), Toast.LENGTH_LONG).show()
                }
            }
        })
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun fetchUpComming(){
        viewModel.getUpcomming().observe(viewLifecycleOwner, {
            when(it){
                is ResponseHelper.Loading -> {

                }
                is ResponseHelper.Success -> {
                    upcommingAdapter.setData(it.body)
                    upcommingAdapter.notifyDataSetChanged()
                }
                is ResponseHelper.Error -> {
                    Toast.makeText(activity, it.message.toString(), Toast.LENGTH_LONG).show()
                }
            }
        })
    }

}