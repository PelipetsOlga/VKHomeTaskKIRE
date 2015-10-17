package com.example.olga.vkhometaskkire.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.olga.vkhometaskkire.R;
import com.example.olga.vkhometaskkire.adapters.FriendsAdapter;
import com.example.olga.vkhometaskkire.datas.UtilsVK;
import com.example.olga.vkhometaskkire.models.User;

import java.util.ArrayList;

public class ListFriendsActivity extends AppCompatActivity {


    private ArrayList<User> allFriends;
    private ArrayList<User> allFriendsOnline;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_friends_layout);

        Intent intent = getIntent();
        final int[] friendsId = intent.getIntArrayExtra(UtilsVK.TAG_ID_ARRAY);
        ArrayList<User> allPeople = UtilsVK.getList();
        allFriends = new ArrayList<User>();
        allFriendsOnline = new ArrayList<User>();

        for (User us:allPeople){
            if (us.isFriend(friendsId)){
                allFriends.add(us);
                if (us.isOnline())
                    allFriendsOnline.add(us);
            }
        }

        ListView lv = (ListView) findViewById(R.id.lv_friends);
        FriendsAdapter adapter = new FriendsAdapter(this, 0, allFriends);
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
}
