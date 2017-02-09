package com.stringbox.snackbartest;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.stringbox.gcsnackbar.GCSnackbar;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";

    private TextView mMViewById1;
    private TextView mMViewById2;
    private TextView mMViewById3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mMViewById1 = (TextView) findViewById(R.id.textview1);
        mMViewById2 = (TextView) findViewById(R.id.textview2);
        mMViewById3 = (TextView) findViewById(R.id.textview3);

        mMViewById1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                new GCSnackbar()
//                        .SnackbarDIY(MainActivity.this, R.layout.gcsnackbar_layout, GCSnackbar.LENGTH_LONG)
//                        .setImageResource(R.id.image1, R.mipmap.fotang_zhong)
//                        .setText(R.id.text1, "你好啊")
//                        .show();
                GCSnackbar.infoSnackbar(MainActivity.this, "正常的消息", GCSnackbar.LENGTH_SHORT).show();
            }
        });

        mMViewById2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GCSnackbar.warnSnackbar(MainActivity.this, "警告的消息", GCSnackbar.LENGTH_SHORT).show();
            }
        });

        mMViewById3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GCSnackbar.errorSnackbar(MainActivity.this, "错误的消息", GCSnackbar.LENGTH_SHORT).show();
            }
        });
    }
}
