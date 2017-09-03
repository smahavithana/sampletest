package com;

import sites.CWJobs;
import sites.*;
import util.XMLHelper;

/* *
 * Created by sampathmahavithana on 12/01/2017.
 */

public class Main {

    public static void main(String[] args) throws Exception {

        //Collect jobs from Search Results Pages
        //CWJobs.getJobs();
        CWJobs_new.getJobs();
        //Totaljobs.getJobs("https://www.totaljobs.com/jobs/qa");
        //Jobsite.getJobs("http://www.jobsite.co.uk/vacancies?search_type=quick&query=qa");
        //JobServe.getJobs("https://www.jobserve.com/gb/en/JobSearch.aspx?shid=B634F62E155A6C5598"); //NOT IMPLEMENTED
        //Indeed.getJobs("https://www.indeed.co.uk/jobs?q=qa","permanent");
        //Reed.getJobs("https://www.reed.co.uk/jobs?keywords=qa");
        //LondonJobs.getJobs("http://www.londonjobs.co.uk/cgi-bin/advsearch?search_type=quick&fp_skill_include=QA");
        //Monster.getJobs("https://www.monster.co.uk/jobs/search/?q=qa&cy=uk ");
        //Adzuna.getJobs("https://www.adzuna.co.uk/jobs/search?q=qa");  //NOT WORKING - SECURITY ISSUES
        //LinkedIn.getJobs("https://uk.linkedin.com/jobs/search?keywords=qa");  //NOT WORKING

        //Collect Job Descriptions from Job Details Pages via WebDriver
        //JobDetailsLoader jobLoader = new JobDetailsLoader();
        //jobLoader.getJobDetails("https://www.CWJobs.co.uk/jobs/qa");

        //XMLHelper.ReadXMLAndWriteToNew();
    }

}
