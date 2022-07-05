# Core 🧠 [![CI][badge-ci]][ci] [![badge-mvnc]][project-mvnc]

[Mizu Client][mizu]'s *WIP* core monolithic repository. Uses [Kotlin], builds onto the [JVM].


## Modules

This repository is split into modules that serve a specific task by themselves. Everything you need to know
about them is described in their specific **README.md** files. Here are the modules, in the import stack order.

- [common] (needs nothing)

- [service-loader] (needs nothing)

- [configuration] (needs [common])

- [client-api] (needs [common], [configuration])


## Importing

You can import individual parts of the [Core][project-url] from [Maven Central][mvnc] by using the required module's
name as the artifact name, and `wtf.mizu.core` as the group by adding it to your dependencies block. As an example, we
will be importing all of the possible modules.

### Gradle

```kt
repositories {
    mavenCentral()
}

dependencies {
    implementation("wtf.mizu.core:common:0.0.1")
    implementation("wtf.mizu.core:service-loader:0.0.1")
    implementation("wtf.mizu.core:configuration:0.0.1")
    implementation("wtf.mizu.core:client-api:0.0.1")
}
```

### Maven

```xml
<dependencies>
    <dependency>
        <groupId>wtf.mizu.core</groupId>
        <artifactId>common</artifactId>
        <version>0.0.1</version>
    </dependency>

    <dependency>
        <groupId>wtf.mizu.core</groupId>
        <artifactId>service-loader</artifactId>
        <version>0.0.1</version>
    </dependency>

    <dependency>
        <groupId>wtf.mizu.core</groupId>
        <artifactId>configuration</artifactId>
        <version>0.0.1</version>
    </dependency>

    <dependency>
        <groupId>wtf.mizu.core</groupId>
        <artifactId>client-api</artifactId>
        <version>0.0.1</version>
    </dependency>
</dependencies>
```


## Libraries

In addition to those modules, we also use some external libraries.

- [Kawa][kawa] - An open-source, powerful and modular Kotlin-enhanced event bus for the JVM, designed by the Mizu team.


## Troubleshooting

If you encounter any kind of problem **related to this library**, you can [open an issue][new-issue] describing what's
up. We ask you to be as precise as you can, so that our developers can help you as fast as possible.

Non-project-related issues will most likely be closed without further ado.


## Contributing

You can contribute to this project by [forking it][fork], making your changes, and
[creating a new pull request][new-pr].

You have to be as precise as possible while doing it though, describing in the commits (or PR description) what you're
changing, why and how.


## Licensing

This project is licensed under [the AGPLv3-only License][license].


<!--- External Links --->

[mizu]: https://mizu.wtf/ "mizu website"

[kotlin]: https://kotlinlang.org "Kotlin website"

[jvm]: https://adoptium.net "Adoptium website"

[mvnc]: https://repo1.maven.org/maven2/ "Maven Central website"

[kawa]: https://github.com/MizuSoftware/kawa "Kawa"


<!-- Project Links -->

[project-url]: https://github.com/MizuSoftware/core "Project homepage"

[fork]: https://github.com/MizuSoftware/core/fork "Fork this repository"

[new-pr]: https://github.com/MizuSoftware/core/pulls/new "Create a new pull request"

[new-issue]: https://github.com/MizuSoftware/core/issues/new "Create a new issue"

[project-mvnc]: https://search.maven.org/search?g:wtf.mizu.kawa "Project Maven Central search"

[ci]: https://github.com/MizuSoftware/core/actions/workflows/build.yml "Continuous integration"

[license]: https://github.com/MizuSoftware/core/blob/main/LICENSE "LICENSE source file"


<!-- Modules -->

[common]: https://github.com/MizuSoftware/core/tree/main/common "Common module"

[configuration]: https://github.com/MizuSoftware/core/tree/main/configuration "Configuration module"

[client-api]: https://github.com/MizuSoftware/core/tree/main/client-api "api module"

[service-loader]: https://github.com/MizuSoftware/core/tree/main/service-loader "Service loader module"


<!-- Badges -->

[badge-mvnc]: https://maven-badges.herokuapp.com/maven-central/wtf.mizu.kawa/core/badge.svg "Maven Central badge"

[badge-ci]: https://github.com/MizuSoftware/core/actions/workflows/build.yml/badge.svg?branch=main "CI badge"
