package com.example.android.logicquiz;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ViewFlipper;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    public void nextPage (View view) {
        ViewFlipper pageFlipper = (ViewFlipper) findViewById(R.id.main_flipper);
        pageFlipper.showNext();
    }
    public void previousPage (View view) {
        ViewFlipper pageFlipper = (ViewFlipper) findViewById(R.id.main_flipper);
        pageFlipper.showPrevious();
    }
}
