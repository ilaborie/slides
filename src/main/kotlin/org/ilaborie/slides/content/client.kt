package org.ilaborie.slides.content

import feign.Feign
import feign.Param
import feign.RequestLine
import org.ilaborie.logger.Logger

interface HelperClient {

    @RequestLine("POST /code?lang={lang}")
    fun codeToHtml(@Param("lang") lang: String, code: String): String

    @RequestLine("GET /compatibility?country={country}&threshold={threshold}&features={features}")
    fun compatibility(@Param("country") country: String,
                      @Param("threshold") threshold: String,
                      @Param("features") features: String): String

    @RequestLine("POST /markdown")
    fun markdown(md: String): String


    @RequestLine("POST /pdf?from={from}&to={to}")
    fun pdf(@Param("from") from: String, @Param("to") to: String): String

}

val logger = Logger("HelperClient")
val cache = mutableMapOf<String, HelperClient>()
fun createClient(url: String): HelperClient =
    cache.getOrPut(url) {
        Feign.builder()
            .requestInterceptor { template -> logger.trace { template.toString() } }
            .target(HelperClient::class.java, url)
    }
