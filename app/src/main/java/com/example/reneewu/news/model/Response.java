package com.example.reneewu.news.model;

import java.util.List;

/**
 * Created by reneewu on 2/23/2017.
 */

public class Response {

    //private Meta meta;
    private List<Doc> docs = null;
    //private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /*
    public Meta getMeta() {
        return meta;
    }

    public void setMeta(Meta meta) {
        this.meta = meta;
    }
    */

    public List<Doc> getDocs() {
        return docs;
    }

    public void setDocs(List<Doc> docs) {
        this.docs = docs;
    }
    /*
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }
    */
}
