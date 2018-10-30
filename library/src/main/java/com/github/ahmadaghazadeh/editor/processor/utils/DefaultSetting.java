package com.github.ahmadaghazadeh.editor.processor.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

public class DefaultSetting implements ITextProcessorSetting {

    private SharedPreferences pref;

    public DefaultSetting(Context context) {
        pref = PreferenceManager.getDefaultSharedPreferences(context);
    }

    @Override
    public boolean getSyntaxHighlight() {
        return pref.getBoolean("SYNTAX_HIGHLIGHT", true);
    }

    @Override
    public void setSyntaxHighlight(boolean syntaxHighlight) {
        pref.edit().putBoolean("SYNTAX_HIGHLIGHT", syntaxHighlight).apply();
    }

    @Override
    public int getMaxTabsCount() {
        return 5; //pref.getInt...
    }

    @Override
    public boolean getImeKeyboard() {
        return pref.getBoolean("USE_IME_KEYBOARD", false);
    }

    @Override
    public String getCurrentTypeface() {
        return pref.getString("FONT_TYPE", "droid_sans_mono");
    }

    @Override
    public int getFontSize() {
        return pref.getInt("FONT_SIZE", 14); //default
    }

    @Override
    public boolean getWrapContent() {
        return pref.getBoolean("WRAP_CONTENT", true);
    }

    @Override
    public boolean getShowLineNumbers() {
        return pref.getBoolean("SHOW_LINE_NUMBERS", true);
    }

    @Override
    public boolean getBracketMatching() {
        return pref.getBoolean("BRACKET_MATCHING", true);
    }

    @Override
    public boolean getHighlightCurrentLine() {
        return pref.getBoolean("HIGHLIGHT_CURRENT_LINE", true);
    }

    @Override
    public boolean getCodeCompletion() {
        return pref.getBoolean("CODE_COMPLETION", true);
    }

    @Override
    public boolean getIndentLine() {
        return pref.getBoolean("INDENT_LINE", true);
    }

    @Override
    public boolean getInsertBracket() {
        return pref.getBoolean("INSERT_BRACKET", true);
    }

    @Override
    public boolean getExtendedKeyboard() {
        return pref.getBoolean("USE_EXTENDED_KEYS", false);
    }
}
