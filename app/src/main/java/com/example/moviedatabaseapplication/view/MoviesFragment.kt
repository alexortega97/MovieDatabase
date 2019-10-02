package com.example.moviedatabaseapplication.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.moviedatabaseapplication.R
import com.example.moviedatabaseapplication.model.Result
import com.example.moviedatabaseapplication.presenter.MoviePresenter
import com.example.moviedatabaseapplication.presenter.MoviePresenterImpl
import kotlinx.android.synthetic.main.fragment_movies.view.*

class MoviesFragment : Fragment(), MovieView {

    private var moviePresenter: MoviePresenter ?= null
    private lateinit var root: View

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        root = inflater.inflate(R.layout.fragment_movies, container, false)

        moviePresenter = MoviePresenterImpl(this)

        getMovies()

        return root
    }

    private fun getMovies(){
        moviePresenter?.loadListMovies()
    }

    override fun showErrorLoadMovies(message: String?) {
        Toast.makeText(activity?.applicationContext, message, Toast.LENGTH_LONG).show()
    }

    override fun sendListMovies(results: List<Result>?) {

        root.recyclerView.layoutManager = LinearLayoutManager(
            activity?.applicationContext,
            RecyclerView.VERTICAL,
            false
        )

        val moviesAdapter = MoviesAdapter(results as ArrayList<Result>)
        root.recyclerView.adapter = moviesAdapter

    }
}