package com.example.olga.vkhometaskkire.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.TextView;

import com.example.olga.vkhometaskkire.R;
import com.example.olga.vkhometaskkire.adapters.FriendsAdapter;
import com.example.olga.vkhometaskkire.datas.UtilsVK;
import com.example.olga.vkhometaskkire.models.User;

import java.util.ArrayList;

public class ListFriendsActivity extends AppCompatActivity {

    private ArrayList<User> currentList;
    private ArrayList<User> allFriends;
    private ArrayList<User> allFriendsOnline;
    private RadioButton btnAll, btnOnline;
    private FriendsAdapter adapter;
    private ListView lv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_friends_layout);

        Intent intent = getIntent();
        final int[] friendsId = intent.getIntArrayExtra(UtilsVK.TAG_ID_ARRAY);
        ArrayList<User> allPeople = UtilsVK.getList();
        allFriends = new ArrayList<User>();
        allFriendsOnline = new ArrayList<User>();

        for (User us : allPeople) {
            if (us.isFriend(friendsId)) {
                allFriends.add(us);
                if (us.isOnline())
                    allFriendsOnline.add(us);
            }
        }

        initViews();
    }

    private void initViews() {
        btnAll = (RadioButton) findViewById(R.id.btn_all);
        btnOnline = (RadioButton) findViewById(R.id.btn_online);
        btnAll.setChecked(true);
        currentList=allFriends;
        btnAll.setOnClickListener(new ButtonClickListener());
        btnOnline.setOnClickListener(new ButtonClickListener());

        lv = (ListView) findViewById(R.id.lv_friends);
        adapter = new FriendsAdapter(this, 0, currentList);
        lv.setAdapter(adapter);

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                User selectedUser = allFriends.get(position);
                Intent intent = new Intent(ListFriendsActivity.this, UserActivity.class);
                intent.putExtra(UtilsVK.TAG_ID, selectedUser.getId());
                startActivity(intent);
                ListFriendsActivity.this.finish();
            }
        });
    }

    private class ButtonClickListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.btn_all:
                    currentList=allFriends;
                    btnOnline.setChecked(false);
                    btnAll.setChecked(true);
                    break;
                case R.id.btn_online:
                    currentList=allFriendsOnline;
                    btnOnline.setChecked(true);
                    btnAll.setChecked(false);
                    break;

            }
            adapter = new FriendsAdapter(ListFriendsActivity.this, 0, currentList);
            lv.setAdapter(adapter);
        }
    }
}
