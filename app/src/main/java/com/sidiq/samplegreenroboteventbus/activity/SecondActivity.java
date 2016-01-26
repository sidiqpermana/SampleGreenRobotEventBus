package com.sidiq.samplegreenroboteventbus.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.sidiq.samplegreenroboteventbus.R;
import com.sidiq.samplegreenroboteventbus.model.PostItem;
import com.sidiq.samplegreenroboteventbus.model.User;

import de.greenrobot.event.EventBus;

public class SecondActivity extends AppCompatActivity implements View.OnClickListener{
    private TextView txtUsername, txtPostTitle;
    private Button btnGoToThirdActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        txtUsername = (TextView)findViewById(R.id.txt_username);
        txtPostTitle = (TextView)findViewById(R.id.txt_post_title);
        btnGoToThirdActivity = (Button)findViewById(R.id.btn_go_to_third_activity);
        btnGoToThirdActivity.setOnClickListener(this);

        EventBus.getDefault().register(this);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btn_go_to_third_activity){
            Intent mIntent = new Intent(SecondActivity.this, ThirdActivity.class);
            startActivity(mIntent);
        }
    }

    @Override
    protected void onDestroy() {
        EventBus.getDefault().unregister(this);
        super.onDestroy();
    }

    public void onEvent(User mUser){
        if (mUser != null){
            txtUsername.setText("Post by Username : " + mUser.getName());
        }
    }

    public void onEvent(PostItem mPostItem){
        if (mPostItem != null){
            txtPostTitle.setText("Post title : "+mPostItem.getName()+" with total view "+mPostItem.getTotalView());
        }
    }
}
