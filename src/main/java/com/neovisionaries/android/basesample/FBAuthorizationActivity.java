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


import android.os.Bundle;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.RadioGroup;
import com.neovisionaries.android.app.BaseActivity;
import com.neovisionaries.android.oauth20.AuthorizationRequest;
import com.neovisionaries.android.oauth20.ResponseType;


public class FBAuthorizationActivity extends BaseActivity
{
    private final String AUTHORIZATION_ENDPOINT = "https://www.facebook.com/dialog/oauth";
    private final String REDIRECT_URI = "https://www.facebook.com/connect/login_success.html";
    private final String[] SCOPES = { "basic_info" };
    private AutoCompleteTextView mClientIdField;
    private RadioGroup mResponseTypeRadioGroup;


    /**
     * Called when this Activity is created.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        // Call the super method as a rule.
        super.onCreate(savedInstanceState);

        // Set appearance of this Activity.
        setContentView(R.layout.fb_authorization_activity);

        // The field to input client_id.
        mClientIdField = (AutoCompleteTextView)findViewById(R.id.clientId);

        // The radio group to specify a response type.
        mResponseTypeRadioGroup = (RadioGroup)findViewById(R.id.responseType);
    }


    /**
     * Called when 'send request' button is clicked.
     */
    public void onClick_SendRequest(View button)
    {
        // Get the selected response type.
        ResponseType responseType = getResponseType();

        // Extract the value of client_id.
        String clientId = getClientId();

        // Create an authorization request.
        AuthorizationRequest request = createRequest(responseType, clientId);

        // Launch an activity which contains a WebView to access
        // the authorization endpoint.
        OAuth20AuthorizationActivity.launch(this, request);
    }


    /**
     * Get the selected response type.
     */
    private ResponseType getResponseType()
    {
        // ID of the checked radio button.
        int id = mResponseTypeRadioGroup.getCheckedRadioButtonId();

        if (id == R.id.responseType_code)
        {
            return ResponseType.code;
        }
        else
        {
            return ResponseType.token;
        }
    }


    /**
     * Get the client ID.
     */
    private String getClientId()
    {
        return mClientIdField.getText().toString();
    }


    /**
     * Create an authorization request.
     *
     * @param clientId
     *         The application's client ID.
     *
     * @return
     *         An authorization request.
     */
    private AuthorizationRequest createRequest(ResponseType responseType, String clientId)
    {
        return new AuthorizationRequest()
            .setEndpoint(AUTHORIZATION_ENDPOINT)
            .setResponseType(responseType)
            .setClientId(clientId)
            .addScopes(SCOPES)
            .addParameter("display", "touch")
            .setScopeDelimiter(',')
            .setRedirectUri(REDIRECT_URI)
            ;
    }
}
