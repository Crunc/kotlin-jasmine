package com.github.crunc.kotlin.jasmin.maven

import org.apache.maven.plugin.AbstractMojo
import org.apache.maven.plugins.annotations.Component
import org.apache.maven.plugins.annotations.LifecyclePhase
import org.apache.maven.plugins.annotations.Mojo
import org.apache.maven.plugins.annotations.Parameter
import org.apache.maven.project.MavenProject
import org.apache.maven.project.MavenProjectHelper
import java.io.File
import java.io.FileOutputStream
import java.io.OutputStreamWriter

@Mojo(
        name = "generate",
        defaultPhase = LifecyclePhase.GENERATE_TEST_RESOURCES
)
class KarmaConfMojo : AbstractMojo() {

    @Component
    private lateinit var projectHelper: MavenProjectHelper

    @Parameter(defaultValue = "\${project}", readonly = true, required = true)
    private lateinit var project: MavenProject

    @Parameter(name = "skip", defaultValue = "false")
    private var skip: Boolean = false

    @Parameter(name = "outputDirectory", defaultValue = "\${project.build.outputDirectory}/test-js", required = true)
    private lateinit var outputDirectory: File

    @Parameter(name = "outputFileName", defaultValue = "karma.conf.js", required = true)
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

        if (!outputDirectory.mkdirs()) {
            throw IllegalStateException("could not create directory ${outputDirectory.path}")
        }

        val target = File(outputDirectory, outputFileName)
        val script = KarmaConfJs().toScript()

        log.info("generating ${target.path}")

        OutputStreamWriter(FileOutputStream(target)).use {
            it.write(script)
        }
    }
}