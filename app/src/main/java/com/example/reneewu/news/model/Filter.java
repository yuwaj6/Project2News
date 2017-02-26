package com.example.reneewu.news.model;

import org.parceler.Parcel;

/**
 * Created by reneewu on 2/25/2017.
 */
@Parcel
public class Filter {
    public String sortOrder;
    public String query_beginDate;
    public boolean isCheckArtsChecked;
    public boolean isCheckFashionChecked;
    public boolean isCheckSportsChecked;


    public Filter() {
        sortOrder = "";
        query_beginDate = "";
        isCheckArtsChecked = false;
        isCheckFashionChecked = false;
        isCheckSportsChecked = false;
    }
}
