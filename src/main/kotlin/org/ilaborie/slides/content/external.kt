package org.ilaborie.slides.content

import org.ilaborie.slides.catchWithDefault
import org.ilaborie.slides.logger
import java.io.File
import java.nio.charset.Charset


// External
sealed class External {
    abstract val fileName: String

    fun link(): String = when (this) {
        is ExternalResource -> resource
        is ExternalFile     -> file.toString()
        is ExternalLink     -> url
    }

    // FIXME datauri

    fun loadTextContent(charset: Charset = Charsets.UTF_8): String =
            when (this) {
                is ExternalResource ->
                    catchWithDefault("No resource: $resource") {
                        val input = this::class.java.getResourceAsStream(this.resource)
                        if (input == null) {
                            logger.error { "No resources $resource" }
                        }
                        input.reader(charset).readText()
                    }
                is ExternalFile     ->
                    catchWithDefault("No file: $file") {
                        this.file.readText(charset)
                    }
                else                -> TODO()
            }

    fun exists(): Boolean =
            when (this) {
                is ExternalResource ->
                    this::class.java.getResourceAsStream(this.resource) != null
                is ExternalFile     ->
                    file.exists()
                is ExternalLink     -> true
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

data class ExternalLink(val url: String) : External() {
    override val fileName: String
        get() = TODO("not implemented")

    override fun create(folder: File) {
        TODO("not implemented")
    }
}

fun Content.toExternal(): Iterable<External> = when (this) {
    EmptyContent               -> emptyList()
    is ExternalHtmlContent     -> listOf(externalHtml)
    is ExternalMarkdownContent -> listOf(externalMarkdown)
    is ExternalCodeContent     -> listOf(externalCode)
    is ExternalSvgContent      -> listOf(externalSvg)
    is ExternalImageContent    -> listOf(externalImage)
    is StyleEditable           -> if (finalCss != null) listOf(initialCss, finalCss) else listOf(initialCss)
    is CompositeContent        -> contents.flatMap { it.toExternal() }
    is RawContent              -> emptyList()
    is HtmlContent             -> emptyList()
    is SvgContent              -> emptyList()
    is MarkdownContent         -> emptyList()
    is Code                    -> emptyList()
    is Title                   -> title.toExternal()
    is Link                    -> content.toExternal()
    is EditableZone            -> content.toExternal()
    is Definitions             -> map.toList()
            .flatMap { (key, value) -> listOf(key, value) }
            .flatMap { it.toExternal() }
    is OrderedList             -> contents.flatMap { it.toExternal() }
    is UnorderedList           -> contents.flatMap { it.toExternal() }
    is Figure                  -> title.toExternal() + externalImage + (copyright?.toExternal() ?: emptyList())
    is Paragraph               -> content.toExternal()
    is Quote                   -> content.toExternal()
    is Strong                  -> content.toExternal()
    is Emphasis                -> content.toExternal()
    is Block                   -> content.toExternal()
}