package com.example.reneewu.news;

import java.util.List;

/**
 * Created by reneewu on 2/20/2017.
 */

public class SearchResult {
    public Response response;
    public String status;

    public class Response {
        public List<Document> docs;
    }
}

