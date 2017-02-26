package com.example.reneewu.news;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;

import com.example.reneewu.news.model.Filter;

import org.parceler.Parcels;

import static com.example.reneewu.news.R.id.datePicker;

/**
 * Created by reneewu on 2/24/2017.
 */

public class FilterFragment extends DialogFragment {

    private Spinner spinner;
    private DatePicker mDatePicker;
    private CheckBox checkArts;
    private CheckBox checkFashion;
    private CheckBox checkSports;
    private Filter filter;

    public FilterFragment() {
        // Empty constructor is required for DialogFragment
        // Make sure not to add arguments to the constructor
        // Use `newInstance` instead as shown below
    }

    public static FilterFragment newInstance(Filter existingFilter) {
        FilterFragment frag = new FilterFragment();
        Bundle args = new Bundle();
        if(existingFilter==null)
        {
            existingFilter = new Filter ();
        }
        args.putParcelable("filter",Parcels.wrap(existingFilter));
        frag.setArguments(args);
        return frag;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_filter, container);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        spinner = (Spinner) view.findViewById(R.id.spinner);
        mDatePicker = (DatePicker) view.findViewById(datePicker);
        checkArts = (CheckBox) view.findViewById(R.id.checkBox_art);
        checkFashion = (CheckBox) view.findViewById(R.id.checkBox_fashion);
        checkSports = (CheckBox) view.findViewById(R.id.checkBox_sports);
        Button btnSave = (Button) view.findViewById(R.id.button_save);

        filter = (Filter) Parcels.unwrap(getArguments().getParcelable("filter"));

        // Update view as exiting filter
        if(filter.sortOrder != "")
            setSpinnerToValue(spinner,filter.sortOrder);

        checkArts.setChecked(filter.isCheckArtsChecked);
        checkFashion.setChecked(filter.isCheckFashionChecked);
        checkSports.setChecked(filter.isCheckSportsChecked);


        if(filter.query_beginDate!="")
            mDatePicker.updateDate(Integer.parseInt(filter.query_beginDate.substring(0,4)),
                    Integer.parseInt(filter.query_beginDate.substring(4,6))-1,
                    Integer.parseInt(filter.query_beginDate.substring(6,8)));


        btnSave.setOnClickListener(v -> {
            FilterFragmentListner listener = (FilterFragmentListner) getActivity();

            //begin date
            int month = mDatePicker.getMonth()+1;
            String s_month;
            if (month < 10){
                s_month = "0" + month;
            }
            else{
                s_month = Integer.toString(month);
            }

            String date =  Integer.toString(mDatePicker.getYear()) +
                    s_month +
                    Integer.toString(mDatePicker.getDayOfMonth());
            Log.v("Date", date);
            filter.query_beginDate = date;

            // sort order
            String value = spinner.getSelectedItem().toString();
            filter.sortOrder = value;

            filter.isCheckArtsChecked = checkArts.isChecked();
            filter.isCheckFashionChecked= checkFashion.isChecked();
            filter.isCheckSportsChecked = checkSports.isChecked();

            //listener.onFinishFilterDialog( value, date , fq);
            listener.onFinishFilterDialog( filter );
            dismiss();
        });
    }

    public interface FilterFragmentListner{
        void onFinishFilterDialog(String sort, String date, String fq);
        void onFinishFilterDialog(Filter filter);
    }

    public void setSpinnerToValue(Spinner spinner, String value) {
        int index = 0;
        SpinnerAdapter adapter = spinner.getAdapter();
        for (int i = 0; i < adapter.getCount(); i++) {
            if (adapter.getItem(i).equals(value)) {
                index = i;
                break; // terminate loop
            }
        }
        spinner.setSelection(index);
    }
}
