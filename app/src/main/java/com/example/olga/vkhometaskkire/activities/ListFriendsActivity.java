package com.example.olga.vkhometaskkire.activities;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
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

public class ListFriendsActivity extends
        ParentActivity {

    private ArrayList<User> currentList;
    private ArrayList<User> allFriends;
    private ArrayList<User> allFriendsOnline;
    private RadioButton btnAll, btnOnline;
    private FriendsAdapter adapter;
    private ListView lv;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_friends_layout);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setTitle(getResources().getString(R.string.action_bar_friends));
        actionBar.setDisplayHomeAsUpEnabled(true);


    }

    private void initViews() {
        btnAll = (RadioButton) findViewById(R.id.btn_all);
        btnAll.setText(getResources().getString(R.string.tab_friends_all) + " " + allFriends.size());
        btnOnline = (RadioButton) findViewById(R.id.btn_online);
        btnOnline.setText(getResources().getString(R.string.tab_friends_online)+" "+allFriendsOnline.size());
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

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);

    }

    @Override
    void onServiceReady() {
        Intent intent = getIntent();
        final int[] friendsId = intent.getIntArrayExtra(UtilsVK.TAG_ID_ARRAY);
        ArrayList<User> allPeople = vkService.getList();
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
