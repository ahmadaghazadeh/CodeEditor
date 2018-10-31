package com.github.ahmadaghazadeh.editor.processor.language

import java.util.regex.Pattern

/**
 * These values here hold true to more than 1 programming language
 */
object LanguageCommons {

    val SYNTAX_NUMBERS = Pattern.compile("(\\b(\\d*[.]?\\d+)\\b)")

    val SYNTAX_STRINGS = Pattern.compile("\"(.*?)\"|'(.*?)'")

    val C_STYLE_LANGUAGE_BRACKETS = charArrayOf('{', '[', '(', '}', ']', ')')

    val SYNTAX_BRACKETS = Pattern.compile("(\\(|\\)|\\{|\\}|\\[|\\])")

    val SYNTAX_SYMBOLS = Pattern.compile("(!|\\+|-|\\*|<|>|=|\\?|\\||:|%|&)")

    val SYNTAX_C_STYLE_COMMENTS = Pattern.compile("/\\*(?:.|[\\n\\r])*?\\*/|//.*")

    // TODO Add the Javadoc /** comment type
    val SYNTAX_JAVA_STYLE_COMMENTS = Pattern.compile("/\\*(?:.|[\\n\\r])*?\\*/|//.*")

}