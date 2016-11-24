package com.group.improve.improvegrouptestproject.presenters;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
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
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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

        if(this.isEmailNotValid(email)){
            mActivity.getmEmail().setError(
                    mActivity.getString(R.string.r_error_email_not_valid));
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


    private boolean isEmailNotValid(String email)
    {
        String regExpn =
                "^(([\\w-]+\\.)+[\\w-]+|([a-zA-Z]{1}|[\\w-]{2,}))@"
                        +"((([0-1]?[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\.([0-1]?"
                        +"[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\."
                        +"([0-1]?[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\.([0-1]?"
                        +"[0-9]{1,2}|25[0-5]|2[0-4][0-9])){1}|"
                        +"([a-zA-Z]+[\\w-]+\\.)+[a-zA-Z]{2,4})$";

        CharSequence inputStr = email;

        Pattern pattern = Pattern.compile(regExpn,Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(inputStr);

        if(matcher.matches())
            return false;
        else
            return true;
    }

}
