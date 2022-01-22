package com.luoye.myutils.base;

/**
 * Created by：luoye
 * Date：2022/1/18
 * 介绍：适配器的数据接口
 */
public abstract class OnBaseListener<T> {

    public void itemDate(T data) { }

    public void itemId(int itemId) { }

    public void itemDataAndId(T data, int itemId) { }
}
