package com.example.reneewu.news.viewHolder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.example.reneewu.news.R;

/**
 * Created by reneewu on 2/25/2017.
 */

public class ViewHolderHeadlineOnly extends RecyclerView.ViewHolder {
    public TextView tvHeadline;

    public ViewHolderHeadlineOnly(View itemView) {
        super(itemView);

        tvHeadline = (TextView) itemView.findViewById(R.id.tvHeadline);
    }
}