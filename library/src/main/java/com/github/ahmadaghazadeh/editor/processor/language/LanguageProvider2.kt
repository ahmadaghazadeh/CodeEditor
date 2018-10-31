package com.github.ahmadaghazadeh.editor.processor.language

import com.github.ahmadaghazadeh.editor.processor.language.legacy.CSSLanguage
import com.github.ahmadaghazadeh.editor.processor.language.legacy.HtmlLanguage
import com.github.ahmadaghazadeh.editor.processor.language.legacy.JSLanguage

/**
 * TODO Will be renamed to (and replace) 'LanguageProvider', for merging reasons it is kept that way
 */
object LanguageProvider2 {

    fun of(supportedLanguage: SupportedLanguage): Language {
        return when (supportedLanguage) {
            SupportedLanguage.CSS -> CSSLanguage()
            SupportedLanguage.JS_LEGACY -> JSLanguage()
            SupportedLanguage.HTML -> HtmlLanguage()
            SupportedLanguage.JAVASCRIPT -> JavaScriptLanguage()
            SupportedLanguage.KOTLIN -> KotlinLanguage()
            SupportedLanguage.SWIFT -> SwiftLanguage()
        }
    }

}