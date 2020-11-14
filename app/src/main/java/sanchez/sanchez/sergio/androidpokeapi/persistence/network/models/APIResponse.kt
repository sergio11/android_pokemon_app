package sanchez.sanchez.sergio.androidpokeapi.persistence.network.models

import com.squareup.moshi.Json

/**
 * Generic API Response Wrapper
 **/
data class APIResponse<T> (
    @field:Json(name = "count") val count: Int,
    @field:Json(name = "next") val next: String?,
    @field:Json(name = "previous") val previous: String?,
    // The results returned by the call.
    @field:Json(name = "results") val data: List<T>
)