package com.example.moodmanagementapp;

import androidx.appcompat.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


public class MonthlyInsights extends Fragment {
    View view;
    private ImageButton editMoodLog;
    private ImageButton fakeEditMoodLog;
    private CalendarView calendar;
    private EditText mood;
    private EditText reason;
    private ImageButton addMood;
    private String moodInput;
    private String reasonInput;
    private TextView moodLog;
    private TextView fakeMoodLog;
    private Long date;
    private Date currentDate;


    public MonthlyInsights() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_monthly_insights, container, false);

        currentDate = new Date();
        moodLog = view.findViewById(R.id.mood_log_text);
        fakeMoodLog = view.findViewById(R.id.mood_log_text2);
        addMood = view.findViewById(R.id.btn_add_mood);
        calendar = view.findViewById(R.id.calender);
        date = calendar.getDate(); // to get the current date.
        fakeEditMoodLog = view.findViewById(R.id.btn_fake_edit_mood);
        editMoodLog = view.findViewById(R.id.btn_edit_mood2);

        editMoodLog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                LayoutInflater inflater1 = requireActivity().getLayoutInflater();
                View dialogView = inflater1.inflate(R.layout.dialog_moodlog,null);

                builder.setView(dialogView)
                        .setPositiveButton("Done", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                mood = (EditText) dialogView.findViewById(R.id.mood_edit);
                                reason= (EditText) dialogView.findViewById(R.id.reason_edit);

                                moodInput = mood.getText().toString();
                                reasonInput = reason.getText().toString();

                                moodLog.setText("9:00 am\nMood: "+moodInput+" (3"+")"+"\nReason: "+ reasonInput);

                            }
                        })
                        .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                dialogInterface.cancel();

                            }
                        });
                builder.show();
            }
        });
        addMood.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                LayoutInflater inflater2 = requireActivity().getLayoutInflater();
                View dialogView = inflater2.inflate(R.layout.dialog_moodlog,null);

                builder.setView(dialogView)
                        .setPositiveButton("Done", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                mood = (EditText) dialogView.findViewById(R.id.mood_edit);
                                reason= (EditText) dialogView.findViewById(R.id.reason_edit);

                                moodInput = mood.getText().toString();
                                reasonInput = reason.getText().toString();

                                String time = getTime();

                                moodLog.setText(time+"\nMood: "+moodInput+" (3"+")"+"\nReason: "+ reasonInput);
                                editMoodLog.setVisibility(View.VISIBLE);

                            }
                        })
                        .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                dialogInterface.cancel();

                            }
                        });
                builder.show();

            }
        });
        calendar.setOnDateChangeListener(new CalendarView.OnDateChangeListener(){

            @Override
            public void onSelectedDayChange(@NonNull CalendarView calendarView, int i, int i1, int i2) {
                String selectedDate =i2+"/"+(i1+1)+"/"+i;
                Date strDate;
                SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                try {
                     strDate = sdf.parse(selectedDate);

                    if(currentDate.getMonth() != strDate.getMonth() || currentDate.getDate() != strDate.getDate() ){
                        // Clear the mood log and allow user to add log.
                        moodLog.setText("");
                        fakeMoodLog.setText("");
                        // Remove the edit buttons
                        editMoodLog.setVisibility(View.INVISIBLE);
                        fakeEditMoodLog.setVisibility(View.INVISIBLE);

                    }else{
                        // Restore everything
                        fakeEditMoodLog.setVisibility(View.VISIBLE);
                        editMoodLog.setVisibility(View.VISIBLE);
                        moodLog.setText(R.string.moodLogOne);
                        fakeMoodLog.setText(R.string.fakeMoodLogOne);
                    }
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }
        });
        // Inflate the layout for this fragment
        return view;
    }

    public String getTime(){
        long date = System.currentTimeMillis();
        SimpleDateFormat time_format = new SimpleDateFormat("h:mm a");
        String time_string = time_format.format(date);
        return time_string;
    }
}
