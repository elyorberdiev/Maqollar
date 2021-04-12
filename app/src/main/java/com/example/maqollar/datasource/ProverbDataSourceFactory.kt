package com.example.maqollar.datasource

import androidx.paging.DataSource
import com.example.maqollar.models.Maqol


class ProverbDataSourceFactory : DataSource.Factory<Int, Maqol>() {

    var proverbDataSource: ProverbDataSource? = null

    override fun create(): DataSource<Int, Maqol> {
        proverbDataSource = ProverbDataSource()
        return proverbDataSource!!
    }
}