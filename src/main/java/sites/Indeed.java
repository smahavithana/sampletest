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
public class Indeed {

    public static void getJobs(String jobsite, String jobType) throws Exception {

        jobsite = jobsite + "&jt=" + jobType;

        try {
            Document doc = Jsoup.connect(jobsite).get();
            Elements ele = doc.select("td[id=resultsCol]");

            int i = 0;

            int s = ele.select("div.row.result").size();
            Job myJobs[] = new Job[s];

            for (Element element : ele.select("div.row.result")) {

                Job j = new Job();

                String job_title = element.select("a[href]").attr("title");
                String job_url =  element.select("a[href]").attr("href");
                String job_decription = element.select("span.summary").text();
                String job_salary = element.select("nobr").text();
                String job_location = element.select("span.location").text();
                String job_date_posted = element.select("span.date").text();
                String job_type = jobType;

                String job_company_name = element.select("span.company").text();
                String job_company_logo = element.select("dd.vacCompany").text(); //No data

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
