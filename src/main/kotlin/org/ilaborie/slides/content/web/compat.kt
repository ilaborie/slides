package org.ilaborie.slides.content.web

import kotlinx.serialization.Optional
import kotlinx.serialization.Serializable

@Serializable
data class Browser(val key: String, val usage: Float, val mobile: Boolean, val versions: List<String>)

@Serializable
data class Feature(
    val key: String,
    val title: String,
    val description: String,
    val spec: String,
    val notes_by_num: Map<String, String>
)

@Serializable
data class Value(val browser: String, val feature: String, val stats: List<Stat>)

@Serializable
data class CompatibilityStatusResult(
    val total: Float,
    val browsers: List<Browser>,
    val features: List<Feature>,
    val values: List<Value>
)


@Serializable
data class Stat(val version: String, @Optional val status: String = "n") {
    fun compatibility(feature: Feature) = CompatibilityStatus.fromString(status, feature)
}

sealed class CompatibilityStatus {
    companion object {
        fun fromString(s: String?, feature: Feature): CompatibilityStatus = when {
            s == null             -> NotAvailable
            s == "n"              -> NotAvailable
            s == "p"              -> NotAvailable
            s == "y"              -> Available
            s.startsWith("a #")   -> Partial(feature.notes_by_num[s.substring(3)] ?: "")
            s.startsWith("a x #") -> Prefix(feature.notes_by_num[s.substring(3)] ?: "")
            s.startsWith("y x")   -> Prefix(feature.notes_by_num[s.substring(3)] ?: "")
            s.startsWith("p d #") -> Flag(feature.notes_by_num[s.substring(3)] ?: "")
            s.startsWith("n d #") -> Flag(feature.notes_by_num[s.substring(3)] ?: "")
            s.startsWith("y #")   -> Partial(feature.notes_by_num[s.substring(3)] ?: "")
            else                  -> NotAvailable
        }
    }
}

object NotAvailable : CompatibilityStatus()
object Available : CompatibilityStatus()
class Partial(val info: String) : CompatibilityStatus()
class Prefix(val info: String) : CompatibilityStatus()
class Flag(val info: String) : CompatibilityStatus()
class Buggy(val info: String) : CompatibilityStatus()
