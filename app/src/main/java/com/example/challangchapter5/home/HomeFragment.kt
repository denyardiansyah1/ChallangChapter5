package com.example.challangchapter5.home

import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager

import com.example.challangchapter5.R
import com.example.challangchapter5.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {
    //lateinit var listAdapter: ListAdapter
    lateinit var gridAdapter: HomeGridAdapter
    lateinit var categoryAdapter: HomeCategoryAdapter

    private var _binding: FragmentHomeBinding? = null
    private lateinit var sharedPref: SharedPreferences
    lateinit var bundle: Bundle
    private val binding get() = _binding!!
    lateinit var  viewModel: HomeViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentHomeBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        listGrid()
        /*binding.switch1.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                listLinear()
            } else {
                listGrid()
            }
        }*/
        /*binding.ivPp1.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_profileFragment)
        }*/
        categoryMenu()
    }

    private fun listGrid() {

        viewModel = ViewModelProvider(this).get(HomeViewModel::class.java)
        viewModel.getMenu()
        binding.rvMenu.layoutManager = GridLayoutManager(context, 2)
        gridAdapter = HomeGridAdapter(emptyList()){
            bundle = Bundle().apply {
                putString("namaMenu", it.nama)
                putString("hargaMenu", it.harga.toString())
                putString("gambar", it.imageUrl)
                putString("loc", it.alamatResto)
                putString("description", it.detail)
            }
            //findNavController().navigate(R.id.action_homeFragment_to_detailFragment, bundle)
        }
        binding.rvMenu.adapter = gridAdapter
        viewModel.liveDataMenuMenu.observe(viewLifecycleOwner,{
            gridAdapter.listMenu = it
            gridAdapter.notifyDataSetChanged()
        })
    }

    /*private fun listLinear() {
        viewModel = ViewModelProvider(this).get(HomeViewModel::class.java)
        viewModel.getMenu()
        binding.rvMenu.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        listAdapter = ListAdapter(emptyList()){
            bundle = Bundle().apply {
                putString("namaMenu", it.nama)
                putString("hargaMenu", it.harga.toString())
                putString("gambar", it.imageUrl)
                putString("loc", it.alamatResto)
                putString("description", it.detail)
            }
            findNavController().navigate(R.id.action_homeFragment_to_detailFragment, bundle)

        }
        binding.rvMenu.adapter = listAdapter
        viewModel.liveDataMenuMenu.observe(viewLifecycleOwner,{ menu ->
            listAdapter.listMenu = menu
            listAdapter.notifyDataSetChanged()
        })


    }*/
    private fun categoryMenu(){
        viewModel = ViewModelProvider(this).get(HomeViewModel::class.java)
        viewModel.getCategories()
        binding.rvCategory.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        categoryAdapter = HomeCategoryAdapter(emptyList()){
            bundle = Bundle().apply {
                putString("namaMenu", it.nama)
                putString("gambar", it.imageUrl)

            }


        }
        binding.rvCategory.adapter = categoryAdapter
        viewModel.liveDataCategory.observe(viewLifecycleOwner,{ menu ->
            categoryAdapter.listMenu = menu
            categoryAdapter.notifyDataSetChanged()
        })

    }


    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}