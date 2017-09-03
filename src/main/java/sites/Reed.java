package sites;

import com.Job;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import util.Helper;
import util.XMLHelper;

import java.io.IOException;

/* *
 * Created by Sampath on 17/02/2017.
 */
public class Reed {

    public static void getJobs(String jobsite) throws Exception {

        try {
            Document doc = Jsoup.connect(jobsite).get();
            Elements ele = doc.select("section[id=server-results]");

            int i = 0;

            int s = ele.select("article.job-result").size();
            Job myJobs[] = new Job[s];

            for (Element element : ele.select("article.job-result")) {

                Job j = new Job();

                String job_title = element.select("h3").text();
                String job_url = "http://www.jobsite.co.uk" + element.select("a[href]").attr("href");
                String job_decription = element.select("div.description.hidden-xs").text();
                String job_salary = element.select("li.salary").text();
                String job_location = element.select("li.location").text();
                String job_type = element.select("li.time").text();

                String job_date_posted_by = element.select("div.posted-by").text();
                String job_date_posted = job_date_posted_by.substring(6,job_date_posted_by.indexOf("by"));
                String job_company_name = job_date_posted_by.substring(job_date_posted_by.indexOf("by") + 2);

                String job_company_logo = element.select("dd.vacCompany").text(); //No

                j.setTitle(job_title);
                j.setLocation(job_location);
                j.setCompany_logo(job_company_logo);
                j.setCompany_name(job_company_name);
                j.setDate_posted(job_date_posted);
                j.setDecription(job_decription);
                j.setType(job_type);
                j.setUrl(job_url);
                j.setSalary(job_salary);
                j.setSpecialism(Helper.getSpecialism(job_title));

                myJobs[i++] = j;
            }

            XMLHelper.Write(, myJobs);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
