package com.example.climatehero.ViewModel;

import android.content.Context;

import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.platform.app.InstrumentationRegistry;

import junit.framework.TestCase;

import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.ArrayList;
import java.util.Random;

@RunWith(AndroidJUnit4.class)
public class DatabaseViewModelTest extends TestCase {

    private DatabaseViewModel databaseViewModel = new DatabaseViewModel();
    private Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
    private ArrayList<String> items = new ArrayList<>();

    @Test
    public void testQueryDbForFood() {
        databaseViewModel.setDB(appContext);
        items.add("Food");
        databaseViewModel.queryDb(items);
        assertEquals("Recycle the food in the compost.",
                databaseViewModel.getSuggestedBin());
    }

    @Test
    public void testQueryDBForNoMatch() {
        databaseViewModel.setDB(appContext);
        ArrayList<String> items = new ArrayList<>();
        items.add("Koala");
        databaseViewModel.queryDb(items);
        assertEquals("Sorry, we found no match for that item.",
                databaseViewModel.getSuggestedBin());
    }

    @Test
    public void testRandomFact() {
        //testing randomness of getFact method
        databaseViewModel.setDB(appContext);

        String fact = databaseViewModel.getFact();
        String fact2 = databaseViewModel.getFact();

        System.out.println(fact);
        System.out.println(fact2);

        assertNotSame(fact2, fact);
    }


}
