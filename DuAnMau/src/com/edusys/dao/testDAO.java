/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.edusys.dao;

import com.edusys.entity.NhanVien;
import java.util.List;

/**
 *
 * @author hi
 */
public class testDAO {
    public static void main(String[] args) {
        NhanVienDAO dao = new NhanVienDAO();
        NhanVien a = dao.selectByID("NoPT");
        System.out.println(a);
    }
}
