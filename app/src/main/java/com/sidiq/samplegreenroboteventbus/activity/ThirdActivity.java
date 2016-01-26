package com.sidiq.samplegreenroboteventbus.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.sidiq.samplegreenroboteventbus.R;
import com.sidiq.samplegreenroboteventbus.model.PostItem;
import com.sidiq.samplegreenroboteventbus.model.User;

import de.greenrobot.event.EventBus;

public class ThirdActivity extends AppCompatActivity implements View.OnClickListener{

    private Button btnUpdateUsername, btnUpdatePost;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third);

        btnUpdatePost = (Button)findViewById(R.id.btn_update_post);
        btnUpdateUsername = (Button)findViewById(R.id.btn_update_username);

        btnUpdatePost.setOnClickListener(this);
        btnUpdateUsername.setOnClickListener(this);

        EventBus.getDefault().register(this);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btn_update_post){
            PostItem mPostItem = new PostItem();
            mPostItem.setName("Android Juara");
            mPostItem.setTotalView(350);

            EventBus.getDefault().post(mPostItem);

        }

        if (v.getId() == R.id.btn_update_username){
            User mUser = new User();
            mUser.setName("Sidiq Permana");

            EventBus.getDefault().post(mUser);
        }
    }

    @Override
    protected void onDestroy() {
        EventBus.getDefault().unregister(this);
        super.onDestroy();
    }

    public void onEvent(User mUser){

    }

    public void onEvent(PostItem postItem){

    }
}
