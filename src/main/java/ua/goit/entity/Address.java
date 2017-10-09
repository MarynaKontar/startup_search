package ua.goit.entity;

import ua.goit.entity.enums.Country;

import javax.persistence.*;
import java.io.Serializable;

/**
 *
 * @KontarMaryna
 * @GuillaumeGingembre
 * @VitaliiProskura
 */

@Entity
@Table(name = "address")
public class Address implements Serializable {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Long id;

    private String city;

    private String region;

    @Enumerated(EnumType.STRING)
    private Country country;

    public Address() {
    }

    public Address(String city, String region, Country country) {
        this.city = city;
        this.region = region;
        this.country = country;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
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

        Address address = (Address) o;

        if (id != null ? !id.equals(address.id) : address.id != null) return false;
        if (city != null ? !city.equals(address.city) : address.city != null) return false;
        if (region != null ? !region.equals(address.region) : address.region != null) return false;
        return country == address.country;
    }

    @Override
    public int hashCode() {
//        String.valueOf(id).hashCode()
        int result = id != null ? Long.hashCode(id) : 0;
        result = 31 * result + (city != null ? city.hashCode() : 0);
        result = 31 * result + (region != null ? region.hashCode() : 0);
        result = 31 * result + (country != null ? country.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Address{" +
                "id=" + id +
                ", city='" + city + '\'' +
                ", region='" + region + '\'' +
                ", country=" + country +
                '}';
    }
}
