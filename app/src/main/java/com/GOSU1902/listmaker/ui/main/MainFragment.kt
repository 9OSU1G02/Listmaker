package com.GOSU1902.listmaker.ui.main

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.preference.PreferenceManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.GOSU1902.listmaker.R
import com.GOSU1902.listmaker.TaskList
import com.GOSU1902.listmaker.databinding.FragmentMainBinding



class MainFragment(private val clickListener: MainFragmentInteractionListener) : Fragment(), ListSelectionRecyclerViewAdapter.ListSelectionRecyclerViewClickListener {
    interface MainFragmentInteractionListener {
        fun listItemTapped(list: TaskList)
    }
    companion object {
        fun newInstance(clickListener: MainFragmentInteractionListener) = MainFragment(clickListener)
    }

    private lateinit var viewModel: MainViewModel
    private lateinit var binding: FragmentMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(requireActivity(), MainViewModelFactory(PreferenceManager.getDefaultSharedPreferences(requireActivity())))[MainViewModel::class.java]
        val recyclerViewAdapter = ListSelectionRecyclerViewAdapter(viewModel.lists, this)
        binding.listsRecyclerview.adapter = recyclerViewAdapter
        viewModel.onListAdded = {
            recyclerViewAdapter.listsUpdated()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMainBinding.inflate(inflater, container, false)
        binding.listsRecyclerview.layoutManager = LinearLayoutManager(requireContext())
        return binding.root
    }

    override fun listItemClicked(list: TaskList) {
        clickListener.listItemTapped(list)
    }

}