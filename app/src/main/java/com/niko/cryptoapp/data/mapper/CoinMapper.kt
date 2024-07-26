package com.niko.cryptoapp.data.mapper

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
    fun mapCoinListToCoinListModel(coinList: CoinList): CoinListModel {
        return CoinListModel(mapCoinListDetailsToCoinListDetailsModel(coinList.data))
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

    fun coinPriceInfoRawDatatToCoinPriceInfoRawDataModel(coinPriceInfoRawData: CoinPriceInfoRawData): CoinPriceInfoRawDataModel {
        return CoinPriceInfoRawDataModel(coinPriceInfoRawData.coinPriceInfoJsonObject)
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