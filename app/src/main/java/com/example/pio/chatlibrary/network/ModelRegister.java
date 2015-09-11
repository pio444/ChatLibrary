package com.example.pio.chatlibrary.network;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class ModelRegister {

    static public class AddressesAttribute {

        @Expose
        private String city;
        @Expose
        private String street;
        @SerializedName("home_number")
        @Expose
        private String homeNumber;
        @SerializedName("postal_code")
        @Expose
        private String postalCode;
        @SerializedName("postal_city")
        @Expose
        private String postalCity;
        @SerializedName("address_type")
        @Expose
        private String addressType;

        /**
         *
         * @return
         * The city
         */
        public String getCity() {
            return city;
        }

        /**
         *
         * @param city
         * The city
         */
        public void setCity(String city) {
            this.city = city;
        }

        /**
         *
         * @return
         * The street
         */
        public String getStreet() {
            return street;
        }

        /**
         *
         * @param street
         * The street
         */
        public void setStreet(String street) {
            this.street = street;
        }

        /**
         *
         * @return
         * The homeNumber
         */
        public String getHomeNumber() {
            return homeNumber;
        }

        /**
         *
         * @param homeNumber
         * The home_number
         */
        public void setHomeNumber(String homeNumber) {
            this.homeNumber = homeNumber;
        }

        /**
         *
         * @return
         * The postalCode
         */
        public String getPostalCode() {
            return postalCode;
        }

        /**
         *
         * @param postalCode
         * The postal_code
         */
        public void setPostalCode(String postalCode) {
            this.postalCode = postalCode;
        }

        /**
         *
         * @return
         * The postalCity
         */
        public String getPostalCity() {
            return postalCity;
        }

        /**
         *
         * @param postalCity
         * The postal_city
         */
        public void setPostalCity(String postalCity) {
            this.postalCity = postalCity;
        }

        /**
         *
         * @return
         * The addressType
         */
        public String getAddressType() {
            return addressType;
        }

        /**
         *
         * @param addressType
         * The address_type
         */
        public void setAddressType(String addressType) {
            this.addressType = addressType;
        }

    }

    static public class EmailsAttribute {

        @Expose
        private String address;

        /**
         *
         * @return
         * The address
         */
        public String getAddress() {
            return address;
        }

        /**
         *
         * @param address
         * The address
         */
        public void setAddress(String address) {
            this.address = address;
        }

    }

    static public class PersonAttributes {

        @Expose
        private String PESEL;
        @SerializedName("date_of_birth")
        @Expose
        private String dateOfBirth;
        @SerializedName("first_name")
        @Expose
        private String firstName;
        @SerializedName("last_name")
        @Expose
        private String lastName;
        @Expose
        private String sex;
        @SerializedName("addresses_attributes")
        @Expose
        private List<AddressesAttribute> addressesAttributes = new ArrayList<AddressesAttribute>();

        /**
         *
         * @return
         * The PESEL
         */
        public String getPESEL() {
            return PESEL;
        }

        /**
         *
         * @param PESEL
         * The PESEL
         */
        public void setPESEL(String PESEL) {
            this.PESEL = PESEL;
        }

        /**
         *
         * @return
         * The dateOfBirth
         */
        public String getDateOfBirth() {
            return dateOfBirth;
        }

        /**
         *
         * @param dateOfBirth
         * The date_of_birth
         */
        public void setDateOfBirth(String dateOfBirth) {
            this.dateOfBirth = dateOfBirth;
        }

        /**
         *
         * @return
         * The firstName
         */
        public String getFirstName() {
            return firstName;
        }

        /**
         *
         * @param firstName
         * The first_name
         */
        public void setFirstName(String firstName) {
            this.firstName = firstName;
        }

        /**
         *
         * @return
         * The lastName
         */
        public String getLastName() {
            return lastName;
        }

        /**
         *
         * @param lastName
         * The last_name
         */
        public void setLastName(String lastName) {
            this.lastName = lastName;
        }

        /**
         *
         * @return
         * The sex
         */
        public String getSex() {
            return sex;
        }

        /**
         *
         * @param sex
         * The sex
         */
        public void setSex(String sex) {
            this.sex = sex;
        }

        /**
         *
         * @return
         * The addressesAttributes
         */
        public List<AddressesAttribute> getAddressesAttributes() {
            return addressesAttributes;
        }

        /**
         *
         * @param addressesAttributes
         * The addresses_attributes
         */
        public void setAddressesAttributes(List<AddressesAttribute> addressesAttributes) {
            this.addressesAttributes = addressesAttributes;
        }

    }

    @Expose
    private String user;
    @Expose
    private String password;
    @SerializedName("password_confirmation")
    @Expose
    private String passwordConfirmation;
    @SerializedName("emails_attributes")
    @Expose
    private List<EmailsAttribute> emailsAttributes = new ArrayList<EmailsAttribute>();
    @SerializedName("person_attributes")
    @Expose
    private PersonAttributes personAttributes;

    /**
     *
     * @return
     * The user
     */
    public String getUser() {
        return user;
    }

    /**
     *
     * @param user
     * The user
     */
    public void setUser(String user) {
        this.user = user;
    }

    /**
     *
     * @return
     * The password
     */
    public String getPassword() {
        return password;
    }

    /**
     *
     * @param password
     * The password
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     *
     * @return
     * The passwordConfirmation
     */
    public String getPasswordConfirmation() {
        return passwordConfirmation;
    }

    /**
     *
     * @param passwordConfirmation
     * The password_confirmation
     */
    public void setPasswordConfirmation(String passwordConfirmation) {
        this.passwordConfirmation = passwordConfirmation;
    }

    /**
     *
     * @return
     * The emailsAttributes
     */
    public List<EmailsAttribute> getEmailsAttributes() {
        return emailsAttributes;
    }

    /**
     *
     * @param emailsAttributes
     * The emails_attributes
     */
    public void setEmailsAttributes(List<EmailsAttribute> emailsAttributes) {
        this.emailsAttributes = emailsAttributes;
    }

    /**
     *
     * @return
     * The personAttributes
     */
    public PersonAttributes getPersonAttributes() {
        return personAttributes;
    }

    /**
     *
     * @param personAttributes
     * The person_attributes
     */
    public void setPersonAttributes(PersonAttributes personAttributes) {
        this.personAttributes = personAttributes;
    }

}