/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.project.dao;

import br.com.project.jdbc.ConnectionFactory;
import br.com.project.model.ItensVendas;
import java.sql.Connection;
import java.sql.PreparedStatement;
import javax.swing.JOptionPane;

/**
 *
 * @author anderson
 */
public class ItemVendaDAO {
    
    private Connection con;
    
    public ItemVendaDAO() {
        this.con = new ConnectionFactory().getConnection();
    }
    
    //cadastrar item
    public void cadastrarItem(ItensVendas obj) {
        try {
            String sql = "insert into tb_itensvendas(venda_id,veiculo_id,qtd,subtotal) values(?,?,?,?)";
            
            PreparedStatement stmt = con.prepareStatement(sql);
            
            stmt.setInt(1, obj.getVenda().getId());
            stmt.setInt(2, obj.getVeiculos().getId());
            stmt.setInt(3, obj.getQtd());
            stmt.setDouble(4, obj.getSubtotal());

            stmt.execute();
            stmt.close();


        } catch (Exception erro) {
            JOptionPane.showMessageDialog(null, "Erro ao Registrar o Item " + erro);

        }
        
        
        
        
    }
    
    
    
}
