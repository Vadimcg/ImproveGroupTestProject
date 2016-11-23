package com.group.improve.improvegrouptestproject.presenters;

import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import com.group.improve.improvegrouptestproject.R;
import com.group.improve.improvegrouptestproject.RegistrationActivity;
import com.group.improve.improvegrouptestproject.SignInActivity;
import com.group.improve.improvegrouptestproject.models.DTO.User;
import com.group.improve.improvegrouptestproject.models.UserModel;

/**
 *
 */
public class SignInPresenter implements IPresenter {

    private SignInActivity mActivity;

    private UserModel mModel;

    public SignInPresenter(SignInActivity mActivity){

        this.mModel=new UserModel();
        this.mActivity= mActivity;
    }

    public boolean check(){
        String userName=mActivity.getUserNameValue();
        String password=mActivity.getPasswordValue();

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
            User userWrap=mModel.getUser(mActivity.getUserNameValue());

            if(userWrap!=null){
                if(userWrap.compareHashes(mActivity.getPasswordValue()))
                    Toast.makeText(mActivity,mActivity.getString(R.string.si_success),Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(mActivity,mActivity.getString(R.string.si_error_incorrect_username_pass),Toast.LENGTH_SHORT).show();

            }else
                Toast.makeText(mActivity,mActivity.getString(R.string.si_error_not_be_found),Toast.LENGTH_SHORT).show();
        }
    }


    public void registrationAction(){
        Intent intent=new Intent(mActivity,RegistrationActivity.class);
        mActivity.startActivity(intent);
    }



}
