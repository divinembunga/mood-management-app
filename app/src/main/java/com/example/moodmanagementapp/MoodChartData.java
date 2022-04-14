package com.example.moodmanagementapp;

public class MoodChartData {
    String day;
    int mood;

    public MoodChartData(String day, int mood) {
        this.day = day;
        this.mood = mood;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public int getMood() {
        return mood;
    }

    public void setMood(int mood) {
        this.mood = mood;
    }
}
