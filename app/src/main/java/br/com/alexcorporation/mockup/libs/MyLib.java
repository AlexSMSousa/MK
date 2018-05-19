package br.com.alexcorporation.mockup.libs;

import android.support.v7.app.ActionBar;

/**
 * Created by Alex on 18/05/2018.
 */

public class MyLib {
    public static MyLib myLib = new MyLib();

    private MyLib() {
    }

    //Metodo utilizado para criar o botão up da toolbar
    public void createButtonUp(ActionBar supportActionBar){

        if(supportActionBar != null){
            supportActionBar.setHomeButtonEnabled(true);
            supportActionBar.setDisplayHomeAsUpEnabled(true);
        }
    }
}
