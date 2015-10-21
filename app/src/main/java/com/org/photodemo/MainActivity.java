package com.org.photodemo;

import android.content.Intent;
import android.view.View;
import android.widget.Button;

import com.org.photodemo.base.BaseActivity;
import com.org.photodemo.ui.RecorderVedioActivity;
import com.org.photodemo.ui.album.AlbumActivity;


public class MainActivity extends BaseActivity {
    private Button btnAlbum;
    private Button btnTakePhonto;
    private Button btnRecordVedio;


    @Override
    public int doGetContentViewId() {
        return R.layout.activity_main;
    }

    @Override
    public void doInitSubView(View view) {
        btnAlbum = queryViewById(R.id.btn_album, true);
        btnTakePhonto = queryViewById(R.id.btn_take_photo, true);
        btnRecordVedio = queryViewById(R.id.btn_record_vedio, true);
    }

    @Override
    public void doInitData() {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_album:
                Intent albumIntent=new Intent(MainActivity.this, AlbumActivity.class);
                startActivity(albumIntent);
                break;
            case R.id.btn_take_photo:
                Intent takephotoIntent=new Intent(MainActivity.this, RecorderVedioActivity.class);
                startActivity(takephotoIntent);
                break;
            case R.id.btn_record_vedio:
                Intent recordVedioIntent=new Intent(MainActivity.this, RecorderVedioActivity.class);
                startActivity(recordVedioIntent);
                break;
            default:
                break;
        }
    }


}
