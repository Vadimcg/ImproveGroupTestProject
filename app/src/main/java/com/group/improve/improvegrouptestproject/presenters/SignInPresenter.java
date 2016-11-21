package com.group.improve.improvegrouptestproject.presenters;

import com.group.improve.improvegrouptestproject.R;
import com.group.improve.improvegrouptestproject.SignInActivity;

/**
 *
 */
public class SignInPresenter implements IPresenter {

    private SignInActivity mActivity;

    public SignInPresenter(SignInActivity mActivity){
        this.mActivity= mActivity;
    }

    public boolean check(){
        String userName=mActivity.getUserName().getText().toString();
        String password=mActivity.getPassword().getText().toString();

        //Check Username
        if(userName.isEmpty()){
            mActivity.getUserName().setError(
                    mActivity.getString(R.string.si_error_username_empty));
            return false;
        }

        //Check password
        if(password.isEmpty()){
            mActivity.getPassword().setError(
                    mActivity.getString(R.string.si_error_password_empty));
            return false;
        }


        return true;

    }

    public void action(){
        if(this.check()){

        }
    }



}
