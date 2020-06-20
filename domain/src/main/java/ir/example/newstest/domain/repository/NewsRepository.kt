package ir.example.newstest.domain.repository

import ir.example.newstest.domain.pojo.NewsEn
import ir.example.newstest.domain.pojo.NewsFa
import kotlinx.coroutines.flow.Flow

interface NewsRepository {

    fun getXmlNewsFromNetwork(): Flow<Result<NewsFa>>

    fun getJsonNewsFromNetwork(params: Pair<String, String>): Flow<Result<NewsEn>>
}