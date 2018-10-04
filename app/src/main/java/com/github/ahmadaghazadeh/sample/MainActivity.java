package com.github.ahmadaghazadeh.sample;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.github.ahmadaghazadeh.sample.BR;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        CodeModel codeModel=new CodeModel("<html>","html");
        ViewDataBinding mViewDataBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        mViewDataBinding.setVariable(BR.viewModel, codeModel);
        mViewDataBinding.setLifecycleOwner(this);


    }


}
