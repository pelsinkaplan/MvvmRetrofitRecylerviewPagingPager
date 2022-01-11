package com.example.mvvmretrofitrecylerviewpagingpager.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.viewpager2.widget.ViewPager2
import com.example.mvvmretrofitrecylerviewpagingpager.R
import com.example.mvvmretrofitrecylerviewpagingpager.adapter.ViewPagerAdapter
import com.example.mvvmretrofitrecylerviewpagingpager.viewmodel.ViewPagerViewModel
import com.example.mvvmretrofitrecylerviewpagingpager.databinding.FragmentViewPagerBinding

class ViewPagerFragment : Fragment() {
    private lateinit var binding: FragmentViewPagerBinding
    private lateinit var viewPagerAdapter: ViewPagerAdapter
    private lateinit var viewModel: ViewPagerViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val tempViewModel: ViewPagerViewModel by viewModels()
        this.viewModel = tempViewModel
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_view_pager, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        val list = viewModel.setItemList()
        viewPagerAdapter = ViewPagerAdapter(list)
        binding.viewPagerAdapter = viewPagerAdapter
    }

}