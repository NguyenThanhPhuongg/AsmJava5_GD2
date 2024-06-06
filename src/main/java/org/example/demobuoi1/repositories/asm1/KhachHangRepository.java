//package org.example.demobuoi1.repositories.asm1;
//
//import org.example.demobuoi1.entity.KhachHang;
//import org.example.demobuoi1.entity.MauSac;
//import org.example.demobuoi1.entity.SanPham;
//import org.springframework.data.domain.Page;
//import org.springframework.data.domain.PageImpl;
//import org.springframework.data.domain.Pageable;
//import org.springframework.stereotype.Repository;
//
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Objects;
//import java.util.Random;
//
//@Repository
//public class KhachHangRepository {
//    private List<KhachHang> list;
//    public KhachHangRepository() {
//        this.list = new ArrayList<>();
//        list.add(new KhachHang(1, "Nguyễn Văn A","03333333","KH001", 1));
//        list.add(new KhachHang(2, "Nguyễn Văn B","03333333","KH002", 0));
//        list.add(new KhachHang(3, "Nguyễn Văn C","03333333","KH003", 1));
//    }
//    public List<KhachHang> findAll() {
//        return list;
//    }
//    public Page<KhachHang> findAllPage(Pageable pageable) {
//        int pageSize = pageable.getPageSize();
//        int pageNumber = pageable.getPageNumber();
//        int startIndex = pageNumber * pageSize;
//        int endIndex = Math.min(startIndex + pageSize, list.size());
//        List<KhachHang> pageContent = list.subList(startIndex, endIndex);
//        return new PageImpl<>(pageContent, pageable, list.size());
//    }
//    public void add(KhachHang khachHang){
//        khachHang.setId( this.list.size() + 1 );
//        this.list.add(khachHang);
//    }
//    public void deleteById(int id){
//        for (int i = 0; i < this.list.size(); i++) {
//            KhachHang khachHang = this.list.get(i);
//            if (khachHang.getId() == id) {
//                this.list.remove(i);
//                break;
//            }
//        }
//    }
//    public KhachHang findById(int id) {
//        for (int i = 0; i < this.list.size(); i++) {
//            KhachHang khachHang = this.list.get(i);
//            if (khachHang.getId() == id) {
//                return khachHang;
//            }
//        }
//        return null;
//    }
//    public KhachHang findByName(String ten) {
//        for (int i = 0; i < this.list.size(); i++) {
//            KhachHang khachHang = this.list.get(i);
//            if (khachHang.getTen().equals(ten)) {
//                return khachHang;
//            }
//        }
//        return null;
//    }
//    public void update(KhachHang khachHang){
//        for (int i = 0; i < this.list.size(); i++) {
//            KhachHang kh = this.list.get(i);
//            if (Objects.equals(kh.getId(), khachHang.getId())) {
//                this.list.set(i, khachHang);
//                break;
//            }
//        }
//    }
//    public  boolean exitByMa(String ma){
//        return list.stream().anyMatch(sp -> sp.getMaKH().equals(ma));
//    }
//    public List<KhachHang> findByAll(String valueSearch, Integer status) {
//        List<KhachHang> listKhachHang = new ArrayList<>();
//        if (list == null) {
//            return listKhachHang;
//        }
//
//        boolean hasValueSearch = valueSearch != null && !valueSearch.isEmpty();
//        boolean hasStatus = status != null;
//
//        for (KhachHang khachHang : list) {
//            boolean maMatches = hasValueSearch && valueSearch.equals(khachHang.getMaKH());
//            boolean tenMatches = hasValueSearch && valueSearch.equals(khachHang.getTen());
//            boolean sdtMatches = hasValueSearch && valueSearch.equals(khachHang.getSdt());
//            boolean statusMatches = hasStatus && status.equals(khachHang.getTrangThai());
//
//            if ((hasValueSearch && (maMatches || tenMatches || sdtMatches)) || (hasStatus && statusMatches)) {
//                if ((hasValueSearch && !(maMatches || tenMatches || sdtMatches)) || (hasStatus && !statusMatches)) {
//                    continue;
//                }
//                listKhachHang.add(khachHang);
//            }
//        }
//
//        return listKhachHang;
//    }
//}
