package com.example.MyMovieFilmApp.ui.main;

import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.rule.ActivityTestRule;

import com.example.MyMovieFilmApp.R;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

@RunWith(AndroidJUnit4.class)
public class DetailesActivityTest {
    @Rule
    public ActivityTestRule<DetailesActivity> detailesActivityActivityTestRule = new ActivityTestRule<>(DetailesActivity.class);

    //    @Test
//    public void DetailesFragTest() {
//        onView(withId(R.id.fragment_detailes)).check(matches(isDisplayed()));
//
//    }
//
    @Before
    public void init() {
        detailesActivityActivityTestRule.getActivity()
                .getSupportFragmentManager().beginTransaction();
    }

    @Test
    public void fragment_can_be_instantiated() {

        onView(withId(R.id.fragment_detailes)).check(matches(isDisplayed()));
        onView(withId(R.id.Movie_title)).check(matches(isDisplayed()));
        onView(withId(R.id.move_vote)).check(matches(isDisplayed()));
        onView(withId(R.id.move_date_relase)).check(matches(isDisplayed()));
    }


}