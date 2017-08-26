package org.ilaborie.slides

import java.text.DecimalFormat


fun Int.format(pattern: String): String = DecimalFormat(pattern).format(this)

