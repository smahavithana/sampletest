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
public class Totaljobs {

    public static void getJobs(String jobsite) throws Exception {

        try {
            Document doc = Jsoup.connect(jobsite).get();
            Elements ele = doc.select("div.col-xs-12.job-results.clearfix");

            int i = 0;

            int s = ele.select("div.job").size();
            Job myJobs[] = new Job[s];

            for (Element element : ele.select("div.job")) {

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
                j.setDate_posted(job_date_posted);
                j.setDecription(job_decription);
                j.setSalary(job_salary);
                j.setType(job_type);
                j.setUrl(job_url);
                j.setSpecialism(Helper.getSpecialism(job_title));

                myJobs[i++] = j;
            }

            XMLHelper.writeFromArray("Totaljobs", myJobs);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String getJobDescription(String jobDetailsLink) throws Exception {

        Document doc = Jsoup.connect(jobDetailsLink).get();
        return doc.select("div.job-description").outerHtml();
    }
}
