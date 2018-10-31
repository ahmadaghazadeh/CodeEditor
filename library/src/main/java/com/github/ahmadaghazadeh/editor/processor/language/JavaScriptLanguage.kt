package com.github.ahmadaghazadeh.editor.processor.language

import java.util.regex.Pattern

class JavaScriptLanguage : Language {

    override val syntaxNumbers: Pattern = LanguageCommons.SYNTAX_NUMBERS

    override val syntaxSymbols: Pattern = LanguageCommons.SYNTAX_SYMBOLS

    override val syntaxBrackets: Pattern = LanguageCommons.SYNTAX_BRACKETS

    override val syntaxKeywords: Pattern = SYNTAX_KEYWORDS

    override val syntaxMethods: Pattern = SYNTAX_METHODS

    override val syntaxStrings: Pattern = LanguageCommons.SYNTAX_STRINGS

    override val syntaxComments: Pattern = LanguageCommons.SYNTAX_C_STYLE_COMMENTS

    override val languageBrackets: CharArray = LanguageCommons.C_STYLE_LANGUAGE_BRACKETS

    override val allCompletions: Array<String> = ALL_KEYWORDS

    companion object {

        private val SYNTAX_KEYWORD_LIST = listOf("break", "continue", "else", "for", "function",
                "if", "in", "new", "this", "var", "while", "return", "case", "catch", "of",
                "typeof", "const", "default", "do", "class", "switch", "try", "null", "true", "false",
                "eval", "let")
                .joinToString("|") {"($it)"}

        private val SYNTAX_KEYWORDS = Pattern.compile("(?<=\\b)($SYNTAX_KEYWORD_LIST)(?=\\b)")

        private val SYNTAX_METHODS = Pattern.compile("(?<=(function) )(\\w+)", Pattern.CASE_INSENSITIVE)

        // -------------------------------- Autocomplete suggestions --------------------------------

        private val ARRAY_METHODS = arrayOf("concat()", "indexOf()", "join()", "lastIndexOf()",
                "pop()", "push()", "reverse()", "shift()", "slice()", "sort()", "splice()", "unshift()")
        private val OUTPUT_METHODS = arrayOf("console.log()", "alert()", "confirm()", "document.write()", "prompt()")
        private val GLOBAL_METHODS = arrayOf("decodeURI()", "decodeURIComponent()", "encodeURI()",
                "encodeURIComponent", "eval()", "isFinite()", "isNaN()", "Number()", "parseFloat()", "parseInt()")

        private val ALL_KEYWORDS = arrayOf(ARRAY_METHODS, OUTPUT_METHODS, GLOBAL_METHODS).flatten().toTypedArray()
    }

}
