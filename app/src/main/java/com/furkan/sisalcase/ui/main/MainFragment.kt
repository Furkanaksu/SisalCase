package com.furkan.sisalcase.ui.main

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.furkan.sisalcase.R
import com.furkan.sisalcase.data.model.ChildrenDetailModel
import com.furkan.sisalcase.data.model.ChildrenModel
import com.furkan.sisalcase.data.model.ListModel
import com.furkan.sisalcase.databinding.MainFragmentBinding
import com.furkan.sisalcase.ui.base.BaseFragment
import com.furkan.sisalcase.ui.main.adapter.ImageAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainFragment : BaseFragment<MainFragmentBinding>() {

    private val viewModel: MainViewModel by viewModels()
    private var data: ListModel? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.rycView.layoutManager = GridLayoutManager(
            requireContext(),
            2,
            RecyclerView.VERTICAL,
            false
        )

        binding.searchBar.clearText()

        observeData()
        search()
    }


    private fun search(){
        binding.searchBar.binding.editTextSearch.addTextChangedListener{text ->
            binding.progress.visibility = View.VISIBLE
            if (text?.isBlank() == true)
            {
                    binding.progress.visibility = View.INVISIBLE
                    binding.error.text = getString(R.string.emtpy_text)
                    binding.error.visibility = View.VISIBLE
            }
            else{
                viewModel.getData(text.toString(),requireContext())
            }
        }
    }

    private fun observeData(){
        viewModel.getData.observe(viewLifecycleOwner, { it ->
            data = it?.data
            if (!data?.data?.children.isNullOrEmpty())
            {
                bindRecylerViewData()
            }
            else
            {
                binding.progress.visibility = View.INVISIBLE
                binding.error.text = getString(R.string.error_text)
                binding.error.visibility = View.VISIBLE
                binding.rycView.visibility = View.INVISIBLE
            }
        })
    }

    private fun bindRecylerViewData(){
        binding.progress.visibility = View.INVISIBLE
        binding.error.visibility = View.INVISIBLE
        binding.rycView.visibility = View.VISIBLE
        binding.rycView.adapter = ImageAdapter(data?.data?.children){
            goDetail(it)
        }
    }

    private fun goDetail(data : ChildrenDetailModel){
        val navController = findNavController(requireActivity(), R.id.main)
        val bundle = Bundle()
        bundle.putSerializable("data", data) // Serializable Object
        navController.navigate(R.id.main_to_detail, bundle)
    }

    override fun layoutResource(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): MainFragmentBinding {
        return MainFragmentBinding.inflate(inflater, container, false)
    }

}