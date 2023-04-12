package lk.ijse.hms.dao;

import lk.ijse.hms.dao.custom.impl.QueryDAOImpl;
import lk.ijse.hms.dao.custom.impl.ReservationDAOImpl;
import lk.ijse.hms.dao.custom.impl.RoomsDAOImpl;
import lk.ijse.hms.dao.custom.impl.StudentDAOImpl;

public class FactoryDAO {
    private static FactoryDAO factoryDAO;

    private FactoryDAO(){
    }

    public static FactoryDAO getFactoryDAO(){
        return factoryDAO == null ? factoryDAO = new FactoryDAO() : factoryDAO;
    }

    public enum Types {
        STUDENT,
        ROOM,
        RECEPTION,
        JOIN_QUERY
    }

    // factory - Object creation logic eka hide krnna.
    // singleton  - object ekak eka parak hadala eka re use karanna

    public SuperDAO getDAO(Types types){
        switch (types){
            case STUDENT:
                return new StudentDAOImpl();
            case ROOM:
                return new RoomsDAOImpl();
            case RECEPTION:
                return new ReservationDAOImpl();
            case JOIN_QUERY:
                return new QueryDAOImpl();
            default: return null;
        }
    }
}