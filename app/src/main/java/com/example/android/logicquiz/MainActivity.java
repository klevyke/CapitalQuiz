package com.example.android.logicquiz;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.ViewFlipper;

public class MainActivity extends AppCompatActivity {
    // Variable for page number
    int currentPage = 0;
    ViewFlipper pageFlipper;
    TextView pageNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        pageFlipper = (ViewFlipper) findViewById(R.id.main_flipper);
        pageNumber = (TextView) findViewById(R.id.page_number);
    }

    /**
     * Flip to the previos View
     * @param view
     */
    public void nextPage (View view) {
        currentPage++;
        // Show/hide the page number and Previous button on last page
        if (currentPage==5) {
            hidePageNumber();
            setNextButtonVisibility(false);
        } else if (currentPage==1) {
            setPreviousButtonVisibility(true);
            updatePageNumber();
        } else {
            updatePageNumber();
        }
        pageFlipper.showNext();
    }

    /**
     * Flip to the next View
     * @param view
     */
    public void previousPage (View view) {
        currentPage--;
        // Hide the page number and Previous button on first page
        if (currentPage==0) {
            hidePageNumber();
            setPreviousButtonVisibility(false);
        } else if (currentPage==4) {
            setNextButtonVisibility(true);
            updatePageNumber();
        } else {
            updatePageNumber();
        }
        pageFlipper.showPrevious();
    }

    /**
     *  Hide the page number on first and last page
     */
    private void hidePageNumber() {
        pageNumber.setVisibility(View.INVISIBLE);
    }

    /**
     *  Hide the previous button on first page
     */
    private void setPreviousButtonVisibility(boolean visible) {
        Button previousButton = (Button) findViewById(R.id.previous_button);
        if (visible) {
            previousButton.setVisibility(View.VISIBLE);
        } else {
            previousButton.setVisibility(View.INVISIBLE);
        }
    }

    /**
     *  Hide the next button on last page
     */
    private void setNextButtonVisibility(boolean visible) {
        Button nextButton = (Button) findViewById(R.id.next_button);
        if (visible) {
            nextButton.setVisibility(View.VISIBLE);
        } else {
            nextButton.setVisibility(View.INVISIBLE);
        }
    }

    /**
     *  Update the page number
     */
    private void updatePageNumber() {
        pageNumber.setText("Page: " + currentPage);
        pageNumber.setVisibility(View.VISIBLE);
    }
}
