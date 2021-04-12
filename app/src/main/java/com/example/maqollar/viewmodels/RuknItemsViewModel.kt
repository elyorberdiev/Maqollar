package com.example.maqollar.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.example.maqollar.datasource.RuknDataSourceFactory
import com.example.maqollar.models.Maqol
import com.example.maqollar.models.Rukn
import java.util.concurrent.Executor
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

class RuknItemsViewModel : ViewModel() {


    var ruknItemList: LiveData<PagedList<Maqol>>? = null
    var executor: Executor? = null

    fun init(rukn: Rukn){
        val ruknDataSourceFactory = RuknDataSourceFactory(rukn)
        val pageConfig = PagedList.Config.Builder()
            .setEnablePlaceholders(true)
            .setInitialLoadSizeHint(10)
            .setPageSize((rukn.count!!/10)+1)
            .build()

        executor = Executors.newFixedThreadPool(1)

        ruknItemList = LivePagedListBuilder(ruknDataSourceFactory, pageConfig)
            .setFetchExecutor((executor as ExecutorService?)!!)
            .build()
    }

    init {

    }

}