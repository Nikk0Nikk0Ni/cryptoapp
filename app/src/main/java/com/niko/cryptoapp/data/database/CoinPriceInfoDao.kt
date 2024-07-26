package com.niko.cryptoapp.data.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.niko.cryptoapp.data.database.entity.CoinPriceEntity

@Dao
interface CoinPriceInfoDao {
    @Query("SELECT * FROM full_price_list ORDER BY lastUpdate DESC")
    fun getPriceList(): LiveData<List<CoinPriceEntity>>

    @Query("SELECT * FROM full_price_list WHERE fromSymbol =:fromSymbol")
    fun getCoinInfo(fromSymbol: String): LiveData<CoinPriceEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertPriceList(priceList: List<CoinPriceEntity>)
}