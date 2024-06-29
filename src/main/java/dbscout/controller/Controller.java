package dbscout.controller;

import dbscout.view.View;

public class Controller {

    private final View view;

    public Controller(final View view) {
        this.view = view;
    }

    public void requestLoginPage() {
        this.view.loginPage();
    } 

    public void requestLupettoPage() {
        this.view.lupettoPage();
    }

}
