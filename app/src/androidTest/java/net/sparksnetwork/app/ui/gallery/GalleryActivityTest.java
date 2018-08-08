package net.sparksnetwork.app.ui.gallery;

import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.test.suitebuilder.annotation.LargeTest;

import net.sparksnetwork.app.R;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;

@RunWith(AndroidJUnit4.class)
@LargeTest
@SuppressWarnings("deprecation")
public class GalleryActivityTest {

    @Rule
    public ActivityTestRule<GalleryActivity> mActivityRule = new ActivityTestRule<>(GalleryActivity.class);

    @Before
    public void setUp() {

    }

    @After
    public void tearDown() {  }

    @Test
    public void testLoadImage() throws InterruptedException {
        Thread.sleep(2000l);
        onView(withId(R.id.btn_grid)).check(matches(isDisplayed()));
        onView(withId(R.id.btn_grid)).perform(click());
        Thread.sleep(2000l);
        onView(withId(R.id.crop_image_menu_crop)).check(matches(isDisplayed()));
        onView(withId(R.id.crop_image_menu_rotate_right)).check(matches(isDisplayed()));
    }

}
