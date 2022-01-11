package com.example.mvvmretrofitrecylerviewpagingpager.viewmodel

import android.app.Application
import com.example.mvvmretrofitrecylerviewpagingpager.model.ViewPagerItem

class ViewPagerViewModel(application: Application) : BaseViewModel(application) {
    lateinit var itemList : List<ViewPagerItem>

    fun setItemList() :  List<ViewPagerItem>{

        val url1 =
            "https://assets.pokemon.com/assets/cms2/img/pokedex/full/133.png"
        val url2 =
            "https://assets.pokemon.com/assets/cms2/img/pokedex/full/025.png"
        val url3 =
            "https://assets.pokemon.com/assets/cms2/img/pokedex/full/007.png"
        val url4 =
            "https://assets.pokemon.com/assets/cms2/img/pokedex/full/039.png"
        val url5 =
            "https://assets.pokemon.com/assets/cms2/img/pokedex/full/001.png"


        itemList  = listOf(ViewPagerItem(url1),ViewPagerItem(url2),ViewPagerItem(url3),ViewPagerItem(url4),ViewPagerItem(url5))

        return itemList

    }
}