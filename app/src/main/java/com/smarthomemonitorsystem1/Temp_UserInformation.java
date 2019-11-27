package com.smarthomemonitorsystem1;

public class Temp_UserInformation {

    private String temperature;
    private String OldValue;
    private String EvenOlder;
    private String humidity;

    public String getOldValue() {
        return OldValue;
    }

    public void setOldValue(String oldValue) {
        OldValue = oldValue;
    }

    public String getEvenOlder() {
        return EvenOlder;
    }

    public void setEvenOlder(String evenOlder) {
        EvenOlder = evenOlder;
    }

    public Temp_UserInformation() {
    }

    public String getTemperature() {
        return temperature;
    }

    public void setTemperature(String temperature) {
        this.temperature = temperature;
    }

    public String getHumidity() {
        return humidity;
    }

    public void setHumidity(String humidity) {
        this.humidity = humidity;
    }
}
