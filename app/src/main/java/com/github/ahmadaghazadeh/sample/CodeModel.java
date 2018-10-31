package com.github.ahmadaghazadeh.sample;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import com.github.ahmadaghazadeh.editor.processor.language.SupportedLanguage;

public class CodeModel extends ViewModel {
    public MutableLiveData<String> code = new MutableLiveData<>();
    public MutableLiveData<SupportedLanguage> lang = new MutableLiveData<>();

    public CodeModel(String code, SupportedLanguage lang) {
        this.code.setValue(code);
        this.lang.setValue(lang);
    }
}
