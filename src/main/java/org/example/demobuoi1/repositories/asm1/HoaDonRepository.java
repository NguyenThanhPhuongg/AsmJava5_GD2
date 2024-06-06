//package org.example.demobuoi1.repositories.asm1;
//
//import org.example.demobuoi1.entity.HoaDon;
//import org.springframework.stereotype.Repository;
//
//import java.util.ArrayList;
//import java.util.Date;
//import java.util.List;
//
//@Repository
//public class HoaDonRepository {
//    private List<HoaDon> list;
//    public HoaDonRepository(){
//        list = new ArrayList<>();
//        list.add(new HoaDon(1, 1, 1, new Date(), 1));
//        list.add(new HoaDon(2, 2, 2, new Date(), 0));
//        list.add(new HoaDon(3, 1, 1, new Date(), 1));
//        list.add(new HoaDon(4, 2, 2, new Date(), 1));
//    }
//    public List<HoaDon> findAll() {
//        return list;
//    }
//    public HoaDon findById(int id) {
//        for(HoaDon h : list) {
//            if(h.getId() == id) {
//                return h;
//            }
//        }
//        return null;
//    }
//
//    public void update(HoaDon hoaDon){
//        for(HoaDon h : list) {
//            if(h.getId() == hoaDon.getId()) {
//                list.set(list.indexOf(h), hoaDon);
//                break;
//            }
//        }
//    }
//    public void save(HoaDon hoaDon){
//        list.add(hoaDon);
//    }
//}
