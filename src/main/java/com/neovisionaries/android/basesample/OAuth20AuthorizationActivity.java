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
import com.neovisionaries.android.oauth20.AccessToken;
import com.neovisionaries.android.oauth20.AuthorizationRequest;
import com.neovisionaries.android.oauth20.OAuth20Error;
import com.neovisionaries.android.oauth20.OAuth20View;
import com.neovisionaries.android.oauth20.OAuth20ViewListener;
import com.neovisionaries.android.util.L;


public class OAuth20AuthorizationActivity extends BaseActivity implements OAuth20ViewListener
{
    private static final String EXTRA_KEY_REQUEST = "request";
    private AuthorizationRequest mRequest;
    private OAuth20View mOAuth20View;


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
        mRequest = extractRequest();

        // Find OAuth20View instance from the view tree.
        mOAuth20View = (OAuth20View)findViewById(R.id.webview);
    }


    @Override
    protected void onResume()
    {
        super.onResume();

        if (isStopping())
        {
            return;
        }

        L.d(this, "== Sending an authorization request.");
        L.d(this, "URL = %s", mRequest.toURL().toString());

        // Send the authorization request to the OAuth 2.0 authorization endpoint.
        mOAuth20View.load(mRequest, this);
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


    @Override
    public void onCodeIssued(OAuth20View view, String code, String state)
    {
        L.d(this, "== Authorization code was issued.");
        L.d(this, "code = %s", code);
        L.d(this, "state = %s", state);
    }


    @Override
    public void onTokenIssued(OAuth20View view, AccessToken accessToken, String state)
    {
        L.d(this, "== Access token was issued.");
        L.d(this, "access_token = %s", accessToken.getAccessToken());
        L.d(this, "token_type = %s", accessToken.getTokenType());
        L.d(this, "expires_in = %d", accessToken.getExpiresIn());

        // RFC 6749 prohibits authorization endpoints from issuing
        // a refresh token for response_type=token.
    }


    @Override
    public void onError(OAuth20View view, OAuth20Error error, String description, String uri, String state)
    {
        L.d(this, "== Error occurred.");
        L.d(this, "error = %s", error.name());
        L.d(this, "error_description = %s", description);
        L.d(this, "error_uri = %s", uri);
        L.d(this, "state = %s", state);
    }
}
