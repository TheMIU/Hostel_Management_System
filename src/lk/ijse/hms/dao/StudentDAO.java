package lk.ijse.hms.dao;

import lk.ijse.hms.entity.Student;
import lk.ijse.hms.util.SessionFactoryConfig;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.NativeQuery;
import org.hibernate.query.Query;
import org.hibernate.transform.Transformers;

import java.util.ArrayList;


public class StudentDAO {
    public static boolean add(Student student) {
        Session session = SessionFactoryConfig.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        try {
            session.save(student);
            transaction.commit();
            session.close();
            return true;
        } catch (Exception ex) {
            transaction.rollback();
            session.close();
            return false;
        }
    }

    public static ArrayList<Student> getData() {
        Session session = SessionFactoryConfig.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        NativeQuery nativeQuery = session.createNativeQuery("SELECT * FROM student ORDER BY CAST(SUBSTRING(id, 2) AS UNSIGNED)");
        Query query = nativeQuery.setResultTransformer(Transformers.aliasToBean(Student.class));

        ArrayList<Student> studentData = (ArrayList<Student>) query.getResultList();

        return studentData;
    }

    public static Boolean delete(Student student) {
        Session session = SessionFactoryConfig.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        try {
            session.delete(student);
            transaction.commit();
            session.close();
            return true;
        } catch (Exception ex) {
            transaction.rollback();
            session.close();
            return false;
        }
    }
}
