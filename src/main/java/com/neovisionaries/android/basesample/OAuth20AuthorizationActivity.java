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
import android.content.Intent;
import android.os.Bundle;
import com.neovisionaries.android.app.BaseActivity;
import com.neovisionaries.android.oauth20.AuthorizationRequest;


public class OAuth20AuthorizationActivity extends BaseActivity
{
    private static final String EXTRA_KEY_REQUEST = "request";


    /**
     * Called when this Activity is created.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        // Call the super method as a rule.
        super.onCreate(savedInstanceState);

        // Set appearance of this Activity.
        setContentView(R.layout.oauth20authorization_activity);

        // Extract the request from the Intent.
        AuthorizationRequest request = extractRequest();

        // Find OAuth20View instance from the view tree.
        findView(R.id.webview);
    }


    /**
     * Extract {@link AuthorizationRequest} from the {@code Intent}.
     */
    private AuthorizationRequest extractRequest()
    {
        return (AuthorizationRequest)getIntent().getParcelableExtra(EXTRA_KEY_REQUEST);
    }


    /**
     * Launch this activity.
     *
     * @param activity
     *
     * @param request
     *         OAuth 2.0 authorization request.
     */
    public static void launch(Activity activity, AuthorizationRequest request)
    {
        // Intent to launch OAuth20AuthorizationActivity.
        Intent intent = new Intent(activity, OAuth20AuthorizationActivity.class);

        // Embed the request into the Intent.
        intent.putExtra(EXTRA_KEY_REQUEST, request);

        // Launch the Activity.
        activity.startActivity(intent);
    }
}
