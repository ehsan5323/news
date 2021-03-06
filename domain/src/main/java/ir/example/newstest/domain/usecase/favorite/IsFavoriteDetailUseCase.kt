package ir.example.newstest.domain.usecase.favorite

import ir.example.newstest.domain.base.BaseUseCase
import ir.example.newstest.domain.error.ApiException
import ir.example.newstest.domain.error.ErrorType
import ir.example.newstest.domain.repository.NewsRepository
import ir.example.newstest.domain.util.FlowResult
import ir.example.newstest.domain.util.Result
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class IsFavoriteDetailUseCase @Inject constructor(
    private val newsRepository: NewsRepository
) : BaseUseCase<String, Boolean>() {

    override fun invoke(params: String): FlowResult<Boolean> =
        newsRepository.getDetailFlows().map {
            when (it) {
                is Result.Success -> {
                    if (it.data.firstOrNull { detail -> detail.link == params } != null) {
                        Result.Success(true)
                    } else Result.Success(false)
                }
                is Result.Error -> Result.Error(ApiException("", ErrorType.UNKNOWN, 0))
                is Result.Loading -> Result.Loading
            }
        }
}

