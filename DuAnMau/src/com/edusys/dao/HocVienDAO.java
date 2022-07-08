/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.edusys.dao;

import com.edusys.entity.HocVien;
import com.edusys.utils.JdbcHelper;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author hi
 */
public class HocVienDAO extends EduSysDAO<HocVien, String>{
    String INSERT_SQL = "INSERT INTO HocVien (MaKH, MaNH, Diem) VALUES (?, ?, ?)";
    String UPDATE_SQL = "UPDATE HocVien SET MaKH=?, MaNH=?, Diem=? WHERE MaHV=?";
    String DELETE_SQL = "DELETE FROM HocVien WHERE MaHV=?";
    String SELECT_ALL_SQL = "SELECT * FROM HocVien ";
    String SELECT_BY_ID_SQL = "SELECT * FROM HocVien WHERE MaHV=?";

    @Override
    public void insert(HocVien entity) {
        JdbcHelper.update(INSERT_SQL, entity.getMaKH(), entity.getMaNH(), entity.getDiem());
    }

    @Override
    public void update(HocVien entity) {
        JdbcHelper.update(UPDATE_SQL,  entity.getMaKH(), entity.getMaNH(), entity.getDiem(),entity.getMaHV());
    }

    public void delete(int key) {
        JdbcHelper.update(DELETE_SQL, key);
    }

    @Override
    public List<HocVien> selectAll() {
        return this.selectBySql(SELECT_ALL_SQL);
    }

    @Override
    public HocVien selectByID(String id) {
        List<HocVien> list = this.selectBySql(SELECT_BY_ID_SQL, id);
        if(list.isEmpty()){
            return null;
        }
        return list.get(0);
    }

    @Override
    protected List<HocVien> selectBySql(String sql, Object... agrs) {
        List<HocVien> list = new ArrayList<HocVien>();
        try {
            ResultSet rs =JdbcHelper.query(sql, agrs);
            while(rs.next()){
                HocVien entity = new HocVien();
                entity.setMaHV(rs.getInt("MaHV"));
                entity.setMaKH(rs.getInt("MaKH"));
                entity.setMaNH(rs.getString("MaNH"));
                entity.setDiem(rs.getFloat("Diem"));
                list.add(entity);
            }
            rs.getStatement().close();
            return list;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    
    public List<HocVien> selectByKhoaHoc(int makh){
        String sql = "select * from hocvien where makh = ?";
        return this.selectBySql(sql, makh);
    }
    
//    @Override
//    public void delete(int mahv) {
//        
//    }

    @Override
    public void delete(String key) {
    }

    public HocVien selectByID(int mahv) {
        List<HocVien> list = this.selectBySql(SELECT_BY_ID_SQL, mahv);
        if(list.isEmpty()){
            return null;
        }
        return list.get(0);    }

//    @Override
//    public void update(HocVien entity) {
//        
//    }

   
}
