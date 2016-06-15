package de.marcreichelt.recyclerviewcrashdemo;

import android.support.test.rule.ActivityTestRule;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import static android.support.test.espresso.Espresso.closeSoftKeyboard;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.Espresso.pressBack;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.matcher.ViewMatchers.withId;

@RunWith(JUnit4.class)
public class RecyclerViewCrashTest {

    @Rule
    public ActivityTestRule<FirstActivity> activityRule = new ActivityTestRule<>(FirstActivity.class);

    @Test
    public void crashOnNexus5_AnimationsMustBeEnabled() throws Exception {
        for (int i = 0; i < 10; i++) {
            onView(withId(R.id.animate)).perform(click());
            Thread.sleep(300);

            closeSoftKeyboard();
            pressBack();
            Thread.sleep(300);
        }
    }

}
