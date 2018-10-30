package com.github.ahmadaghazadeh.sample;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

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
        CodeModel codeModel = new CodeModel(code, "js");
        ActivityMainBinding mViewDataBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        mViewDataBinding.setVariable(BR.viewModel, codeModel);
        mViewDataBinding.setLifecycleOwner(this);
    }


}
