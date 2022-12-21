package com.example.lt1.ui.fragments.recipes

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.lt1.viewmodels.MainViewModel
import com.example.lt1.R
import com.example.lt1.adapters.RecipesAdapter
import com.example.lt1.databinding.FragmentRecipesBinding
import com.example.lt1.util.NetworkResult
import com.example.lt1.util.observeOnce
import com.example.lt1.viewmodels.RecipesViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class RecipesFragment : Fragment() {

    private val args by navArgs<RecipesFragmentArgs>()

    private var _binding: FragmentRecipesBinding? = null
    private val binding get() = _binding!!

    private lateinit var mainViewModel: MainViewModel
    private val mAdapter by lazy { RecipesAdapter() }
    private lateinit var mView:View
    private lateinit var recipesViewModel: RecipesViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainViewModel = ViewModelProvider(requireActivity())[MainViewModel::class.java]
        recipesViewModel = ViewModelProvider(requireActivity())[RecipesViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentRecipesBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = this
        binding.mainViewModel = mainViewModel
        setupRecyclerView()
        readDatabase()
        binding.recipesFab.setOnClickListener {
            findNavController().navigate(R.id.action_recipesFragment_to_recipesBottomSheet)
        }
        return binding.root
    }
    private fun requestApiData() {
        mainViewModel.getRecipes(recipesViewModel.applyQueries())
        mainViewModel.recipesResponse.observe(viewLifecycleOwner,{ response ->
            when(response){
                is NetworkResult.Success -> {

                    response.data?.let { mAdapter.setData(it) }
                }
                is NetworkResult.Error -> {
                    Toast.makeText(
                        requireContext(),
                        response.message.toString(),
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        })
    }
    private fun readDatabase() {
        lifecycleScope.launch {
            mainViewModel.readRecipes.observeOnce(viewLifecycleOwner, { database ->
                if (database.isNotEmpty() && !args.backFromBottomSheet) {
                    Log.d("RecipesFragment", "readDatabase called!")
                    mAdapter.setData(database[0].foodRecipe)

                } else {
                    requestApiData()
                }
            })
        }
    }
    private fun setupRecyclerView() {
        binding.recyclerview.adapter = mAdapter
        binding.recyclerview.layoutManager = LinearLayoutManager(requireContext())

    }
    private fun loadDataFromCache() {
        lifecycleScope.launch {
            mainViewModel.readRecipes.observe(viewLifecycleOwner, {database->
                if (database.isNotEmpty()) {
                    mAdapter.setData(database[0].foodRecipe)
                }
            })
        }
    }
    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}