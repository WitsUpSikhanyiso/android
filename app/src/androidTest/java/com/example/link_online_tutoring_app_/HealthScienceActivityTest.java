package com.example.link_online_tutoring_app_;

import android.content.Intent;

import androidx.test.rule.ActivityTestRule;

import com.example.link_online_tutoring_app_.FacultiesAcitvities.CommerceActivity;
import com.example.link_online_tutoring_app_.FacultiesAcitvities.HealthScienceActivity;

import org.junit.Rule;
import org.junit.Test;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

public class HealthScienceActivityTest {

    @Rule
    public ActivityTestRule rule = new ActivityTestRule(HealthScienceActivity.class, true, false);


    @Test
    public void onCreate7() {
    }


    @Test
    public void clickseven(){
        rule.launchActivity(new Intent());
        onView(withId(R.id.private_chat)).perform(click());

    }


    @Test
    public void onCreate8() {
    }

    @Test
    public void clickEight(){
        rule.launchActivity(new Intent());
        onView(withId(R.id.posting)).perform(click());

    }


    @Test
    public void onCreate9() {
    }

    @Test
    public void clickNine(){
        rule.launchActivity(new Intent());
        onView(withId(R.id.action_faculties)).perform(click());

    }


}
