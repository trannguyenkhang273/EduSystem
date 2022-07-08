/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.edusys.dao;

import com.edusys.entity.NhanVien;
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
public class NhanVienDAO extends EduSysDAO<NhanVien, String> {

    String INSERT_SQL = "INSERT INTO NhanVien (MaNV, MatKhau, HoTen, VaiTro,email,gioitinh) VALUES (?, ?, ?, ?,?,?)";
    String UPDATE_SQL = "UPDATE NhanVien SET MatKhau=?, HoTen=?, VaiTro=? ,email=? WHERE MaNV=?";
    String DELETE_SQL = "DELETE FROM NhanVien WHERE MaNV=?";
    String SELECT_ALL_SQL = "SELECT * FROM NhanVien ";
    String SELECT_BY_ID_SQL = "SELECT * FROM NhanVien where MaNV = ?";

    @Override
    public void insert(NhanVien entity) {
        JdbcHelper.update(INSERT_SQL, entity.getMaNV(), entity.getMatKhau(), entity.getHoTen(), entity.isVaiTro(), entity.getEmail(),entity.getGioitinh());
    }

    @Override
    public void update(NhanVien entity) {
        JdbcHelper.update(UPDATE_SQL, entity.getMatKhau() ,entity.getHoTen() ,entity.isVaiTro(),entity.getEmail() ,entity.getMaNV());
    }

    @Override
    public void delete(String key) {
        JdbcHelper.update(DELETE_SQL, key);
    }

    @Override
    public List<NhanVien> selectAll() {
        return this.selectBySql(SELECT_ALL_SQL);
    }

    @Override
    public NhanVien selectByID(String id) {
        List<NhanVien> list = this.selectBySql(SELECT_BY_ID_SQL, id);
        if(list.isEmpty()){
            return null;
        }
        return list.get(0);
    }

    @Override
    protected List<NhanVien> selectBySql(String sql, Object... agrs) {
        List<NhanVien> list = new ArrayList<NhanVien>();
        try {
            ResultSet rs =JdbcHelper.query(sql, agrs);
            while(rs.next()){
                NhanVien entity = new NhanVien();
                entity.setMaNV(rs.getString("MaNV"));
                entity.setMatKhau(rs.getString("MatKhau"));
                entity.setHoTen(rs.getString("HoTen"));
                entity.setVaiTro(rs.getBoolean("VaiTro"));
                entity.setEmail(rs.getString("email"));
                entity.setGioitinh(rs.getString("gioitinh"));
                list.add(entity);
            }
            rs.getStatement().close();
            return list;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void update1(NhanVien entity) {
        JdbcHelper.update("UPDATE NhanVien SET MatKhau=? WHERE MaNV=?", entity.getMatKhau()  ,entity.getMaNV());
    }
}
