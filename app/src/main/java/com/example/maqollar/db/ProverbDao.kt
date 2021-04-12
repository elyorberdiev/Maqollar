package com.example.maqollar.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.maqollar.models.Maqol

@Dao
interface ProverbDao {

    @Insert
    fun insertProverb(maqol: Maqol)

    @Delete
    fun deleteProverb(maqol: Maqol)

    @Query("select * from maqol")
    fun getData():LiveData<List<Maqol>>

    @Query("select * from maqol where id = :id")
    fun getMaqolById(id:Int):Maqol

}