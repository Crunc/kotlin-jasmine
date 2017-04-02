package com.github.crunc.kotlin.jasmine.maven

import com.github.crunc.kotlin.jasmine.maven.conf.*
import com.github.crunc.kotlin.jasmine.maven.node.PackageDependency
import com.github.crunc.kotlin.jasmine.maven.node.PackageJson
import org.apache.maven.plugin.AbstractMojo
import org.apache.maven.plugins.annotations.LifecyclePhase
import org.apache.maven.plugins.annotations.Mojo
import org.apache.maven.plugins.annotations.Parameter
import org.apache.maven.project.MavenProject
import java.io.File
import java.nio.file.Path

@Mojo(name = GenerateKarmaTestMojo.Name, defaultPhase = LifecyclePhase.GENERATE_TEST_RESOURCES)
class GenerateKarmaTestMojo : AbstractMojo() {

    companion object {
        const val Name = "generate-karma-test"
    }

    @Parameter(defaultValue = "\${project}", readonly = true, required = true)
    private lateinit var project: MavenProject

    @Parameter(defaultValue = "false")
    private var skip: Boolean = false

    @Parameter(defaultValue = "\${project.build.testOutputDirectory}", required = true)
    private lateinit var baseDirectory: File

    private val basePath: Path get() = baseDirectory.toPath()

    @Parameter(defaultValue = "\${project.build.outputDirectory}", required = true)
    private lateinit var mainDirectory: File

    private val mainPath: Path get() = mainDirectory.toPath()

    @Parameter(defaultValue = "karma.conf.js", required = true)
    private lateinit var karmaConfFileName: String

    @Parameter(defaultValue = "test-main.js", required = true)
    private lateinit var testMainFileName: String

    @Parameter(defaultValue = "package.json", required = true)
    private lateinit var packageJsonFileName: String

    @Parameter
    private var browsers: List<String> = emptyList()

    private val karmaBrowsers: List<KarmaBrowser> get() {
        if (browsers.isEmpty()) {
            return listOf(KarmaBrowser.PhantomJS)
        } else {
            return browsers.map { KarmaBrowser.parse(it) }
                    .filter { it != KarmaBrowser.Unknown }
        }
    }

    private val karmaBrowserDependencies: List<PackageDependency> get() = karmaBrowsers
            .map { it.dependency }

    private val karmaPluginDependencies: List<PackageDependency> get() = listOf(
            PackageDependency.KarmaJasmine
    ).plus(karmaBrowserDependencies)

    private val packageDependencies: List<PackageDependency> get() = listOf(
            PackageDependency.Karma,
            PackageDependency.KarmaRequireJS,
            PackageDependency.JasmineCore,
            PackageDependency.RequireJS
    ).plus(karmaPluginDependencies)

    private val nodeModules: Path get() = baseDirectory.toPath().resolve("node_modules")

    private val requireJsFilePattern: Path get() {
        return basePath.relativize(
                nodeModules.resolve("requirejs")
                        .resolve("require.js")
        )
    }

    private val karmaRequireJsFilePattern: Path get() {
        return basePath.relativize(
                nodeModules.resolve("karma-requirejs")
                        .resolve("lib")
                        .resolve("adapter.js")
        )
    }

    private val mainJsFilePattern: Path get() {
        return basePath.relativize(
                mainPath.resolve("**")
                        .resolve("!(*.meta).js")
        )
    }

    private val rootTestJsFilePattern: Path get() {
        return basePath.relativize(
                basePath.resolve("!(test-main|karma.conf|*.meta).js")
        )
    }

    private val nestedTestJsFilePattern: Path get() {
        return basePath.relativize(
                basePath.resolve("!(node_modules|node)")
                        .resolve("!(*.meta).js")
        )
    }

    private val testMainJsFilePattern: Path get() {
        return basePath.relativize(
                basePath.resolve("test-main.js")
        )
    }

    private val karmaFiles: List<KarmaFile> get() {
        return listOf(
                KarmaFile(pattern = requireJsFilePattern, watched = false),
                KarmaFile(pattern = karmaRequireJsFilePattern, watched = false),
                KarmaFile(pattern = mainJsFilePattern, watched = false),
                KarmaFile(pattern = rootTestJsFilePattern, watched = false),
                KarmaFile(pattern = nestedTestJsFilePattern, watched = false),
                KarmaFile(pattern = testMainJsFilePattern, watched = false)
        )
    }

    override fun execute() {

        when {

            project.packaging == "pom" ->
                log.debug("[$Name] goal could not be applied to pom project")

            skip ->
                log.debug("skipping [$Name] as per configuration")

            else ->
                generateKarmaTest()
        }
    }

    private fun generateKarmaTest() {

        if (!baseDirectory.exists() && !baseDirectory.mkdirs()) {
            throw IllegalStateException("could not create directory ${baseDirectory.path}")
        }

        generateKarmaConf()
        generateTestMain()
        generatePackageJson()
    }

    private fun generateKarmaConf(): KarmaConfJs {

        val karmaConf = KarmaConfJs(
                basePath = "",
                port = 9876,
                frameworks = listOf(
                        KarmaFramework.Jasmine
                ),
                plugins = listOf(
                        KarmaPlugin.KarmaJasmine
                ),
                files = karmaFiles,
                browsers = karmaBrowsers
        )

        val karmaConfFile = File(baseDirectory, karmaConfFileName)

        log.info("generating Karma configuration file: ${karmaConfFile.path}")

        karmaConfFile.bufferedWriter().use(karmaConf::writeTo)

        return karmaConf
    }

    private fun generateTestMain(): TestMainJs {

        val testMain = TestMainJs()
        val testMainFile = File(baseDirectory, testMainFileName)

        log.info("generating test main file: ${testMainFile.path}")

        testMainFile.bufferedWriter().use(testMain::writeTo)

        return testMain
    }

    private fun generatePackageJson(): PackageJson {

        val packageJson = PackageJson(
                name = project.artifactId,
                version = project.version,
                dependencies = packageDependencies
        )

        val packageJsonFile = File(baseDirectory, packageJsonFileName)

        log.info("generating package descriptor: ${packageJsonFile.path}")

        packageJsonFile.bufferedWriter().use(packageJson::writeTo)

        return packageJson
    }
}