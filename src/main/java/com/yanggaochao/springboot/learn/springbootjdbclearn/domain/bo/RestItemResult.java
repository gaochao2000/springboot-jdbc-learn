package com.yanggaochao.springboot.learn.springbootjdbclearn.domain.bo;

/**
 * 单个对象返回结果
 *
 * @author 杨高超
 * @since 2018-03-09
 */
public class RestItemResult<T> {
    private String result;
    private String message;
    private T item;

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getItem() {
        return item;
    }

    public void setItem(T item) {
        this.item = item;
    }
}
