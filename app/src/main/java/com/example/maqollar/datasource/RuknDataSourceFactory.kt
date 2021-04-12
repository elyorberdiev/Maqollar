package com.example.maqollar.datasource

import androidx.paging.DataSource
import com.example.maqollar.models.Maqol
import com.example.maqollar.models.Rukn

class RuknDataSourceFactory(var rukn: Rukn):DataSource.Factory<Int,Maqol>() {

    var ruknDataSource:RuknDataSource? = null
    override fun create(): DataSource<Int, Maqol> {
        ruknDataSource = RuknDataSource(rukn)
        return ruknDataSource!!
    }
}