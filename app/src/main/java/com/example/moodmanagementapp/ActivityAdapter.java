package com.example.moodmanagementapp;

import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;


public class ActivityAdapter extends RecyclerView.Adapter<ActivityAdapter.Viewholder> {

    private Context context;
    private ArrayList<ActivityModel> activityModelArrayList;


    public ActivityAdapter(Context context, ArrayList<ActivityModel> activityModelArrayList) {
        this.context = context;
        this.activityModelArrayList = activityModelArrayList;
    }

    @NonNull
    @Override
    public ActivityAdapter.Viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Inflate the layout for each item of recycler view.
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_layout, parent, false);
        return new Viewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ActivityAdapter.Viewholder holder, int position) {
        // Set data to the cards
        ActivityModel model = activityModelArrayList.get(position);
        holder.activityName.setText(model.getActivityName());
        holder.activityInfo.setText("" + model.getActivityInfo());
        holder.activityImg.setImageResource(model.getActivityImg());

        holder.activityCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                View dialogView = LayoutInflater.from(context).inflate(R.layout.dialog_activityres,null);
                WebView webView = dialogView.findViewById(R.id.web_recipes);

                // Load URL
                webView.loadUrl("https://www.eatingwell.com/gallery/7891936/15-minute-dessert-recipes/?");

                builder.setView(dialogView);
                builder.setTitle("Activity Resources")
                        .setPositiveButton("Close", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                dialogInterface.cancel();
                            }
                        });
                builder.show();
            }
        });
    }

    @Override
    public int getItemCount() {
        // Number of card items
        return activityModelArrayList.size();
    }

    // View holder class for initializing of views such as TextView and ImageView.
    public class Viewholder extends RecyclerView.ViewHolder {
        private ImageView activityImg;
        private TextView activityName, activityInfo;
        private CardView activityCard;

        public Viewholder(@NonNull View itemView) {
            super(itemView);
            activityName = itemView.findViewById(R.id.activity_name);
            activityInfo = itemView.findViewById(R.id.activity_info);
            activityImg = itemView.findViewById(R.id.activity_image);
            activityCard = itemView.findViewById(R.id.activity_card);
        }

    }
}
