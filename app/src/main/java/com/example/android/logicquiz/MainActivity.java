package com.example.android.logicquiz;

import android.content.res.Resources;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.ViewFlipper;

public class MainActivity extends AppCompatActivity {
    // Variable for page number
    int currentPage = 1;
    // variable to get the String resources
    android.content.res.Resources resources;
    // pageFlipper variable to flip pages on next button click
    ViewFlipper pageFlipper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        resources = getResources();
        pageFlipper = (ViewFlipper) findViewById(R.id.main_flipper);
    }

    /**
     * Flip to the next View in ViewFlipper
     * @param view
     */
    public void nextPage (View view) {
        // Increase the page number
        currentPage++;
        // Hide the page number on last page
        if (currentPage==6) {
            hideNextButton();
            displayPoints(calculatePoints());
        }
        // Go to next page
        pageFlipper.showNext();
    }

    /**
     *  Hide the next button on last page
     */
    private void hideNextButton() {
        Button nextButton = (Button) findViewById(R.id.next_button);
        nextButton.setVisibility(View.INVISIBLE);
    }

    /**
     * Display the result of the quiz
     * @param points number of points
     */
    private void displayPoints(int points) {
        // Get the users name
        EditText nameEditText = (EditText) findViewById(R.id.name);
        String name = nameEditText.getText().toString();

        // Declare the Sring variable returned
        String resultText = resources.getString(R.string.result, name, "" + points);

        // Display message based on result
        if (points >= 90) {
            resultText += resources.getString(R.string.good_job);
        } else if (points>=50){
            resultText += resources.getString(R.string.nice);
        } else {
            resultText += resources.getString(R.string.try_again);
        }

        // Set the result's TextView
        TextView restulTextView = (TextView) findViewById(R.id.result_text);
        restulTextView.setText(resultText);
    }

    /**
     * Calculate the result of the quiz
     * @return number of points
     */
    private int calculatePoints () {
        int points = 10;
        // Check the first answer
        RadioGroup question1RadioGroup = (RadioGroup) findViewById(R.id.question1_radio);
        int radioButtonID = question1RadioGroup.getCheckedRadioButtonId();
        View radioButton = question1RadioGroup.findViewById(radioButtonID);
        int index = question1RadioGroup.indexOfChild(radioButton);

        if (index == 2) {
            points += 20;
        }
        // Check the second answer
        RadioGroup question2RadioGroup = (RadioGroup) findViewById(R.id.question2_radio);
        radioButtonID = question2RadioGroup.getCheckedRadioButtonId();
        radioButton = question2RadioGroup.findViewById(radioButtonID);
        index = question2RadioGroup.indexOfChild(radioButton);

        if (index == 1) {
            points += 20;
        }
        // Check the third answers enered value
        EditText question3EditText = (EditText) findViewById(R.id.danube_capitals);
        String answer3 = question3EditText.getText().toString();

        if (answer3.equals("4")) {
            points += 20;
        }

        // Check the fourth question's checkboxes (+10 point for every correct answer, -10 for every wrong answer)
        CheckBox question4Checkbox1 = (CheckBox) findViewById(R.id.question4_checkbox1);
        if (question4Checkbox1.isChecked()) {
            points -= 10;
        }
        CheckBox question4Checkbox2 = (CheckBox) findViewById(R.id.question4_checkbox2);
        if (question4Checkbox2.isChecked()) {
            points += 10;
        }
        CheckBox question4Checkbox3 = (CheckBox) findViewById(R.id.question4_checkbox3);
        if (question4Checkbox3.isChecked()) {
            points -= 10;
        }
        CheckBox question4Checkbox4 = (CheckBox) findViewById(R.id.question4_checkbox4);
        if (question4Checkbox4.isChecked()) {
            points += 10;
        }
        CheckBox question4Checkbox5 = (CheckBox) findViewById(R.id.question4_checkbox5);
        if (question4Checkbox5.isChecked()) {
            points += 10;
        }
        CheckBox question4Checkbox6 = (CheckBox) findViewById(R.id.question4_checkbox6);
        if (question4Checkbox6.isChecked()) {
            points -= 10;
        }

        return points;
    }
    // Reset the quiz
    public void resetQuiz(View view) {
        setContentView(R.layout.activity_main);
        pageFlipper = (ViewFlipper) findViewById(R.id.main_flipper);
        currentPage = 1;
    }
}