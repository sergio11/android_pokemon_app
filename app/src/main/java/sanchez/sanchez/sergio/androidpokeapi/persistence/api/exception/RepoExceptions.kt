package sanchez.sanchez.sergio.androidpokeapi.persistence.api.exception

import java.lang.Exception

/**
 * Repo Error Common Exception
 * @param cause
 */
class RepoErrorException(cause: Throwable): Exception(cause)

/**
 * Repo No Result Exception
 * @param cause
 */
class RepoNoResultException(cause: Throwable): Exception(cause)