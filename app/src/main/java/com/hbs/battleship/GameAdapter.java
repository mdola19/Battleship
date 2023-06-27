package com.hbs.battleship;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.FrameLayout;

public class GameAdapter extends BaseAdapter {
    private Context mContext;
    private int[] mArray;

    public GameAdapter(Context context, int[] array) {
        mContext = context;
        mArray = array;
    }

    @Override
    public int getCount() {
        return mArray.length;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        FrameLayout frameLayout;
        if (convertView == null) {
            LayoutInflater inflater = LayoutInflater.from(mContext);
            frameLayout = (FrameLayout) inflater.inflate(R.layout.grid_cell_layout, parent, false);
        }

        else {
            frameLayout = (FrameLayout) convertView;
        }

        // Set the background color of the frame layout
//        System.out.println(position);
        frameLayout.setBackgroundColor(mArray[position % mArray.length]);

        frameLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    ((GameScreen) mContext).onCellClick(position);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        });

        return frameLayout;
    }
}
