package com.carrizane.mvppersonorm.activities;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

import com.baoyz.swipemenulistview.SwipeMenu;
import com.baoyz.swipemenulistview.SwipeMenuCreator;
import com.baoyz.swipemenulistview.SwipeMenuItem;
import com.baoyz.swipemenulistview.SwipeMenuListView;
import com.carrizane.mvppersonorm.R;
import com.carrizane.mvppersonorm.entities.Person;
import com.carrizane.mvppersonorm.presenters.PersonPresenter;
import com.carrizane.mvppersonorm.presentersImp.PersonPresenterImp;
import com.carrizane.mvppersonorm.utils.PersonAdapter;
import com.carrizane.mvppersonorm.views.PersonView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements PersonView {

    SwipeMenuListView personList;
    FloatingActionButton createBtn;

    PersonPresenter presenter;
    PersonAdapter personAdapter;

    List<Person> list;

    int positionList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        personList = (SwipeMenuListView) findViewById(R.id.personList);
        createBtn = (FloatingActionButton) findViewById(R.id.addPerson);

        presenter = new PersonPresenterImp(this);

        list = new ArrayList<>();
        list.clear();
        list = presenter.showAll();
        personAdapter = new PersonAdapter(this, list);

        personList.setAdapter(personAdapter);

        createBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), PersonFormActivity.class));
            }
        });

        SwipeMenuCreator creator = new SwipeMenuCreator() {
            @Override
            public void create(SwipeMenu menu) {
                SwipeMenuItem editItem = new SwipeMenuItem(getApplicationContext());
                editItem.setIcon(R.drawable.ic_edit_person);
                editItem.setWidth(150);
                editItem.setBackground(new ColorDrawable(Color.rgb(103, 195, 218)));

                SwipeMenuItem deleteItem = new SwipeMenuItem(getApplicationContext());
                deleteItem.setIcon(R.drawable.ic_delete_person);
                deleteItem.setWidth(150);
                deleteItem.setBackground(new ColorDrawable(Color.rgb(229, 95, 68)));

                menu.addMenuItem(editItem);
                menu.addMenuItem(deleteItem);
            }
        };

        personList.setMenuCreator(creator);

        personList.setOnMenuItemClickListener(new SwipeMenuListView.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(int position, SwipeMenu menu, int index) {
                switch (index) {
                    case 0:
                        Person person = list.get(position);
                        Intent intent = new Intent(getApplicationContext(), PersonFormActivity.class);
                        Log.i("ID_LONG", String.valueOf(person.getId()));
                        intent.putExtra("id", person.getId().intValue());
                        intent.putExtra("name", person.getName());
                        intent.putExtra("last_name", person.getLast_name());
                        intent.putExtra("address", person.getAddress());
                        startActivity(intent);
                        break;
                    case 1:
                        deleteDialog(position);
                        personList.setAdapter(personAdapter);
                        break;
                }
                return false;
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void showResult(String msg) {
        //Nothing
    }

    @Override
    public void showError(String msg) {
        //Nothing
    }

    @Override
    public void showServerError(String msg) {
        Toast.makeText(MainActivity.this, msg, Toast.LENGTH_SHORT).show();
    }

    private void deleteDialog(int pos){
        positionList = pos;
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        builder.setTitle("Delete a Person");
        builder.setMessage("Are you sure?");

        builder.setNegativeButton("CANCEL", null);
        builder.setPositiveButton("YES", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Person p = list.get(positionList);
                presenter.deletePerson(p);
                list = presenter.showAll();
                personAdapter = new PersonAdapter(MainActivity.this, list);

                personList.setAdapter(personAdapter);
            }
        });

        AlertDialog dialog = builder.create();
        dialog.show();
    }
}