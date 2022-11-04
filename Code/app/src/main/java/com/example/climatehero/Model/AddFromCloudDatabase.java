package com.example.climatehero.Model;

import java.util.ArrayList;

public class AddFromCloudDatabase {

    public static void addToClassification(DatabaseHelper db, ArrayList<String> list, int version){

        db.insertClassificationData(list.get(0), list.get(1), version);

        int j;
        for(int i = 2; i < list.size(); i++){
            j = i + 1;
            db.insertClassificationData(list.get(i), list.get(j), null);
            i++;
        }
    }

    public static void addToFacts(DatabaseHelper db, ArrayList<String> list){

        for(int i = 0; i < list.size(); i++){
            db.insertFactsData(list.get(i));
        }
    }
}
