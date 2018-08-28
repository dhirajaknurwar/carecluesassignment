package com.master.care.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class DoctorsDataModel implements Serializable {


    @SerializedName("meta")
    @Expose
    private Meta meta;
    @SerializedName("data")
    @Expose
    private List<DataModel> data = null;

    public Meta getMeta() {
        return meta;
    }

    public void setMeta(Meta meta) {
        this.meta = meta;
    }

    public List<DataModel> getData() {
        return data;
    }

    public void setData(List<DataModel> data) {
        this.data = data;
    }


    public static class DataModel implements Serializable {

        @SerializedName("id")
        @Expose
        private int id;
        @SerializedName("first_name")
        @Expose
        private String firstName;
        @SerializedName("last_name")
        @Expose
        private String lastName;
        @SerializedName("gender")
        @Expose
        private String gender;
        @SerializedName("date_of_birth")
        @Expose
        private String dateOfBirth;
        @SerializedName("rating")
        @Expose
        private String rating;
        @SerializedName("years_of_experience")
        @Expose
        private int yearsOfExperience;
        @SerializedName("uri")
        @Expose
        private String uri;
        @SerializedName("qualifications")
        @Expose
        private List<Qualification> qualifications = null;

        @SerializedName("links")
        @Expose
        private List<Links> links = null;

        public List<Links> getLinks() {
            return links;
        }

        public void setLinks(List<Links> links) {
            this.links = links;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getFirstName() {
            return firstName;
        }

        public void setFirstName(String firstName) {
            this.firstName = firstName;
        }

        public String getLastName() {
            return lastName;
        }

        public void setLastName(String lastName) {
            this.lastName = lastName;
        }

        public String getGender() {
            return gender;
        }

        public void setGender(String gender) {
            this.gender = gender;
        }

        public String getDateOfBirth() {
            return dateOfBirth;
        }

        public void setDateOfBirth(String dateOfBirth) {
            this.dateOfBirth = dateOfBirth;
        }

        public String getRating() {
            return rating;
        }

        public void setRating(String rating) {
            this.rating = rating;
        }

        public int getYearsOfExperience() {
            return yearsOfExperience;
        }

        public void setYearsOfExperience(int yearsOfExperience) {
            this.yearsOfExperience = yearsOfExperience;
        }

        public String getUri() {
            return uri;
        }

        public void setUri(String uri) {
            this.uri = uri;
        }

        public List<Qualification> getQualifications() {
            return qualifications;
        }

        public void setQualifications(List<Qualification> qualifications) {
            this.qualifications = qualifications;
        }

    }


    public static class Links implements Serializable {

        @SerializedName("rel")
        @Expose
        private String rel;
        @SerializedName("href")
        @Expose
        private String href;


        public String getHref() {
            return href;
        }

        public String getRel() {
            return rel;
        }

        public void setRel(String rel) {
            this.rel = rel;
        }

        public void setHref(String href) {
            this.href = href;
        }
    }

    public static class Meta implements Serializable {

        @SerializedName("total")
        @Expose
        private int total;
        @SerializedName("page_size")
        @Expose
        private int pageSize;
        @SerializedName("page_no")
        @Expose
        private int pageNo;
        @SerializedName("type")
        @Expose
        private String type;

        public int getTotal() {
            return total;
        }

        public void setTotal(int total) {
            this.total = total;
        }

        public int getPageSize() {
            return pageSize;
        }

        public void setPageSize(int pageSize) {
            this.pageSize = pageSize;
        }

        public int getPageNo() {
            return pageNo;
        }

        public void setPageNo(int pageNo) {
            this.pageNo = pageNo;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

    }

    public static class Qualification implements Serializable {

        @SerializedName("id")
        @Expose
        private int id;
        @SerializedName("degree")
        @Expose
        private String degree;
        @SerializedName("specialty")
        @Expose
        private String specialty;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getDegree() {
            return degree;
        }

        public void setDegree(String degree) {
            this.degree = degree;
        }

        public String getSpecialty() {
            return specialty;
        }

        public void setSpecialty(String specialty) {
            this.specialty = specialty;
        }

    }

}
