package com.sidiq.samplegreenroboteventbus.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.sidiq.samplegreenroboteventbus.R;
import com.sidiq.samplegreenroboteventbus.model.User;

import de.greenrobot.event.EventBus;

public class FirstActivity extends AppCompatActivity implements View.OnClickListener{
    private TextView txtUsername;
    private Button btnGoToSecondActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtUsername = (TextView)findViewById(R.id.txt_username);
        btnGoToSecondActivity = (Button)findViewById(R.id.btn_go_to_second_activity);
        btnGoToSecondActivity.setOnClickListener(this);

        EventBus.getDefault().register(this);

    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btn_go_to_second_activity){
            Intent mIntent = new Intent(FirstActivity.this, SecondActivity.class);
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
            txtUsername.setText("Username : "+mUser.getName());
        }
    }
}
