package com.org.photodemo;

import android.view.View;
import android.widget.Button;

import com.org.photodemo.base.BaseActivity;


public class MainActivity extends BaseActivity {
    private Button btnAlbum;

    @Override
    public int doGetContentViewId() {
        return R.layout.activity_main;
    }

    @Override
    public void doInitSubView(View view) {
        btnAlbum = queryViewById(R.id.btn_album, true);
    }

    @Override
    public void doInitData() {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_album:
                break;
            default:
                break;
        }
    }


}
