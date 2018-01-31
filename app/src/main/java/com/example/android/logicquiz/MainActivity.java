package com.example.android.logicquiz;

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
    ViewFlipper pageFlipper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        pageFlipper = (ViewFlipper) findViewById(R.id.main_flipper);
    }

    /**
     * Flip to the previos View
     * @param view
     */
    public void nextPage (View view) {

        currentPage++;
        // Hide the page number on last page
        if (currentPage==6) {
            hideNextButton();
            displayPoints(calculatePoints());
            //showresetButton();
        }
        pageFlipper.showNext();
    }

    /**
     *  Hide the next button on last page
     */
    private void hideNextButton() {
        Button nextButton = (Button) findViewById(R.id.next_button);
        nextButton.setVisibility(View.INVISIBLE);
    }
    private void displayPoints(int points) {
        TextView restulText = (TextView) findViewById(R.id.result_text);
        restulText.setText("" + points);
    }
    private int calculatePoints () {
        int points = 0;
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
}
