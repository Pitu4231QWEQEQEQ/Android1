package stu.cn.ua.lab1.Activity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.format.DateUtils;
import android.util.Log;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.Calendar;

import stu.cn.ua.lab1.R;
import stu.cn.ua.lab1.model.Player;

public class OptionsActivity extends AppCompatActivity {
    private static final String TAG =
            OptionsActivity.class.getSimpleName();

    public static final String ARG_PLAYER = "PLAYER";
    public static final String EXTRA_PLAYER = "PLAYER";

    private EditText firstNameEditText;
    private EditText lastNameEditText;
    private TextView bithdayTextView;
    private RadioGroup genderRadioGroup;
    private String gender;

    Calendar dateAndTime=Calendar.getInstance();
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_options);

        firstNameEditText = findViewById(R.id.firstNameEditText);
        lastNameEditText = findViewById(R.id.lastNameEditText);
        bithdayTextView = findViewById(R.id.dateTextView);
        genderRadioGroup = findViewById(R.id.genderRadioGroup);

        findViewById(R.id.selectDateButton).setOnClickListener(v -> {
            new DatePickerDialog(this, d,
                    dateAndTime.get(Calendar.YEAR),
                    dateAndTime.get(Calendar.MONTH),
                    dateAndTime.get(Calendar.DAY_OF_MONTH))
                    .show();
        });

        Player player = (Player)getIntent().getParcelableExtra(ARG_PLAYER);
        if(player != null){
            firstNameEditText.setText(player.getFirstName());
            lastNameEditText.setText(player.getLastName());
            bithdayTextView.setText(player.getBirthday());
            RadioButton maleRadioButton = findViewById(R.id.radio_male);
            RadioButton femaleRadioButton = findViewById(R.id.radio_female);
            if(player.getGender().equals("Male")){
                maleRadioButton.setChecked(true);
            } else {
                femaleRadioButton.setChecked(true);
            }
        }

        findViewById(R.id.doneButton).setOnClickListener(v -> {
            createNewPlayer();
        });

        findViewById(R.id.cancelButton).setOnClickListener(v -> {
            finish();
        });

    }

    private void createNewPlayer(){
        switch (genderRadioGroup.getCheckedRadioButtonId()){
            case R.id.radio_male:
                gender = "Male";
                break;
            case R.id.radio_female:
                gender = "Female";
                break;
        }
        Player player = new Player(
                firstNameEditText.getText().toString(),
                lastNameEditText.getText().toString(),
                bithdayTextView.getText().toString(),
                gender
        );

        Intent intent = new Intent();
        intent.putExtra(EXTRA_PLAYER, player);
        setResult(RESULT_OK, intent);
        finish();
    }


    DatePickerDialog.OnDateSetListener d=new DatePickerDialog.OnDateSetListener() {
        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
            dateAndTime.set(Calendar.YEAR, year);
            dateAndTime.set(Calendar.MONTH, monthOfYear);
            dateAndTime.set(Calendar.DAY_OF_MONTH, dayOfMonth);
            setInitialDateTime();
        }
    };

    private void setInitialDateTime() {

        bithdayTextView.setText(DateUtils.formatDateTime(this,
                dateAndTime.getTimeInMillis(),
                DateUtils.FORMAT_SHOW_DATE | DateUtils.FORMAT_SHOW_YEAR));
    }
}
