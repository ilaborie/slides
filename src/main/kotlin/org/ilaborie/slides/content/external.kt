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

    fun exists(): Boolean =
            when (this) {
                is ExternalResource ->
                    this::class.java.getResourceAsStream(this.resource) != null
                is ExternalFile     ->
                    file.exists()
            }

    abstract fun create(folder: File)
}

data class ExternalResource(val resource: String) : External() {
    override fun create(folder: File) {
        val path = if (resource.startsWith("/")) resource.substring(1) else resource
        val file = folder.resolve(path)
        file.parentFile.mkdirs()
        file.createNewFile()
    }

    override val fileName: String
        get() = resource.split("/").last()
}

data class ExternalFile(val file: File) : External() {
    override val fileName: String
        get() = file.name

    override fun create(folder: File) {
        file.createNewFile()
    }

}

fun Content.toExternal(): Iterable<External> = when (this) {
    is ExternalHtmlContent     -> listOf(externalHtml)
    is ExternalMarkdownContent -> listOf(externalMarkdown)
    is ExternalCodeContent     -> listOf(externalCode)
    is ExternalSvgContent      -> listOf(externalSvg)
    is ExternalImageContent    -> listOf(externalImage)
    is CompositeContent        -> contents.flatMap { it.toExternal() }
    else                       -> emptyList()
}