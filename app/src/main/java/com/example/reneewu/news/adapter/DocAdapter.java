package com.example.reneewu.news.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.reneewu.news.R;
import com.example.reneewu.news.model.Doc;
import com.example.reneewu.news.model.Multimedia;
import com.example.reneewu.news.viewHolder.ViewHolderHeadlineOnly;
import com.example.reneewu.news.viewHolder.ViewHolderWithThumbnail;

import java.util.List;

/**
 * Created by reneewu on 2/23/2017.
 */

public class DocAdapter extends
        RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private final int WITHTHUMBNAIL = 0, HEADLINEONLY = 1;

    // Store a member variable for the contacts
    private List<Doc> mDocs;

    // Store the context for easy access
    private Context mContext;

    // Pass in the contact array into the constructor
    public DocAdapter(Context context, List<Doc> docs) {
        mDocs = docs;
        mContext = context;
    }

    // Easy access to the context object in the recyclerview
    private Context getContext() {
        return mContext;
    }

    // Usually involves inflating a layout from XML and returning the holder
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder viewHolder;
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        switch (viewType) {
            case WITHTHUMBNAIL:
                View v1 = inflater.inflate(R.layout.item_content, parent, false);
                viewHolder = new ViewHolderWithThumbnail(v1);
                break;
            case HEADLINEONLY:
                View v2 = inflater.inflate(R.layout.item_content_headline, parent, false);
                viewHolder = new ViewHolderHeadlineOnly(v2);
                break;
            default:
                View v = inflater.inflate(R.layout.item_content, parent, false);
                viewHolder = new ViewHolderWithThumbnail(v);
                break;
        }

        return viewHolder;
    }

    // Involves populating data into the item through holder
    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int position) {
        if (viewHolder.getItemViewType() == WITHTHUMBNAIL) {
            ViewHolderWithThumbnail vt = (ViewHolderWithThumbnail) viewHolder;
            // Get the data model based on position
            Doc item = mDocs.get(position);

            // Set item views based on your views and data model
            TextView textView = vt.tvHeadline;
            textView.setText(item.getHeadline().getMain());
            TextView tag = vt.tvNewsDesk;
            tag.setText(item.getNewsDesk());
            Multimedia thumbnail = getThumbnailMedia(item.getMultimedia());
            String url = "http://www.nytimes.com/" + thumbnail.getUrl();

            Glide.with(mContext)
                    .load(url)
                    .into(vt.ivThumbnail);
        } else {
            ViewHolderHeadlineOnly vh = (ViewHolderHeadlineOnly) viewHolder;
            // Get the data model based on position
            Doc item = mDocs.get(position);

            // Set item views based on your views and data model
            TextView textView = vh.tvHeadline;
            textView.setText(item.getHeadline().getMain());

            TextView tag = vh.tvNewsDesk;
            tag.setText(item.getNewsDesk());
        }
    }

    @Override
    public int getItemCount() {
        return mDocs.size();
    }

    private Multimedia getThumbnailMedia(List<Multimedia> multimedia){
        for( Multimedia media : multimedia) {
            if(media.getSubtype().equals("thumbnail")) {
                return media;
            }
        }
        return null;
    }

    @Override
    public int getItemViewType(int position) {
        if (getThumbnailMedia(mDocs.get(position).getMultimedia())!=null) {
            return WITHTHUMBNAIL;
        } else {
            return HEADLINEONLY;
        }
    }
}

