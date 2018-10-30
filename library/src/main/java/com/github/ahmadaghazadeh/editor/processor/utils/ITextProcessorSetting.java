package com.github.ahmadaghazadeh.editor.processor.utils;


public interface ITextProcessorSetting {

    public void setSyntaxHighlight(boolean syntaxHighlight) ;

    public boolean getSyntaxHighlight();

    public int getMaxTabsCount();

    public boolean getImeKeyboard();

    public String getCurrentTypeface();

    public int getFontSize();

    public boolean getWrapContent();

    public boolean getShowLineNumbers();

    public boolean getBracketMatching();

    public boolean getHighlightCurrentLine() ;

    public boolean getCodeCompletion();

    public boolean getIndentLine();

    public boolean getInsertBracket();

    public boolean getExtendedKeyboard();
}
