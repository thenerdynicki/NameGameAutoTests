package com.willowtreeapps;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

public class WebTest {

    private WebDriver driver;

    /**
     * Change the prop if you are on Windows or Linux to the corresponding file type
     * The chrome WebDrivers are included on the root of this project, to get the
     * latest versions go to https://sites.google.com/a/chromium.org/chromedriver/downloads
     */
    @Before
    public void setup() {
        System.setProperty("webdriver.chrome.driver", "/home/the_nicki/Downloads/chromedriver");
        Capabilities capabilities = DesiredCapabilities.chrome();
        driver = new ChromeDriver(capabilities);
        driver.navigate().to("http://www.ericrochester.com/name-game/");
    }

    @Test
    public void test_validate_title_is_present() {
        new HomePage(driver)
                .validateTitleIsPresent();
    }

    @Test
    public void test_clicking_photo_increases_tries_counter() {
        new HomePage(driver)
                .validateClickingFirstPhotoIncreasesTriesCounter();
    }

    @Test
    public void test_validate_streak_counter_increments() {
        new HomePage(driver).validateStreakCounterIncrements();
    }

    @Test
    public void test_validate_streak_counter_resets() {
        new HomePage(driver).validateStreakCounterResets();
    }

    @Test
    public void test_validate_tries_correct_counter_increments() {
        new HomePage(driver).validateTriesCorrectCounterIncrements();
    }

    @Test
    public void test_picture_Changes() {
        new HomePage(driver).validatePictureChanges();
    }

    @After
    public void teardown() {
        driver.quit();
        System.clearProperty("webdriver.chrome.driver");
    }

}
