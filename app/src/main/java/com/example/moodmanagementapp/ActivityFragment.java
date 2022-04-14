package com.example.moodmanagementapp;

import android.graphics.Color;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;


public class ActivityFragment extends Fragment {
    View view;
    GridView grid;
    int logos[] = {R.drawable.sleep,
            R.drawable.watching_tv,
            R.drawable.bake,
            R.drawable.coffee_break,
            R.drawable.exercise,
            R.drawable.mountain,
            R.drawable.ellipsis
    };
    String text[] = {"Nap", "Movie", "Bake", "Coffee","Exercise","Nature", "Other"};
    private RecyclerView activityRV;
    private ArrayList<ActivityModel> activityModelArrayList;


    public ActivityFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_activity, container, false);
        grid = (GridView) view.findViewById(R.id.my_grid);
        activityRV = view.findViewById(R.id.activity_list);

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

        activityModelArrayList = new ArrayList<>();
        activityModelArrayList.add((new ActivityModel("Bake","You deserve a nice treat",R.drawable.bake)));
        activityModelArrayList.add((new ActivityModel("Phone a friend","Talking to someone about it might help",R.drawable.call)));
        activityModelArrayList.add((new ActivityModel("Grab a coffee","Enjoy some coffee hot or hold to gain some energy",R.drawable.coffee_break)));
        activityModelArrayList.add((new ActivityModel("Meet up with a friend","Talk it out or have fun",R.drawable.conversation)));
        activityModelArrayList.add((new ActivityModel("Make a meal","Home cooked meals are great",R.drawable.eating)));
        activityModelArrayList.add((new ActivityModel("Listen to music","Listen to your favourite tunes",R.drawable.entertainment)));
        activityModelArrayList.add((new ActivityModel("Exercise","Hit up the gym to release dopamine ",R.drawable.exercise)));
        activityModelArrayList.add((new ActivityModel("Gardening","Tending to plants is so therapeutic",R.drawable.gardening)));
        activityModelArrayList.add((new ActivityModel("Play an instrument","Get into your feels",R.drawable.guitar)));
        activityModelArrayList.add((new ActivityModel("Pamper yourself","Skin care routine time, self care is important",R.drawable.make_up)));
        activityModelArrayList.add((new ActivityModel("Meditate","Take deep breaths and relax",R.drawable.meditation)));
        activityModelArrayList.add((new ActivityModel("Enjoy the outdoors","Get some nice fresh air and enjoy the nature",R.drawable.mountain)));
        activityModelArrayList.add((new ActivityModel("Play games","Play your favourite video game",R.drawable.online_game)));
        activityModelArrayList.add((new ActivityModel("Online shop","Retail therapy, you deserve a treat",R.drawable.online_shopping)));
        activityModelArrayList.add((new ActivityModel("Art","Paint, draw or sketch. Let the creativity flow",R.drawable.painting)));
        activityModelArrayList.add((new ActivityModel("Read a book","Read another chapter of your favourite book",R.drawable.reading)));
        activityModelArrayList.add((new ActivityModel("Green exercise","Exercise in nature - do not forget to bring water",R.drawable.running_man)));
        activityModelArrayList.add((new ActivityModel("Sing","Enjoy a good singing session",R.drawable.singing)));
        activityModelArrayList.add((new ActivityModel("Take a nap","You might feel great after",R.drawable.sleep)));
        activityModelArrayList.add((new ActivityModel("Yoga","Stretch the stress out",R.drawable.warrior)));
        activityModelArrayList.add((new ActivityModel("Sport","Have fun with a sport of your choice",R.drawable.sport)));
        activityModelArrayList.add((new ActivityModel("Clean","De-cluttering your space might ease your mind",R.drawable.washing_clothes)));
        activityModelArrayList.add((new ActivityModel("Watch a movie or show","Get cosy, grab some snacks and enjoy",R.drawable.watching_tv)));

        ActivityAdapter activityAdapter = new ActivityAdapter(getContext(),activityModelArrayList);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL, false);
        activityRV.setLayoutManager(linearLayoutManager);
        activityRV.setAdapter(activityAdapter);


        return view;
    }

}
