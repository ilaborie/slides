package org.ilaborie.slides.content

import org.ilaborie.slides.catchWithDefault
import java.io.File
import java.nio.charset.Charset


// External
sealed class External {
    abstract val fileName: String

    fun link(): String = when (this) {
        is ExternalResource -> resource
        is ExternalFile     -> file.toString()
    }

    // FIXME datauri


    fun loadTextContent(charset: Charset = Charsets.UTF_8): String =
            when (this) {
                is ExternalResource ->
                    catchWithDefault("No resource: $resource") {
                        this::class.java
                                .getResourceAsStream(this.resource)
                                .reader(charset)
                                .readText()
                    }
                is ExternalFile     ->
                    catchWithDefault("No file: $file") {
                        this.file.readText(charset)
                    }
            }
}

data class ExternalResource(val resource: String) : External() {
    override val fileName: String
        get() = resource.split("/").last()
}

data class ExternalFile(val file: File) : External() {
    override val fileName: String
        get() = file.name

}