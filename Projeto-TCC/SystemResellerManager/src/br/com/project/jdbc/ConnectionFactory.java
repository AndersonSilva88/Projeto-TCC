/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.project.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 *
 * @author Anderson
 */
public class ConnectionFactory {
    
    public Connection getConnection() {
        try {
            return DriverManager.getConnection("jdbc:mysql://127.0.0.1/bdautos","usuarioauto","user1234");
        } catch (Exception erro) {
            throw new RuntimeException(erro);
        }
    }
}
