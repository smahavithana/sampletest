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
public class Monster {

    //Notes
    //Sometimes this is not working. Change the URL a bit, add a space & try again.
    public static void getJobs(String jobsite) throws Exception {

        try {
            Document doc = Jsoup.connect(jobsite).get();
            Elements ele = doc.select("section[id=resultsWrapper]");

            int i = 0;

            int s = ele.select("div.js_result_details-left").size();
            Job myJobs[] = new Job[s];

            for (Element element : ele.select("div.js_result_details-left")) {

                Job j = new Job();

                String job_title = element.select("h2").text();
                String job_url = element.select("a[href]").attr("href");
                String job_decription = element.select("p.jobDesc").text(); //No
                String job_salary = element.select("dd.salary").text();  //No
                String job_location = element.select("div.job-specs.job-specs-location").text();
                String job_date_posted = element.select("dd.posted").text();  //No
                String job_type = element.select("dd.duration").text();  //No


                String job_company_name = element.select("div.company").text();
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
