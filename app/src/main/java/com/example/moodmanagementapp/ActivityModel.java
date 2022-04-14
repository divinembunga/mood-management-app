package com.example.moodmanagementapp;

public class ActivityModel {
    private String activityName;
    private String activityInfo;
    private int activityImg;

    public ActivityModel(String activityName, String activityInfo, int activityImg) {
        this.activityName = activityName;
        this.activityInfo = activityInfo;
        this.activityImg = activityImg;
    }

    public String getActivityName() {
        return activityName;
    }

    public void setActivityName(String activityName) {
        this.activityName = activityName;
    }

    public String getActivityInfo() {
        return activityInfo;
    }

    public void setActivityInfo(String activityInfo) {
        this.activityInfo = activityInfo;
    }

    public int getActivityImg() {
        return activityImg;
    }

    public void setActivityImg(int activityImg) {
        this.activityImg = activityImg;
    }
}
