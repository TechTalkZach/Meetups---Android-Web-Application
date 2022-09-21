package com.projetXML.meetups.utilities;

import android.content.Context;
import android.content.DialogInterface;

import androidx.appcompat.app.AlertDialog;

public class Utilities {

    public static void alertMsg(Context context, String message){
            AlertDialog.Builder alertDialogBuilder=new AlertDialog.Builder(context);
            alertDialogBuilder.setTitle("Alerte");

            alertDialogBuilder.setMessage(message);
            alertDialogBuilder.setCancelable(false);


            alertDialogBuilder.setNeutralButton("Ok",new DialogInterface.OnClickListener(){
                @Override
                public void onClick(DialogInterface dialog,int which){
                    dialog.cancel();
                }
            });

            AlertDialog alertDialog=alertDialogBuilder.create();
            alertDialog.show();
        }//alertMsg()
}
