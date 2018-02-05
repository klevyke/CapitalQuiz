package com.example.android.logicquiz;

import android.content.res.Resources;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewFlipper;
import com.example.android.logicquiz.QuizState;


public class MainActivity extends AppCompatActivity {

    // variable to get the String resources
    android.content.res.Resources resources;
    // pageFlipper variable to flip pages on next button click
    ViewFlipper pageFlipper;
    RadioGroup question1RadioGroup;
    RadioGroup question2RadioGroup;
    CheckBox question4Checkbox1;
    CheckBox question4Checkbox2;
    CheckBox question4Checkbox3;
    CheckBox question4Checkbox4;
    CheckBox question4Checkbox5;
    CheckBox question4Checkbox6;
    Button nextButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        resources = getResources();
        pageFlipper = (ViewFlipper) findViewById(R.id.main_flipper);
        question1RadioGroup = (RadioGroup) findViewById(R.id.question1_radio);
        question2RadioGroup = (RadioGroup) findViewById(R.id.question2_radio);
        question4Checkbox1 = (CheckBox) findViewById(R.id.question4_checkbox1);
        question4Checkbox2 = (CheckBox) findViewById(R.id.question4_checkbox2);
        question4Checkbox3 = (CheckBox) findViewById(R.id.question4_checkbox3);
        question4Checkbox4 = (CheckBox) findViewById(R.id.question4_checkbox4);
        question4Checkbox5 = (CheckBox) findViewById(R.id.question4_checkbox5);
        question4Checkbox6 = (CheckBox) findViewById(R.id.question4_checkbox6);
        nextButton = (Button) findViewById(R.id.next_button);
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        // Declare the object
        QuizState stateOfQuiz = new QuizState();

        // Save the user’s current page
        stateOfQuiz.currentpage = pageFlipper.getDisplayedChild();

        // Save the 2 RadioButtons
        stateOfQuiz.question1RadioCheckedID = question1RadioGroup.getCheckedRadioButtonId();
        stateOfQuiz.question2RadioCheckedID = question2RadioGroup.getCheckedRadioButtonId();

        // set the quastion4's checkboxes
        if (question4Checkbox1.isChecked()) {
            stateOfQuiz.checkBox1Checked = true;
        }
        if (question4Checkbox2.isChecked()) {
            stateOfQuiz.checkBox2Checked = true;
        }
        if (question4Checkbox3.isChecked()) {
            stateOfQuiz.checkBox3Checked = true;
        }
        if (question4Checkbox4.isChecked()) {
            stateOfQuiz.checkBox4Checked = true;
        }
        if (question4Checkbox5.isChecked()) {
            stateOfQuiz.checkBox5Checked = true;
        }
        if (question4Checkbox6.isChecked()) {
            stateOfQuiz.checkBox6Checked = true;
        }
        // Check if next button is changed
        if (nextButton.getVisibility() == View.VISIBLE) {
            stateOfQuiz.nextButtonVisible = true;
        } else {
            stateOfQuiz.nextButtonVisible = false;
        }
        // Save the object
        savedInstanceState.putParcelable("savedState", stateOfQuiz);
        // Always call the superclass so it can save the view hierarchy state
        super.onSaveInstanceState(savedInstanceState);
    }

    public void onRestoreInstanceState(Bundle savedInstanceState) {
        // Restore state members from saved instance
        QuizState stateOfQuiz;
        stateOfQuiz = savedInstanceState.getParcelable("savedState");
        //set current page
        pageFlipper.setDisplayedChild(stateOfQuiz.currentpage);
        //set the radiobuttons
        question1RadioGroup.check(stateOfQuiz.question1RadioCheckedID);
        question2RadioGroup.check(stateOfQuiz.question2RadioCheckedID);
        //set the checkboxes
        question4Checkbox1.setChecked(stateOfQuiz.checkBox1Checked);
        question4Checkbox2.setChecked(stateOfQuiz.checkBox2Checked);
        question4Checkbox3.setChecked(stateOfQuiz.checkBox3Checked);
        question4Checkbox4.setChecked(stateOfQuiz.checkBox4Checked);
        question4Checkbox5.setChecked(stateOfQuiz.checkBox5Checked);
        question4Checkbox6.setChecked(stateOfQuiz.checkBox6Checked);
        if (!stateOfQuiz.nextButtonVisible) {
            hideNextButton();
        }
        // Always call the superclass so it can restore the view hierarchy
        super.onRestoreInstanceState(savedInstanceState);
    }
    /**
     * Flip to the next View in ViewFlipper
     * @param view
     */
    public void nextPage (View view) {
        if (isCompleted()) {
            // Variable for page number
            int currentPage = pageFlipper.getDisplayedChild();
            // Increase the page number
            if (currentPage == 3) {
                changeNextButtonText();
            }
            // Hide next button and display the result
            if (currentPage == 4) {
                hideNextButton();
                displayPoints(calculatePoints());
            }
            // Go to next page
            pageFlipper.showNext();
            // Scroll to top
            ScrollView scrollContainer = (ScrollView) findViewById(R.id.scroll_container);
            scrollContainer.fullScroll(ScrollView.FOCUS_UP);
        }
    }

    /**
     *  Hide the next button on last page
     */
    private void changeNextButtonText() {
        // Update the text of the button
        nextButton.setText(resources.getString(R.string.show_result));

        // Align to left of the layout
        RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams)nextButton.getLayoutParams();
        params.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
        params.removeRule(RelativeLayout.ALIGN_PARENT_RIGHT);
        params.setMargins(16,16,16,16);
        nextButton.setLayoutParams(params); //causes layout update
    }

    /**
     *  Hide the next button on last page
     */
    private void hideNextButton() {
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

        // Declare the String variable for text displayed
        String resultText = "";

        // Display message based on result
        if (points >= 90) {
            resultText += resources.getString(R.string.good_job, name);
        } else if (points>=50){
            resultText += resources.getString(R.string.nice, name);
        } else {
            resultText += resources.getString(R.string.try_again, name);
        }

        // Set the result's TextView
        TextView restulTextView = (TextView) findViewById(R.id.result_text);
        restulTextView.setText(resultText);

        // Declare the String variable for toast message and display it
        String resultToastText = resources.getString(R.string.result, "" + points);
        Toast.makeText(this, resultToastText, Toast.LENGTH_LONG).show();

    }

    /**
     * Calculate the result of the quiz
     * @return number of points
     */
    private int calculatePoints () {
        // We start form 10 points
        int points = 10;

        // Check the first answer
        question1RadioGroup = (RadioGroup) findViewById(R.id.question1_radio);
        int radioButtonID = question1RadioGroup.getCheckedRadioButtonId();
        View radioButton = question1RadioGroup.findViewById(radioButtonID);
        int index = question1RadioGroup.indexOfChild(radioButton);
        if (index == 2) {
            points += 20;
        }

        // Check the second answer
        question2RadioGroup = (RadioGroup) findViewById(R.id.question2_radio);
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
        if (question4Checkbox1.isChecked()) {
            points -= 10;
        }
        if (question4Checkbox2.isChecked()) {
            points += 10;
        }
        if (question4Checkbox3.isChecked()) {
            points -= 10;
        }
        if (question4Checkbox4.isChecked()) {
            points += 10;
        }
        if (question4Checkbox5.isChecked()) {
            points += 10;
        }
        if (question4Checkbox6.isChecked()) {
            points -= 10;
        }

        return points;
    }

    /**
     * Checks if the EditText field is completed or if one RadioButton of RadioGroup is selected
     * @return if the RadioGroup or the Edittext is completed
     */
    private boolean isCompleted() {
        // Get the current view (every child of the ViewFlipper is a LinearLayout)
        LinearLayout currentView = (LinearLayout) pageFlipper.getCurrentView();

        // The last element of the LinearLayout is the input element. Let's get it
        View currentInputChildView = currentView.getChildAt(currentView.getChildCount() - 1);

        // Check if it's a RadioGroup or an Edittext
        if (currentInputChildView instanceof RadioGroup) {
            // Cast the view to RadioGroup
            RadioGroup thisRadioGroup = (RadioGroup) currentInputChildView;
            // If teher is no RadioButton selected display a toast message and return false
            if (thisRadioGroup.getCheckedRadioButtonId() < 0) {
                Toast.makeText(this, resources.getString(R.string.must_check_something), Toast.LENGTH_LONG).show();
                return false;
            }
        }
        if (currentInputChildView instanceof EditText) {
            // Cast the view to EditText
            EditText thisEditText = (EditText) currentInputChildView;
            // If the lenght is less than 1 then display a toast message and return false
            if (thisEditText.getText().length() < 1) {
                Toast.makeText(this, resources.getString(R.string.must_enter_something), Toast.LENGTH_LONG).show();
                return false;
            }
        }
        // Return true if the type of the current view isn't EditText or RadioGroup
        return true;
    }
    /**
     * Reset the quiz
     * @view
     */
    public void resetQuiz(View view) {
        setContentView(R.layout.activity_main);
        pageFlipper = (ViewFlipper) findViewById(R.id.main_flipper);
        question1RadioGroup = (RadioGroup) findViewById(R.id.question1_radio);
        question2RadioGroup = (RadioGroup) findViewById(R.id.question2_radio);
        question4Checkbox1 = (CheckBox) findViewById(R.id.question4_checkbox1);
        question4Checkbox2 = (CheckBox) findViewById(R.id.question4_checkbox2);
        question4Checkbox3 = (CheckBox) findViewById(R.id.question4_checkbox3);
        question4Checkbox4 = (CheckBox) findViewById(R.id.question4_checkbox4);
        question4Checkbox5 = (CheckBox) findViewById(R.id.question4_checkbox5);
        question4Checkbox6 = (CheckBox) findViewById(R.id.question4_checkbox6);
        nextButton = (Button) findViewById(R.id.next_button);
    }
}