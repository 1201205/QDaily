package com.hyc.qdaily.beans;

/**
 * Created by ray on 17/3/2.
 */

public class BaseBean<T> {
    private Meta meta;
    private T response;


    public Error getError() {
        return error;
    }


    public void setError(Error error) {
        this.error = error;
    }


    private Error error;


    public Meta getMeta() {
        return meta;
    }


    public void setMeta(Meta meta) {
        this.meta = meta;
    }


    public T getResponse() {
        return response;
    }


    public void setResponse(T response) {
        this.response = response;
    }
}
