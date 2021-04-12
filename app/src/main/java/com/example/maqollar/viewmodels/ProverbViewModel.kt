package com.example.maqollar.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.example.maqollar.datasource.ProverbDataSourceFactory
import com.example.maqollar.models.Maqol
import java.util.concurrent.Executor
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

class
ProverbViewModel:ViewModel() {

    var proverbList:LiveData<PagedList<Maqol>>? = null
    var executor:Executor? = null

    init {
        val proverbDataSourceFactory = ProverbDataSourceFactory()
        val pageConfig = PagedList.Config.Builder()
            .setEnablePlaceholders(true)
            .setInitialLoadSizeHint(10)
            .setPageSize(100)
            .build()

        executor = Executors.newFixedThreadPool(2)

        proverbList = LivePagedListBuilder(proverbDataSourceFactory,pageConfig)
            .setFetchExecutor((executor as ExecutorService?)!!)
            .build()
    }
}