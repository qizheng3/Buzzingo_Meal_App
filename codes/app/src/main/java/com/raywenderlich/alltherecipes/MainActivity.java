/*
 * Copyright (c) 2016 Razeware LLC
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package com.raywenderlich.alltherecipes;

import com.melnykov.fab.FloatingActionButton;
import com.wdullaer.materialdatetimepicker.date.DatePickerDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ListView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import android.widget.*;
import android.content.Intent;
import android.content.Context;

import java.util.Calendar;


import static com.raywenderlich.alltherecipes.R.id.fab;
import static com.raywenderlich.alltherecipes.Recipe.getRecipesFromFile;


public class MainActivity extends AppCompatActivity {
    private ListView mListView;
    public Calendar calendar;
    public FloatingActionButton arrivalStartDateBtn;
    @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    setContentView(R.layout.activity_main);
        arrivalStartDateBtn=(FloatingActionButton) findViewById(fab);
        arrivalStartDateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setDate(true);
            }
        });
        calendar = Calendar.getInstance();


        SimpleDateFormat sdfDateFormatter = new SimpleDateFormat("MMM dd, yyyy");
      ArrayList<Recipe> list=getRecipesFromFile("Panda Express.json",this);
      System.out.println("++++++++++++++"+list.get(0).description);
      mListView = (ListView) findViewById(R.id.recipe_list_view);
// 1
      final ArrayList<Recipe> recipeList = Recipe.getRecipesFromFile("recipes.json", this);
// 2
      String[] listItems = new String[recipeList.size()];
// 3
      for(int i = 0; i < recipeList.size(); i++){
          Recipe recipe = recipeList.get(i);
          listItems[i] = recipe.title;
      }
// 4
        RecipeAdapter adapter = new RecipeAdapter(this, recipeList);
        mListView.setAdapter(adapter);
        final Context context = this;
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // 1
                Recipe selectedRecipe = recipeList.get(position);

                // 2
                Intent detailIntent = new Intent(context, Food_list.class);

                // 3
                detailIntent.putExtra("title", selectedRecipe.title);
                //detailIntent.putExtra("url", selectedRecipe.instructionUrl);

                // 4
                startActivity(detailIntent);
            }

        });
        arrivalStartDateBtn.attachToListView(mListView);

  }
    public void setDate(final boolean isArrival) {
        Calendar currCalendar;

            currCalendar = calendar;

        DatePickerDialog dpd = DatePickerDialog.newInstance(
                new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePickerDialog view, int year, int monthOfYear, int dayOfMonth) {
                        SimpleDateFormat sdf = new SimpleDateFormat("MMM dd, yyyy");
                        // Set the new calendar dates

                        calendar.set(Calendar.YEAR, year);
                        calendar.set(Calendar.MONTH, monthOfYear);
                        calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);


                    }


                },
                currCalendar.get(Calendar.YEAR),
                currCalendar.get(Calendar.MONTH),
                currCalendar.get(Calendar.DAY_OF_MONTH)
        );
        dpd.show(getFragmentManager(), "Datepickerdialog");
    }

}
