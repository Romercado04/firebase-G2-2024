package ar.edu.unlam.mobile.scaffold.data.kitty.repository

import ar.edu.unlam.mobile.scaffold.data.kitty.network.KittyNetworkRepository
import ar.edu.unlam.mobile.scaffold.domain.kitty.models.Kitty
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class KittyDefaultRepository @Inject constructor(private val networkRepository: KittyNetworkRepository) : KittyRepository {
    override suspend fun getKitty(): Flow<Kitty> {
        return this.networkRepository.getRandomKitty().map { it.toKitty() }
    }

//    override suspend fun getAllKitties(): Flow<List<Kitty>> {
//        return this.networkRepository.getAllKitties()
//            .map { list ->
//                list.map { it.toKitty() }
//            }
//    }
}
