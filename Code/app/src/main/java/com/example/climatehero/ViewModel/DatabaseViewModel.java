package com.example.climatehero.ViewModel;


import android.content.Context;

import androidx.lifecycle.ViewModel;

import com.example.climatehero.Model.AddFromCloudDatabase;
import com.example.climatehero.Model.AddToDatabase;
import com.example.climatehero.Model.DatabaseConn;
import com.example.climatehero.Model.DatabaseHelper;
import com.example.climatehero.R;

import java.util.ArrayList;
import java.util.Objects;
import java.util.Random;


public class DatabaseViewModel extends ViewModel {

        private DatabaseHelper myDb;
        private String suggestedBin;
        private String fact;
        private int cloudDbVersion;
        private int localDbVersion;

        public void setDB(Context context){
                if(myDb == null) {

                        myDb = new DatabaseHelper(context);
                        localDbVersion = myDb.getVersionFromTable();

                        if (localDbVersion == 1) {
                                myDb.clearIfExist();
                                AddToDatabase.add(myDb);
                        }

                        if (checkDbVersion()) { /* If newer db in cloud, download and replace the local db */
                                myDb.clearIfExist();
                                ArrayList<String> fact = getCloudDBFacts();
                                AddFromCloudDatabase.addToFacts(myDb, fact);
                                ArrayList<String> classification = getCloudDBClassification();
                                AddFromCloudDatabase.addToClassification(myDb, classification, cloudDbVersion);
                        }
                }
        }

        public void queryDb(ArrayList<String> items) {
                suggestedBin = "Sorry, we found no match for that item.";
                String result;
                for (String s : items) {
                        result = myDb.getSuggestedBin(s);
                        if (!Objects.equals(result, "NoMatch")) {
                                suggestedBin = "Recycle the " + s.toLowerCase() +
                                        " in the " + result.toLowerCase() + ".";
                                break;
                        } else {
                                suggestedBin = "Sorry, we found no match for that item.";
                        }
                }
        }

        private void queryDbFacts() {
                ArrayList<String> facts;
                facts = myDb.getFact();
                Random random = new Random();
                if(facts.get(0) == "NoMatch"){
                        fact = "You are a Climate Hero!!";
                } else if (facts.size() == 1){
                        fact = facts.get(0);
                } else {
                        fact = facts.get(random.nextInt(facts.size()));
                }
        }

        public String getSuggestedBin(){
                return suggestedBin;
        }

        public String getFact(){
                queryDbFacts();
                return fact;
        }

        //If updated database in cloud, use that one, if not, use local database
        public boolean checkDbVersion(){
                final int[] cloudDB = new int[1];
                Thread thread = new Thread(new Runnable() {

                        @Override
                        public void run() {
                                try {
                                        DatabaseConn db = new DatabaseConn();
                                        cloudDB[0] = db.getDatabaseVersion();
                                } catch (Exception e) {
                                        System.out.print(e.getMessage());
                                        e.printStackTrace();
                                }
                        }
                });
                thread.start();
                try{
                        thread.join();
                } catch (Exception e){
                        System.out.print(e.getMessage());
                        e.printStackTrace();
                }
                if(cloudDB[0] > localDbVersion){
                        cloudDbVersion = cloudDB[0];
                        return true;
                } else return false;
        }

        //Get the whole table Classification from cloud to replace the local database
        public ArrayList<String> getCloudDBClassification() {
                final ArrayList<String>[] cloudDB = new ArrayList[]{new ArrayList<>()};
                Thread thread = new Thread(new Runnable() {

                        @Override
                        public void run() {
                                try {
                                        DatabaseConn db = new DatabaseConn();
                                        cloudDB[0] = db.getClassification();

                                } catch (Exception e) {
                                        System.out.print(e.getMessage());
                                        e.printStackTrace();
                                }
                        }
                });
                thread.start();
                try {
                        thread.join();
                } catch (Exception e) {
                        System.out.print(e.getMessage());
                        e.printStackTrace();
                }
                return cloudDB[0];
        }

        //Get the whole table facts from cloud to replace the local database
        public ArrayList<String> getCloudDBFacts() {
                final ArrayList<String>[] cloudDB = new ArrayList[]{new ArrayList<>()};
                Thread thread = new Thread(new Runnable() {

                        @Override
                        public void run() {
                                try {
                                        DatabaseConn db = new DatabaseConn();
                                        cloudDB[0] = db.getFacts();

                                } catch (Exception e) {
                                        System.out.print(e.getMessage());
                                        e.printStackTrace();
                                }
                        }
                });
                thread.start();
                try {
                        thread.join();
                } catch (Exception e) {
                        System.out.print(e.getMessage());
                        e.printStackTrace();
                }
                return cloudDB[0];
        }
}