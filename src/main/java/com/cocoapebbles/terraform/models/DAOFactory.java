package com.cocoapebbles.terraform.models;

import com.cocoapebbles.terraform.models.entity.EntityDAO;

/**
 * The DAO Factory object to abstract the implementation of DAO interfaces.
 */
public class DAOFactory {

    public static EntityDAO getEntityDAO(){
        return EntityDAO.getInstance();
    }
}
