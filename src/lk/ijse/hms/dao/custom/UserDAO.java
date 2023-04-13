/*
 * Kasun Miuranga
 * Copyright (c) 2023
 */

package lk.ijse.hms.dao.custom;

import lk.ijse.hms.dao.CrudDAO;
import lk.ijse.hms.entity.User;

public interface UserDAO extends CrudDAO<User> {
    String getUser(String id);

    String getPassword(String id);
}
