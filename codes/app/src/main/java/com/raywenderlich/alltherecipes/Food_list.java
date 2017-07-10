package com.raywenderlich.alltherecipes;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Context;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.InputStream;
import java.util.ArrayList;
import com.melnykov.fab.FloatingActionButton;
import com.wdullaer.materialdatetimepicker.date.DatePickerDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ListView;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import android.widget.*;
import android.widget.AdapterView.OnItemClickListener;
import android.content.Intent;
import android.content.Context;
import java.io.IOException;
import java.io.InputStream;
import java.util.Calendar;

import android.widget.Button;


import static com.raywenderlich.alltherecipes.R.id.fab;
import static com.raywenderlich.alltherecipes.Recipe.getRecipesFromFile;
public class Food_list extends AppCompatActivity {
    private ListView mListView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_list);

        String title = this.getIntent().getExtras().getString("title");

        ArrayList<Recipe> list=getRecipesFromFile(title+".json",this);

        mListView = (ListView) findViewById(R.id.recipe_list_view1);
// 1
        final ArrayList<Recipe> recipeList = Recipe.getRecipesFromFile(title+".json", this);
// 2
        String[] listItems = new String[recipeList.size()];
// 3
        for(int i = 0; i < recipeList.size(); i++){
            Recipe recipe = recipeList.get(i);
            listItems[i] = recipe.title;
        }
        RecipeAdapter adapter = new RecipeAdapter(this, recipeList);
        mListView.setAdapter(adapter);
        final Context context = this;
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // 1
                Recipe selectedRecipe = recipeList.get(position);

                // 2
                Intent detailIntent = new Intent(context, RecipeDetailActivity.class);

                // 3
                detailIntent.putExtra("title", selectedRecipe.title);
                detailIntent.putExtra("url", selectedRecipe.instructionUrl);

                // 4
                startActivity(detailIntent);
            }

        });

    }

}
