package com.github.crunc.kotlin.jasmine.maven

import com.github.crunc.kotlin.jasmine.maven.conf.KarmaConfJs
import org.apache.maven.execution.MavenSession
import org.apache.maven.plugin.AbstractMojo
import org.apache.maven.plugins.annotations.Component
import org.apache.maven.plugins.annotations.LifecyclePhase
import org.apache.maven.plugins.annotations.Mojo
import org.apache.maven.plugins.annotations.Parameter
import org.apache.maven.project.MavenProject
import org.apache.maven.project.MavenProjectHelper
import org.sonatype.plexus.build.incremental.BuildContext
import java.io.File

@Mojo(name = "generate-karma-conf", defaultPhase = LifecyclePhase.GENERATE_TEST_RESOURCES)
class KarmaConfMojo : AbstractMojo() {

    @Component
    private lateinit var projectHelper: MavenProjectHelper

    @Component
    private var buildContext: BuildContext? = null

    @Parameter(defaultValue = "\${project}", readonly = true, required = true)
    private lateinit var project: MavenProject

    @Parameter(property = "session", defaultValue = "\${session}", readonly = true)
    private lateinit var session: MavenSession

    @Parameter(property = "skip", defaultValue = "false")
    var skip: Boolean = false

    @Parameter(property = "outputDirectory", defaultValue = "\${project.build.testOutputDirectory}", required = true)
    private lateinit var outputDirectory: File

    @Parameter(property = "outputFileName", defaultValue = "karma.conf.js", required = true)
    private lateinit var outputFileName: String

    override fun execute() {

        when {
            project.packaging == "pom" -> {
                log.debug("generate goal could not be applied to pom project")
                return
            }
            skip -> {
                log.debug("skipping karma.conf.js generation as per configuration")
                return
            }
            else -> generateKarmaConf()
        }
    }

    private fun generateKarmaConf() {

        if (!(outputDirectory.mkdirs())) {
            throw IllegalStateException("could not create directory ${outputDirectory.path}")
        }

        val target = File(outputDirectory, outputFileName)
        val script = createKarmaConf().toScript()

        log.info("generating ${target.path}")

        target.bufferedWriter().use { out ->
            out.write(script)
        }
    }

    private fun createKarmaConf(): KarmaConfJs =
            KarmaConfJs()
}