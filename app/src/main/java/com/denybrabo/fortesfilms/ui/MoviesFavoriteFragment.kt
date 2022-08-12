package com.denybrabo.fortesfilms.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get
import com.denybrabo.fortesfilms.R
import com.denybrabo.fortesfilms.databinding.FragmentMoviesFavoriteBinding
import com.denybrabo.fortesfilms.viewmodels.MovieDetailsViewModel

class MoviesFavoriteFragment : Fragment() {
    private var _binding: FragmentMoviesFavoriteBinding? = null
    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
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
        _binding = FragmentMoviesFavoriteBinding.inflate(inflater, container, false)
        val view = binding.root

        viewModel.readAllData.observe(viewLifecycleOwner){
            binding.textView6.text = it.elementAt(1).title
        }

        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}