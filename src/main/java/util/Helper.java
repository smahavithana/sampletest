package util;

import sites.CWJobs;
import sites.Totaljobs;

/* *
 * Created by Sampath on 17/02/2017.
 */
public class Helper {

    public static String getSpecialism(String title){
        String specialism = "QA Engineer";

        if(title.contains("Test Analyst")){
            specialism = "Test Analyst";
        }

        if(title.contains("QA Analyst")){
            specialism = "QA Analyst";
        }

        if(title.contains("Big Data")){
            specialism = "Big Data Tester";
        }

        if(title.contains("Graduate")){
            specialism = "Graduate Test Analyst";
        }

        if(title.contains("Junior QA")||title.contains("Junior Test")){
            specialism = "Junior QA Engineer";
        }

        if(title.contains("Lead QA")||title.contains("Lead Test")){
            specialism = "Lead QA Engineer";
        }

        if(title.contains("Manual Tester")||title.contains("Manual QA")){
            specialism = "Manual Tester";
        }

        if(title.contains("Mobile Automation")){
            specialism = "Mobile Automation Tester";
        }

        if(title.contains("Performance")){
            specialism = "Performance Tester";
        }

        if(title.contains("QA Manager")||title.contains("Test Manager")){
            specialism = "QA Manager";
        }

        if(title.contains("Developer in")||title.contains("SDET")){
            specialism = "Software Developer in Test (SDET)";
        }

        return specialism;
    }

    public static void removeNonQAJobs(){

    }


    public static String getJobDescription(String jobDetailsLink) throws Exception{

        String jobLongDescription = "";
        if(jobDetailsLink.contains("cwjobs.co.uk"))
            jobLongDescription =  CWJobs.getJobDescription(jobDetailsLink);

        if(jobDetailsLink.contains("totaljobs.com"))
            jobLongDescription =  Totaljobs.getJobDescription(jobDetailsLink);

        return jobLongDescription;
    }

    public static Boolean isExistingJob(String url){
        return true;
    }
}
