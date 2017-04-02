package com.github.crunc.kotlin.jasmine.maven.node

import com.github.crunc.kotlin.jasmine.maven.Writable
import java.io.Writer

class PackageJson(
        val name: String,
        val version: String,
        val private: Boolean = true,
        val dependencies: List<PackageDependency>
) : Writable {

    private val script: String get() = """
{
  "name": "$name",
  "version": "$version",
  "private": $private,
  "dependencies": {
    ${dependencies.map { it.toJsonProperty() }.joinToString(",\n    ")}
  }
}
""".trim()

    override fun writeTo(writer: Writer) {
        writer.write(script)
    }
}