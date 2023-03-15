public class DeliveryAddress {
    public String name;
    public String street;
    public String city;
    public String province;
    public String postalCode;
    public DeliveryAddress() { }

    public DeliveryAddress(String name, String street, String city, String province, String postalCode) {
        this.name = name;
        this.street = street;
        this.city = city;
        this.province = province;
        this.postalCode = postalCode;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getStreet() {
        return street;
    }
    public void setStreet(String street) {
        this.street = street;
    }
    public String getCity() {
        return city;
    }
    public void setCity(String city) {
        this.city = city;
    }
    public String getProvince() {
        return province;
    }
    public void setProvince(String province) {
        this.province = province;
    }
    public String getPostalCode() {
        return postalCode;
    }
    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }
}
