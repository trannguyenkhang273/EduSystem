/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.edusys.dao;

import com.edusys.entity.NguoiHoc;
import com.edusys.utils.JdbcHelper;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author hi
 */
public class NguoiHocDAO extends EduSysDAO<NguoiHoc, String>{
    String INSERT_SQL = "INSERT INTO NguoiHoc (MaNH, HoTen, NgaySinh, GioiTinh, DienThoai, Email, GhiChu, MaNV, NgayDK) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
    String UPDATE_SQL = "UPDATE NguoiHoc SET HoTen=?, NgaySinh=?, GioiTinh=?, DienThoai=?, Email=?, GhiChu=?, MaNV=?, NgayDK=? WHERE MaNH=?";
    String DELETE_SQL = "DELETE FROM NguoiHoc WHERE MaNH=?";
    String SELECT_ALL_SQL = "SELECT * FROM NguoiHoc";
    String SELECT_BY_ID_SQL = "SELECT * FROM NguoiHoc where MaNH = ?";

    @Override
    public void insert(NguoiHoc entity) {
        JdbcHelper.update(INSERT_SQL, entity.getMaNH(), entity.getHoTen(), entity.getNgaySinh(), entity.isGioiTinh(),
                entity.getDienThoai(),entity.getEmail(),entity.getGhiChu(),entity.getMaNV(),entity.getNgayDK());
    }

    @Override
    public void update(NguoiHoc entity) {
        JdbcHelper.update(UPDATE_SQL,  entity.getHoTen(), entity.getNgaySinh(), entity.isGioiTinh(),
                entity.getDienThoai(),entity.getEmail(),entity.getGhiChu(),entity.getMaNV(),entity.getNgayDK(),entity.getMaNH());
    }

    @Override
    public void delete(String key) {
        JdbcHelper.update(DELETE_SQL, key);
    }

    @Override
    public List<NguoiHoc> selectAll() {
        return this.selectBySql(SELECT_ALL_SQL);
    }

    @Override
    public NguoiHoc selectByID(String id) {
        List<NguoiHoc> list = this.selectBySql(SELECT_BY_ID_SQL, id);
        if(list.isEmpty()){
            return null;
        }
        return list.get(0);
    }

    @Override
    protected List<NguoiHoc> selectBySql(String sql, Object... agrs) {
        List<NguoiHoc> list = new ArrayList<NguoiHoc>();
        try {
            ResultSet rs =JdbcHelper.query(sql, agrs);
            while(rs.next()){
                NguoiHoc entity = new NguoiHoc();
                entity.setMaNH(rs.getString("MaNH"));
                entity.setHoTen(rs.getString("HoTen"));
                entity.setNgaySinh(rs.getDate("NgaySinh"));
                entity.setGioiTinh(rs.getBoolean("GioiTinh"));
                entity.setDienThoai(rs.getString("DienThoai"));
                entity.setEmail(rs.getString("Email"));
                entity.setGhiChu(rs.getString("GhiChu"));
                entity.setMaNV(rs.getString("MaNV"));
                entity.setNgayDK(rs.getDate("NgayDK"));
                list.add(entity);
            }
            rs.getStatement().close();
            return list;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    
    public List<NguoiHoc> selectNotInCourse(int makh, String keyword){
        String sql = "select * from nguoihoc "
                + " where hoten like ? and "
                + " maNH not in(select manh from hocvien where makh = ?) ";
        return this.selectBySql(sql, "%"+keyword+"%",makh);
    }
}
