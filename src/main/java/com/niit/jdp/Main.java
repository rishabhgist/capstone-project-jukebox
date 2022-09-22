package com.niit.jdp;

import com.niit.jdp.exception.InsertErrorException;
import com.niit.jdp.service.CatalogueService;

import java.sql.SQLException;

public class Main {
    public static void main(String[] args) {
        CatalogueService catalogue;
        try {
            catalogue = new CatalogueService();
            catalogue.printDefault();
        } catch (SQLException | InsertErrorException e) {
            System.out.println(e.getMessage());
        }
    }
}