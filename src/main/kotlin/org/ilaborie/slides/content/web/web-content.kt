package org.ilaborie.slides.content.web

import kotlinx.serialization.json.JSON
import org.ilaborie.slides.content.Content
import org.ilaborie.slides.content.External
import org.ilaborie.slides.content.createClient
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