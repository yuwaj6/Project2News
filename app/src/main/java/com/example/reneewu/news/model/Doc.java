package com.example.reneewu.news.model;

import java.util.List;

/**
 * Created by reneewu on 2/23/2017.
 */

public class Doc {
    private List<Multimedia> multimedia = null;
    private Headline headline;
    private String web_url;
    private String news_desk;

    public String getWebUrl() {
        return web_url;
    }

    public void setWebUrl(String webUrl) {
        this.web_url = webUrl;
    }

    public List<Multimedia> getMultimedia() {
        return multimedia;
    }

    public void setMultimedia(List<Multimedia> multimedia) {
        this.multimedia = multimedia;
    }

    public Headline getHeadline() {
        return headline;
    }

    public void setHeadline(Headline headline) {
        this.headline = headline;
    }



    public String getNewsDesk() {
        return news_desk;
    }

    public void setNewsDesk(String newsDesk) {
        this.news_desk = newsDesk;
    }
    /*

    private String snippet;
    private String leadParagraph;
    private Object _abstract;
    private Object printPage;
    private List<Object> blog = null;
    private String source;

    private List<Keyword> keywords = null;
    private String pubDate;
    private String documentType;

    private String sectionName;
    private String subsectionName;
    private Byline byline;
    private String typeOfMaterial;
    private String id;
    private Object wordCount;
    private Object slideshowCredits;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();



    public String getSnippet() {
        return snippet;
    }

    public void setSnippet(String snippet) {
        this.snippet = snippet;
    }

    public String getLeadParagraph() {
        return leadParagraph;
    }

    public void setLeadParagraph(String leadParagraph) {
        this.leadParagraph = leadParagraph;
    }

    public Object getAbstract() {
        return _abstract;
    }

    public void setAbstract(Object _abstract) {
        this._abstract = _abstract;
    }

    public Object getPrintPage() {
        return printPage;
    }

    public void setPrintPage(Object printPage) {
        this.printPage = printPage;
    }

    public List<Object> getBlog() {
        return blog;
    }

    public void setBlog(List<Object> blog) {
        this.blog = blog;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }



    public List<Keyword> getKeywords() {
        return keywords;
    }

    public void setKeywords(List<Keyword> keywords) {
        this.keywords = keywords;
    }

    public String getPubDate() {
        return pubDate;
    }

    public void setPubDate(String pubDate) {
        this.pubDate = pubDate;
    }

    public String getDocumentType() {
        return documentType;
    }

    public void setDocumentType(String documentType) {
        this.documentType = documentType;
    }

    public String getNewsDesk() {
        return newsDesk;
    }

    public void setNewsDesk(String newsDesk) {
        this.newsDesk = newsDesk;
    }

    public String getSectionName() {
        return sectionName;
    }

    public void setSectionName(String sectionName) {
        this.sectionName = sectionName;
    }

    public String getSubsectionName() {
        return subsectionName;
    }

    public void setSubsectionName(String subsectionName) {
        this.subsectionName = subsectionName;
    }

    public Byline getByline() {
        return byline;
    }

    public void setByline(Byline byline) {
        this.byline = byline;
    }

    public String getTypeOfMaterial() {
        return typeOfMaterial;
    }

    public void setTypeOfMaterial(String typeOfMaterial) {
        this.typeOfMaterial = typeOfMaterial;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Object getWordCount() {
        return wordCount;
    }

    public void setWordCount(Object wordCount) {
        this.wordCount = wordCount;
    }

    public Object getSlideshowCredits() {
        return slideshowCredits;
    }

    public void setSlideshowCredits(Object slideshowCredits) {
        this.slideshowCredits = slideshowCredits;
    }

    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }*/

}
