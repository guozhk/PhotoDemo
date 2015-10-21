package com.org.photodemo.ui.album;

import android.view.View;
import android.widget.ListView;

import com.org.photodemo.R;
import com.org.photodemo.base.BaseActivity;
import com.org.photodemo.ui.album.bean.GalleryEntity;
import com.org.photodemo.ui.album.util.PhotoSelectUtil;

import java.util.List;

/**
 * 自定义本地相册
 */
public class AlbumActivity extends BaseActivity {
    private ListView lvAlbum;
    private AlbumAdapter mAlbumAdapter;

    @Override
    public void doInitSubView(View view) {
        lvAlbum=queryViewById(R.id.lv_album);

    }

    @Override
    public void doInitData() {
        List<GalleryEntity> galleryEntityList= PhotoSelectUtil.QueryALLGalleryList(AlbumActivity.this);
        mAlbumAdapter= new AlbumAdapter(AlbumActivity.this,galleryEntityList);
        lvAlbum.setAdapter(mAlbumAdapter);
    }

    @Override
    public int doGetContentViewId() {
        return R.layout.activity_album;
    }

    @Override
    public void onClick(View v) {

    }
}
