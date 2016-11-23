package com.group.improve.improvegrouptestproject;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.view.View;

import com.group.improve.improvegrouptestproject.elements.ImpGroupEditText;
import com.group.improve.improvegrouptestproject.presenters.RegistrationPresenter;


public class RegistrationActivity extends ImpGroupActivity {

    private ImpGroupEditText mFullName;
    private ImpGroupEditText mUserName;
    private ImpGroupEditText mEmail;
    private ImpGroupEditText mPass;
    private ImpGroupEditText mBirth;


    public ImpGroupEditText getmFullName() {
        return mFullName;
    }
    public String getFullNameValue(){
        return this.mFullName.getText().toString();
    }

    public ImpGroupEditText getmUserName() {
        return mUserName;
    }
    public String getUserNameValue(){
        return this.mUserName.getText().toString();
    }

    public ImpGroupEditText getmBirth() {
        return mBirth;
    }
    public String getBirthValue(){
        return this.mBirth.getText().toString();
    }

    public ImpGroupEditText getmPass() {
        return mPass;
    }
    public String getPassValue(){
        return this.mPass.getText().toString();
    }


    public ImpGroupEditText getmEmail() {
        return mEmail;
    }
    public String getEmailValue(){
        return this.mEmail.getText().toString();
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        this.mPresenter=new RegistrationPresenter(this);


        this.mFullName=(ImpGroupEditText)this.findViewById(R.id.r_et_fullname);
        this.mUserName=(ImpGroupEditText)this.findViewById(R.id.r_et_username);
        this.mEmail=(ImpGroupEditText)this.findViewById(R.id.r_et_email);
        this.mPass=(ImpGroupEditText)this.findViewById(R.id.r_et_pass);
        this.mBirth=(ImpGroupEditText)this.findViewById(R.id.r_et_birth);


        this.mBirth.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {

                if(hasFocus)
                    ((RegistrationPresenter)mPresenter).showCalendar();
            }
        });


        FloatingActionButton actionButton=(FloatingActionButton)this.findViewById(R.id.r_fb_action);
        actionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RegistrationActivity.this.mPresenter.action();
            }
        });

    }


}
