package models;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class Contact {
    private String name;
    private String phoneNumber;
    private String birthDate;
    private int age;

    public Contact(String name, String phoneNumber, String birthDate) throws ParseException {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.birthDate = birthDate;
        this.age = toAge(birthDate);
    }

    private int toAge(String birthDate) throws ParseException {
        SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
        formatter.getCalendar().setLenient(false);
        Date toDate = formatter.parse(birthDate);
        long toMilli = toDate.getTime();
        long diff = new Date().getTime() - toMilli;
        return (int) (TimeUnit.MILLISECONDS.toDays(diff)) / 365;
    }
}
