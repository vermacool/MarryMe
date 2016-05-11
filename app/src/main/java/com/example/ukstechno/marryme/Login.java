package com.example.ukstechno.marryme;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

/**
 * Created by admin on 12/29/2015.
 */
public class Login extends Activity {
    Button loginBtn;
    String emailname;
    String  paswordTv;
    EditText  emailTv,passwordTv;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        emailTv=(EditText)findViewById(R.id.emailTv);
        loginBtn=(Button)findViewById(R.id.loginBtn);
        passwordTv=(EditText)findViewById(R.id.passwordTv);

        /*loginBtn = (Button) findViewById(R.id.loginBtn);
        loginBtn.setOnClickListener(this);
        emailTv = (EditText) findViewById(R.id.emailTv);
        passwordTv = (EditText)  findViewById(R.id.TFPassword);*/



    }


    /*public void onClick(View v) {

        switch (v.getId())
        {
            case R.id.loginBtn:

                emailname = emailTv.getText().toString();
                paswordTv = passwordTv.getText().toString();

                if(checkField())

                {
                    //  Glo

                    AlertDialog a =  new AlertDialog.Builder(this)
                            .setTitle("Fields Are Blank")

                            .setPositiveButton("ok", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {

                                    dialog.dismiss();
                                    // continue with delete
                                }
                            })

                            .setIcon(android.R.drawable.ic_dialog_alert)
                            .show();
                }else
                {
                    LoginAsyc r = new LoginAsyc(Login.this,emailname,paswordTv);
                    r.execute();
                    //Toast.makeText(Login.this, "Registration Successfull", 5000).show();
                   // Intent i=new Intent(Login.this,MainActivity.class);
                    //startActivity(i);
                }

                break;
        }

    }

    public  boolean  checkField()
    {
        boolean  isFieldOk= false;

        if(emailname.equals("") && paswordTv.equals(""))
        {
            isFieldOk= true;
        }
        return  isFieldOk;
    }*/


}
