package com.example.moodmanagementapp;

import androidx.appcompat.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;

import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;


public class WeeklyInsights extends Fragment {
    View view;
    BarChart barChart;
    ArrayList<BarEntry> barEntryArrayList;
    ArrayList<String> labelNames;
    ArrayList<String> yAxisVals;
    ArrayList<MoodChartData> moodChartData;
    private TextView moodLog;
    private TextView fakeMoodLog;
    private ImageButton editMoodLog;
    private ImageButton fakeEditMoodLog;
    private ImageButton addMood;
    private EditText mood;
    private EditText reason;
    private String moodInput;
    private String reasonInput;

    public WeeklyInsights() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_weekly_insights, container, false);

        Bitmap bitmapGood = BitmapFactory.decodeResource(getResources(),R.drawable.happywhite);

        // Logs
        moodLog = view.findViewById(R.id.mood_log_text_w);
        fakeMoodLog = view.findViewById(R.id.mood_log_text2_w);

        // Buttons
        addMood = view.findViewById(R.id.btn_add_mood_w);
        fakeEditMoodLog = view.findViewById(R.id.btn_fake_edit_mood_w);
        editMoodLog = view.findViewById(R.id.btn_edit_mood2_w);
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

        // Bar chart
        barChart = (BarChart) view.findViewById(R.id.bar_chart);
        barEntryArrayList = new ArrayList<>();
        labelNames = new ArrayList<>();
        yAxisVals = new ArrayList<>(Arrays.asList("None","Bad", "Not Good", "Ok", "Good", "Great"));
        moodChartData = new ArrayList<>();
        moodChartData = fillMoodChart();

        for(int i=0; i < moodChartData.size();i++){
            String day = moodChartData.get(i).getDay();
            int mood = moodChartData.get(i).getMood();

            barEntryArrayList.add(new BarEntry(i,mood));
            labelNames.add(day);
        }


       MyBarDataSet barDataSet = new MyBarDataSet(barEntryArrayList,"Weekly Mood");
        barDataSet.setDrawValues(false);
        barDataSet.setColors(ContextCompat.getColor(getContext(), R.color.colorAngry),
                ContextCompat.getColor(getContext(), R.color.colorSad),
                ContextCompat.getColor(getContext(), R.color.colorOk),
                ContextCompat.getColor(getContext(), R.color.colorHappy),
                ContextCompat.getColor(getContext(), R.color.colorVeryHappy));
        BarData barData = new BarData(barDataSet);
        barChart.setData(barData);
        // X axis
        XAxis xAxis = barChart.getXAxis();
        xAxis.setValueFormatter(new IndexAxisValueFormatter(labelNames));
        xAxis.setDrawGridLines(false);
        barChart.getAxisLeft().setDrawGridLines(false);
        barChart.getAxisLeft().setGranularity(1f);
        barChart.getAxisLeft().setValueFormatter(new IndexAxisValueFormatter(yAxisVals));
        barChart.getAxisRight().setDrawGridLines(false);

        barChart.setDescription(null);
        barChart.getAxisRight().setDrawLabels(false);
        barChart.getLegend().setEnabled(false);
        barChart.animateY(2000);
        barChart.setRenderer(new ImageBarChartRenderer(barChart,barChart.getAnimator(),barChart.getViewPortHandler(),bitmapGood));
        // Refreshes drawing
        barChart.invalidate();

        barChart.setOnChartValueSelectedListener(new OnChartValueSelectedListener() {
            @Override
            public void onValueSelected(Entry e, Highlight h) {
                float mood = e.getY();
                switch((int)mood){
                    case 1:
                        moodLog.setText(R.string.moodLogAngry);
                        fakeMoodLog.setText(R.string.fakeMoodLogAngry);
                        break;
                    case 2:
                        moodLog.setText(R.string.moodLogSad);
                        fakeMoodLog.setText(R.string.fakeMoodLogSad);
                        break;
                    case 3:
                        moodLog.setText(R.string.moodLogOk);
                        fakeMoodLog.setText(R.string.fakeMoodLogOk);
                        break;
                    case 4:
                    default:
                        moodLog.setText(R.string.moodLogHappy);
                        fakeMoodLog.setText(R.string.fakeMoodLogHappy);
                        break;
                    case 5:
                        moodLog.setText(R.string.moodLogVHappy);
                        fakeMoodLog.setText(R.string.fakeMoodLogVHappy);
                        break;

                }
            }

            @Override
            public void onNothingSelected() {

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

    public ArrayList<MoodChartData> fillMoodChart(){
        ArrayList<MoodChartData> moodChartData = new ArrayList<>();
        moodChartData.add(new MoodChartData("Mon",2));
        moodChartData.add(new MoodChartData("Tue",3));
        moodChartData.add(new MoodChartData("Wed",1));
        moodChartData.add(new MoodChartData("Thu",3));
        moodChartData.add(new MoodChartData("Fri",5));
        moodChartData.add(new MoodChartData("Sat",5));
        moodChartData.add(new MoodChartData("Sun",4));

        return moodChartData;

    }

}
