package util;

/* *
 * Created by Sampath on 16/02/2017.
 */
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import com.Job;
import org.w3c.dom.*;
import sites.CWJobs;

public class XMLHelper {

    private static String write_file_name = "MyCrawler/Data/jobs_";
    private static String read_file_name = "MyCrawler/Data/jobs_20170219_0948.xml";

    public static void Write(String site, Job[] b) throws Exception {

        DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder docBuilder = docFactory.newDocumentBuilder();

        // root elements
        Document doc = docBuilder.newDocument();
        Element rootElement = doc.createElement("Jobs");
        doc.appendChild(rootElement);

        for (int i=0;i<b.length;i++) {

            String id = "job_" + (i+1);

            Element job = doc.createElement("Job");
            rootElement.appendChild(job);

            Attr attr = doc.createAttribute("id");
            attr.setValue(id);
            job.setAttributeNode(attr);

            Element title = doc.createElement("title");
            title.appendChild(doc.createTextNode(b[i].getTitle()));
            job.appendChild(title);

            Element url = doc.createElement("url");
            url.appendChild(doc.createTextNode(b[i].getUrl()));
            job.appendChild(url);

            Element location = doc.createElement("location");
            location.appendChild(doc.createTextNode(b[i].getLocation()));
            job.appendChild(location);

            Element date_posted = doc.createElement("datePosted");
            date_posted.appendChild(doc.createTextNode(b[i].getDate_posted()));
            job.appendChild(date_posted);

            Element date_expire = doc.createElement("dateExpire");
            date_expire.appendChild(doc.createTextNode(b[i].getDate_expire()));
            job.appendChild(date_expire);

            Element date_app_close = doc.createElement("dateAppClose");
            date_app_close.appendChild(doc.createTextNode(b[i].getDate_app_close()));
            job.appendChild(date_app_close);

            Element decription = doc.createElement("decription");
            decription.appendChild(doc.createTextNode(b[i].getDecription()));
            job.appendChild(decription);

            Element salary = doc.createElement("salary");
            salary.appendChild(doc.createTextNode(b[i].getSalary()));
            job.appendChild(salary);

            Element company_name = doc.createElement("companyName");
            company_name.appendChild(doc.createTextNode(b[i].getCompany_name()));
            job.appendChild(company_name);

            Element company_logo = doc.createElement("companyLogo");
            company_logo.appendChild(doc.createTextNode(b[i].getCompany_logo()));
            job.appendChild(company_logo);

            Element specialism = doc.createElement("specialism");
            specialism.appendChild(doc.createTextNode(b[i].getSpecialism()));
            job.appendChild(specialism);

            Element type = doc.createElement("type");
            type.appendChild(doc.createTextNode(b[i].getType()));
            job.appendChild(type);

            Element longDescription = doc.createElement("longDescription");
            longDescription.appendChild(doc.createTextNode(b[i].getLong_description()));
            job.appendChild(longDescription);

        }

        // write the content into xml file
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmm").format(Calendar.getInstance().getTime());
        String fileName = write_file_name + timeStamp + ".xml";

        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();
        DOMSource source = new DOMSource(doc);
        StreamResult result = new StreamResult(new File(fileName));

        transformer.transform(source, result);

        System.out.println("File saved!");
    }

    public static void ReadXMLAndWriteToNew(){

        try {

            File fXmlFile = new File(read_file_name);
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(fXmlFile);

            doc.getDocumentElement().normalize();
            NodeList nList = doc.getElementsByTagName("Job");

            int size = nList.getLength();
            Job[] myJobs = new Job[size];
            int i = 0;

            for (int temp = 0; temp < size; temp++) {

                Node nNode = nList.item(temp);
                Job j = new Job();

                if (nNode.getNodeType() == Node.ELEMENT_NODE) {

                    Element eElement = (Element) nNode;

                    System.out.println("Reading Job id : " + eElement.getAttribute("id"));

                    j.setTitle(eElement.getElementsByTagName("title").item(0).getTextContent());
                    j.setLocation(eElement.getElementsByTagName("location").item(0).getTextContent());
                    j.setUrl(eElement.getElementsByTagName("url").item(0).getTextContent());
                    j.setSalary(eElement.getElementsByTagName("salary").item(0).getTextContent());
                    j.setDecription(eElement.getElementsByTagName("decription").item(0).getTextContent());
                    j.setDate_posted(eElement.getElementsByTagName("datePosted").item(0).getTextContent());
                    j.setDate_expire(eElement.getElementsByTagName("dateExpire").item(0).getTextContent());
                    j.setDate_app_close(eElement.getElementsByTagName("dateAppClose").item(0).getTextContent());
                    j.setCompany_name(eElement.getElementsByTagName("companyName").item(0).getTextContent());
                    j.setSpecialism(eElement.getElementsByTagName("specialism").item(0).getTextContent());
                    j.setCompany_logo(eElement.getElementsByTagName("companyLogo").item(0).getTextContent());
                    j.setType(eElement.getElementsByTagName("type").item(0).getTextContent());


                    //Collect Job Descriptions from Job Details Pages via WebDriver
                    //JobDetailsLoader jobLoader = new JobDetailsLoader();
                    //j.setLong_description(jobLoader.getJobDescription("cwjobs",eElement.getElementsByTagName("url").item(0).getTextContent()));
                    j.setLong_description(CWJobs.getJobDescription(eElement.getElementsByTagName("url").item(0).getTextContent()));

                }

                myJobs[i++] = j;
            }

            Write("site_name", myJobs);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}