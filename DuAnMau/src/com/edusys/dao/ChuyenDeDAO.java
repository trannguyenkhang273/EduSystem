/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.edusys.dao;

import com.edusys.entity.ChuyenDe;
import com.edusys.entity.KhoaHoc;
import com.edusys.utils.JdbcHelper;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author hi
 */
public class ChuyenDeDAO extends EduSysDAO<ChuyenDe, Object>{
    String INSERT_SQL = "INSERT INTO ChuyenDe(MaCD, TenCD, HocPhi, ThoiLuong, Hinh, MoTa) VALUES (?,?,?,?,?,?)";
    String UPDATE_SQL = "UPDATE ChuyenDe SET TenCD = ?, HocPhi = ?, ThoiLuong = ?, Hinh = ?, MoTa = ? where MaCD = ?";
    String DELETE_SQL = "delete from ChuyenDe where MaCD = ?";
    String SELECT_ALL_SQL = "SELECT * FROM ChuyenDe";
    String SELECT_BY_ID_SQL = "SELECT * FROM ChuyenDe where MaCD = ?";

    @Override
    public void insert(ChuyenDe entity) {
        JdbcHelper.update(INSERT_SQL, entity.getMaCD(), entity.getTenCD(), entity.getHocPhi(), entity.getThoiLuong(),
                entity.getHinh(), entity.getMoTa());
       
    }

    @Override
    public void update(ChuyenDe entity) {
        JdbcHelper.update(UPDATE_SQL,  entity.getTenCD(), entity.getHocPhi(), entity.getThoiLuong(), entity.getHinh(), entity.getMoTa(), entity.getMaCD());
    }// ko sua gi het


    @Override
    public List<ChuyenDe> selectAll() {
        System.out.println("1");
        return this.selectBySql(SELECT_ALL_SQL);
    }


    @Override
    protected List<ChuyenDe> selectBySql(String sql, Object... agrs) {
        List<ChuyenDe> list = new ArrayList<ChuyenDe>();
        try {
            System.out.println("5");
            ResultSet rs =JdbcHelper.query(sql, agrs);
            while(rs.next()){
                ChuyenDe entity = new ChuyenDe();
                System.out.println("2");
                entity.setMaCD(rs.getString("MaCD"));
                entity.setTenCD(rs.getString("TenCD"));
                entity.setHocPhi(rs.getFloat("HocPhi"));
                entity.setThoiLuong(rs.getInt("ThoiLuong"));
                entity.setHinh(rs.getString("Hinh"));
                entity.setMoTa(rs.getString("MoTa"));
                System.out.println("3");
                list.add(entity);
            }
            rs.getStatement().close();
            return list;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void delete(Object key) {
        JdbcHelper.update(DELETE_SQL, key);
 }
    public List<ChuyenDe> selectByKeyword(String keyword) {
        String sql="SELECT * FROM ChuyenDe WHERE tencd LIKE ?";
        return selectBySql(sql, "%"+keyword+"%");
    }
    @Override
    public ChuyenDe selectByID(Object id) {
        List<ChuyenDe> list = this.selectBySql(SELECT_BY_ID_SQL, id);
        if(list.isEmpty()){
            return null;
        }
        return list.get(0);
    }
}
