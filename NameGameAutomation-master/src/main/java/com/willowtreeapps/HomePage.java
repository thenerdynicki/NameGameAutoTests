package com.willowtreeapps;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.Random;

/**
 * Created on 5/23/17.
 */
public class HomePage extends BasePage {


    public HomePage(WebDriver driver) {
        super(driver);
    }

    public void validateTitleIsPresent() {
        WebElement title = driver.findElement(By.cssSelector("h1"));
        Assert.assertTrue(title != null);
    }


    public void validateClickingFirstPhotoIncreasesTriesCounter() {
        //Wait for page to load
        sleep(6000);

        int count = Integer.parseInt(driver.findElement(By.className("attempts")).getText());

        driver.findElement(By.className("photo")).click();

        sleep(6000);

        int countAfter = Integer.parseInt(driver.findElement(By.className("attempts")).getText());

        Assert.assertTrue(countAfter > count);

    }

    public void validateStreakCounterIncrements() {
        sleep(6000);

        int count = Integer.parseInt(driver.findElement(By.className("streak")).getText()); // Retrieve the Streak counter

        // Iterate through 5 rounds of the game
        for (int i = 0; i < 5; i++) {
            WebElement question = driver.findElement(By.cssSelector("span")); // Retrieve Employee Name in question
            String questionName = question.getText();
            List<WebElement> photos = driver.findElements(By.className("photo"));  // Retrieve all photos
            for (WebElement photoFromList : photos) {
                WebElement photoName = photoFromList.findElement(By.className("name")); //Retrieve the name associated with each photo
                String name = photoName.getText();
                // System.out.print(name);
                if (questionName.equals(name)) {  //If the Employee Name matches the picture, click it
                    photoFromList.click();
                }
            }
            sleep(4000);
        }

        int countAfter = Integer.parseInt(driver.findElement(By.className("streak")).getText()); //Retrieve new Streak value
        Assert.assertTrue(countAfter > count);  //True if the Streak counter after is greater than the original value
    }

    public void validateStreakCounterResets() {
        sleep(6000);

        int count = Integer.parseInt(driver.findElement(By.className("streak")).getText()); // Retrieve the Streak counter

        // Iterate through 5 rounds of the game
        for (int i = 0; i < 5; i++) {
            WebElement question = driver.findElement(By.cssSelector("span")); //Retrieve Employee Name in question
            String questionName = question.getText();
            List<WebElement> photos = driver.findElements(By.className("photo"));  //Retrieve all photos
            for (WebElement photoFromList : photos) {
                WebElement photoName = photoFromList.findElement(By.className("name")); //Retrieve the name associated with each photo
                String name = photoName.getText();
                if (questionName.equals(name)) {  //If the Employee Name matches the picture, click it
                    photoFromList.click();
                }
            }
            sleep(4000);
        }
        for (int j = 0; j < 1; j++) { // Iterate through 1 round of the game
            WebElement question = driver.findElement(By.cssSelector("span")); //Retrieve Employee Name in question
            String questionName = question.getText();
            List<WebElement> photos = driver.findElements(By.className("photo"));  //Retrieve all photos
            for (WebElement photoFromList : photos) {
                WebElement photoName = photoFromList.findElement(By.className("name")); //Retrieve the name associated with each photo
                String name = photoName.getText();
                if (questionName != name) {  //If the Employee Name does not match the picture, click it
                    photoFromList.click();
                }
            }
            sleep(4000);
        }
        int countAfter = Integer.parseInt(driver.findElement(By.className("streak")).getText()); //Retrieve new Streak value
        Assert.assertTrue(countAfter == 0); //True if the Streak counter after is set back to 0
    }


    public void validateTriesCorrectCounterIncrements() {
        sleep(6000);

        int correct = Integer.parseInt(driver.findElement(By.className("attempts")).getText()); //Retrieve the Tries counter

        // Iterate through 10 rounds of the game
        for (int j = 0; j < 10; j++) {
            //Retrieve all photos that have not yet been marked as incorrect (not red in color)
            List<WebElement> photos = driver.findElements(By.cssSelector(".photo:not(.wrong)"));
            WebElement question = driver.findElement(By.cssSelector("span")); //Retrieve Employee Name in question
            String questionName = question.getText();

            Random r = new Random();
            int randomValue = r.nextInt(photos.size()); //Get a random value that is between 0 and (photos list's size)-1
            photos.get(randomValue).click(); //Click on the random photo from the list.
            WebElement photo = photos.get(randomValue); //Identify which photo was selected
            WebElement photoName = photo.findElement(By.className("name")); //Retrieve the name associated with the selected photo
                String name = photoName.getText();
                if (questionName.equals(name)) {  //If the Employee Name matches the picture, increase the correct value by 1
                    correct++;
                }

                sleep(4000);
            }

            int correctAfter = Integer.parseInt(driver.findElement(By.className("correct")).getText()); //Retrieve new Correct value
            int triesAfter = Integer.parseInt(driver.findElement(By.className("attempts")).getText()); //Retrieve new Tries value
            //True if the Correct counter reflects the total that was calculated in the for loop AND
            //True if the Attempts counter reflects 10 random selections
            Assert.assertTrue(correctAfter == correct && triesAfter == 10);
        }

        public void validatePictureChanges () {
            sleep(6000);

            List<WebElement> photosList = driver.findElements(By.className("photo")); //Retrieve all photos
            WebElement nameInQuestion = driver.findElement(By.cssSelector("span")); //Retrieve Employee Name in question

            //Iterate through 1 round of the game
            for (int i = 0; i < 1; i++) {
                WebElement question = driver.findElement(By.cssSelector("span")); //Retrieve Employee Name in question
                String questionName = question.getText();
                List<WebElement> photos = driver.findElements(By.className("photo"));  //Retrieve all photos
                for (WebElement photoFromList : photos) {
                    WebElement photoName = photoFromList.findElement(By.className("name")); //Retrieve the name associated with each photo
                    String name = photoName.getText();
                    if (questionName.equals(name)) {  //If the Employee Name matches the picture, click it
                        photoFromList.click();
                        String answer = question.getText();
                    }
                }
                sleep(4000);
            }
            List<WebElement> photosListAfter = driver.findElements(By.className("photo")); //Retrieve new set of photos
            WebElement nameAfter = driver.findElement(By.cssSelector("span"));  //Retrieve new Employee Name in question
            //True if the new photos do not match the original set of photos AND
            //True if the new Employee Name in question does not match the original,
            Assert.assertTrue(photosList != photosListAfter && nameInQuestion != nameAfter);
        }


    }
