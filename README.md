# Core 🧠 [![CI][badge-ci]][ci] [![badge-mvnc]][project-mvnc]

[Mizu Client][mizu-website]'s *WIP* core monolithic repository. Uses [Kotlin], builds onto the [JVM].


## Modules

This repository is split into modules that serve a specific task by themselves. Everything you need to know
about them is described in their specific README.md files. Here are the modules, in the import stack order.

- [common]

- [configuration]

- [client-api]

- [service-loader]


## Libraries

In addition to those modules, we also use some external libraries.

- [Kawa][kawa] - An open-source, powerful and modular Kotlin-enhanced event bus for the JVM, designed by the Mizu team.


<!--- External Links --->

[mizu-website]: https://mizu.wtf/ "mizu website"

[kotlin]: https://kotlinlang.org "Kotlin website"

[jvm]: https://adoptium.net "Adoptium website"

[mvnc]: https://repo1.maven.org/maven2/ "Maven Central website"

[kawa]: https://github.com/MizuSoftware/kawa "Kawa"


<!-- Project Links -->

[project-mvnc]: https://search.maven.org/search?g:wtf.mizu.kawa "Project Maven Central search"

[ci]: https://github.com/MizuSoftware/core/actions/workflows/build.yml "Continuous integration"


<!-- Modules -->

[common]: https://github.com/MizuSoftware/core/tree/main/common "Common module"

[configuration]: https://github.com/MizuSoftware/core/tree/main/configuration "Configuration module"

[client-api]: https://github.com/MizuSoftware/core/tree/main/client-api "api module"

[service-loader]: https://github.com/MizuSoftware/core/tree/main/service-loader "Service loader module"


<!-- Badges -->

[badge-mvnc]: https://maven-badges.herokuapp.com/maven-central/wtf.mizu.kawa/core/badge.svg "Maven Central badge"

[badge-ci]: https://github.com/MizuSoftware/core/actions/workflows/build.yml/badge.svg?branch=main "CI badge"
