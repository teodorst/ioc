package com.example.ioc.evshare.listeners;

import android.util.Log;
import android.widget.AbsListView;


public abstract class EndlessScrollListener implements AbsListView.OnScrollListener {
    String TAG = "EndlessScrollListener";
    // The minimum number of items to have below your current scroll position
    // before loading more.
    private int visibleTreshold = 5;

    // The current offset index of data you have loaded
    private int currentPage = 0;

    // The total number of items in the dataset after the last load
    private int previousTotalItemCount = 0;

    // True if we are still waiting for the last set of data to load.
    private boolean loading = true;
    // Sets the starting page index
    private int startingPageIndex = 0;

    public EndlessScrollListener() {
    }

    public EndlessScrollListener(int visibleTreshold) {
        this.visibleTreshold = visibleTreshold;
    }

    public EndlessScrollListener(int visibleThreshold, int startPage) {
        this.visibleTreshold = visibleThreshold;
        this.startingPageIndex = startPage;
        this.currentPage = startPage;
    }

    @Override
    public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
        Log.d(TAG, "onScroll: " + firstVisibleItem + " " + visibleItemCount + " " + totalItemCount + " ");

        // If list is invalidated because number of total items is 0 and previous number isn't
        if (totalItemCount < previousTotalItemCount) {
            this.currentPage = this.startingPageIndex;
            this.previousTotalItemCount = totalItemCount;
            if (totalItemCount == 0) {
                this.loading = true;
            }
        }

        // if it's still loading we check if data has been updated
        if (loading && (totalItemCount > previousTotalItemCount)) {
            loading = false;
            previousTotalItemCount =  totalItemCount;
            currentPage ++;
        }


        // if it isn't currently loading, we check if we need to load next page of data
        if (!loading && (firstVisibleItem + visibleItemCount + visibleTreshold) >= totalItemCount) {
            Log.d(TAG, "onScroll: Loading new data");

            loading = onLoadMore(currentPage + 1, totalItemCount);
        }

    }


    public abstract boolean onLoadMore(int page, int totalItemCount);


    @Override
    public void onScrollStateChanged(AbsListView view, int scrollState) {
        // Don't take any action on changed
    }

}
