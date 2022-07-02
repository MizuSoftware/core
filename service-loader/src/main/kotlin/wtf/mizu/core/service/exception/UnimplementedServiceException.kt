package wtf.mizu.core.service.exception

/**
 * Exception thrown when a service's implementation instance isn't found.
 *
 * @param [serviceClass]
 *
 * @author xtrm
 * @since 0.0.1
 */
class UnimplementedServiceException(
    serviceClass: Class<*>,
) : RuntimeException(
    "Service ${serviceClass.simpleName}'s implementation wasn't found!"
)
