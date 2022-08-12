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
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.denybrabo.fortesfilms.R
import com.denybrabo.fortesfilms.databinding.FragmentMovieDetailsBinding
import com.denybrabo.fortesfilms.models.MoviesFavoriteModel
import com.denybrabo.fortesfilms.viewmodels.MovieDetailsViewModel

class MovieDetailsFragment : Fragment() {
    private var _binding: FragmentMovieDetailsBinding? = null
    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    private val args: MovieDetailsFragmentArgs by navArgs()
    private lateinit var viewModel: MovieDetailsViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this).get()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMovieDetailsBinding.inflate(inflater, container, false)
        val view = binding.root

        setView()
        setListerner()

        return view
    }

    fun setView(){
        binding.textTitleDetails.text = args.movieDetails.title
        Glide.with(requireContext()).load(args.movieDetails.poster).into(binding.imageDetails)
        binding.textRunTimeDetails.text = args.movieDetails.runtime
        binding.movieDescriptionDetails.text = args.movieDetails.plot
        binding.textActoresDetails.text = args.movieDetails.actors
    }

    fun setListerner(){
        var p: MoviesFavoriteModel = MoviesFavoriteModel(
            id          = args.movieDetails.id,
            title       = args.movieDetails.title,
            year        = args.movieDetails.year,
            rated       = args.movieDetails.rated,
            released    = args.movieDetails.released,
            runtime     = args.movieDetails.runtime,
            genre       = args.movieDetails.genre,
            director    = args.movieDetails.director,
            writer      = args.movieDetails.writer,
            actors      = args.movieDetails.actors,
            plot        = args.movieDetails.plot,
            poster      = args.movieDetails.poster
        )

        binding.backDetails.setOnClickListener {
            findNavController().navigate(R.id.action_movieDetailsFragment_to_homeFragment)
        }
        if (!args.favorite){
            binding.buttonAddFavorite.setOnClickListener {
                if (p != null) {
                    viewModel.addFavoriteMovie(p)
                    Toast.makeText(requireContext(), "Sucesso ao salvar", Toast.LENGTH_LONG).show()
                }
            }
        } else{
            binding.buttonAddFavorite.text = "Remover dos favoritos"
            binding.buttonAddFavorite.setOnClickListener {
                viewModel.deleteFavoriteMovie(p)
                Toast.makeText(requireContext(), "Removido dos favoritos\ncom sucesso", Toast.LENGTH_LONG).show()
                findNavController().navigate(R.id.action_movieDetailsFragment_to_homeFragment)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}