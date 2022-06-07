/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.project.dao;

import br.com.project.jdbc.ConnectionFactory;
import br.com.project.model.Fornecedores;
import br.com.project.model.Veiculos;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Connection;
import java.util.ArrayList;
import java.sql.SQLException;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author Anderson
 */
public class VeiculosDAO {

    private Connection con;

    public VeiculosDAO() {
        this.con = new ConnectionFactory().getConnection();
    }

    //cadastar veiculos
    public void cadastrar(Veiculos obj) {
        try {

            String sql = "insert into tb_veiculos(placa, marca, modelo, cor, combustivel,"
                    + " renavan, chassi, ano_fabricacao, ano_modelo, km, valor, observacoes, for_id) "
                    + "values(?,?,?,?,?,?,?,?,?,?,?,?,?)";
            PreparedStatement stmt = con.prepareStatement(sql);

            stmt.setString(1, obj.getPlaca());
            stmt.setString(2, obj.getMarca());
            stmt.setString(3, obj.getModelo());
            stmt.setString(4, obj.getCor());
            stmt.setString(5, obj.getCombustivel());
            stmt.setLong(6, obj.getRenavan());
            stmt.setString(7, obj.getChassi());
            stmt.setInt(8, obj.getAno_fabricacao());
            stmt.setInt(9, obj.getAno_modelo());
            stmt.setInt(10, obj.getKm());
            stmt.setDouble(11, obj.getValor());
            stmt.setString(12, obj.getObservacoes());

            stmt.setInt(13, obj.getFornecedor().getId());

            stmt.execute();
            stmt.close();

            JOptionPane.showMessageDialog(null, "Cadastrado com Sucesso");

        } catch (Exception erro) {
            JOptionPane.showMessageDialog(null, "Erro" + erro);
        }
    }

    //lista veículos
    public List<Veiculos> listarVeiculos() {
        try {
            List<Veiculos> lista = new ArrayList<>();

            String sql = "select v.id, v.placa, v.marca, v.modelo, v.cor, v.combustivel,v.renavan, v.chassi, "
                    + "v.ano_fabricacao, v.ano_modelo, v.km, v.valor, v.observacoes, f.nome from tb_veiculos as v "
                    + "inner join tb_fornecedores as f on(v.for_id = f.id)";

            PreparedStatement stmt = con.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Veiculos obj = new Veiculos();
                Fornecedores f = new Fornecedores();

                obj.setId(rs.getInt("v.id"));
                obj.setPlaca(rs.getString("v.placa"));
                obj.setMarca(rs.getString("v.marca"));
                obj.setModelo(rs.getString("v.modelo"));
                obj.setCor(rs.getString("v.cor"));
                obj.setCombustivel(rs.getString("v.combustivel"));
                obj.setRenavan(rs.getLong("v.renavan"));
                obj.setChassi(rs.getString("v.chassi"));
                obj.setAno_fabricacao(rs.getInt("v.ano_fabricacao"));
                obj.setAno_modelo(rs.getInt("v.ano_modelo"));
                obj.setKm(rs.getInt("v.km"));
                obj.setValor(rs.getDouble("v.valor"));
                obj.setObservacoes(rs.getString("v.observacoes"));

                f.setNome(rs.getString(("f.nome")));

                obj.setFornecedor(f);

                lista.add(obj);

            }

            return lista;

        } catch (SQLException erro) {
            JOptionPane.showMessageDialog(null, "Erro" + erro);
            return null;
        }

    }

    //alterar produtos ou veiculos
    public void alterar(Veiculos obj) {
        try {
            //alterar de acordo com tb_veiculos
            String sql = "update tb_veiculos set placa=?, marca=?, modelo=?, cor=?, combustivel=?,"
                    + "renavan=?, chassi=?, ano_fabricacao=?, ano_modelo=?  valor=?, observacoes=?, for_id=? where id=?";

            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, obj.getPlaca());
            stmt.setString(2, obj.getMarca());
            stmt.setString(3, obj.getModelo());
            stmt.setString(4, obj.getCor());
            stmt.setString(5, obj.getCombustivel());
            stmt.setLong(6, obj.getRenavan());
            stmt.setString(7, obj.getChassi());
            stmt.setInt(8, obj.getAno_fabricacao());
            stmt.setInt(9, obj.getAno_modelo());
            stmt.setInt(10, obj.getKm());
            stmt.setDouble(11, obj.getValor());
            stmt.setString(12, obj.getObservacoes());

            stmt.setInt(13, obj.getFornecedor().getId());

            stmt.setInt(14, obj.getId());

            stmt.execute();
            stmt.close();

            JOptionPane.showMessageDialog(null, "Alterado com Sucesso");

        } catch (Exception erro) {
            JOptionPane.showMessageDialog(null, "Erro" + erro);
        }
    }

    //excluir produtos ou veiculos
    public void excluir(Veiculos obj) {
        try {

            String sql = "delete from tb_veiculos where id=?";

            PreparedStatement stmt = con.prepareStatement(sql);

            stmt.setInt(1, obj.getId());

            stmt.execute();
            stmt.close();

            JOptionPane.showMessageDialog(null, "Excluido com Sucesso");

        } catch (Exception erro) {

            JOptionPane.showMessageDialog(null, "Erro" + erro);
        }
    }

    //listar veiculo por modelo
    public List<Veiculos> listarVeiculoPorModelo(String modelo) {
        try {
            List<Veiculos> lista = new ArrayList<>();

            String sql = "select v.id, v.placa, v.marca, v.modelo, v.cor, v.combustivel, "
                    + "v.renavan, v.chassi, v.ano_fabricacao, v.ano_modelo, "
                    + "v.km, v.valor, v.observacoes, f.nome from tb_veiculos as v " //espaço apo p
                    + "inner join tb_fornecedores as f on(v.for_id = f.id) where v.modelo like?";

            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, modelo);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Veiculos obj = new Veiculos();
                Fornecedores f = new Fornecedores();

                obj.setId(rs.getInt("v.id"));
                obj.setPlaca(rs.getString("v.placa"));
                obj.setMarca(rs.getString("v.marca"));
                obj.setModelo(rs.getString("v.modelo"));
                obj.setCor(rs.getString("v.cor"));
                obj.setCombustivel(rs.getString("v.combustivel"));
                obj.setRenavan(rs.getLong("v.renavan"));
                obj.setChassi(rs.getString("v.chassi"));
                obj.setAno_fabricacao(rs.getInt("v.ano_fabricacao"));
                obj.setAno_modelo(rs.getInt("v.ano_modelo"));
                obj.setKm(rs.getInt("v.km"));
                obj.setValor(rs.getDouble("v.valor"));
                obj.setObservacoes(rs.getString("v.observacoes"));

                f.setNome(rs.getString(("f.nome")));

                obj.setFornecedor(f);

                lista.add(obj);

            }

            return lista;

        } catch (SQLException erro) {
            JOptionPane.showMessageDialog(null, "Erro" + erro);
            return null;
        }

    }

    //parei aqui 27/05/2022
    public Veiculos consultarPorModelo(String modelo) { //alterar para lista veiculo por nome/modelo
        try {

            String sql = "select v.id, v.placa, v.marca, v.modelo, v.cor,v.combustivel, v.renavan, v.chassi,"
                    + "v.ano_fabricacao, v.ano_modelo, v.km, v.valor, v.observacoes, f.nome from tb_veiculos as v " //espaço apo p
                    + "inner join tb_fornecedores as f on(v.for_id = f.id) where v.modelo = ?"; //produto especifico =

            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, modelo);

            ResultSet rs = stmt.executeQuery();
            Veiculos obj = new Veiculos();
            Fornecedores f = new Fornecedores();

            if (rs.next()) {

                obj.setId(rs.getInt("v.id"));
                obj.setPlaca(rs.getString("v.placa"));
                obj.setMarca(rs.getString("v.marca"));
                obj.setModelo(rs.getString("v.modelo"));
                obj.setCor(rs.getString("v.cor"));
                obj.setCombustivel(rs.getString("v.combustivel"));
                obj.setRenavan(rs.getLong("v.renavan"));
                obj.setChassi(rs.getString("v.chassi"));
                obj.setAno_fabricacao(rs.getInt("v.ano_fabricacao"));
                obj.setAno_modelo(rs.getInt("v.ano_modelo"));
                obj.setKm(rs.getInt("v.km"));
                obj.setValor(rs.getDouble("v.valor"));
                obj.setObservacoes(rs.getString("v.observacoes"));

                f.setNome(rs.getString(("f.nome")));

                obj.setFornecedor(f);
            }

            return obj;

        } catch (Exception erro) {
            JOptionPane.showMessageDialog(null, "Veiculo não encontrado!");
            return null;
        }

    }
    
    public Veiculos consultarPorPlaca(String placa) { //alterar para lista veiculo por nome/modelo
        try {

            String sql = "select v.id, v.placa, v.marca, v.modelo, v.cor,v.combustivel, v.renavan, v.chassi,"
                    + "v.ano_fabricacao, v.ano_modelo, v.km, v.valor, v.observacoes, f.nome from tb_veiculos as v " //espaço apo p
                    + "inner join tb_fornecedores as f on(v.for_id = f.id) where v.placa = ?"; //produto especifico =

            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, placa);

            ResultSet rs = stmt.executeQuery();
            Veiculos obj = new Veiculos();
            Fornecedores f = new Fornecedores();

            if (rs.next()) {

                obj.setId(rs.getInt("v.id"));
                obj.setPlaca(rs.getString("v.placa"));
                obj.setMarca(rs.getString("v.marca"));
                obj.setModelo(rs.getString("v.modelo"));
                obj.setCor(rs.getString("v.cor"));
                obj.setCombustivel(rs.getString("v.combustivel"));
                obj.setRenavan(rs.getLong("v.renavan"));
                obj.setChassi(rs.getString("v.chassi"));
                obj.setAno_fabricacao(rs.getInt("v.ano_fabricacao"));
                obj.setAno_modelo(rs.getInt("v.ano_modelo"));
                obj.setKm(rs.getInt("v.km"));
                obj.setValor(rs.getDouble("v.valor"));
                obj.setObservacoes(rs.getString("v.observacoes"));

                f.setNome(rs.getString(("f.nome")));

                obj.setFornecedor(f);
            }

            return obj;

        } catch (Exception erro) {
            JOptionPane.showMessageDialog(null, "Veiculo não encontrado!");
            return null;
        }

    }
    
    
    
    

}
