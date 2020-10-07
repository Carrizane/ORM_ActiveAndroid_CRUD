package com.carrizane.mvppersonorm.interactorsImp;

import android.util.Log;

import com.activeandroid.query.Select;
import com.carrizane.mvppersonorm.entities.Person;
import com.carrizane.mvppersonorm.interactors.PersonInteractor;
import com.carrizane.mvppersonorm.presenters.PersonPresenter;

import java.util.List;

public class PersonInteractorImp implements PersonInteractor {

    private PersonPresenter presenter;

    public PersonInteractorImp(PersonPresenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void addPerson(Person p) {
        if(p.getName().equals("")){
            presenter.ShowError("Name required!");
        }else if(p.getLast_name().equals("")){
            presenter.ShowError("Last name required!");
        }else if(p.getAddress().equals("")){
            presenter.ShowError("Address required!");
        }else{
            try {
                p.save();
                presenter.ShowResult("Person saved.");
            }catch (Exception e){
                    presenter.ShowServerError(e.getMessage());
            }
        }
    }

    @Override
    public void deletePerson(Person p) {
        Log.i("ID_PERSON", String.valueOf(p.getId()));
        p = Person.load(Person.class, p.getId());
        p.delete();
    }

    @Override
    public void editPerson(int id, Person p) {
        Person old = Person.load(Person.class, id);
        old.setName(p.getName());
        old.setLast_name(p.getLast_name());
        old.setAddress(p.getAddress());
        old.save();
    }

    @Override
    public List<Person> showAll() {
        return new Select().from(Person.class).execute();
    }

}
