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


import android.content.Intent;
import android.os.Bundle;
import com.neovisionaries.android.app.BaseRootActivity;


/**
 * The root Activity of this application.
 */
public class RootActivity extends BaseRootActivity
{
    /**
     * Called when this Activity is created.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        // Call the super method as a rule.
        super.onCreate(savedInstanceState);

        // Set appearance of this Activity.
        setContentView(R.layout.root_activity);
    }


    /**
     * Called from within BaseRootActivity's onResume()
     * if App.getInstance().isStopping() returns false.
     */
    @Override
    protected void dispatch()
    {
        // Launch HomeActivity.
        startActivity(new Intent(this, HomeActivity.class));
    }
}
