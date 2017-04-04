package com.example.aditya.realmexample;

import android.app.Dialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import io.realm.Realm;
import io.realm.RealmQuery;
import io.realm.RealmResults;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    DataList dataListAdapter;
    List<DataObject> dataList = new ArrayList<>();
    String idToEdit;
    Realm realm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        realm = Realm.getInstance(MainActivity.this);


        final EditText editText = (EditText) findViewById(R.id.edit_name);
        final EditText editText1 = (EditText) findViewById(R.id.edit_age);
        final EditText editText2 = (EditText) findViewById(R.id.edit_number);
        final EditText editText3 = (EditText) findViewById(R.id.edit_email);

        Button button = (Button) findViewById(R.id.submit);
        Button edit = (Button) findViewById(R.id.edit);

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

        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Dialog dialog = new Dialog(MainActivity.this);
                dialog.setContentView(R.layout.enter_id);

                final EditText editTextId = (EditText) dialog.findViewById(R.id.editTextId);
                Button buttonOk = (Button) dialog.findViewById(R.id.buttonOk);
                buttonOk.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        idToEdit = editTextId.getText().toString();
                        /*RealmQuery<ObjectData> results1 =
                                realm.where(ObjectData.class).equalTo("id",Integer.parseInt(idToEdit));
                        Log.d("resultforquery", String.valueOf(realm.where(ObjectData.class).equalTo("id",Integer.parseInt(idToEdit))));*/
                        if(getData(Integer.parseInt(idToEdit)) != null) {
                            final Dialog dialog2 = new Dialog(MainActivity.this);
                            dialog2.setContentView(R.layout.edit_the_details);
                            dialog2.setTitle("Edit fields");
                            final EditText editText = (EditText) dialog2.findViewById(R.id.edit_name);
                            final EditText editText1 = (EditText) dialog2.findViewById(R.id.edit_age);
                            final EditText editText2 = (EditText) dialog2.findViewById(R.id.edit_number);
                            final EditText editText3 = (EditText) dialog2.findViewById(R.id.edit_email);
                            final TextView textViewID = (TextView) dialog2.findViewById(R.id.textViewID);

                            Button button = (Button) dialog2.findViewById(R.id.submit);
                            textViewID.setText(String.valueOf(getData(Integer.parseInt(idToEdit)).getId()));
                            editText.setText(getData(Integer.parseInt(idToEdit)).getName());
                            editText1.setText(getData(Integer.parseInt(idToEdit)).getAge());
                            editText2.setText(getData(Integer.parseInt(idToEdit)).getNumber());
                            editText3.setText(getData(Integer.parseInt(idToEdit)).getEmail());

                            button.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    int id = Integer.parseInt(idToEdit);

                                    RealmResults<ObjectData> results = realm.where(ObjectData.class).equalTo("id", id).findAll();

                                    realm.beginTransaction();

                                    for (int i = 0; i < results.size(); i++) {
                                        results.get(i).setName(editText.getText().toString());
                                        results.get(i).setAge(editText1.getText().toString());
                                        results.get(i).setNumber(editText2.getText().toString());
                                        results.get(i).setEmail(editText3.getText().toString());
                                    }

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
                                    }
                                    dialog2.dismiss();
                                    dialog.dismiss();
                                }
                            });

                            dialog2.show();
                            Log.d("resultforqueryname", getData(Integer.parseInt(idToEdit)).getName());
                        } else {
                            Toast.makeText(MainActivity.this, "No such ID exists", Toast.LENGTH_SHORT).show();
                            dialog.dismiss();
                        }
                        dialog.dismiss();
                    }
                });


                dialog.show();
            }
        });


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    if (editText.getText().toString().equals("")) {
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
                    for (ObjectData c : results1) {
                        DataObject dataObject = new DataObject(c.getId(),
                                c.getName(),
                                c.getAge(),
                                c.getNumber(),
                                c.getEmail());
                        dataList.add(dataObject);

                        dataListAdapter.notifyDataSetChanged();


                        Log.d("RealmResult", String.valueOf(c.getId()) + ", " + c.getName() + ", " + c.getAge() + ", " + c.getNumber() + ", " + c.getEmail());
                    }

                    editText.setText("");
                    editText1.setText("");
                    editText2.setText("");
                    editText3.setText("");
                } catch (Exception e) {
                    e.printStackTrace();
                    Log.d("catch error", e.getMessage());
                    Toast.makeText(MainActivity.this, ""+e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });


    }

    public void clear() {
        this.dataList.clear();
        dataListAdapter.notifyDataSetChanged();
    }

    public ObjectData getData(int id) {

        return realm.where(ObjectData.class).equalTo("id", id).findFirst();
    }
}
