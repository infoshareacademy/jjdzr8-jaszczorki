package com.isa.control;

import static com.isa.entity.appConstants.AppConstants.ACCEPT_OR_BACK_TO_MENU_MESSAGE;


public class DeleteOptions extends SubMenuNavigator{

    private static final String DELETE = "Skasuj ogłoszenie.";
    public void showDeleteDetails(){

        System.out.println(DELETE);
        System.out.println(ACCEPT_OR_BACK_TO_MENU_MESSAGE);
        subMenuActions();
    }
}
