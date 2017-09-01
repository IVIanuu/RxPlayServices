/*
 * Copyright 2017 Manuel Wrage
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.ivianuu.rxplayservices;

import android.content.Context;
import android.support.annotation.NonNull;

import com.google.android.gms.common.api.GoogleApiClient;

import io.reactivex.functions.Consumer;

/**
 * Implementation serving a Google api client that is usable with Actions.
 */
class GoogleApiClientActions extends BaseClient {

    private final Consumer<GoogleApiClient> onClientConnected;
    private final Consumer<Throwable> onError;

    private GoogleApiClientActions(Context context,
                                   Consumer<GoogleApiClient> onClientConnected,
                                   Consumer<Throwable> onError,
                                   GoogleApi googleApi) {
        super(context);
        this.onClientConnected = onClientConnected;
        this.onError = onError;

        buildClient(googleApi);
        connect();
    }

    /**
     * Returns a new google api client actions
     */
    static void create(@NonNull Context context,
                       @NonNull GoogleApi googleApi,
                       @NonNull Consumer<GoogleApiClient> onClientConnected,
                       @NonNull Consumer<Throwable> onError) {
        new GoogleApiClientActions(context, onClientConnected, onError, googleApi);
    }

    @Override
    void onClientConnected(GoogleApiClient googleApiClient) {
        try {
            onClientConnected.accept(googleApiClient);
        } catch (Exception e) {
            onClientError(e);
        }
        disconnect();
    }

    @Override
    void onClientError(Throwable throwable) {
        try {
            onError.accept(throwable);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}