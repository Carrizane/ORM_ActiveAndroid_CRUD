package com.carrizane.mvppersonorm.presentersImp;

import com.carrizane.mvppersonorm.entities.Person;
import com.carrizane.mvppersonorm.interactors.PersonInteractor;
import com.carrizane.mvppersonorm.interactorsImp.PersonInteractorImp;
import com.carrizane.mvppersonorm.presenters.PersonPresenter;
import com.carrizane.mvppersonorm.views.PersonView;

import java.util.List;

public class PersonPresenterImp implements PersonPresenter {

    private PersonView view;
    private PersonInteractor interactor;

    public PersonPresenterImp(PersonView view) {
        this.view = view;
        interactor = new PersonInteractorImp(this);
    }

    @Override
    public void addPerson(Person p) {
        if(view != null){
            interactor.addPerson(p);
        }
    }

    @Override
    public void deletePerson(Person p) {
        if(view != null){
            interactor.deletePerson(p);
        }
    }

    @Override
    public void editPerson(int id, Person p) {
        if(view != null){
            interactor.editPerson(id, p);
        }
    }

    @Override
    public List<Person> showAll() {
        if(view != null){
            return interactor.showAll();
        }
        return null;
    }

    @Override
    public void ShowError(String msg) {
        if(view != null){
            view.showError(msg);
        }
    }

    @Override
    public void ShowResult(String msg) {
        if(view != null){
            view.showResult(msg);
        }
    }

    @Override
    public void ShowServerError(String msg) {
        if(view != null){
            view.showServerError(msg);
        }
    }

}
