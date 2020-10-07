package com.carrizane.mvppersonorm.presenters;

import com.carrizane.mvppersonorm.entities.Person;

import java.util.List;

public interface PersonPresenter {

    void addPerson(Person p);
    void deletePerson(Person p);
    void editPerson(int id, Person p);
    List<Person> showAll();
    void ShowError(String msg);
    void ShowResult(String msg);
    void ShowServerError(String msg);

}
