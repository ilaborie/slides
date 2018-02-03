package org.ilaborie.slides.dsl


import org.ilaborie.slides.content.*
import org.ilaborie.slides.content.web.*
import java.util.*

typealias IContentBuilder = () -> Content

sealed class IContentContainer {
    abstract operator fun invoke(): Content
}

class ContentContainer : IContentContainer() {
    private var builders = listOf<IContentBuilder>()

    fun add(builder: ContentContainer.() -> Unit, f: (Content) -> Content) {
        val container = ContentContainer()
        builder(container)
        builders += { f(container()) }
    }

    fun add(content: IContentBuilder) {
        builders += content
    }

    fun addList(builder: ListContentContainer.() -> Unit, f: (List<Content>) -> Content) {
        val listContainer = ListContentContainer(f)
        builder(listContainer)
        builders += { listContainer() }
    }

    fun addMap(builder: MapContentContainer.() -> Unit, f: (Map<Content, Content>) -> Content) {
        val mapContainer = MapContentContainer(f)
        builder(mapContainer)
        builders += { mapContainer() }
    }

    operator fun plus(s: String) {
        builders += { HtmlContent(s) }
    }

    override operator fun invoke(): Content =
        builders.fold(EmptyContent as Content) { acc, builder ->
            acc + builder()
        }
}

class ListContentContainer(private val f: (List<Content>) -> Content) : IContentContainer() {
    private var builders = listOf<IContentBuilder>()

    override operator fun invoke(): Content =
        f(builders.map { it() })
}

class MapContentContainer(private val f: (Map<Content, Content>) -> Content) : IContentContainer() {
    private var builders = mapOf<IContentBuilder, IContentBuilder>()
    override operator fun invoke(): Content =
        f(builders
              .map { (key, value) -> key() to value() }
              .toMap())
}


/**
 * Content
 */
// Code
fun ContentContainer.code(language: Language = Language.None, codeBuilder: () -> String) {
    add { Code(language = language, code = codeBuilder()) }
}

fun ContentContainer.codeFromResource(resource: String) {
    add {
        ExternalCodeContent(
            language = Language.findForExtension(resource) ?: Language.None,
            externalCode = ExternalResource(resource)
        )
    }
}

fun ContentContainer.htmlFromResource(resource: String) {
    add {
        ExternalHtmlContent(externalHtml = ExternalResource(resource))
    }
}


// Code
fun ContentContainer.codeEditorFromResources(
        title: String,
        initialResource: String,
        finalResource: String,
        actions: List<CodeEditorAction> = CodeEditorAction.defaultActions) {
    add {
        ExternalCodeEditor(title, ExternalResource(initialResource), ExternalResource(finalResource), actions)
    }
}


// Image

fun ContentContainer.img(alt: String, resource: String, title: String? = null) {
    add {
        ExternalImageContent(
            alt = alt,
            title = title,
            externalImage = ExternalResource(resource)
        )
    }
}

fun ContentContainer.svg(resource: String) {
    add {
        ExternalSvgContent(externalSvg = ExternalResource(resource))
    }
}

fun ContentContainer.figure(title: String, link: String, copyright: String? = null) {
    add {
        Figure(
            title = HtmlContent(title),
            copyright = copyright?.let { HtmlContent(it) },
            externalImage = ExternalLink(link)
        )
    }
}

// Bloc
fun ContentContainer.notice(kind: NoticeKind = NoticeKind.Info, builder: ContentContainer.() -> Unit) {
    add(builder) {
        Notice(kind = kind, content = it)
    }
}

fun ContentContainer.block(builder: ContentContainer.() -> Unit) {
    add(builder) {
        Block(content = it)
    }
}

fun ContentContainer.header(level: Int, builder: ContentContainer.() -> Unit) {
    add(builder) {
        Title(title = it, level = level)
    }
}

fun ContentContainer.headerText(level: Int, builder: () -> String) {
    add {
        Title(title = HtmlContent(builder()), level = level)
    }
}

fun ContentContainer.p(builder: ContentContainer.() -> Unit) {
    add(builder) {
        Paragraph(content = it)
    }
}

fun ContentContainer.pText(builder: () -> String) {
    add {
        Paragraph(content = HtmlContent(builder()))
    }
}

fun ContentContainer.quote(author: String? = null, cite: String? = null, builder: ContentContainer.() -> Unit) {
    add(builder) {
        Quote(content = it, author = author, cite = cite)
    }
}

// inline

fun ContentContainer.html(builder: () -> String) {
    add {
        HtmlContent(builder())
    }
}

fun ContentContainer.em(builder: ContentContainer.() -> Unit) {
    add(builder) {
        Emphasis(content = it)
    }
}

fun ContentContainer.link(link: String, alt: String? = null, builder: ContentContainer.() -> Unit) {
    add(builder) {
        Link(content = it, link = link, alt = alt ?: it.renderAsString())
    }
}

fun ContentContainer.linkText(link: String, alt: String? = null, builder: () -> String) {
    add {
        val text = builder()
        Link(content = HtmlContent(text), link = link, alt = alt ?: text)
    }
}

// List

fun ContentContainer.ul(builder: ListContentContainer.() -> Unit) {
    addList(builder) {
        UnorderedList(it)
    }
}

fun ContentContainer.ol(builder: ListContentContainer.() -> Unit) {
    addList(builder) {
        OrderedList(it)
    }
}

fun ContentContainer.definitions(builder: MapContentContainer.() -> Unit) {
    addMap(builder) {
        Definitions(it)
    }
}


// CSS

fun ContentContainer.styleEditable(
        code: String,
        soluce: String = "$code-final"
) {
    add {
        StyleEditable(ExternalResource("$code.css"), ExternalResource("$soluce.css"))
    }
}

fun ContentContainer.editableZone(htmlResource: String) {
    add {
        EditableZone(ExternalHtmlContent(ExternalResource(htmlResource)))
    }
}

fun ContentContainer.cssLiveCode(
        code: String,
        soluce: String = "$code-final",
        playground: String = "$code.html"
) {
    styleEditable(code, soluce)
    editableZone(playground)
}

fun ContentContainer.cssCompatibility(
        country: String = Locale.getDefault().country,
        threshold: Number = 0.5f,
        features: List<String>
) {
    add {
        CssCompatibility(
            country = country,
            threshold = threshold,
            features = features
        )
    }
}


