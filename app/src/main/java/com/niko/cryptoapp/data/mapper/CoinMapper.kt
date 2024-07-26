package com.niko.cryptoapp.data.mapper

import com.google.gson.Gson
import com.niko.cryptoapp.data.database.entity.CoinPriceEntity
import com.niko.cryptoapp.data.network.dto.Coin
import com.niko.cryptoapp.data.network.dto.CoinDetails
import com.niko.cryptoapp.data.network.dto.CoinList
import com.niko.cryptoapp.data.network.dto.CoinPriceInfoRawData
import com.niko.cryptoapp.domain.models.CoinDetailsModel
import com.niko.cryptoapp.domain.models.CoinListModel
import com.niko.cryptoapp.domain.models.CoinModel
import com.niko.cryptoapp.domain.models.CoinPriceInfoRawDataModel
import com.niko.cryptoapp.domain.models.CoinPriceModel

object CoinMapper {
    fun mapCoinListToString(coinList: CoinList): String {
        val coinListModel = CoinListModel(mapCoinListDetailsToCoinListDetailsModel(coinList.data))
        return coinListModel.data?.map { coinDetailModel -> coinDetailModel.coinModelInfo?.name }
            ?.joinToString(",").toString()
    }

    fun mapCoinDetailsToCoinDetailsModel(coinDetails: CoinDetails): CoinDetailsModel {
        return CoinDetailsModel(mapCoinToCoinModel(coinDetails.coinInfo))
    }

    fun mapCoinListDetailsToCoinListDetailsModel(coinDetails: List<CoinDetails>?): List<CoinDetailsModel>? {
        return coinDetails?.map { mapCoinDetailsToCoinDetailsModel(it) }
    }

    fun mapCoinToCoinModel(coin: Coin?): CoinModel {
        return CoinModel(
            coin?.name,
        )
    }

    fun coinPriceInfoRawDataToListCoinPriceModel(coinPriceInfoRawData: CoinPriceInfoRawData): List<CoinPriceModel> {
        return getPriceModelFromRawDataModel(CoinPriceInfoRawDataModel(coinPriceInfoRawData.coinPriceInfoJsonObject))
    }

    private fun getPriceModelFromRawDataModel(coinPriceInfoRawDataModel: CoinPriceInfoRawDataModel):List<CoinPriceModel>{
        val result = mutableListOf<CoinPriceModel>()
        val jsonObject = coinPriceInfoRawDataModel.coinPriceInfoJsonObject ?: return result
        val coinKeySet = jsonObject.keySet()
        for(coinKey in coinKeySet){
            val currentJson = jsonObject.getAsJsonObject(coinKey)
            val currencyKeySet = currentJson.keySet()
            for(currencyKey in currencyKeySet){
                val priceInfo = Gson().fromJson(currentJson.getAsJsonObject(currencyKey),CoinPriceModel::class.java)
                result.add(priceInfo)
            }
        }
        return result
    }

    fun coinPriceEntityToCoinPriceModel(cpe: CoinPriceEntity): CoinPriceModel {
        return CoinPriceModel(
            cpe.type,
            cpe.market,
            cpe.fromSymbol,
            cpe.toSymbol,
            cpe.flags,
            cpe.lostMarket,
            cpe.price,
            cpe.lastUpdate,
            cpe.lastVolume,
            cpe.latVolumeTo,
            cpe.volumeHour,
            cpe.highHour,
            cpe.volumeDay,
            cpe.highDay,
            cpe.lowDay,
            cpe.volume24Hour,
            cpe.imageUrl
        )
    }
    fun coinPriceModelToCoinPriceEntity(coinPriceModel: CoinPriceModel): CoinPriceEntity {
        return CoinPriceEntity(
            coinPriceModel.type,
            coinPriceModel.market,
            coinPriceModel.fromSymbol.toString(),
            coinPriceModel.toSymbol,
            coinPriceModel.flags,
            coinPriceModel.lastMarket,
            coinPriceModel.price,
            coinPriceModel.lastUpdate,
            coinPriceModel.lastVolume,
            coinPriceModel.latVolumeTo,
            coinPriceModel.volumeHour,
            coinPriceModel.highHour,
            coinPriceModel.volumeDay,
            coinPriceModel.highDay,
            coinPriceModel.lowDay,
            coinPriceModel.volume24Hour,
            coinPriceModel.imageUrl
        )
        }
}