package com.base.pokedex.data.repository

import com.base.pokedex.data.local.datasource.interfaces.PokemonLocalDataSource
import com.base.pokedex.data.mapper.asEntity
import com.base.pokedex.data.remote.datasource.interfaces.PokemonRemoteDataSource
import com.base.pokedex.data.remote.response.PokemonListResponse
import com.base.pokedex.domain.Result
import com.base.pokedex.domain.entity.PokemonEntity
import com.base.pokedex.domain.entity.PokemonInfoEntity
import com.base.pokedex.domain.mapper
import com.base.pokedex.domain.repository.PokemonRepository
import com.base.pokedex.domain.result
import com.base.pokedex.util.TimeUtil
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject


class PokemonRepositoryImpl @Inject constructor(
    private val remoteDataSource: PokemonRemoteDataSource,
    private val localDataSource: PokemonLocalDataSource
) : BaseRepository(), PokemonRepository {


    override suspend fun getPokemonList(
        limit: Int,
        offset: Int
    ): Flow<Result<List<PokemonEntity>>> {
        return safeApiCall { remoteDataSource.getPokemonList(limit, offset) }
            .mapper { response: PokemonListResponse ->
                response.results.asEntity()
            }
    }

    override suspend fun getPokemonInfoByName(pokemonName: String): Flow<Result<PokemonInfoEntity>> =
        flow {
            emit(Result.Loading)

            /*
            1. Local에서 데이터 조회
             */
            val pokemonInfoEntity = localDataSource.getPokemonInfoByName(pokemonName)
            val isDateDifferenceOverOneDay =
                TimeUtil.isDateDifferenceOverOneDay(pokemonInfoEntity?.update)
            /*
            2. 객체가 있고 update 일자가 1일 미만 인경우 객체 반환
             */
            if (pokemonInfoEntity != null && !isDateDifferenceOverOneDay) {
                emit(Result.Success(pokemonInfoEntity))
            }
            /*
            3. 객체가 없거나 update 일자가 1일 이상인 경우 서버에서 조회
             */
            else {
                var entity: PokemonInfoEntity? = null
                /*
                3-1. api 결과를 변환
                 */
                emit(
                    apiCall { remoteDataSource.getPokemonInfoByName(pokemonName) }
                        .result {
                            it.asEntity().run {
                                update = TimeUtil.currentTime
                                entity = this
                                this
                            }
                        }
                )

                /*
                3-2. 정상적으로 호출한 경우 local에 데이터 update
                 */
                entity?.let {
                    localDataSource.upsertPokemonInfo(it)
                }
            }
        }.catch { e ->
            e.printStackTrace()
            emit(Result.Failure(Exception(e)))
        }.flowOn(Dispatchers.IO)

    override suspend fun getPokemonInfoById(pokemonId: Int): Flow<Result<PokemonInfoEntity>> =
        flow {
            emit(Result.Loading)

            val pokemonInfoEntity = localDataSource.getPokemonInfoById(pokemonId)
            val isDateDifferenceOverOneDay =
                TimeUtil.isDateDifferenceOverOneDay(pokemonInfoEntity?.update)

            if (pokemonInfoEntity != null && !isDateDifferenceOverOneDay) {
                emit(Result.Success(pokemonInfoEntity))
            } else {
                var entity: PokemonInfoEntity? = null
                emit(
                    apiCall { remoteDataSource.getPokemonInfoById(pokemonId) }
                        .result {
                            it.asEntity().run {
                                update = TimeUtil.currentTime
                                entity = this
                                this
                            }
                        }
                )
                entity?.let {
                    localDataSource.upsertPokemonInfo(it)
                }
            }
        }.catch { e ->
            e.printStackTrace()
            emit(Result.Failure(Exception(e)))
        }.flowOn(Dispatchers.IO)

    override suspend fun upsertPokemonInfo(pokemonInfoEntity: PokemonInfoEntity) {
        localDataSource.upsertPokemonInfo(pokemonInfoEntity)
    }

}