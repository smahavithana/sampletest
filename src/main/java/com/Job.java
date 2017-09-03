package com;

/* *
 * Created by Sampath on 16/02/2017.
 */
public class Job {

    private String title;
    private String url;
    private String location;
    private String date_posted;
    private String date_expire;
    private String date_app_close;
    private String decription;
    private String type;
    private String salary;
    private String company_name;
    private String company_logo;
    private String specialism;
    private String long_description;

    public String getTitle() {
        return title;
    }

    public String getUrl() {
        return url;
    }

    public String getLocation() {
        return location;
    }

    public String getDate_posted() {
        return date_posted;
    }

    public String getDate_expire() {
        return date_expire;
    }
    public String getDate_app_close() {
        return date_app_close;
    }

    public String getDecription() {
        return decription;
    }

    public String getType() {
        return type;
    }

    public String getSpecialism() {  return specialism; }

    public String getCompany_name() {
        return company_name;
    }

    public String getCompany_logo() {
        return company_logo;
    }

    public String getSalary() { return salary; }

    public String getLong_description() {  return long_description; }


    public void setTitle(String title) {
        this.title = title;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void setDate_posted(String date_posted) {
        this.date_posted = date_posted;
    }

    public void setDate_expire(String date_expire) {
        this.date_expire = date_expire;
    }

    public void setDate_app_close(String date_app_close) {
        this.date_app_close = date_app_close;
    }
    public void setDecription(String decription) {
        this.decription = decription;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setCompany_name(String company_name) {
        this.company_name = company_name;
    }

    public void setCompany_logo(String company_logo) {
        this.company_logo = company_logo;
    }

    public void setSpecialism(String specialism) {  this.specialism = specialism; }

    public void setSalary(String salary) { this.salary = salary; }

    public void setLong_description(String long_description) { this.long_description = long_description; }


    public Job() {
    }
}
