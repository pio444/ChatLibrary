package com.example.pio.chatlibrary.login;

import android.util.Log;
import android.widget.Toast;

import com.example.pio.chatlibrary.network.ModelRegister;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by bober on 17.09.15.
 */
public class Validation {

    public static final String TAG = Validation.class.getSimpleName();

    private String registerName;
    private String registerPassword;
    private String registerConfirmPassword;
    private String registerE_mail;
    private String registerFirstName;
    private String registerLastName;
    private String registerPesel;
    private String dataOfBirthday;
    private String registerCity;
    private String registerPostalCode;
    private String registerStreet;
    private String registerPostalCity;
    private String registerHomeNumber;
    private String registerSex;
    private String registerType;

    public Validation(String registerName,
                      String registerPassword,
                      String registerConfirmPassword,
                      String registerE_mail,
                      String registerFirstName,
                      String registerLastName,
                      String registerPesel,
                      String dataOfBirthday,
                      String registerCity,
                      String registerPostalCode,
                      String registerStreet,
                      String registerPostalCity,
                      String registerHomeNumber,
                      String registerSex,
                      String registerType) {

        this.registerName = registerName;
        this.registerPassword = registerPassword;
        this.registerConfirmPassword = registerConfirmPassword;
        this.registerE_mail = registerE_mail;
        this.registerFirstName = registerFirstName;
        this.registerLastName = registerLastName;
        this.registerPesel = registerPesel;
        this.dataOfBirthday = dataOfBirthday;
        this.registerCity = registerCity;
        this.registerPostalCode = registerPostalCode;
        this.registerStreet = registerStreet;
        this.registerPostalCity = registerPostalCity;
        this.registerHomeNumber = registerHomeNumber;
        this.registerSex = registerSex;
        this.registerType = registerType;

    }

    public Validation(String registerName,
                      String registerPassword) {

        this.registerName = registerName;
        this.registerPassword = registerPassword;

    }

    public String validationRegister() {
        Log.d(TAG, registerPesel);
        if (!ModelValidation.validationName(registerName)) {
            return "Invalid login";
        }
        else if (!ModelValidation.validationPassword(registerPassword, registerConfirmPassword)) {
            return "Invalid Password";
        }
        else if (!ModelValidation.validationE_mail(registerE_mail)) {
            return "Invalid E-mail";
        }
        else if (!ModelValidation.validationFirstName(registerFirstName)) {
            return "Invalid First Name";
        }
        else if (!ModelValidation.validationLastName(registerLastName)) {
            return "Invalid Last Name";
        }
        else if (!ModelValidation.validationPesel(registerPesel)) {
            return "Invalid Pesel";
        }
        else if (dataOfBirthday == null) {
            return "Invalid Data Of Birthday";
        }
        else if (!ModelValidation.validationCity(registerCity)) {
            return "Invalid City";
        }
        else if (!ModelValidation.validationPostalCode(registerPostalCode)) {
            return "Invalid Postal Code";
        }
        else if (!ModelValidation.validationStreet(registerStreet)) {
            return "Invalid Street";
        }
        else if (!ModelValidation.validationPostalCity(registerPostalCity)) {
            return "Invalid Postal City";
        }
        else if (!ModelValidation.validationHomeNumber(registerHomeNumber)) {
            return "Invalid Home Number";
        }
        return "true";
    }

    public String validationLogin() {
        if (!ModelValidation.validationName(registerName)) {
            return "Invalid login";
        }
        else if (!ModelValidation.validationPassword(registerPassword, registerPassword)) {
            return "Invalid Password";
        }
        return "true";
    }

    public ModelRegister creatModelRegister() {

        ModelRegister model = new ModelRegister();
        model.setUser(registerName);
        Log.d(TAG + "/dane", "user: " + registerName);
        model.setPassword(registerPassword);
        Log.d(TAG + "/dane", "Password: " + registerPassword);
        model.setPasswordConfirmation(registerConfirmPassword);
        Log.d(TAG + "/dane", "ConfirmPassword: " + registerConfirmPassword);

        /***Emails attributes***/
        List<ModelRegister.EmailsAttribute> emailsAttributes = new ArrayList<ModelRegister.EmailsAttribute>();
        ModelRegister.EmailsAttribute email_sAttributes = new ModelRegister.EmailsAttribute();
        email_sAttributes.setAddress(registerE_mail);
        Log.d(TAG + "/dane", "E_mail: " + registerE_mail);
        emailsAttributes.add(email_sAttributes);
        model.setEmailsAttributes(emailsAttributes);

        /***Person attributes***/
        ModelRegister.PersonAttributes personAttributes = new ModelRegister.PersonAttributes();
        personAttributes.setPESEL(registerPesel);
        Log.d(TAG + "/dane", "Pesel: " + registerPesel);
        personAttributes.setDateOfBirth(dataOfBirthday);
        Log.d(TAG + "/dane", "DateOfBirth: " + dataOfBirthday);
        personAttributes.setFirstName(registerFirstName);
        Log.d(TAG + "/dane", "FirstName: " + registerFirstName);
        personAttributes.setLastName(registerLastName);
        Log.d(TAG + "/dane", "LastName: " + registerLastName);
        personAttributes.setSex(registerSex);
        Log.d(TAG + "/dane", "Sex: " + registerSex);

        /***Person attr***/
        List<ModelRegister.AddressesAttribute> addressesAttributes = new ArrayList<ModelRegister.AddressesAttribute>();
        ModelRegister.AddressesAttribute addresses_Attributes = new ModelRegister.AddressesAttribute();
        addresses_Attributes.setCity(registerCity);
        Log.d(TAG + "/dane", "City: " + registerCity);
        addresses_Attributes.setStreet(registerStreet);
        Log.d(TAG + "/dane", "Street: " + registerStreet);
        addresses_Attributes.setHomeNumber(registerHomeNumber);
        Log.d(TAG + "/dane", "HomeNumber: " + registerHomeNumber);
        addresses_Attributes.setPostalCode(registerPostalCode);
        Log.d(TAG + "/dane", "PostalCode: " + registerPostalCode);
        addresses_Attributes.setPostalCity(registerPostalCity);
        Log.d(TAG + "/dane", "PostalCity: " + registerPostalCity);
        addresses_Attributes.setAddressType(registerType);
        Log.d(TAG + "/dane", "PostalType: " + registerType);
        addressesAttributes.add(addresses_Attributes);
        personAttributes.setAddressesAttributes(addressesAttributes);
        model.setPersonAttributes(personAttributes);

        return model;
    }

}
