const val JAVA_VERSION: Int = 17

object Coordinates {
    const val name = "Core"
    const val description = "Mizu Client's core monolithic repository."
    const val vendor = "Mizu Software"

    const val gitHost = "github.com"
    const val repoId = "MizuSoftware/$name"
    const val gitUrl = "https://$gitHost/$repoId"
    const val mainGitBranch = "main"

    const val group = "wtf.mizu.core"
    const val version = "0.0.1"
}

object Pom {
    val licenses = arrayOf<License>(
        License(
            "GNU Affero General Public License version 3",
            "https://opensource.org/licenses/AGPL-3.0"
        ),
    )

    val developers = arrayOf<Developer>(
        Developer("Shyrogan", "<none>"),
        Developer("lambdagg", "lambdagg@tuta.io"),
        Developer("xtrm", "oss@xtrm.me"),
    )
}

/**
 * A POM-compliant License object.
 */
data class License(
    val name: String,
    val url: String,
    val distribution: String = "repo",
)

/**
 * A POM-compliant Developer object.
 */
data class Developer(
    val name: String,
    val id: String,
    val email: String?,
) {
    constructor(
        nameAndId: String,
        email: String
    ) : this(nameAndId, nameAndId, email)
}
