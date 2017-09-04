package sites;

import com.Job;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import util.DateHelper;
import util.Helper;
import util.XMLHelper;
import java.io.IOException;
import java.util.ArrayList;

/* *
 * Created by Sampath Mahavithana on 17/02/2017.
 */
public class CWJobs {


    static DateHelper dateHelper = new DateHelper();
    static Helper util = new Helper();
    static String jobExpiryDate = "1911772800";
    static String site_url = "https://www.cwjobs.co.uk/jobs/qa?page=";

    public static void getJobs() throws Exception {

        int resultsPerPage = 10;
        int noOfJobs = getJobCount();
        //int noOfJobs = 100;

        int pages = noOfJobs/resultsPerPage;
        int i = 0;
        ArrayList<Job> jobList = new ArrayList<Job>();

        for(int x = 1; x<=pages; x++){
            String url = site_url + x;

            try {
                Document doc = Jsoup.connect(url).get();
                Elements ele = doc.select("div.col-xs-12.job-results.clearfix");

                for (Element element : ele.select("div.job")) {
                    Job j = new Job();

                    if(i<noOfJobs){
                        String job_title = element.select("meta[property=title]").attr("content");

                        if(!util.isValidQAJob(job_title)){
                            System.out.println("Skipping job: "+ job_title );
                            break;
                        }
                        else {
                            System.out.println("Reading Job Details: " + (i+1) + ": " +  job_title);
                            String job_url = element.select("meta[property=url]").attr("content");
                            String job_location = element.select("meta[property=addressRegion]").attr("content");
                            String job_date_posted = element.select("meta[property=datePosted]").attr("content");
                            String job_decription = element.select("p.job-intro").toString();
                            String job_salary = element.select("li.salary").text();
                            String job_type = element.select("li.job-type").text();
                            String job_company_name = element.select("meta[property=name]").attr("content");
                            String job_company_logo = element.select("meta[property=logo]").attr("content");

                            j.setTitle(job_title);
                            j.setLocation(job_location);
                            j.setCompany_logo(job_company_logo);
                            j.setCompany_name(job_company_name);
                            j.setDate_posted(dateHelper.getUnixDateTime(job_date_posted));
                            j.setDate_expire(jobExpiryDate);
                            j.setDate_app_close(jobExpiryDate);
                            j.setDecription(job_decription);
                            j.setSalary(job_salary);
                            j.setType(job_type);
                            j.setUrl(job_url);
                            j.setSpecialism(Helper.getSpecialism(job_title));
                            j.setLong_description(getJobDescription(job_url));
                        }

                        jobList.add(j);
                        i++;
                    }
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        XMLHelper.writeFromArrayList("cwjobs", jobList);
    }

    private static int getJobCount(){
        int no_of_jobs = 1;

        try {
            Document doc = Jsoup.connect(site_url).get();
            Elements ele = doc.select("div.page-title");
            no_of_jobs = Integer.parseInt(ele.select("span").text()) ;
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        return no_of_jobs;
    }

    private static String getJobDescription(String jobDetailsLink) throws Exception {

        Document doc = Jsoup.connect(jobDetailsLink).get();
        return doc.select("div.job-description").outerHtml();
    }


}
