package wtf.mizu.core.service.exception

/**
 * Thrown when a service's implementation instance could not be found.
 *
 * @param serviceClass the said service's class.
 *
 * @author xtrm
 * @since 0.0.1
 */
class UnimplementedServiceException(
    serviceClass: Class<*>,
) : RuntimeException(
    "Service ${serviceClass.simpleName}'s implementation wasn't found!"
)
