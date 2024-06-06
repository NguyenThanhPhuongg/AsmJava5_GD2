package org.example.demobuoi1.repositories.asm1;

import org.example.demobuoi1.entity.HoaDonChiTiet;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class HoaDonChiTietRepository {
    List<HoaDonChiTiet> list;
    public HoaDonChiTietRepository() {
        list = new ArrayList<>();
//        list.add(new HoaDonChiTiet(1, 1, 1,10,200.0,0));
//        list.add(new HoaDonChiTiet(2, 2, 2,20,240.0,1));
    }
    public List<HoaDonChiTiet> findAll() {
        return list;
    }
    
}
