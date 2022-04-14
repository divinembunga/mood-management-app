package com.example.moodmanagementapp;

import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;

import java.util.List;

// Gives bar chart colours by mood
public class MyBarDataSet extends BarDataSet {


    public MyBarDataSet(List<BarEntry> yVals, String label) {
        super(yVals, label);
    }

    @Override
    public int getColor(int index) {
        if (this.getEntryForIndex(index).getY() == 1)
            return mColors.get(0);
        else if (this.getEntryForIndex(index).getY() == 2)
            return mColors.get(1);
        else if (this.getEntryForIndex(index).getY() == 3)
            return mColors.get(2);
        else if (this.getEntryForIndex(index).getY() == 4)
            return mColors.get(3);
        else
            return mColors.get(4);

    }


}
