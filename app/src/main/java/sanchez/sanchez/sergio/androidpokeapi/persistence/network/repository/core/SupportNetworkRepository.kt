package sanchez.sanchez.sergio.androidpokeapi.persistence.network.repository.core

import io.ktor.client.features.*
import io.ktor.client.response.*
import sanchez.sanchez.sergio.androidpokeapi.persistence.network.exception.*
import java.io.IOException
import java.nio.charset.Charset

/**
 * Some HTTP response codes that We could get when making something request
 */
const val BAD_REQUEST_CODE: Int = 400
const val UNAUTHORIZED_CODE: Int = 401
const val NOT_FOUND_CODE: Int = 404
const val INTERNAL_SERVER_ERROR_CODE: Int = 500
const val CONFLICT_ERROR_CODE: Int = 409
const val FORBIDDEN_CODE: Int = 403

abstract class SupportNetworkRepository {

    /**
     * Wrap for safe Network Call
     * @param onExecuted
     */
    protected suspend fun <T> safeNetworkCall(onExecuted: suspend () -> T): T {
        return try {
            onExecuted()
        } catch (exception: IOException){
            // map interrupted I/O to Network No Internet Exception
            throw NetworkNoInternetException()
        } catch (exception: NetworkException) {
            throw exception
        } catch (exception: ResponseException) {
            try {
                throw onApiException(exception)
            } catch (exception: Exception) {
                throw NetworkErrorException(cause = exception)
            }
        } catch (exception: Exception) {
            throw NetworkErrorException(cause = exception)
        }
    }

    /**
     * Map HTTP Error codes to exceptions to easy handler
     * @param responseException
     */
    open suspend fun onApiException(responseException: ResponseException): Exception =
        responseException.response.let {
            when(it.status.value) {
                BAD_REQUEST_CODE -> NetworkBadRequestException(message = it.readText(Charset.forName("UTF-8")), cause = responseException)
                UNAUTHORIZED_CODE -> NetworkUnauthorizedException(message = it.readText(Charset.forName("UTF-8")), cause = responseException)
                FORBIDDEN_CODE -> NetworkForbiddenException(message = it.readText(Charset.forName("UTF-8")), cause = responseException)
                NOT_FOUND_CODE -> NetworkNoResultException(message = it.readText(Charset.forName("UTF-8")), cause = responseException)
                INTERNAL_SERVER_ERROR_CODE -> NetworkErrorException(message = it.readText(Charset.forName("UTF-8")), cause = responseException)
                CONFLICT_ERROR_CODE -> NetworkUnverifiedAccountException(message = it.readText(Charset.forName("UTF-8")), cause = responseException)
                else -> NetworkErrorException()
            }
        }
}