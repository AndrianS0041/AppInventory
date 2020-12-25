package com.andrian.appinventory.ui.inventory

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.andrian.appinventory.R
import com.andrian.appinventory.databinding.FragmentInventoryBinding
import com.andrian.appinventory.ui.home.MainActivity
import com.bumptech.glide.Glide.init

class InventoryFragment : Fragment() {
    private val parent: MainActivity by lazy { activity as MainActivity }
    private lateinit var binding: FragmentInventoryBinding
    private val viewModel: InventoryViewModel by lazy { InventoryViewModel() }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentInventoryBinding.inflate(inflater, container, false).apply {
            viewModel = this@InventoryFragment.viewModel
            lifecycleOwner = this@InventoryFragment
        }
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        init()
        observe()
    }

    private fun init() {
        binding.recyclerView.adapter = InventoryAdapter(parent)
        viewModel.listInventory()
        binding.swipeRefresh.setOnRefreshListener {
            viewModel.listInventory()
        }
    }

    private fun observe() {
        viewModel.loading.observe(viewLifecycleOwner) {
            binding.swipeRefresh.isRefreshing = it
        }

        viewModel.actionState.observe(viewLifecycleOwner) {
            if (it.isConsumed) {
                Log.i("ActionState", "isConsumed")
            } else if (!it.isSuccess) {
                Toast.makeText(parent, it.message, Toast.LENGTH_SHORT).show()
            }
        }
    }
}