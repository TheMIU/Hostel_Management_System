package lk.ijse.hms;

import lk.ijse.hms.entity.Reservation;
import lk.ijse.hms.entity.Room;
import lk.ijse.hms.entity.Student;
import lk.ijse.hms.util.SessionFactoryConfig;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class TestMain {
    public static void main(String[] args) throws ParseException {

        Student s1 = new Student();
        s1.setName("Kamal");
        s1.setAddress("galle");
        s1.setContact_no("0773572070");
        s1.setId("S001");
        s1.setGender("male");
        s1.setDob(LocalDate.parse("2000/10/31", DateTimeFormatter.ofPattern("yyyy/MM/dd")));

        Room r1 = new Room();
        r1.setRoom_type_id("Room001");
        r1.setQty(1);
        r1.setKey_money("5000");
        r1.setType("room");

        Reservation res01 = new Reservation();
        res01.setRes_id("R001");
        res01.setStudent(s1);
        res01.setRoom(r1);
        res01.setStatus("booked");
        res01.setDate(LocalDate.now());

        r1.getReservationList().add(res01);
        s1.getReservationList().add(res01);

        Session session = SessionFactoryConfig.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        session.save(s1);
        session.save(r1);

        //session.save(res01);

        transaction.commit();
        session.close();


    }
}
