package com.nathan.battlefury.rest.event;

import retrofit.RetrofitError;

/**
 * Created by nathan on 6/3/15.
 */
public class ApiErrorEvent {
    private RetrofitError error;

    public ApiErrorEvent(RetrofitError error) {
        this.error = error;
    }
    public String getErrorMessage() {
        return error.getMessage();
    }
}
