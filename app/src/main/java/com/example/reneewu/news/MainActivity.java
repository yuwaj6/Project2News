package com.example.reneewu.news;

import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.support.customtabs.CustomTabsIntent;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.reneewu.news.adapter.DocAdapter;
import com.example.reneewu.news.helper.EndlessRecyclerViewScrollListener;
import com.example.reneewu.news.helper.ItemClickSupport;
import com.example.reneewu.news.model.Doc;
import com.example.reneewu.news.model.Filter;
import com.example.reneewu.news.model.NYTimes;

import java.io.IOException;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity implements FilterFragment.FilterFragmentListner {
    private RecyclerView rvResult;
    private String sortOrder;
    private String query;
    private String query_beginDate;
    private String query_fq;
    private Filter filter;
    private List<Doc> docs;
    private DocAdapter adapter;
    // Store a member variable for the listener
    private EndlessRecyclerViewScrollListener scrollListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Lookup the recyclerview in activity layout
        rvResult = (RecyclerView) findViewById(R.id.rvResults);

        ItemClickSupport.addTo(rvResult).setOnItemClickListener(
                (recyclerView, position, v) -> {
                    // do it
                    Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.ic_action_name);
                    String url = docs.get(position).getWebUrl();

                    Intent intent = new Intent(Intent.ACTION_SEND);
                    intent.setType("text/plain");
                    intent.putExtra(Intent.EXTRA_TEXT, url);
                    int requestCode = 100;

                    PendingIntent pendingIntent = PendingIntent.getActivity(getApplicationContext(),
                            requestCode,
                            intent,
                            PendingIntent.FLAG_UPDATE_CURRENT);
                    CustomTabsIntent.Builder builder = new CustomTabsIntent.Builder();

                    // Map the bitmap, text, and pending intent to this icon
                    // Set tint to be true so it matches the toolbar color
                    builder.setActionButton(bitmap, "Share Link", pendingIntent, true);
                    // set toolbar color and/or setting custom actions before invoking build()
                    // Once ready, call CustomTabsIntent.Builder.build() to create a CustomTabsIntent

                    CustomTabsIntent customTabsIntent = builder.build();

                    // and launch the desired Url with CustomTabsIntent.launchUrl()
                    customTabsIntent.launchUrl(getApplicationContext(), Uri.parse(url));
                }
        );
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        if(!isNetworkAvailable()&&!isOnline())
            Toast.makeText(getBaseContext(), "Cannot connect to internet. Please check your network permission or contact your network supplier", Toast.LENGTH_LONG).show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_search, menu);
        MenuItem searchItem = menu.findItem(R.id.action_search);
        final SearchView searchView = (SearchView) MenuItemCompat.getActionView(searchItem);
        searchView.setOnQueryTextListener(new android.support.v7.widget.SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String input) {
                // perform query here
                query = input;
                search(0);

                // workaround to avoid issues with some emulators and keyboard devices firing twice if a keyboard enter is used
                // see https://code.google.com/p/android/issues/detail?id=24599
                searchView.clearFocus();

                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        if (id == R.id.menu_filter) {
            showFilterDialog();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void setList() {
        // Create adapter passing in the sample user data
        adapter = new DocAdapter(this, docs);
        // Attach the adapter to the recyclerview to populate items
        rvResult.setAdapter(adapter);

        // First param is number of columns and second param is orientation i.e Vertical or Horizontal
        StaggeredGridLayoutManager gridLayoutManager =
                new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        // Attach the layout manager to the recycler view
        rvResult.setLayoutManager(gridLayoutManager);

        // Retain an instance so that you can call `resetState()` for fresh searches
        scrollListener = new EndlessRecyclerViewScrollListener(gridLayoutManager) {
            @Override
            public void onLoadMore(int page, int totalItemsCount, RecyclerView view) {
                // Triggered only when new data needs to be appended to the list
                // Add whatever code is needed to append new items to the bottom of the list
                search(page);
            }
        };
        // Adds the scroll listener to RecyclerView
        rvResult.addOnScrollListener(scrollListener);

    }

    public void search(final int page) {
        Log.v("page",Integer.toString(page));

        String apiKey = "3f632b3a47a14f81ab604fbf70fbba34";
        if(docs != null && page == 0){
            // 1. First, clear the array of data
            docs.clear();
            // 2. Notify the adapter of the update
            adapter.notifyDataSetChanged(); // or notifyItemRangeRemoved
            // 3. Reset endless scroll listener when performing a new search
            scrollListener.resetState();
        }

        /*
        RequestInterceptor requestInterceptor = new RequestInterceptor() {
            @Override
            public void intercept(RequestFacade request) {
                request.addQueryParam("apikey", apiKey);
            }
        };*/

        // Add the interceptor to OkHttpClient
        //OkHttpClient client = new OkHttpClient();
        //client.interceptors().add(requestInterceptor);

        Retrofit retrofit = new Retrofit.Builder()
                //.client(client)
                .baseUrl("https://api.nytimes.com/svc/search/v2/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        nytimesService service = retrofit.create(nytimesService.class);
        Call<NYTimes> call;

        if (filter != null) {
            if (filter.isCheckArtsChecked || filter.isCheckFashionChecked || filter.isCheckSportsChecked) {
                String fq = "news_desk:(";
                if (filter.isCheckArtsChecked)
                    fq += "\"Arts\" ";
                if (filter.isCheckFashionChecked)
                    fq += "\"Fashion & Style\" ";
                if (filter.isCheckSportsChecked)
                    fq += "\"Sports\" ";

                fq += ")";

                call = service.articlesearch(apiKey, query, page, filter.sortOrder, filter.query_beginDate, fq);
            } else if (sortOrder != "" && query_beginDate != "") {
                call = service.articlesearch(apiKey, query, page, filter.sortOrder, filter.query_beginDate);
            } else {
                call = service.articlesearch(apiKey, query, page, filter.sortOrder);
            }
        } else {
            call = service.articlesearch(apiKey, query, page);
        }

        call.enqueue(new Callback<NYTimes>() {
            @Override
            public void onResponse(Call<NYTimes> call, Response<NYTimes> response) {
                int status = response.code();
                Log.v("code", String.valueOf(status));
                NYTimes result = response.body();
                Log.v("response", result.getResponse().toString());

                List<Doc> responseDocs = result.getResponse().getDocs();

                if(docs !=null){
                    int position = docs.size();
                    int count = responseDocs.size();
                    docs.addAll(responseDocs);
                    adapter.notifyItemRangeInserted(position, count);
                }
                else{
                    docs = responseDocs;
                    setList();
                }
            }

            @Override
            public void onFailure(Call<NYTimes> call, Throwable t) {
                Toast.makeText(getBaseContext(), "Failed to search articles. Please try again later.", Toast.LENGTH_LONG).show();
            }
        });
    }

    private void showFilterDialog() {
        FragmentManager fm = getSupportFragmentManager();
        FilterFragment filterDialogFragment = FilterFragment.newInstance(filter);
        filterDialogFragment.show(fm, "fragment_filter");
    }

    @Override
    public void onFinishFilterDialog(String sort, String beginDate, String fq) {
        sortOrder = sort;
        query_beginDate = beginDate;
        query_fq = fq;

        search(0);
    }

    public void onFinishFilterDialog(Filter queryFilter) {
        filter = queryFilter;

        // reset page as 0
        search(0);
    }

    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnectedOrConnecting();
    }

    private boolean isOnline() {
        Runtime runtime = Runtime.getRuntime();
        try {
            Process ipProcess = runtime.exec("/system/bin/ping -c 1 8.8.8.8");
            int     exitValue = ipProcess.waitFor();
            return (exitValue == 0);
        } catch (IOException e)          { e.printStackTrace(); }
        catch (InterruptedException e) { e.printStackTrace(); }
        return false;
    }
}
