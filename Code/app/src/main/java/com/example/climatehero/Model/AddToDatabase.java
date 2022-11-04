package com.example.climatehero.Model;

public class AddToDatabase {

        public static void add(DatabaseHelper db){

            //Database version
            db.insertClassificationData("database" , "data" , 1);
        }
}

