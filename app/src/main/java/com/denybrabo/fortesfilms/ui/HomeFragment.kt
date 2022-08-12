package com.denybrabo.fortesfilms.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.GridLayoutManager
import com.denybrabo.fortesfilms.R
import com.denybrabo.fortesfilms.adapter.MoviesAdapter
import com.denybrabo.fortesfilms.databinding.FragmentHomeBinding
import com.denybrabo.fortesfilms.models.MoviesSagaModel
import com.denybrabo.fortesfilms.viewmodels.HomeViewModel
import com.denybrabo.fortesfilms.viewmodels.MovieDetailsViewModel

class HomeFragment : Fragment() {
    private var _binding: FragmentHomeBinding? = null
    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    private lateinit var viewModel: HomeViewModel
    private lateinit var offViewModel: MovieDetailsViewModel
    private var listMovies: MutableList<MoviesSagaModel> = arrayListOf()
    private var auxListMovies: MutableList<MoviesSagaModel> = arrayListOf()
    private var visibility: Boolean = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this).get()
        offViewModel = ViewModelProvider(this).get()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val view = binding.root

        setRecycler()
        setListener()

        return view
    }


    fun setRecycler(){
        viewModel.getMovies()?.observe(viewLifecycleOwner){
            if (it.isNotEmpty()){
                listMovies = it
                auxListMovies = it
                configRecycler()
                offViewModel.deleteAllMovies()
                it.forEach {
                    offViewModel.addMovie(it)
                }
            } else{
                offViewModel.readAllData.observe(viewLifecycleOwner){
                    listMovies = it
                    auxListMovies = it
                    configRecycler()
                    if (listMovies.isEmpty()){
                        binding.textViewEmptyOffMode.visibility = View.VISIBLE
                    } else{
                        binding.textViewEmptyOffMode.visibility = View.INVISIBLE
                    }
                }
            }
        }
    }

    fun configRecycler(){
        binding.recyclerMovies.adapter = MoviesAdapter(listMovies)
        binding.recyclerMovies.layoutManager = GridLayoutManager(requireContext(), 2)
    }

    fun setView(){
        if (!visibility){
            binding.constraintLayoutFilters.visibility = View.VISIBLE
            binding.textViewFilter.text = "Fechar opções"
            binding.imageView.setImageResource(R.drawable.ic_baseline_keyboard_arrow_up_24)
        } else{
            binding.constraintLayoutFilters.visibility = View.GONE
            binding.textViewFilter.text = "Abrir opções"
            binding.imageView.setImageResource(R.drawable.ic_baseline_keyboard_arrow_down_24)
        }
    }

    fun setListener(){
        binding.textViewFilter.setOnClickListener {
            visibility = !visibility
            setView()
        }

        binding.filter.setOnClickListener {
            listMovies = auxListMovies.filter { it.runtime.substring(0,3).toInt() <= 120 }.toMutableList()
            configRecycler()
        }

        binding.filter2.setOnClickListener {
            listMovies = auxListMovies.filter { it.runtime.substring(0,3).toInt() >= 120 }.toMutableList()
            configRecycler()
        }

        binding.filterAll.setOnClickListener {
            setRecycler()
        }

        binding.buttonToFavorite.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_moviesFavoriteFragment)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}