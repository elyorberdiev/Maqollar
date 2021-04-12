package com.example.maqollar.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity
class Maqol:Serializable {

    @PrimaryKey
    var id:Int? = null

    var english:String? = null

    var russian:String? = null

    var uzbek:String? = null

//    constructor(id: Int?, english: String?, russian: String?, uzbek: String?) {
//        this.id = id
//        this.english = english
//        this.russian = russian
//        this.uzbek = uzbek
//    }
//
    constructor()


}