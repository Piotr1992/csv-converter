package com.kodilla.csvconverter;

import net.bytebuddy.asm.Advice;
import org.springframework.batch.item.ItemProcessor;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

public class PersonProcessor implements ItemProcessor<Person, Person> {

    @Override
    public Person process(Person item) throws Exception {

        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy", Locale.ENGLISH);
        Date secondDate = sdf.parse(item.getBirthdate());
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(secondDate);
        LocalDate ld = LocalDate.now();
        String age = String.valueOf(ld.getYear() - calendar.get(Calendar.YEAR));

        return new Person(item.getId(), item.getFirstname(), item.getLastname(), age);

    }
}