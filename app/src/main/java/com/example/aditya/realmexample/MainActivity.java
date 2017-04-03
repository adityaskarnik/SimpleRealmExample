package com.example.aditya.realmexample;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import io.realm.Realm;
import io.realm.RealmResults;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    DataList dataListAdapter;
    List<DataObject> dataList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Realm realm = Realm.getInstance(MainActivity.this);


        final EditText editText = (EditText) findViewById(R.id.edit_name);
        final EditText editText1 = (EditText) findViewById(R.id.edit_age);
        final EditText editText2 = (EditText) findViewById(R.id.edit_number);
        final EditText editText3 = (EditText) findViewById(R.id.edit_email);

        Button button = (Button) findViewById(R.id.submit);

        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);

        dataListAdapter = new DataList(dataList);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(dataListAdapter);
        Log.d("recycler view","init view");

        RealmResults<ObjectData> results1 =
                realm.where(ObjectData.class).findAll();
        for(ObjectData c:results1) {
            DataObject dataObject = new DataObject(c.getId(),
                    c.getName(),
                    c.getAge(),
                    c.getNumber(),
                    c.getEmail());
            dataList.add(dataObject);

            dataListAdapter.notifyDataSetChanged();
        }

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(editText.getText().toString().equals("")) {
                    return;
                }
                realm.beginTransaction();
                final ObjectData object = realm.createObject(ObjectData.class);

                object.setId(realm.where(ObjectData.class).max("id").intValue() + 1);
                object.setName(editText.getText().toString());
                object.setAge(editText1.getText().toString());
                object.setNumber(editText2.getText().toString());
                object.setEmail(editText3.getText().toString());

                realm.commitTransaction();

                clear();

                RealmResults<ObjectData> results1 =
                        realm.where(ObjectData.class).findAll();
                for(ObjectData c:results1) {
                DataObject dataObject = new DataObject(c.getId(),
                        c.getName(),
                        c.getAge(),
                        c.getNumber(),
                        c.getEmail());
                dataList.add(dataObject);

                dataListAdapter.notifyDataSetChanged();




                    Log.d("RealmResult", String.valueOf(c.getId())+", "+c.getName()+", "+c.getAge()+", "+c.getNumber()+", "+c.getEmail());
                }

                editText.setText("");
                editText1.setText("");
                editText2.setText("");
                editText3.setText("");
            }
        });


    }

    public void clear() {
        this.dataList.clear();
        dataListAdapter.notifyDataSetChanged();
    }
}
