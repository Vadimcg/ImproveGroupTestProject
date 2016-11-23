package com.group.improve.improvegrouptestproject;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.view.View;
import android.widget.TextView;

import com.group.improve.improvegrouptestproject.elements.ImpGroupEditText;
import com.group.improve.improvegrouptestproject.presenters.SignInPresenter;

/**
 * A login screen that offers login via email/password.
 */
public class SignInActivity extends ImpGroupActivity{

    private ImpGroupEditText mUserName;
    public  ImpGroupEditText getUserName(){
        return this.mUserName;
    }
    public  String getUserNameValue(){
        return this.mUserName.getText().toString();
    }

    private ImpGroupEditText mPassword;
    public ImpGroupEditText getPassword(){
        return  this.mPassword;
    }
    public  String getPasswordValue(){
        return this.mPassword.getText().toString();
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        super.mPresenter=new SignInPresenter(this);

        this.mUserName=(ImpGroupEditText)this.findViewById(R.id.si_et_username);
        this.mPassword=(ImpGroupEditText)this.findViewById(R.id.si_et_pass);

        //Launch checking if user clicks the action button
        FloatingActionButton  actionButton=(FloatingActionButton)this.findViewById(R.id.si_fb_action);
        actionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SignInActivity.this.mPresenter.action();
            }
        });


        TextView sTvReg=(TextView)this.findViewById(R.id.si_tv_reg);
        sTvReg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((SignInPresenter)mPresenter).registrationAction();
            }
        });
    }




}

