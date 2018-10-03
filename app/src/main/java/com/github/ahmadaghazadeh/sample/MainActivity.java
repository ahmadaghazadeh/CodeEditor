package com.github.ahmadaghazadeh.sample;

import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import com.github.ahmadaghazadeh.sample.BR;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        CodeModel codeModel=ViewModelProviders.of(this).get(CodeModel.class);
        ViewDataBinding mViewDataBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        mViewDataBinding.setVariable(BR.viewModel, codeModel);
        mViewDataBinding.setLifecycleOwner(this);
    }


}
