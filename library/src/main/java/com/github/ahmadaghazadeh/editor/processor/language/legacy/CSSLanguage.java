/*
 * Copyright (C) 2018 Light Team Software
 *
 * This file is part of ModPE IDE.
 *
 * ModPE IDE is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * ModPE IDE is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package com.github.ahmadaghazadeh.editor.processor.language.legacy;

import com.github.ahmadaghazadeh.editor.processor.language.Language;
import com.github.ahmadaghazadeh.editor.processor.utils.text.ArrayUtils;

import java.util.regex.Pattern;

public class CSSLanguage implements Language {


    private static final Pattern SYNTAX_NUMBERS = Pattern.compile("(\\b(\\d*[.]?\\d+)\\b)");
    private static final Pattern SYNTAX_SYMBOLS = Pattern.compile(
             "(!|\\+|-|\\*|<|>|=|\\?|\\||:|%|&)");
    private static final Pattern SYNTAX_BRACKETS = Pattern.compile("(\\(|\\)|\\{|\\}|\\[|\\])");
    private static final Pattern SYNTAX_KEYWORDS = Pattern.compile("([^\\r\\n,{}]+)(,(?=[^}]*\\{)|\\s*(?=\\{))"); //Слова без CASE_INSENSITIVE
    private static final Pattern SYNTAX_METHODS = Pattern.compile(
            "(?<=(function) )(\\w+)", Pattern.CASE_INSENSITIVE);
    private static final Pattern SYNTAX_STRINGS = Pattern.compile("\"(.*?)\"|'(.*?)'");
    private static final Pattern SYNTAX_COMMENTS = Pattern.compile("/\\*(?:.|[\\n\\r])*?\\*/|//.*");
    private static final char[] LANGUAGE_BRACKETS = new char[]{'{', '[', '(', '}', ']', ')'}; //do not change

    /**
     * Слова для автопродолжения кода.
     */

    private static final String[] CSS_KEYWORDS = new String[]{
            ":active",
            ":after",
            ":before",
            ":first",
            ":first-child",
            ":first-letter",
            ":first-line",
            ":focus",
            ":hover",
            ":lang",
            ":left",
            ":link",
            ":right",
            ":visited",
            "@charset",
            "@font-face",
            "@import",
            "@media",
            "@page",
            "above",
            "absolute",
            "ActiveBorder",
            "ActiveCaption",
            "always",
            "AppWorkspace",
            "aqua",
            "armenian",
            "attr",
            "auto",
            "avoid",
            "azimuth",
            "background",
            "background-attachment",
            "background-color",
            "background-image",
            "background-position",
            "background-repeat",
            "baseline",
            "behind",
            "below",
            "bidi-override",
            "black",
            "blink",
            "block",
            "blue",
            "bold",
            "bolder",
            "border",
            "border-bottom",
            "border-bottom-color",
            "border-bottom-style",
            "border-bottom-width",
            "border-collapse",
            "border-color",
            "border-left",
            "border-left-color",
            "border-left-style",
            "border-left-width",
            "border-right",
            "border-right-color",
            "border-right-style",
            "border-right-width",
            "border-spacing",
            "border-style",
            "border-top",
            "border-top-color",
            "border-top-style",
            "border-top-width",
            "border-width",
            "both",
            "bottom",
            "ButtonFace",
            "ButtonHighlight",
            "ButtonShadow",
            "ButtonText",
            "capitalize",
            "caption",
            "caption-side",
            "CaptionText",
            "center",
            "center-left",
            "center-right",
            "circle",
            "cjk-ideographic",
            "clear",
            "clip",
            "close-quote",
            "code",
            "collapse",
            "color",
            "compact",
            "condensed",
            "content",
            "continuous",
            "counter-increment",
            "counter-reset",
            "crop",
            "cros",
            "crosshair",
            "cue",
            "cue-after",
            "cue-before",
            "cursor",
            "decimal",
            "decimal-leading-zero",
            "default",
            "deg",
            "digits",
            "direction",
            "disc",
            "display",
            "e-resize",
            "elevation",
            "em",
            "embed",
            "empty-cells",
            "ex",
            "expanded",
            "extra-condensed",
            "extra-expanded",
            "far-left",
            "far-right",
            "fast",
            "faster",
            "fixed",
            "float",
            "font",
            "font-family",
            "font-size",
            "font-size-adjust",
            "font-stretch",
            "font-style",
            "font-variant",
            "font-weight",
            "fuchsia",
            "georgian",
            "grad",
            "gray",
            "GrayText",
            "green",
            "hebrew",
            "height",
            "help",
            "hidden",
            "hide",
            "high",
            "higher",
            "Highlight",
            "HighlightText",
            "hiragana",
            "hiragana-iroha",
            "hz",
            "icon",
            "InactiveBorder",
            "InactiveCaption",
            "InactiveCaptionText",
            "InfoBackground",
            "InfoText",
            "inherit",
            "inline",
            "inline-table",
            "inside",
            "italic",
            "justify",
            "katakana",
            "katakana-iroha",
            "khz",
            "landscape",
            "left",
            "left-side",
            "leftwards",
            "letter-spacing",
            "level",
            "lighter",
            "lime",
            "line-height",
            "line-through",
            "list-item",
            "list-style",
            "list-style-image",
            "list-style-position",
            "list-style-type",
            "loud",
            "low",
            "lower",
            "lower-alpha",
            "lower-greek",
            "lower-latin",
            "lower-roman",
            "lowercase",
            "ltr",
            "margin",
            "margin-bottom",
            "margin-left",
            "margin-right",
            "margin-top",
            "marker",
            "marker-offset",
            "marks",
            "maroon",
            "max-height",
            "max-width",
            "medium",
            "menu",
            "MenuText",
            "message-box",
            "middle",
            "min-height",
            "min-width",
            "mix",
            "move",
            "ms",
            "n-resize",
            "narrower",
            "navy",
            "ne-resize",
            "no-close-quote",
            "no-open-quote",
            "no-repeat",
            "none",
            "normal",
            "nowrap",
            "nw-resize",
            "oblique",
            "olive",
            "once",
            "open-quote",
            "orphans",
            "outline",
            "outline-color",
            "outline-style",
            "outline-width",
            "outside",
            "overflow",
            "overline",
            "padding",
            "padding-bottom",
            "padding-left",
            "padding-right",
            "padding-top",
            "page",
            "page-break-after",
            "page-break-before",
            "page-break-inside",
            "pause",
            "pause-after",
            "pause-before",
            "pitch",
            "pitch-range",
            "play-during",
            "pointer",
            "portrait",
            "position",
            "pre",
            "pt",
            "purple",
            "px",
            "quotes",
            "rad",
            "red",
            "relative",
            "repeat",
            "repeat-x",
            "repeat-y",
            "richness",
            "right",
            "right-side",
            "rightwards",
            "rtl",
            "run-in",
            "s-resize",
            "scroll",
            "Scrollbar",
            "se-resize",
            "semi-condensed",
            "semi-expanded",
            "separate",
            "show",
            "silent",
            "silver",
            "size",
            "slow",
            "slower",
            "small-caps",
            "small-caption",
            "soft",
            "speak",
            "speak-header",
            "speak-numeral",
            "speak-ponctuation",
            "speech-rate",
            "spell-out",
            "square",
            "static",
            "status-bar",
            "stress",
            "sub",
            "super",
            "sw-resize",
            "table",
            "table-caption",
            "table-cell",
            "table-column",
            "table-column-group",
            "table-footer-group",
            "table-header-group",
            "table-layout",
            "table-row",
            "table-row-group",
            "teal",
            "text",
            "text-align",
            "text-bottom",
            "text-decoration",
            "text-indent",
            "text-shadow",
            "text-top",
            "text-transform",
            "ThreeDDarkShadow",
            "ThreeDFace",
            "ThreeDHighlight",
            "ThreeDLightShadow",
            "ThreeDShadow",
            "top",
            "transparent",
            "ultra-condensed",
            "ultra-expanded",
            "underline",
            "unicode-bidi",
            "upper-alpha",
            "upper-latin",
            "upper-roman",
            "uppercase",
            "vertical-align",
            "visibility",
            "visible",
            "voice-family",
            "volume",
            "w-resize",
            "wait",
            "white",
            "white-space",
            "wider",
            "widows",
            "width",
            "Window",
            "WindowFrame",
            "WindowText",
            "word-spacing",
            "x-fast",
            "x-high",
            "x-loud",
            "x-low",
            "x-slow",
            "x-soft",
            "yellow",
            "z-index"
    };


    /**
     * Соединение всех массивов в один. Этот массив и будет использоваться для
     * получения слов в редакторе.
     */
    private static final String[] ALL_KEYWORDS = ArrayUtils.join(String.class,CSS_KEYWORDS);

    public final Pattern getSyntaxNumbers() {
        return SYNTAX_NUMBERS;
    }

    public final Pattern getSyntaxSymbols() {
        return SYNTAX_SYMBOLS;
    }

    public final Pattern getSyntaxBrackets() {
        return SYNTAX_BRACKETS;
    }

    public final Pattern getSyntaxKeywords() {
        return SYNTAX_KEYWORDS;
    }

    public final Pattern getSyntaxMethods() {
        return SYNTAX_METHODS;
    }

    public final Pattern getSyntaxStrings() {
        return SYNTAX_STRINGS;
    }

    public final Pattern getSyntaxComments() {
        return SYNTAX_COMMENTS;
    }

    public final char[] getLanguageBrackets() {
        return LANGUAGE_BRACKETS;
    }

    public final String[] getAllCompletions() {
        return ALL_KEYWORDS;
    }

}
