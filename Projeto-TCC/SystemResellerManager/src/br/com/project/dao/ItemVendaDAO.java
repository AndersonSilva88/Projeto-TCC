/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.project.dao;

import br.com.project.jdbc.ConnectionFactory;
import br.com.project.model.Clientes;
import br.com.project.model.ItensVendas;
import br.com.project.model.Veiculos;
import br.com.project.model.Vendas;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
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
    
    //vendas por id
     public List<ItensVendas> listarItensPorVenda(int venda_id) {
        try {
            List<ItensVendas> lista = new ArrayList<>();

            String sql = "select i.id, v.modelo, i.qtd, v.valor,i.subtotal from tb_itensvendas as i"
                    + " inner join tb_veiculos as v on(i.veiculos_id = v.id) where i.venda_id =?";

            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setInt(1, venda_id);

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                ItensVendas item = new ItensVendas();
                Veiculos v = new Veiculos();

                item.setId(rs.getInt("i.id"));
                v.setModelo(rs.getString("v.modelo"));
                item.setQtd(rs.getInt("i.subtotal"));
                v.setValor(rs.getDouble("v.valor"));
                item.setSubtotal(rs.getDouble("i.qtd"));
                
                item.setVeiculos(v);
                
                
                lista.add(item);
                
            }

            return lista;

        } catch (SQLException erro) {
            JOptionPane.showMessageDialog(null, "Erro" + erro);
            return null;
        }

    }

}
