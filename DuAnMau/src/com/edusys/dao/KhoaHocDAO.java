/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.edusys.dao;

import com.edusys.entity.KhoaHoc;
import com.edusys.utils.JdbcHelper;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author hi
 */
public class KhoaHocDAO extends EduSysDAO<KhoaHoc, Object>{
    String INSERT_SQL = "insert into KhoaHoc(MaCD, HocPhi, ThoiLuong, NgayKG, GhiChu, MaNV, NgayTao) VALUES (?, ?, ?, ?, ?, ?, ?)";
    String UPDATE_SQL = "update KhoaHoc set  MaCD=?, HocPhi=?, ThoiLuong=?, NgayKG=?, GhiChu=?, MaNV=?, NgayTao=? WHERE MaKH=?";
    String DELETE_SQL = "delete from KhoaHoc where MaKH = ?";
    String SELECT_ALL_SQL = "select * from KhoaHoc";
    String SELECT_BY_ID_SQL = "SELECT * FROM KhoaHoc WHERE MaKH=?";

    @Override
    public void insert(KhoaHoc entity) {
        JdbcHelper.update(INSERT_SQL, entity.getMaCD(), entity.getHocPhi(), entity.getThoiLuong(), entity.getNgayKG(),
                entity.getGhiChu(), entity.getMaNV(), entity.getNgayTao());
    }

    @Override
    public void update(KhoaHoc entity) {
        JdbcHelper.update(UPDATE_SQL,  entity.getMaCD(), entity.getHocPhi(), entity.getThoiLuong(), entity.getNgayKG(),
                entity.getGhiChu(), entity.getMaNV(), entity.getNgayTao(), entity.getMaKH());
    }


    @Override
    public List<KhoaHoc> selectAll() {
        return this.selectBySql(SELECT_ALL_SQL);
    }


    @Override
    protected List<KhoaHoc> selectBySql(String sql, Object... agrs) {
        List<KhoaHoc> list = new ArrayList<KhoaHoc>();
        try {
            ResultSet rs =JdbcHelper.query(sql, agrs);
            while(rs.next()){
                KhoaHoc entity = new KhoaHoc();
                entity.setMaKH(rs.getInt("makh"));
                entity.setMaCD(rs.getString("MaCD"));
                entity.setHocPhi(rs.getFloat("HocPhi"));
                entity.setThoiLuong(rs.getInt("ThoiLuong"));
                entity.setNgayKG(rs.getDate("NgayKG"));
                entity.setGhiChu(rs.getString("GhiChu"));
                entity.setMaNV(rs.getString("MaNV"));
                entity.setNgayTao(rs.getDate("NgayTao"));
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

    @Override
    public KhoaHoc selectByID(Object id) {
        List<KhoaHoc> list = this.selectBySql(SELECT_BY_ID_SQL, id);
        if(list.isEmpty()){
            return null;
        }
        return list.get(0);
    }
    public List<KhoaHoc> selectbyChuyenDe(String macd){
        String sql ="select *from khoahoc where macd=?";
        return this.selectBySql(sql, macd);
    }
    

}
