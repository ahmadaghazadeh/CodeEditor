package com.github.ahmadaghazadeh.editor.processor.language

/**
 * Enumeration of supported languages. The CodeEditor will receive such a enum and resolves it
 * via the [LanguageProvider] to a [Language] instance.
 */
enum class SupportedLanguage {
    CSS, JS_LEGACY, HTML,       // <-- These were already defined by the library
    JAVASCRIPT, KOTLIN, SWIFT   // <-- These are our own language definitions
}