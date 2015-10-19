package com.example.olga.vkhometaskkire.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.RadioButton;

import com.example.olga.vkhometaskkire.R;
import com.example.olga.vkhometaskkire.adapters.FriendsAdapter;
import com.example.olga.vkhometaskkire.datas.UtilsVK;
import com.example.olga.vkhometaskkire.models.User;

import java.util.ArrayList;

public class GroupMembersActivity extends AppCompatActivity {

    private ArrayList<User> currentList;
    private ArrayList<User> allMembers;
    private ArrayList<User>  allMembersFriends;
    private RadioButton btnAll, btnFriends;
    private FriendsAdapter adapter;
    private ListView lv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_friends_layout);

        Intent intent = getIntent();
        final int[] membersId = intent.getIntArrayExtra(UtilsVK.TAG_ID_ARRAY);
        String title=intent.getStringExtra(UtilsVK.TAG_TITLE);
        if (!TextUtils.isEmpty(title)){
            ActionBar actionBar = getSupportActionBar();
            actionBar.setDisplayShowHomeEnabled(true);
            actionBar.setTitle(title);
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        ArrayList<User> allPeople = UtilsVK.getList();
        allMembers = new ArrayList<User>();
          allMembersFriends = new ArrayList<User>();

        for (User us : allPeople) {
            if (us.isMember(membersId)) {
                allMembers.add(us);
                if (us.isMyFriend())
                      allMembersFriends.add(us);
            }
        }

        initViews();
    }

    private void initViews() {
        btnAll = (RadioButton) findViewById(R.id.btn_all);
        btnAll.setText(getResources().getString(R.string.tab_members_all));
        btnFriends = (RadioButton) findViewById(R.id.btn_online);
        btnFriends.setText(getResources().getString(R.string.tab_members_friends));
        btnAll.setChecked(true);
        currentList= allMembers;
        btnAll.setOnClickListener(new ButtonClickListener());
        btnFriends.setOnClickListener(new ButtonClickListener());

        lv = (ListView) findViewById(R.id.lv_friends);
        adapter = new FriendsAdapter(this, 0, currentList);
        lv.setAdapter(adapter);

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                User selectedUser = allMembers.get(position);
                Intent intent = new Intent(GroupMembersActivity.this, UserActivity.class);
                intent.putExtra(UtilsVK.TAG_ID, selectedUser.getId());
                startActivity(intent);
                GroupMembersActivity.this.finish();
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


    private class ButtonClickListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.btn_all:
                    currentList= allMembers;
                    btnFriends.setChecked(false);
                    btnAll.setChecked(true);
                    break;
                case R.id.btn_online:
                    currentList=  allMembersFriends;
                    btnFriends.setChecked(true);
                    btnAll.setChecked(false);
                    break;

            }
            adapter = new FriendsAdapter(GroupMembersActivity.this, 0, currentList);
            lv.setAdapter(adapter);
        }
    }
}