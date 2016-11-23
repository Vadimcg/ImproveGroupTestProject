package com.group.improve.improvegrouptestproject.presenters;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.widget.DatePicker;
import android.widget.Toast;

import com.group.improve.improvegrouptestproject.R;
import com.group.improve.improvegrouptestproject.RegistrationActivity;
import com.group.improve.improvegrouptestproject.SignInActivity;
import com.group.improve.improvegrouptestproject.models.DTO.User;
import com.group.improve.improvegrouptestproject.models.UserModel;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

/**
 *
 */

public class RegistrationPresenter implements IPresenter {


    private Calendar mCalendar;
    private DatePickerDialog.OnDateSetListener mDate;


    private RegistrationActivity mActivity;

    private UserModel mModel;

    public RegistrationPresenter(RegistrationActivity mActivity){

        this.mModel=new UserModel();
        this.mActivity= mActivity;

        this.mCalendar = Calendar.getInstance();

        this.mDate = new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {
                mCalendar.set(Calendar.YEAR, year);
                mCalendar.set(Calendar.MONTH, monthOfYear);
                mCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                updateLabel();
            }
        };
    }



    public boolean check(){
        String userName=mActivity.getUserNameValue();
        String password=mActivity.getPassValue();
        String email=mActivity.getEmailValue();

        //Check Username
        if(userName.isEmpty()){
            mActivity.getmUserName().setError(
                    mActivity.getString(R.string.r_error_username_empty));
            return false;
        }

        //Check password
        if(password.isEmpty()){
            mActivity.getmPass().setError(
                    mActivity.getString(R.string.r_error_password_empty));
            return false;
        }



        //Check email
        if(email.isEmpty()){
            mActivity.getmEmail().setError(
                    mActivity.getString(R.string.r_error_email_empty));
            return false;
        }


        return true;

    }

    public void action(){
        if(this.check()){
            User userWrap=mModel.getUser(mActivity.getUserNameValue());

            if(userWrap!=null){
                mActivity.getmUserName()
                        .setError(mActivity.getString(R.string.r_error_name_is_using));

            }else{

                User user=new User.Builder()
                        .setbFullName(mActivity.getFullNameValue())
                        .setbUserName(mActivity.getUserNameValue())
                        .setbEmail(mActivity.getEmailValue())
                        .setbBirth(mActivity.getBirthValue())
                        .setbPass(mActivity.getPassValue())
                        .build();

                if(mModel.saveUser(user)!=UserModel.ERROR_SAVE){
                    Intent intent=new Intent(mActivity, SignInActivity.class);
                    mActivity.startActivity(intent);
                }else
                    Toast.makeText(mActivity,
                            mActivity.getString(R.string.r_error_db_save),Toast.LENGTH_SHORT).show();
            }

        }
    }

    public void showCalendar(){
        new DatePickerDialog(this.mActivity, mDate, mCalendar
                .get(Calendar.YEAR), mCalendar.get(Calendar.MONTH),
                mCalendar.get(Calendar.DAY_OF_MONTH)).show();
    }


    private void updateLabel() {

        String myFormat = "MM/dd/yy";
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);

        mActivity.getmBirth().setText(sdf.format(mCalendar.getTime()));
    }

}
