package de.marcreichelt.recyclerviewcrashdemo;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class SecondActivity extends AppCompatActivity {

    @SuppressWarnings("ConstantConditions")
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.second_activity);

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        final MyAdapter adapter = new MyAdapter();
        recyclerView.setAdapter(adapter);

        // this is important: we are going to increase the height of the first item
        //   -> e.g. some network data coming in that we can show
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                adapter.increaseSizeOfFirstItem();
            }
        }, 10);
    }

    public class MyAdapter extends RecyclerView.Adapter<MyViewHolder> {

        static final int VIEW_TYPE_FIRST = 0;
        static final int VIEW_TYPE_SECOND = 1;

        private boolean increaseHeightOfFirstItem = false;

        public MyAdapter() {
            setHasStableIds(true);
        }

        public void increaseSizeOfFirstItem() {
            increaseHeightOfFirstItem = true;
            notifyDataSetChanged();
        }

        @Override
        public long getItemId(int position) {
            return getItemViewType(position);
        }

        @Override
        public int getItemViewType(int position) {
            // for simplicity, position == viewType
            return position;
        }

        @Override
        public int getItemCount() {
            // we only have two items: type 0 and type 1
            return 2;
        }

        @Override
        public MyViewHolder onCreateViewHolder(ViewGroup group, int viewType) {
            View layout;
            switch (viewType) {

                case VIEW_TYPE_FIRST:
                    layout = inflate(group, R.layout.first_layout);     // height == wrap_content, but we're going to increase it after 10ms
                    return new MyViewHolder(layout);

                case VIEW_TYPE_SECOND:
                    layout = inflate(group, R.layout.second_layout);    // height == 370dp
                    return new MyViewHolder(layout);

                default:
                    break;
            }

            throw new IllegalArgumentException("view type not known: " + viewType);
        }

        private View inflate(ViewGroup viewGroup, @LayoutRes int layoutResId) {
            return LayoutInflater.from(SecondActivity.this).inflate(layoutResId, viewGroup, false);
        }

        @Override
        public void onBindViewHolder(MyViewHolder holder, int position) {
            if (position == 0 && increaseHeightOfFirstItem) {
                increaseHeight(holder.itemView);
            }
        }

        private void increaseHeight(View view) {
            ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
            layoutParams.height = dpToPx(350);
            view.setLayoutParams(layoutParams);
        }

    }

    private int dpToPx(float dp) {
        float scale = getResources().getDisplayMetrics().density;
        return (int) (dp * scale + 0.5f);
    }

    static class MyViewHolder extends RecyclerView.ViewHolder {

        public MyViewHolder(View itemView) {
            super(itemView);
        }

    }

}
