package com.example.simpleparadox.listycity;

import android.app.Activity;
import android.widget.EditText;
import android.widget.ListView;

import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.rule.ActivityTestRule;

import com.robotium.solo.Solo;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;

/**
 * Test class for MainActivity.
 */
@RunWith(AndroidJUnit4.class)
public class MainActivityTest {
    private Solo solo;

    @Rule
    public ActivityTestRule<MainActivity> rule =
            new ActivityTestRule<>(MainActivity.class, true, true);

    /**
     * Runs before all tests and creates solo instance.
     * @throws Exception
     */
    @Before
    public void setUp() throws Exception {
        solo = new Solo(InstrumentationRegistry.getInstrumentation(), rule.getActivity());
    }

    /**
     * Gets the activity.
     * @throws Exception
     */
    @Test
    public void start() throws Exception {
        Activity activity = rule.getActivity();
    }

    /**
     * Add a city to the listview and check the city name using assertTrue
     * Clear all cities from the listview and check for cities using assertFalse
     */
    @Test
    public void checkList() {
        // current activity must be MainActivity
        solo.assertCurrentActivity("Wrong activity", MainActivity.class);

        // add a new city "Edmonton"
        solo.clickOnButton("ADD CITY");
        solo.enterText((EditText) solo.getView(R.id.editText_name), "Edmonton");
        solo.clickOnButton("CONFIRM");
        solo.clearEditText((EditText) solo.getView(R.id.editText_name));

        // wait for 2 secs and find minimum one match
        assertTrue(solo.waitForText("Edmonton", 1, 2000));

        // clear all cities in list
        solo.clickOnButton("CLEAR ALL");

        // true if no matches are found
        assertFalse(solo.searchText("Edmonton"));
    }

    /**
     * Check item from the listview
     */
    @Test
    public void checkCityListItem() {
        // current activity must be MainActivity
        solo.assertCurrentActivity("Wrong activity", MainActivity.class);

        // add a new city "Edmonton"
        solo.clickOnButton("ADD CITY");
        solo.enterText((EditText) solo.getView(R.id.editText_name), "Edmonton");
        solo.clickOnButton("CONFIRM");
        solo.clearEditText((EditText) solo.getView(R.id.editText_name));

        MainActivity activity = (MainActivity) solo.getCurrentActivity();
        final ListView cityList = activity.cityList;
        String city = (String) cityList.getItemAtPosition(0);
        assertEquals("Edmonton", city);
    }

    /**
     * Checks if the activity switched correctly to showActivity, and verifies consistency of
     * text, and finally checks if activity switches correctly when back button is pressed.
     */
    @Test
    public void checkActivitySwitch() {
        solo.assertCurrentActivity("Wrong activity", MainActivity.class);
        solo.clickOnButton("ADD CITY");
        solo.enterText((EditText) solo.getView(R.id.editText_name), "Edmonton");
        solo.clickOnButton("CONFIRM");
        solo.clearEditText((EditText) solo.getView(R.id.editText_name));

        assertTrue(solo.waitForText("Edmonton", 1, 2000));

        MainActivity activity = (MainActivity) solo.getCurrentActivity();
        solo.clickOnText("Edmonton");
        solo.assertCurrentActivity("Wrong activity", showActivity.class);
        assertTrue(solo.waitForText("Edmonton", 1, 2000));
        solo.clickOnButton("Back");
        solo.assertCurrentActivity("Wrong activity", MainActivity.class);
    }

    /**
     * Closes the activity after each test
     * @throws Exception
     */
    @After
    public void tearDown() throws Exception {
        solo.finishOpenedActivities();
    }

}
