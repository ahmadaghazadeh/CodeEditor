package com.github.ahmadaghazadeh.sample;

import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.github.ahmadaghazadeh.editor.document.Document;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
//        Document doc=Document.newInstance("");
//        transaction.replace(R.id.fragment, doc);
//        transaction.addToBackStack(doc.getClass().getName());
//        transaction.commit();
    }
}
