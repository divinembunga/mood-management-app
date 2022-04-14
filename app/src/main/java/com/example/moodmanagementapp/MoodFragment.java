package com.example.moodmanagementapp;

import android.annotation.SuppressLint;
//import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;

import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.appcompat.app.AlertDialog;

import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;


public class MoodFragment extends Fragment {

    View view;
    TextView moodString, dateString, timeString;
    EditText editText;
    private ImageButton editMoodButton, sendButton, contactButton, infoButton;
    private ImageView moodIcon;
    private RelativeLayout relativeLayout;
    public Handler Alerthandler;
    GridView grid;
    int logos[] = {R.drawable.sleep,
            R.drawable.watching_tv,
            R.drawable.bake,
            R.drawable.coffee_break,
            R.drawable.exercise,
            R.drawable.mountain,
    };
    String text[] = {"Nap", "Movie", "Bake", "Coffee","Exercise","Nature"};

    public MoodFragment(){
        // require an empty public constructor
    }


    @SuppressLint("HandlerLeak")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_mood, container, false);
        relativeLayout = view.findViewById(R.id.firstFragment);
        moodString = view.findViewById(R.id.my_mood_string);
        editText = view.findViewById(R.id.edit_reason);


        dateString = view.findViewById(R.id.date);
        timeString = view.findViewById(R.id.time);
        long date = System.currentTimeMillis();
        SimpleDateFormat date_format = new SimpleDateFormat("EE, MMM dd");
        SimpleDateFormat time_format = new SimpleDateFormat("h:mm a");
        String date_string = date_format.format(date);
        String time_string = time_format.format(date);
        dateString.setText(date_string);
        timeString.setText(time_string);


        moodIcon = view.findViewById(R.id.my_mood);
        contactButton = view.findViewById(R.id.contacts_btn);
        infoButton = view.findViewById(R.id.info_btn);
        editMoodButton = view.findViewById(R.id.btn_edit_mood);
        sendButton = view.findViewById(R.id.btn_reason_send);

        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                editText.setText("");
                Toast.makeText(getContext(), "Reason was recorded",Toast.LENGTH_LONG)
                        .show();

            }
        });

        editMoodButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("MoodFrag:","BTN CLICKED");
                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                builder.setTitle("How are you feeling?")
                        .setItems(R.array.moodArray, new DialogInterface.OnClickListener() {
                            @SuppressLint("ResourceAsColor")
                            public void onClick(DialogInterface dialog, int which) {
                                switch (which){
                                    case 0:
                                        relativeLayout.setBackgroundColor(ContextCompat.getColor(getActivity(), R.color.colorVeryHappy));
                                        moodString.setText(R.string.stringVeryHappy);
                                        moodIcon.setImageResource(R.drawable.very_happy);
                                        return;
                                    case 1:
                                        relativeLayout.setBackgroundColor(ContextCompat.getColor(getActivity(), R.color.colorHappy));
                                        moodString.setText(R.string.stringHappy);
                                        moodIcon.setImageResource(R.drawable.happy);
                                        return;
                                    case 2:
                                        relativeLayout.setBackgroundColor(ContextCompat.getColor(getActivity(), R.color.colorOk));
                                        moodString.setText(R.string.stringOk);
                                        moodIcon.setImageResource(R.drawable.okay);
                                        return;
                                    case 3:
                                        relativeLayout.setBackgroundColor(ContextCompat.getColor(getActivity(), R.color.colorSad));
                                        moodString.setText(R.string.stringSad);
                                        moodIcon.setImageResource(R.drawable.sad);
                                        negativeMoodHandler();
                                        return;
                                    case 4:
                                        relativeLayout.setBackgroundColor(ContextCompat.getColor(getActivity(), R.color.colorAngry));
                                        moodString.setText(R.string.stringAngry);
                                        moodIcon.setImageResource(R.drawable.very_sad);
                                        negativeMoodHandler();
                                        return;
                                    default:
                                        return;
                                }
                            }
                        });
                builder.show();
            }
        });

        contactButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                LayoutInflater inflaterContacts= requireActivity().getLayoutInflater();
                View dialogView = inflaterContacts.inflate(R.layout.dialog_contacts,null);

                builder.setView(dialogView)
                        .setPositiveButton("Done", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                dialogInterface.cancel();

                            }
                        });
                builder.show();

            }
        });

        infoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                moodDetectionInfoModal();
            }
        });

        // Start modal simulator
        Alerthandler = new Handler(){
            public void handleMessage(Message msg){
                switch (msg.what) {
                    case 0:
                        startModal();
                        break;
                }
            }
        };

        Alerthandler.sendEmptyMessageAtTime(0,15000);

        return view;
    }

    public  void startModal(){
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater3 = requireActivity().getLayoutInflater();
        View dialogView = inflater3.inflate(R.layout.dialog_affirmation,null);

        builder.setView(dialogView)
                .setPositiveButton("Got it", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.cancel();
                        moodDetectedModal();

                    }
                });
        builder.show();

    }

    public void moodDetectedModal(){
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Mood Detected !")
                .setMessage("We have detected that your mood may be 'Not Good'. Is this correct?")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        relativeLayout.setBackgroundColor(ContextCompat.getColor(getActivity(), R.color.colorSad));
                        moodString.setText(R.string.stringSad);
                        moodIcon.setImageResource(R.drawable.sad);
                        moodScaleHandler();
                    }
                })
                .setNeutralButton("Learn More", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        learnMoreModal();
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.cancel();
                        Toast.makeText(getContext(), "Thank you for letting us know",Toast.LENGTH_LONG)
                                .show();

                    }
                });
        builder.show();

    }

    public void moodScaleHandler(){
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater4 = requireActivity().getLayoutInflater();
        View dialogView = inflater4.inflate(R.layout.dialog_moodscale,null);

        builder.setView(dialogView)
                .setPositiveButton("Done", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.cancel();
                        negativeMoodHandler();

                    }
                });
        builder.show();
    }



    public void negativeMoodHandler(){
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater2 = requireActivity().getLayoutInflater();
        View dialogView = inflater2.inflate(R.layout.activity_modal,null);

        grid = (GridView) dialogView.findViewById(R.id.modal_grid);

        CustomAdapter customAdapter = new CustomAdapter(getContext(),logos,text);
        grid.setAdapter(customAdapter);
        grid.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                ImageView imgView = view.findViewById(R.id.icon);
                TextView textView = view.findViewById(R.id.icon_text);
                if(imgView.getTag() != "greyed"){
                    imgView.setColorFilter(Color.argb(150,200,200,200)) ;
                    imgView.setTag("greyed");
                    textView.setTextColor(Color.argb(150,200,200,200));
                }else{
                    imgView.setColorFilter(Color.rgb(255,255,255));
                    imgView.setTag("");
                    textView.setTextColor(Color.rgb(255,255,255));
                }
            }
        });
        builder.setView(dialogView)
                .setPositiveButton("Done", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Toast.makeText(getContext(), "Thank you for your response",Toast.LENGTH_LONG)
                                .show();
                        dialogInterface.cancel();

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

    public void learnMoreModal(){
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("More Information")
                .setMessage("We have detected that your mood may be 'Not Good' from the following:\n\n- We noticed that you have not been in contact(SMS or calls) with anyone for a day.\n\n- We noticed you have been laying down for most of the day (5+ hours)\n\n- You have not picked up your phone for over 3 hours.")
                .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.cancel();
                        moodDetectedModal();
                    }
                });

        builder.show();

    }

    public void moodDetectionInfoModal(){
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Mood Detection Information")
                .setMessage(" - We detect your mood with the help of sensors on your phone.\n\n - We confirm with you whether or not the detection is correct, this is so we can learn to adapt to you.\n\n - We strive to make better and more accurate detections for you. ")
                .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.cancel();
                    }
                });

        builder.show();

    }


}
