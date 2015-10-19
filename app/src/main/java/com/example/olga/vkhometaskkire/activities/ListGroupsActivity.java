package com.example.olga.vkhometaskkire.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.olga.vkhometaskkire.R;
import com.example.olga.vkhometaskkire.adapters.GroupsAdapter;
import com.example.olga.vkhometaskkire.datas.UtilsVK;
import com.example.olga.vkhometaskkire.models.Group;

import java.util.ArrayList;

public class ListGroupsActivity extends AppCompatActivity {

    private GroupsAdapter adapter;
    private ListView lv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_groups);

        Intent intent = getIntent();
        final int[] groupsId = intent.getIntArrayExtra(UtilsVK.TAG_ID_ARRAY);

        ArrayList<Group> allGroups = UtilsVK.getListGroups();
        final ArrayList<Group> myGroups = new ArrayList<Group>();

        for (Group gr : allGroups) {
            if (gr.isMember(groupsId)) {
                myGroups.add(gr);
            }
        }

        if (myGroups == null || myGroups.size() == 0) {
            finish();
        } else {
            ActionBar actionBar = getSupportActionBar();
            actionBar.setDisplayShowHomeEnabled(true);
            actionBar.setTitle(getResources().getString(R.string.action_bar_group) + " " + myGroups.size());
            actionBar.setDisplayHomeAsUpEnabled(true);

            lv = (ListView) findViewById(R.id.lv_groups);
            adapter = new GroupsAdapter(this, 0, myGroups);
            lv.setAdapter(adapter);

            lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Group group=myGroups.get(position);
                    Intent intenMembers=new Intent(ListGroupsActivity.this, GroupMembersActivity.class);
                    final int[] membersId = group.getMembersId();
                    intenMembers.putExtra(UtilsVK.TAG_ID_ARRAY, membersId);
                    intenMembers.putExtra(UtilsVK.TAG_TITLE, group.getTitle());
                    startActivity(intenMembers);
                    ListGroupsActivity.this.finish();
                }
            });
        }
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);

    }

}
