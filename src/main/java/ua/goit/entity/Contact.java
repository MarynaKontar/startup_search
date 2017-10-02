package ua.goit.entity;

import ua.goit.entity.enums.Country;

import javax.persistence.*;

/**
 * Created by Maryna Kontar on 13.09.2017.
 * @KontarMaryna
 */
@Embeddable
public class Contact {

    //    @Email
    private String email;
    private String phoneNumber;
    private String city;
    @Enumerated(EnumType.STRING)
    private Country country;

    public Contact() {
    }

    public Contact(String email, String phoneNumber, String city, Country country) {
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.city = city;
        this.country = country;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Contact contact = (Contact) o;

        if (email != null ? !email.equals(contact.email) : contact.email != null) return false;
        if (phoneNumber != null ? !phoneNumber.equals(contact.phoneNumber) : contact.phoneNumber != null) return false;
        if (city != null ? !city.equals(contact.city) : contact.city != null) return false;
        return country == contact.country;
    }

    @Override
    public int hashCode() {
        int result = email != null ? email.hashCode() : 0;
        result = 31 * result + (phoneNumber != null ? phoneNumber.hashCode() : 0);
        result = 31 * result + (city != null ? city.hashCode() : 0);
        result = 31 * result + (country != null ? country.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Contact{}";
    }
}
