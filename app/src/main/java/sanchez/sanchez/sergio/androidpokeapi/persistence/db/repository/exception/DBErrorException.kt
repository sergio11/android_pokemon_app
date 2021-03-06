package sanchez.sanchez.sergio.androidpokeapi.persistence.db.repository.exception

import java.lang.Exception

/**
 * DB Common Error Exception
 */
class DBErrorException(message: String? = null, cause: Throwable? = null): Exception(message, cause)