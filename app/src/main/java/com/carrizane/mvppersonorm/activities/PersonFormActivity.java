package com.carrizane.mvppersonorm.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.carrizane.mvppersonorm.R;
import com.carrizane.mvppersonorm.entities.Person;
import com.carrizane.mvppersonorm.presenters.PersonPresenter;
import com.carrizane.mvppersonorm.presentersImp.PersonPresenterImp;
import com.carrizane.mvppersonorm.views.PersonView;

public class PersonFormActivity extends AppCompatActivity implements PersonView {

    TextView title;

    EditText name, last_name, address;

    Button add;

    Person person;
    PersonPresenter presenter;

    int idPerson;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_person_form);
        setContentView(R.layout.activity_person_form);

        title = (TextView) findViewById(R.id.titleCreate);

        name = (EditText) findViewById(R.id.nameInput);
        last_name = (EditText) findViewById(R.id.lastnameInput);
        address = (EditText) findViewById(R.id.addressInput);

        add = (Button) findViewById(R.id.addBtn);

        presenter = new PersonPresenterImp(this);

        Bundle extras = getIntent().getExtras();
        if(extras == null){
            add.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    person = new Person();
                    person.setName(name.getText().toString());
                    person.setLast_name(last_name.getText().toString());
                    person.setAddress(address.getText().toString());
                    createPerson(person);
                }
            });
        }else{
            title.setText("Update");
            add.setText("UPDATE");
            idPerson = extras.getInt("id");
            String n, l, a;
            n = extras.getString("name");
            l = extras.getString("last_name");
            a = extras.getString("address");
            name.setText(n);
            last_name.setText(l);
            address.setText(a);
            person = new Person();
            add.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    person.setName(name.getText().toString());
                    person.setLast_name(last_name.getText().toString());
                    person.setAddress(address.getText().toString());
                    updatePerson(idPerson, person);
                }
            });
        }
    }

    @Override
    public void showResult(String msg) {
        Toast.makeText(PersonFormActivity.this, msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showError(String msg) {
        if(name.getText().toString().equals("")){
            name.setError(msg);
        }else if(last_name.getText().toString().equals("")){
            last_name.setError(msg);
        }else if(address.getText().toString().equals("")){
            address.setError((msg));
        }
    }

    @Override
    public void showServerError(String msg) {
        Toast.makeText(PersonFormActivity.this, msg, Toast.LENGTH_SHORT).show();
    }

    private void createPerson(Person p){
        presenter.addPerson(p);
        Intent intent = new Intent(PersonFormActivity.this, MainActivity.class);
        startActivity(intent);
    }

    private void updatePerson(int id, Person p){
        presenter.editPerson(id, person);
        Intent intent = new Intent(PersonFormActivity.this, MainActivity.class);
        startActivity(intent);
    }

}