package com.permissiondemo;

import android.graphics.Paint;
import android.os.Bundle;

import com.permissiondemo.utils.IForm;

/**
 * 漂亮的表格
 * 界面不美观,看这里,格式化一下内容背景
 */
public class BeautifulTabActivity extends BaseActivity implements IForm{
//漂亮的表格布局,开始展示丰富的功能.界面不美观,看这里,格式化一下内容背景
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_beautiful_tab);
    }

    @Override
    public int getSpanWidthSize() {
        return 10;
    }

    @Override
    public int getSpanHeightsize() {
        return 100;
    }

    @Override
    public Paint.Align getAlign() {
        return null;
    }
}
