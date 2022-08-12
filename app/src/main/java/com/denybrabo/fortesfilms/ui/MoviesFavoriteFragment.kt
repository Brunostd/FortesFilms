package com.denybrabo.fortesfilms.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.denybrabo.fortesfilms.R
import com.denybrabo.fortesfilms.adapter.FavoriteAdapter
import com.denybrabo.fortesfilms.adapter.MoviesAdapter
import com.denybrabo.fortesfilms.databinding.FragmentMoviesFavoriteBinding
import com.denybrabo.fortesfilms.models.MoviesSagaModel
import com.denybrabo.fortesfilms.viewmodels.MovieDetailsViewModel

class MoviesFavoriteFragment : Fragment() {
    private var _binding: FragmentMoviesFavoriteBinding? = null
    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    private lateinit var viewModel: MovieDetailsViewModel
    private var listFavorite: MutableList<MoviesSagaModel> = arrayListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this).get()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMoviesFavoriteBinding.inflate(inflater, container, false)
        val view = binding.root

        setView()
        setListener()

        return view
    }

    fun setView(){
        viewModel.readAllFavoriteData.observe(viewLifecycleOwner){
            var p: MoviesSagaModel
            it.forEach { data ->
                p = MoviesSagaModel(
                    id          = data.id,
                    title       = data.title,
                    year        = data.year,
                    rated       = data.rated,
                    released    = data.released,
                    runtime     = data.runtime,
                    genre       = data.genre,
                    director    = data.director,
                    writer      = data.writer,
                    actors      = data.actors,
                    plot        = data.plot,
                    poster      = data.poster
                )
                listFavorite.add(p)
            }
            binding.recyclerFavorite.adapter = FavoriteAdapter(listFavorite)
            binding.recyclerFavorite.layoutManager = GridLayoutManager(requireContext(), 2)

            if (listFavorite.isEmpty()){
                binding.textViewEmptyFavorite.visibility = View.VISIBLE
            }

        }
    }

    fun setListener(){

        binding.backFavorite.setOnClickListener {
            findNavController().navigate(R.id.action_moviesFavoriteFragment_to_homeFragment)
        }

        binding.buttonCleanFavoriteList.setOnClickListener {
            viewModel.deleteAllFavoriteMovies()
            Toast.makeText(requireContext(), "Lista limpa com sucesso", Toast.LENGTH_LONG).show()
            findNavController().navigate(R.id.action_moviesFavoriteFragment_to_homeFragment)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}