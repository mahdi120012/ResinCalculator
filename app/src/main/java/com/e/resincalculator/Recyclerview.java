package com.e.resincalculator;

import android.content.Context;
import android.view.View;
import android.widget.ProgressBar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;
import java.util.ArrayList;

public class Recyclerview {

    public static void define_recyclerviewYh(final Context c, RecyclerView recyclerView,
                                             final RecyclerAdapter recyclerAdapter,
                                             final ArrayList<RecyclerModel> recyclerModels,
                                             final ProgressBar progressBar) {

        recyclerView.setHasFixedSize(true);
        recyclerView.setItemViewCacheSize(20);
        recyclerView.setDrawingCacheEnabled(true);
        recyclerView.setDrawingCacheQuality(View.DRAWING_CACHE_QUALITY_HIGH);
        recyclerView.setNestedScrollingEnabled(false);
        recyclerView.setLayoutManager(new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.HORIZONTAL));
        recyclerView.setAdapter(recyclerAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(c, LinearLayoutManager.HORIZONTAL, true));

        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {

            // for this tutorial, this is the ONLY method that we need, ignore the rest
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if (dy > 0) {
                    // Recycle view scrolling downwards...
                    // this if statement detects when user reaches the end of recyclerView, this is only time we should load more
                    if (!recyclerView.canScrollVertically(RecyclerView.FOCUS_DOWN)) {
                        // remember "!" is the same as "== false"
                        // here we are now allowed to load more, but we need to be careful
                        // we must check if itShouldLoadMore variable is true [unlocked]
                        if (LoadData.itShouldLoadMore) {
                            //LoadData.loadMoreAllMahsolInCat(c, recyclerAdapter, recyclerModels,cat2Name);
                        }
                    }

                }
            }
        });
    }

}
