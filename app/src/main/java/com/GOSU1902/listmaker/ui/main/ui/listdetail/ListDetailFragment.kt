package com.GOSU1902.listmaker.ui.main.ui.listdetail

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.GOSU1902.listmaker.R
import com.GOSU1902.listmaker.databinding.FragmentListDetailBinding
import com.GOSU1902.listmaker.databinding.FragmentMainBinding


class ListDetailFragment : Fragment() {
    lateinit var binding: FragmentListDetailBinding
    companion object {
        fun newInstance() = ListDetailFragment()
    }

    private lateinit var viewModel: ListDetailViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(requireActivity()).get(ListDetailViewModel::class.java)
        val recyclerViewAdapter = ListItemsRecyclerViewAdapter(viewModel.list)
        binding.listItemsRecyclerview.adapter = recyclerViewAdapter
        binding.listItemsRecyclerview.layoutManager = LinearLayoutManager(requireContext())
        viewModel.onTaskAdded = {
            recyclerViewAdapter.notifyDataSetChanged()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentListDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

}