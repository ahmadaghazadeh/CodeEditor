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

package com.github.ahmadaghazadeh.editor.manager;

import android.content.Context;
import android.graphics.Typeface;

import timber.log.Timber;

import java.util.HashMap;

public class TypefaceManager {

    /**
     * Основная работа со шрифтами.
     */

    public static String ROBOTO = "Roboto";
    public static String ROBOTO_LIGHT = "Roboto Light";
    public static String SOURCE_CODE_PRO = "Source Code Pro";
    public static String DROID_SANS_MONO = "Droid Sans Mono";

    private static HashMap<String, String> fontMap = new HashMap<>();

    static {
        fontMap.put(ROBOTO, "fonts/roboto.ttf");
        fontMap.put(ROBOTO_LIGHT, "fonts/roboto_light.ttf");
        fontMap.put(SOURCE_CODE_PRO, "fonts/source_code_pro.ttf");
        fontMap.put(DROID_SANS_MONO, "fonts/droid_sans_mono.ttf");
    }

    /**
     * Загрузка шрифта из assets.
     * @param context - контекст приложения, откуда будут загружаться шрифты.
     * @param fontType - шрифт для загрузки, существующий в fontMap.
     * @return - возвращает выбранный Typeface.
     */
    public static Typeface get(Context context, String fontType) {
        if (fontType.equals(DROID_SANS_MONO)) {
            return Typeface.MONOSPACE;
        }
        String file = fontMap.get(fontType);
        if (file == null) {
            return Typeface.MONOSPACE;
        }
        Typeface typeface = Typeface.createFromAsset(context.getAssets(), file);
        if (typeface == null) {
            Timber.d("typeface is null, use monospace");
            return Typeface.MONOSPACE;
        }
        return typeface;
    }
}
