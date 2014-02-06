/*
 * Copyright (C) 2014 Neo Visionaries Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.neovisionaries.android.basesample;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;
import com.neovisionaries.android.app.BottomListActivity;
import com.neovisionaries.android.util.SystemServices;


/**
 * The home Activity of this application.
 */
public final class HomeActivity extends BottomListActivity
{
    /**
     * Menu items.
     */
    private static Item[] items = {
        new Item("Exit",    ExitActivity.class),
        new Item("Restart", RestartActivity.class)
    };


    /**
     * Inflater.
     */
    private LayoutInflater inflater;


    /**
     * Called when this Activity is created.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        // Call the super method as a rule.
        super.onCreate(savedInstanceState);

        // Get the inflater.
        inflater = SystemServices.getLayoutInflater();

        // Set appearance of this Activity.
        setContentView(R.layout.home_activity);

        // Set an adapter which feeds menu items to this ListActivity.
        setListAdapter(new ItemAdapter(this));
    }


    /**
     * Menu item.
     */
    private static class Item
    {
        /**
         * Menu title.
         */
        public String title;


        /**
         * Activity to launch.
         */
        public Class<? extends Activity> activityClass;


        /**
         * Constructor with a menu title and an Activity to launch.
         */
        public Item(String title, Class<? extends Activity> activityClass)
        {
            this.title = title;
            this.activityClass = activityClass;
        }
    }


    /**
     * Adapter which feeds menu items.
     */
    private class ItemAdapter extends ArrayAdapter<Item> implements OnClickListener
    {
        /**
         * Constructor with context.
         */
        public ItemAdapter(Context context)
        {
            super(context, 0, items);
        }


        /**
         * Create a View for a menu item.
         */
        @Override
        public View getView(int position, View view, ViewGroup parent)
        {
            if (view == null)
            {
                // Inflate a view for a menu item.
                view = inflater.inflate(R.layout.home_list_item, null);

                // Set a listener to 'launch' button.
                ((Button)view.findViewById(R.id.launch_button)).setOnClickListener(this);
            }

            // Get the item at the position.
            Item item = getItem(position);

            // Set the item to the button.
            ((Button)view.findViewById(R.id.launch_button)).setTag(item);

            // Set the title.
            ((TextView)view.findViewById(R.id.title)).setText(item.title);

            return view;
        }


        /**
         * Called when 'launch' button is clicked.
         */
        @Override
        public void onClick(View button)
        {
            // Retrieve the menu item from the button.
            Item item = (Item)button.getTag();

            // Start an Activity associated with the menu item.
            startActivity(item.activityClass);
        }
    }


    /**
     * Start the given Activity.
     */
    private void startActivity(Class<? extends Activity> activityClass)
    {
        // Start the Activity.
        startActivity(new Intent(this, activityClass));
    }
}
