package com.niko.cryptoapp.data.mapper

import com.google.gson.Gson
import com.niko.cryptoapp.data.database.entity.CoinPriceEntity
import com.niko.cryptoapp.data.network.dto.CoinList
import com.niko.cryptoapp.data.network.dto.CoinPrice
import com.niko.cryptoapp.data.network.dto.CoinPriceInfoRawData
import com.niko.cryptoapp.domain.models.CoinPriceModel
import java.sql.Date
import java.sql.Timestamp
import java.text.SimpleDateFormat
import java.util.Locale
import java.util.TimeZone

object CoinMapper {
    const val BASE_IMG_URL = "https:cryptocompare.com"
    fun mapCoinListToString(coinList: CoinList): String {
        return coinList.data?.map { it.coinInfo?.name }?.joinToString(",") ?: ""
    }

    fun coinPriceInfoRawDataToListCoinPrice(coinPriceInfoRawData: CoinPriceInfoRawData): List<CoinPrice> {
        return getPriceModelFromRawDataModel(CoinPriceInfoRawData(coinPriceInfoRawData.coinPriceInfoJsonObject))
    }

    private fun getPriceModelFromRawDataModel(coinPriceInfoRawDataModel: CoinPriceInfoRawData): List<CoinPrice> {
        val result = mutableListOf<CoinPrice>()
        val jsonObject = coinPriceInfoRawDataModel.coinPriceInfoJsonObject ?: return result
        val coinKeySet = jsonObject.keySet()
        for (coinKey in coinKeySet) {
            val currentJson = jsonObject.getAsJsonObject(coinKey)
            val currencyKeySet = currentJson.keySet()
            for (currencyKey in currencyKeySet) {
                val priceInfo = Gson().fromJson(
                    currentJson.getAsJsonObject(currencyKey),
                    CoinPrice::class.java
                )
                result.add(priceInfo)
            }
        }
        return result
    }

    fun coinPriceEntityToCoinPriceModel(cpe: CoinPriceEntity): CoinPriceModel {
        return CoinPriceModel(
           cpe.fromSymbol,
            cpe.toSymbol,
            cpe.price,
            convertTimeStampToTime(cpe.lastUpdate),
            cpe.highDay,
            cpe.lowDay,
            cpe.lastMarket,
            cpe.imageUrl
        )
    }

    private fun convertTimeStampToTime(timestamp: Long?): String{
        if(timestamp==null) return ""
        val stamp = Timestamp(timestamp*1000)
        val date = Date(stamp.time)
        val pattern = "HH:mm:ss"
        val sdf = SimpleDateFormat(pattern, Locale.getDefault())
        sdf.timeZone = TimeZone.getDefault()
        return sdf.format(date)
    }

    fun coinPriceToCoinPriceEntity(coinPriceModel: CoinPrice): CoinPriceEntity {
        return CoinPriceEntity(
            coinPriceModel.fromSymbol?:"",
            coinPriceModel.toSymbol,
            coinPriceModel.price,
            coinPriceModel.lastUpdate,
            coinPriceModel.highDay,
            coinPriceModel.lowDay,
            coinPriceModel.lastMarket,
            BASE_IMG_URL + coinPriceModel.imageUrl
        )
    }
}
