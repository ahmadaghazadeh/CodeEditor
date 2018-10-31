package com.github.ahmadaghazadeh.sample;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.github.ahmadaghazadeh.editor.processor.language.LanguageProvider;
import com.github.ahmadaghazadeh.editor.processor.language.SupportedLanguage;
import com.github.ahmadaghazadeh.editor.widget.CodeEditor;
import com.github.ahmadaghazadeh.sample.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private final String code = "// Initializing a class definition\n" +
            "class Hero {\n" +
            "    constructor(name, level) {\n" +
            "        this.name = name;\n" +
            "        this.level = level;\n" +
            "    }\n" +
            "}";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        CodeEditor editor = findViewById(R.id.editor);
        CodeModel codeModel = new CodeModel(code, SupportedLanguage.JAVASCRIPT);

        codeModel.code.observe(this, s -> editor.setText(s, 1));
        codeModel.lang.observe(this, lang -> editor.setLanguage(LanguageProvider.INSTANCE.of(lang)));
    }


}
