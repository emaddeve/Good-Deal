package com.emad.gooddeals.http;

/**
 * Created by emad on 22/03/16.
 */
public interface Callback<T> {
    void onResponse(T t);
}
