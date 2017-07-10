package com.raywenderlich.alltherecipes;

import android.content.Context;
import android.graphics.Typeface;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by luoyinfeng on 4/2/17.
 */

public class RecipeAdapter extends BaseAdapter {
    private Context mContext;
    private LayoutInflater mInflater;
    private ArrayList<Recipe> mDataSource;
    private static final HashMap<String, Integer> LABEL_COLORS = new HashMap<String, Integer>() {{
        put("1", R.color.colorLowCarb);
        put("2", R.color.colorLowFat);
        put("3", R.color.colorLowSodium);
        put("4", R.color.colorMediumCarb);
        put("5", R.color.colorVegetarian);
        put("6", R.color.colorLowCarb);
        put("7", R.color.colorLowFat);
        put("8", R.color.colorLowSodium);
        put("8", R.color.colorMediumCarb);
        put("9", R.color.colorVegetarian);


        put("Balanced", R.color.colorBalanced);
        put("Chinese", R.color.colorLowCarb);
        put("American", R.color.colorLowFat);
        put("Fast-food", R.color.colorLowSodium);
        put("India", R.color.colorMediumCarb);
        put("Moxige", R.color.colorVegetarian);
        put("Buffet", R.color.colorBalanced);
    }};
    public RecipeAdapter(Context context, ArrayList<Recipe> items) {
        mContext = context;
        mDataSource = items;
        mInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return mDataSource.size();
    }

    @Override
    public Object getItem(int position) {
        return mDataSource.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View rowView = mInflater.inflate(R.layout.list_item_recipe, parent, false);
        // Get title element
        TextView titleTextView =
                (TextView) rowView.findViewById(com.raywenderlich.alltherecipes.R.id.recipe_list_title);

// Get subtitle element
        TextView subtitleTextView =
                (TextView) rowView.findViewById(com.raywenderlich.alltherecipes.R.id.recipe_list_subtitle);

// Get detail element
        TextView detailTextView =
                (TextView) rowView.findViewById(com.raywenderlich.alltherecipes.R.id.recipe_list_detail);

// Get thumbnail element
        ImageView thumbnailImageView =
                (ImageView) rowView.findViewById(com.raywenderlich.alltherecipes.R.id.recipe_list_thumbnail);
        // 1
        Recipe recipe = (Recipe) getItem(position);

// 2
        titleTextView.setText(recipe.title);
        subtitleTextView.setText(recipe.description);
        detailTextView.setText(recipe.label);

// 3
        Picasso.with(mContext).load(recipe.imageUrl).placeholder(R.mipmap.ic_launcher).into(thumbnailImageView);

        Typeface titleTypeFace = Typeface.createFromAsset(mContext.getAssets(), "fonts/JosefinSans-Bold.ttf");
        titleTextView.setTypeface(titleTypeFace);

        Typeface subtitleTypeFace =
                Typeface.createFromAsset(mContext.getAssets(), "fonts/JosefinSans-SemiBoldItalic.ttf");
        subtitleTextView.setTypeface(subtitleTypeFace);

        Typeface detailTypeFace = Typeface.createFromAsset(mContext.getAssets(), "fonts/Quicksand-Bold.otf");
        detailTextView.setTypeface(detailTypeFace);

        detailTextView.setTextColor(ContextCompat.getColor(mContext, LABEL_COLORS.get(recipe.label)));

        return rowView;
    }
    private static class ViewHolder {
        public TextView titleTextView;
        public TextView subtitleTextView;
        public TextView detailTextView;
        public ImageView thumbnailImageView;
    }
}
