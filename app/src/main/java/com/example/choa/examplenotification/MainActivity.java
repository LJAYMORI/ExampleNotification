package com.example.choa.examplenotification;

import android.app.Notification;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private static final int ID_W_640 = R.id.width_640;
    private static final int ID_W_720 = R.id.width_720;
    private static final int ID_W_1080 = R.id.width_1080;
    private static final int ID_W_TEST= R.id.width_test;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(ID_W_640).setOnClickListener(this);
        findViewById(ID_W_720).setOnClickListener(this);
        findViewById(ID_W_1080).setOnClickListener(this);
        findViewById(ID_W_TEST).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case ID_W_640:
                showNotification(R.drawable.tire_xx);
                break;
            case ID_W_720:
                showNotification(R.drawable.boots);
                break;
            case ID_W_1080:
                showNotification(R.drawable.mimi);
                break;
            case ID_W_TEST:
//                showNotification(R.drawable.layer_5px);
                getBitmapFromUrl();
                break;
        }
    }

//    Bitmap icon = getProfileImageFromUrl(this, appIconUrl);
//    if (icon == null) {
//        icon = getDefaultIcon();
//    }
//    builder.setSmallIcon(getSmallIconRes());
//    builder.setLargeIcon(icon);
//    builder.setStyle(new NotificationCompat.BigTextStyle()
//            .setBigContentTitle(title)
//    .bigText(content));
//    builder.setContentTitle(title);
//    builder.setContentText(content);
//    builder.setTicker(content);
//    builder.setNumber(parseInt(count));
//    builder.setOnlyAlertOnce(true);
//    builder.setAutoCancel(true);
//    builder.setContentIntent(pi);
//    builder.extend(makeReplyWearableExtender(icon, url, sender));

//    NotificationCompat.BigPictureStyle notiStyle = new
//            NotificationCompat.BigPictureStyle();
//    notiStyle.setBigContentTitle("Big Picture Expanded");
//    notiStyle.setSummaryText("Nice big picture.");

    private void showNotification(int imageResource) {
        Bitmap icon = getDefaultIcon();

        NotificationCompat.BigPictureStyle bigPictureStyle = new NotificationCompat.BigPictureStyle();
        bigPictureStyle.setBigContentTitle("TITLE AREA");
        bigPictureStyle.setSummaryText("SUMMARY AREA");
        bigPictureStyle.bigPicture(getExpandImage(imageResource));

        Notification notification = new NotificationCompat.Builder(this)
                .setContentTitle("TITLE AREA")
                .setLargeIcon(icon)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentText("CONTENT AREA")
                .setStyle(bigPictureStyle)
                .build();

        NotificationManagerCompat.from(this).notify("notification", 0, notification);
    }

    private Bitmap getDefaultIcon() {
        return getExpandImage(R.mipmap.ic_launcher);
    }

    private Bitmap getExpandImage(int resId) {
        return BitmapFactory.decodeResource(getResources(), resId);
    }

    private void getBitmapFromUrl() {
        Glide.with(this)
                .load("http://ssproxy.ucloudbiz.olleh.com/v1/AUTH_24f160cf-10af-4ee0-b49a-d49046d645d3/chaser/com.com2us.kungfupet.normal2.freefull.google.global.android.common1453951808.jpg")
                .asBitmap()
                .into(new SimpleTarget<Bitmap>(720, 432) {
                    @Override
                    public void onResourceReady(Bitmap resource, GlideAnimation<? super Bitmap> glideAnimation) {
                        Bitmap icon = getDefaultIcon();

                        NotificationCompat.BigPictureStyle bigPictureStyle = new NotificationCompat.BigPictureStyle();
                        bigPictureStyle.setBigContentTitle("TITLE AREA");
                        bigPictureStyle.setSummaryText("SUMMARY AREA");
                        bigPictureStyle.bigPicture(resource);

                        Notification notification = new NotificationCompat.Builder(MainActivity.this)
                                .setContentTitle("TITLE AREA")
                                .setLargeIcon(icon)
                                .setPriority(1)
                                .setSmallIcon(R.mipmap.ic_launcher)
                                .setContentText("CONTENT AREA")
                                .setStyle(bigPictureStyle)
                                .build();

                        NotificationManagerCompat.from(MainActivity.this).notify("notification", 0, notification);
                    }
                });
    }
}
