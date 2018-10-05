package com.github.ahmadaghazadeh.sample;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.github.ahmadaghazadeh.editor.widget.CodeEditor;
import com.github.ahmadaghazadeh.sample.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        CodeModel codeModel = new CodeModel("<html>", "html");
        ActivityMainBinding mViewDataBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        mViewDataBinding.setVariable(BR.viewModel, codeModel);
        mViewDataBinding.setLifecycleOwner(this);
        mViewDataBinding.editor.setOnTextChange(str -> {
            Toast.makeText(this,str,Toast.LENGTH_LONG).show();
        });

    }


}
