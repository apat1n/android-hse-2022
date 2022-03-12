package com.example.tictactoe;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

public class GridViewAdapter extends BaseAdapter {
    private final Context mContext;

    public GridViewAdapter(Context mContext) {
        this.mContext = mContext;
    }

    @Override
    public int getCount() {
        return 3 * 3;
    }

    @Override
    public Object getItem(int i) {
        if (AppContext.board == null) {
            return null;
        }
        return AppContext.board[i / 3][i % 3];
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ImageView imageView;
        if (view != null) {
            imageView = (ImageView) view;
        } else {
            imageView = new ImageView(mContext);
            final int size = (int) (AppContext.gameFieldSize / 3);
            imageView.setLayoutParams(new GridView.LayoutParams(size, size));
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            if (AppContext.board != null) {
                Player playerOnPosition = AppContext.board[i / 3][i % 3];
                if (playerOnPosition != null) {
                    imageView.setImageResource(playerOnPosition.getDrawableId());
                }
            }
        }

        return imageView;
    }
}
