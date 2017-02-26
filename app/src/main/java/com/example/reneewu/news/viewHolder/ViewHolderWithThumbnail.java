package com.example.reneewu.news.viewHolder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.reneewu.news.R;

/**
 * Created by reneewu on 2/25/2017.
 */

public class ViewHolderWithThumbnail extends RecyclerView.ViewHolder {
    // Your holder should contain a member variable
    // for any view that will be set as you render a row
    public TextView tvHeadline;
    public ImageView ivThumbnail;
    public TextView tvNewsDesk;

    // We also create a constructor that accepts the entire item row
    // and does the view lookups to find each subview
    public ViewHolderWithThumbnail(View itemView) {
        // Stores the itemView in a public final member variable that can be used
        // to access the context from any ViewHolder instance.
        super(itemView);

        tvHeadline = (TextView) itemView.findViewById(R.id.tvHeadline);
        ivThumbnail = (ImageView) itemView.findViewById(R.id.ivThumbnail);
        tvNewsDesk = (TextView) itemView.findViewById(R.id.tvTag);
    }
}