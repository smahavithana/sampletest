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

/* *
 * Created by Sampath on 17/02/2017.
 */
public class TechnoJobs {

    public static void getJobs() throws Exception {

        DateHelper dateHelper = new DateHelper();

        String site = "https://www.cwjobs.co.uk/jobs/qa?page=";
        int resultsPerPage = 1;
        int pages = 1;
        int i = 0;
        int noOfJobs = resultsPerPage*pages;
        String jobExpiryDate = "1911772800";

        Job myJobs[] = new Job[noOfJobs];

        for(int x = 1; x<=pages; x++){
            String url = site + x;

            try {
                Document doc = Jsoup.connect(url).get();
                Elements ele = doc.select("div.col-xs-12.job-results.clearfix");

                //int s = ele.select("div.job").size();
                //Job myJobs[] = new Job[s];

                for (Element element : ele.select("div.job")) {

                    if(i<noOfJobs){
                        System.out.println("Writing Job Details: "+ (i+1));

                        Job j = new Job();

                        String job_title = element.select("meta[property=title]").attr("content");
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

                        myJobs[i++] = j;
                    }
                }

                //XMLHelper.Write(myJobs);

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        XMLHelper.Write(, myJobs);
    }

    public static String getJobDescription(String jobDetailsLink) throws Exception {

        Document doc = Jsoup.connect(jobDetailsLink).get();
        return doc.select("div.job-description").outerHtml();
    }
}
