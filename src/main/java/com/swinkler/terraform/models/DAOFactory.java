package com.swinkler.terraform.models;

import com.swinkler.terraform.models.entity.EntityDAO;
import com.swinkler.terraform.models.shape.ShapeDAO;

/**
 * The DAO Factory object to abstract the implementation of DAO interfaces.
 */
public class DAOFactory {

    public static EntityDAO getEntityDAO(){
        return EntityDAO.getInstance();
    }
    public static ShapeDAO getShapeDAO(){return ShapeDAO.getInstance();}
}
