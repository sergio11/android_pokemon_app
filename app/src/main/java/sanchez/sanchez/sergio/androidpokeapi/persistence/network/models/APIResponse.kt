package sanchez.sanchez.sergio.androidpokeapi.persistence.network.models

import com.google.gson.annotations.SerializedName

/**
 * Generic API Response Wrapper
 **/
data class APIResponse<T> (
    @SerializedName(value = "count") val count: Int,
    @SerializedName(value = "next") val next: String?,
    @SerializedName(value = "previous") val previous: String?,
    // The results returned by the call.
    @SerializedName(value = "results") val data: List<T>
)