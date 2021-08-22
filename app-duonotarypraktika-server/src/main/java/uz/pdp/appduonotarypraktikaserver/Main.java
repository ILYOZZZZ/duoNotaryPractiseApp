package uz.pdp.appduonotarypraktikaserver;

import uz.pdp.appduonotarypraktikaserver.entity.TimeTable;

import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class Main {
    public static void main(String[] args) {
//        DateTimeFormatter formatter=DateTimeFormatter.ofPattern("hh:mm a");
//        LocalDate date = LocalDate.of(2020, 9, 4);
//        int range = 35;
//        List<String> timeList = new ArrayList<>();
//        LocalTime time = LocalTime.of(0, 0, 0);
//        for (int i = 0; i < 24 *60/range; i++) {
//            timeList.add(time.format(formatter));
//            time= time.plusMinutes(range);
//        }
//
//        String weekday= new SimpleDateFormat("EEEE", Locale.ENGLISH).format(new Date().getTime());
//        System.out.println(weekday);
//
//        System.out.println("FRIDAY".equalsIgnoreCase(weekday));
        LocalDate date = LocalDate.parse("2017-06-22");
        System.out.println("LocalDate is: "+date);

        LocalDateTime localDateTime1 = date.atStartOfDay();
        System.out.println("LocalDateTime Start of the Day: "+
                localDateTime1);

    }
}
