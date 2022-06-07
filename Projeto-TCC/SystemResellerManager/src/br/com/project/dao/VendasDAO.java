/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.project.dao;

import br.com.project.jdbc.ConnectionFactory;
import br.com.project.model.Vendas;
import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author anderson
 */
public class VendasDAO {

    private Connection con;

    public VendasDAO() {
        this.con = new ConnectionFactory().getConnection();
    }

    //cadastrar venda //arrumar aqui erro de sql
    public void cadastrarVenda(Vendas obj) {
        try {
            String sql = "insert into tb_vendas(cliente_id, data_venda, total_venda, observacoes)"
                    + "values(?,?,?,?)";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setInt(1, obj.getCliente().getId());
            stmt.setString(2, obj.getData_venda());
            stmt.setDouble(3, obj.getTotal_venda());
            stmt.setString(4, obj.getObs());

            stmt.execute();
            stmt.close();

            JOptionPane.showMessageDialog(null, "Venda Registrada com Sucesso");

        } catch (Exception erro) {
            JOptionPane.showMessageDialog(null, "Erro ao Registrar a Venda " + erro);

        }
    }

    //retorna venda
    public int retornaUltimaVenda() {
        try {
            int idvenda = 0;
            String query = "select max(id) id from vendas";
            PreparedStatement ps = con.prepareStatement(query);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                Vendas p = new Vendas();

                p.setId(rs.getInt("id"));

                idvenda = p.getId();
            }

            return idvenda;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
