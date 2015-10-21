package com.org.photodemo.ui.album.util;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.provider.MediaStore;
import android.text.TextUtils;

import com.org.photodemo.ui.album.bean.GalleryEntity;
import com.org.photodemo.util.MyLogger;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by GZK on 2015/10/21.
 */

public class PhotoSelectUtil {
    static MyLogger myLogger=MyLogger.getMyLogger();
    public static ArrayList<GalleryEntity> QueryALLGalleryList(Context context) {
        ArrayList<GalleryEntity> galleryList = new ArrayList<GalleryEntity>();

        String[] projection = {MediaStore.Images.Media._ID, MediaStore.Images.Media.DATA, MediaStore.Images.Media.BUCKET_ID,
                MediaStore.Images.Media.BUCKET_DISPLAY_NAME, MediaStore.Images.ImageColumns.ORIENTATION, "COUNT(1) AS count"};
        String selection = "0==0) GROUP BY (" + MediaStore.Images.Media.BUCKET_ID;
        String sortOrder = MediaStore.Images.Media.DATE_MODIFIED;

        Cursor cur = context.getContentResolver().query(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, projection, selection, null, sortOrder);
        if (cur != null && cur.moveToFirst()) {
            int id_column = cur.getColumnIndex(MediaStore.Images.Media._ID);
            int image_id_column = cur.getColumnIndex(MediaStore.Images.Media.DATA);
            int bucket_id_column = cur.getColumnIndex(MediaStore.Images.Media.BUCKET_ID);
            int bucket_name_column = cur.getColumnIndex(MediaStore.Images.Media.BUCKET_DISPLAY_NAME);
            int count_column = cur.getColumnIndex("count");

            do {
                int id = cur.getInt(id_column);
                String image_path = cur.getString(image_id_column);
                int bucket_id = cur.getInt(bucket_id_column);
                String bucket_name = cur.getString(bucket_name_column);
                int orientation = cur.getInt(cur.getColumnIndex(MediaStore.Images.ImageColumns.ORIENTATION));
                if (TextUtils.isEmpty(bucket_name)) {
                    Uri mUri = Uri.parse("content://media/external/images/media");
                    Uri mImageUri = Uri.withAppendedPath(mUri, "" + id);
                    int deleteNUM = context.getContentResolver().delete(mImageUri, null, null);
                    myLogger.d(("image_path:" + image_path + ",deleteNUM:" + deleteNUM + ",mImageUri:" + mImageUri));
                    continue;
                }
                int count = cur.getInt(count_column);
                GalleryEntity gallery = new GalleryEntity();
                gallery.setId(id);
                gallery.setImagePath(image_path);
                gallery.setBucketId(bucket_id);
                gallery.setBucketName(bucket_name);
                gallery.setCount(count);
                gallery.setOrientation(orientation);
                galleryList.add(gallery);
            } while (cur.moveToNext());
            cur.close();
        }
        GalleryComparator comparator = new GalleryComparator();
        Collections.sort(galleryList, comparator);
        return galleryList;
    }
}
