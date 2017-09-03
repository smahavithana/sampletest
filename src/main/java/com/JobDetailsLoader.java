package com;

import org.jsoup.select.Elements;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.*;

import java.util.List;

/**
 * Created by Sampath on 18/02/2017.
 */
public class JobDetailsLoader {

    FirefoxDriver driver;

    public void getJobDetails(String jobsite)throws Exception{

        System.setProperty("webdriver.firefox.marionette", "geckodriver");
        driver = new FirefoxDriver();
        driver.get(jobsite);

        List<WebElement> jobs = driver.findElementsByClassName("job ");

        int i = 0;

        int s = jobs.size();
        String jobLinks[] = new String[s];

        for ( WebElement job: jobs) {

            WebElement title = job.findElement(By.className("job-title"));
            jobLinks[i++] = title.findElement(By.tagName("a")).getAttribute("href");
        }

        getJobDescriptionFromLinks(jobLinks);


        driver.close();
    }

    public void getJobDescriptionFromLinks(String jobLinks[]){

        for(int i=0;i<jobLinks.length;i++){

            driver.get(jobLinks[i]);

            WebElement myDynamicElement = (new WebDriverWait(driver, 10))
                .until(ExpectedConditions.presenceOfElementLocated(By.className("job-description")));

            String jobDescription = driver.findElement(By.className("job-description")).getAttribute("outerHTML");

            System.out.print(jobDescription);
        }
    }

    public String getJobDescription(String jobSite, String jobDetailsLink){

        System.setProperty("webdriver.firefox.marionette", "geckodriver");
        driver = new FirefoxDriver();
        driver.get(jobDetailsLink);
        String jobDescription = "";

        WebElement myDynamicElement = (new WebDriverWait(driver, 10))
                .until(ExpectedConditions.presenceOfElementLocated(By.className("job-description")));

        if(jobSite == "cwjobs" || jobSite == "totaljobs")
            jobDescription = driver.findElement(By.className("job-description")).getAttribute("outerHTML");

        driver.close();
        return jobDescription;

    }
}
