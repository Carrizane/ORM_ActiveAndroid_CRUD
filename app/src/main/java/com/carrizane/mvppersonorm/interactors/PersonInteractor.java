package com.carrizane.mvppersonorm.interactors;

import com.carrizane.mvppersonorm.entities.Person;

import java.util.List;

public interface PersonInteractor {

    void addPerson(Person p);
    void deletePerson(Person p);
    void editPerson(int id, Person p);
    List<Person> showAll();

}
