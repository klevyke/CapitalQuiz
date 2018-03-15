package com.example.android.logicquiz;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created on parcelabler.com
 */

public class QuizState implements Parcelable {
    int currentpage;
    int question1RadioCheckedID;
    int question2RadioCheckedID;
    boolean checkBox1Checked;
    boolean checkBox2Checked;
    boolean checkBox3Checked;
    boolean checkBox4Checked;
    boolean checkBox5Checked;
    boolean checkBox6Checked;
    boolean nextButtonVisible;
    String resultText;

    public QuizState() {}

    protected QuizState(Parcel in) {
        currentpage = in.readInt();
        question1RadioCheckedID = in.readInt();
        question2RadioCheckedID = in.readInt();
        checkBox1Checked = in.readByte() != 0x00;
        checkBox2Checked = in.readByte() != 0x00;
        checkBox3Checked = in.readByte() != 0x00;
        checkBox4Checked = in.readByte() != 0x00;
        checkBox5Checked = in.readByte() != 0x00;
        checkBox6Checked = in.readByte() != 0x00;
        nextButtonVisible = in.readByte() != 0x00;
        resultText = in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(currentpage);
        dest.writeInt(question1RadioCheckedID);
        dest.writeInt(question2RadioCheckedID);
        dest.writeByte((byte) (checkBox1Checked ? 0x01 : 0x00));
        dest.writeByte((byte) (checkBox2Checked ? 0x01 : 0x00));
        dest.writeByte((byte) (checkBox3Checked ? 0x01 : 0x00));
        dest.writeByte((byte) (checkBox4Checked ? 0x01 : 0x00));
        dest.writeByte((byte) (checkBox5Checked ? 0x01 : 0x00));
        dest.writeByte((byte) (checkBox6Checked ? 0x01 : 0x00));
        dest.writeByte((byte) (nextButtonVisible ? 0x01 : 0x00));
        dest.writeString(resultText);
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
