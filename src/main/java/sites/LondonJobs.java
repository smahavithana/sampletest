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
public class LondonJobs {

    //Notes
    //Featured jobs are not considering. To get featured job use "div.semi.clearfix"

    public static void getJobs(String jobsite) throws Exception {

        try {
            Document doc = Jsoup.connect(jobsite).get();
            Elements ele = doc.select("div[id=vacs]");

            int i = 0;

            int s = ele.select("div.lineage.clearfix").size();
            Job myJobs[] = new Job[s];

            for (Element element : ele.select("div.lineage.clearfix")) {

                Job j = new Job();

                String job_title = element.select("h3").text();
                String job_url = "http://www.londonjobs.co.uk" + element.select("a[href]").attr("href");
                String job_decription = element.select("p.jobDesc").text();
                String job_salary = element.select("dd.salary").text();
                String job_location = element.select("dd.location").text();
                String job_date_posted = element.select("dd.posted").text();
                String job_type = element.select("dd.duration").text();

                //No company details in Search Results Page for Normal Jobs
                String job_company_name = element.select("dd.vacCompany").text();
                String job_company_logo = element.select("dd.vacCompany").text();

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
