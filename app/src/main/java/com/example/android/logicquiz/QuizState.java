package com.example.android.logicquiz;

import android.os.Parcel;
import android.os.Parcelable;
import android.widget.RadioGroup;

/**
 * Created by Levy on 01.02.2018.
 */

public class QuizState implements Parcelable {
    int currentpage;
    int question1RadioCheckedIndex;
    int question2RadioCheckedIndex;

    public QuizState() {}

    protected QuizState(Parcel in) {
        currentpage = in.readInt();
        question1RadioCheckedIndex = in.readInt();
        question2RadioCheckedIndex = in.readInt();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(currentpage);
        dest.writeValue(question1RadioCheckedIndex);
        dest.writeValue(question2RadioCheckedIndex);
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<QuizState> CREATOR = new Parcelable.Creator<QuizState>() {
        @Override
        public QuizState createFromParcel(Parcel in) {
            return new QuizState(in);
        }

        @Override
        public QuizState[] newArray(int size) {
            return new QuizState[size];
        }
    };
}
