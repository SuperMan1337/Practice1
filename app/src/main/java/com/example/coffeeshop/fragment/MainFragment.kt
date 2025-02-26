package com.example.coffeeshop.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.coffeeshop.R
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.coffeeshop.adapter.CategoryAdapter
import com.example.coffeeshop.adapter.OffersAdapter
import com.example.coffeeshop.adapter.PopularAdapter
import com.example.coffeeshop.databinding.FragmentHomeBinding
import com.example.coffeeshop.viewmodel.MainViewModel


class MainFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private val viewModel = MainViewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initCategory()
        initPopular()
        initOffer()
    }

    private fun initOffer() {
        binding.progressBarOffer.visibility = View.VISIBLE
        viewModel.offer.observe(viewLifecycleOwner, Observer {
            binding.recyclerViewOffer.layoutManager =
                LinearLayoutManager(requireContext(),
                    LinearLayoutManager.HORIZONTAL,
                    false
                )
            binding.recyclerViewOffer.adapter = OffersAdapter(it)
            binding.progressBarOffer.visibility = View.GONE
        })
        viewModel.loadOffer()
    }

    private fun initPopular() {
        binding.progressBarPopular.visibility = View.VISIBLE
        viewModel.popular.observe(viewLifecycleOwner, Observer {
            binding.recyclerViewPopular.layoutManager =
                LinearLayoutManager(requireContext(),
                    LinearLayoutManager.HORIZONTAL,
                    false
                )
            binding.recyclerViewPopular.adapter = PopularAdapter(it)
            binding.progressBarPopular.visibility = View.GONE
        })
        viewModel.loadPopular()
    }

    private fun initCategory() {
        binding.progressBarCategory.visibility = View.VISIBLE
        viewModel.category.observe(viewLifecycleOwner, Observer {
            binding.recyclerViewCategory.layoutManager =
                LinearLayoutManager(requireContext(),
                    LinearLayoutManager.HORIZONTAL,
                    false
                )
            binding.recyclerViewCategory.adapter = CategoryAdapter(it)
            binding.progressBarCategory.visibility = View.GONE
        })
        viewModel.loadCategory()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}

