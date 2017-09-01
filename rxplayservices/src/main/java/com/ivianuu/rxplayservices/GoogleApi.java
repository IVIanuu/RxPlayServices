package com.ivianuu.rxplayservices;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.google.android.gms.common.api.Api;

/**
 * Definition of a Google API
 */
interface GoogleApi {

    /**
     * Returns requested API
     */
    @NonNull
    Api api();

    /**
     * Returns Options for the API, might be null
     */
    @Nullable
    Api.ApiOptions.HasOptions options();
}