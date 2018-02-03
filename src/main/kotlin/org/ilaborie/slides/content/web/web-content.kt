package org.ilaborie.slides.content.web

import kotlinx.serialization.json.JSON
import org.ilaborie.slides.content.*
import org.ilaborie.table.Table


data class StyleEditable(val initialCss: External, val finalCss: External? = null) : Content()

data class EditableZone(val content: Content) : Content()


data class CssCompatibility(
        val country: String,
        val threshold: Number,
        private val features: List<String>
) : Content() {
    private val result: CompatibilityStatusResult by lazy {
        val helperClient = createClient("http://localhost:5000/")
        val json = helperClient.compatibility(country, threshold.toString(), features.joinToString(separator = ","))
        try {
            JSON.parse<CompatibilityStatusResult>(json)
        } catch (e: Exception) {
            throw IllegalStateException("Cannot parse:\n$json", e)
        }
    }

    private fun getBrowser(key: String): Browser = result.browsers
        .find { it.key == key }
            ?: throw IllegalArgumentException("Browser $key not found")

    private fun getFeature(key: String): Feature = result.features
        .find { it.key == key }
            ?: throw IllegalArgumentException("Feature $key not found")

    val table: Table<Feature, Browser, Value> by lazy {
        val init = Table.sparse<Feature, Browser, Value>()
        result.values.fold(init) { table, value ->
            val feature = getFeature(value.feature)
            val browser = getBrowser(value.browser)
            table.add(feature, browser, value)
        }
    }
}

// Code Editor
sealed class CodeEditorAction(val key: String) {
    companion object {
        val allActions =
            listOf(ToggleFullScreen, ToggleConsole, ResetCode, LoadFinalCode, ClearConsole, FormatCode, RunCode)
        val defaultActions = listOf(ToggleFullScreen, ResetCode, LoadFinalCode, ClearConsole, FormatCode, RunCode)
    }
}

object ToggleFullScreen : CodeEditorAction("full-screen")
object ToggleConsole : CodeEditorAction("toggle-console")
object ResetCode : CodeEditorAction("reset")
object LoadFinalCode : CodeEditorAction("load-final")
object ClearConsole : CodeEditorAction("clear-console")
object FormatCode : CodeEditorAction("format")
object RunCode : CodeEditorAction("run")

data class CodeEditor(
        val title: String,
        val code: String,
        val finalCode: String,
        val language: Language = Language.None,
        val actions: List<CodeEditorAction> = CodeEditorAction.defaultActions) : Content() {

    val asCode by lazy {
        Code(finalCode, language)
    }
}

data class ExternalCodeEditor(
        private val title: String,
        private val externalCode: External,
        private val externalFinalCode: External,
        private val actions: List<CodeEditorAction> = CodeEditorAction.defaultActions) :
        Content() {

    val codeEditor by lazy {
        val language = Language.findForExtension(externalCode.fileName) ?: Language.None
        CodeEditor(title, externalCode.textContent, externalFinalCode.textContent, language, actions)
    }
}
