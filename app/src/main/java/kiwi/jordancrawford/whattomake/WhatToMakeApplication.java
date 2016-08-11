package kiwi.jordancrawford.whattomake;

import android.app.Application;
import android.util.Log;

import org.json.JSONException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by Jordan on 12/08/16.
 */
public class WhatToMakeApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        final ArrayList<Meal> meals;
        try {
            SampleData.setupSampleData(getResources().openRawResource(R.raw.sampledata));
            Collections.sort(SampleData.getAllMeals());
        } catch(IOException exception) {
            System.out.println("Could not read the sample data.");
            return;
        } catch(JSONException exception) {
            System.out.println("The sample data file is invalid.");
            return;
        }
    }
}
