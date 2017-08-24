package com.tbd.birthdayplanner;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.ListFragment;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ListView;

public class PlanListActivity extends FragmentActivity {

    static final int PAGE_NUMBER = 3;
    PlanListAdapter adapter;
    ViewPager pager;

    ImageButton planButton;
    ImageButton followButton;
    ImageButton configButton;
    ImageButton[] buttonArray;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plan_list);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        adapter = new PlanListAdapter(getSupportFragmentManager());

        pager = (ViewPager)findViewById(R.id.pager);
        pager.setAdapter(adapter);
        createMenuButtons();
        pager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            public void onPageScrollStateChanged(int state) {}
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {}
            public void onPageSelected(int position) {
                changeButtonDisplay(position);
            }
        });
    }

    private void createMenuButtons() {
        planButton = (ImageButton)findViewById(R.id.plan);
        planButton.setSelected(true);
        planButton.setBackgroundColor(0xFF5262AF);
        followButton = (ImageButton)findViewById(R.id.follow);
        configButton = (ImageButton)findViewById(R.id.config);
        buttonArray = new ImageButton[]{planButton, followButton, configButton};
        for(int index = 0; index < buttonArray.length; index++){
            ImageButton button = buttonArray[index];
            final int indexFinal = index;
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    pager.setCurrentItem(indexFinal);
                }
            });
        }
    }

    private void changeButtonDisplay(int indexFinal) {
        ImageButton selectedButton = buttonArray[indexFinal];
        selectedButton.setSelected(true);
        selectedButton.setBackgroundColor(0xFF5262AF);

        //Unselect other buttons
        for(int indexOtherButton = 0; indexOtherButton < buttonArray.length; indexOtherButton++){
            if(indexOtherButton != indexFinal){
                buttonArray[indexOtherButton].setSelected(false);
                buttonArray[indexOtherButton].setBackgroundColor(0xFF3F51B5);
            }
        }
    }

    public static class PlanListAdapter extends FragmentPagerAdapter {
        public PlanListAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public int getCount() {
            return PAGE_NUMBER;
        }

        @Override
        public Fragment getItem(int position) {
            return ArrayListFragment.newInstance(position);
        }
    }

    public static class ArrayListFragment extends ListFragment {
        int mNum;

        /**
         * Create a new instance of CountingFragment, providing "num"
         * as an argument.
         */
        static ArrayListFragment newInstance(int num) {
            ArrayListFragment f = new ArrayListFragment();

            // Supply num input as an argument.
            Bundle args = new Bundle();
            args.putInt("num", num);
            f.setArguments(args);

            return f;
        }

        /**
         * When creating, retrieve this instance's number from its arguments.
         */
        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            mNum = getArguments() != null ? getArguments().getInt("num") : 1;
        }

        /**
         * The Fragment's UI is just a simple text view showing its
         * instance number.
         */
        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View v = inflater.inflate(R.layout.fragment_pager_list, container, false);
            return v;
        }

        @Override
        public void onActivityCreated(Bundle savedInstanceState) {
            super.onActivityCreated(savedInstanceState);
            setListAdapter(new ArrayAdapter<String>(getActivity(),
                    android.R.layout.simple_list_item_1, Cheeses.CHEESES));
        }

        @Override
        public void onListItemClick(ListView l, View v, int position, long id) {
            Log.i("FragmentList", "Item clicked: " + id);
        }
    }
}