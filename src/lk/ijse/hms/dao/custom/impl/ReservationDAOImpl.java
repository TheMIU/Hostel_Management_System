/*
 * Kasun Miuranga
 * Copyright (c) 2023
 */

package lk.ijse.hms.dao.custom.impl;

import lk.ijse.hms.dao.custom.ReservationDAO;
import lk.ijse.hms.entity.Reservation;
import lk.ijse.hms.entity.Room;
import lk.ijse.hms.util.SessionFactoryConfig;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.NativeQuery;

import java.util.ArrayList;
import java.util.List;

public class ReservationDAOImpl implements ReservationDAO {
    @Override
    public ArrayList<Reservation> getData() {
        return null;
    }

    @Override
    public Boolean delete(String id) {
        return null;
    }

    @Override
    public boolean add(Reservation entity) {
        return false;
    }

    @Override
    public boolean update(Reservation entity) {
        return false;
    }

    @Override
    public String getCurrentID() {
        Session session = SessionFactoryConfig.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        try{
            String sql = "SELECT * FROM reservation ORDER BY CAST(SUBSTRING(res_id, 3) AS UNSIGNED) DESC LIMIT 1";
            NativeQuery sqlQuery = session.createSQLQuery(sql);

            sqlQuery.addEntity(Reservation.class);

            List<Reservation> reservationList = sqlQuery.list();
            for(Reservation reservation : reservationList){
                return reservation.getRes_id();
            }
            transaction.commit();
            session.close();

        }catch (Exception e){
            transaction.commit();
            session.close();
            return null;
        }
        return null;
    }
}
