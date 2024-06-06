//package org.example.demobuoi1.repositories.asm1;
//
//import org.example.demobuoi1.entity.KichThuoc;
//import org.example.demobuoi1.entity.MauSac;
//import org.springframework.data.domain.Page;
//import org.springframework.data.domain.PageImpl;
//import org.springframework.data.domain.Pageable;
//import org.springframework.stereotype.Repository;
//
//import java.util.ArrayList;
//import java.util.List;
//
//@Repository
//public class KichThuocRepository {
//    List<KichThuoc> list;
//    public KichThuocRepository() {
//        list = new ArrayList<>();
//        list.add(new KichThuoc(1, "KT001","M",0));
//        list.add(new KichThuoc(2, "KT002","S",1));
//        list.add(new KichThuoc(3, "KT003","XS",1));
//        list.add(new KichThuoc(4, "KT004","S",1));
//    }
//    public List<KichThuoc> findAll() {
//        return list;
//    }
//    public Page<KichThuoc> findAllPage(Pageable pageable){
//        int pageSize = pageable.getPageSize();
//        int pageNumber = pageable.getPageNumber();
//        int startIndex = pageNumber * pageSize;
//        int endIndex = Math.min(startIndex + pageSize, list.size());
//        List<KichThuoc> pageContent = list.subList(startIndex, endIndex);
//        return new PageImpl<>(pageContent, pageable, list.size());
//    }
//    public void add(KichThuoc kichThuoc){
//        kichThuoc.setId(this.list.size()+1);
//        this.list.add(kichThuoc);
//    }
//    public void deleteById(int id){
//        for (int i = 0; i < this.list.size(); i++) {
//            KichThuoc kichThuoc = this.list.get(i);
//            if (kichThuoc.getId() == id) {
//                this.list.remove(i);
//                break;
//            }
//        }
//    }
//    public KichThuoc findById(int id) {
//        for (int i = 0; i < this.list.size(); i++) {
//            KichThuoc kichThuoc = this.list.get(i);
//            if (kichThuoc.getId() == id) {
//                return kichThuoc;
//            }
//        }
//        return null;
//    }
//    public void update(KichThuoc kichThuoc) {
//        for (int i = 0; i < this.list.size(); i++) {
//            KichThuoc kt = this.list.get(i);
//            if (kt.getId() == kichThuoc.getId()) {
//                this.list.set(i, kichThuoc);
//                break;
//            }
//        }
//    }
//    public  boolean exitByMa(String ma){
//        return list.stream().anyMatch(sp -> sp.getMa().equals(ma));
//    }
//    public List<KichThuoc> findByAll(String valueSearch, Integer status) {
//        List<KichThuoc> listKichThuoc = new ArrayList<>();
//        if (list == null) {
//            return listKichThuoc;
//        }
//
//        boolean hasValueSearch = valueSearch != null && !valueSearch.isEmpty();
//        boolean hasStatus = status != null;
//
//        for (KichThuoc kichThuoc : list) {
//            boolean maMatches = hasValueSearch && valueSearch.equals(kichThuoc.getMa());
//            boolean tenMatches = hasValueSearch && valueSearch.equals(kichThuoc.getTen());
//            boolean statusMatches = hasStatus && status.equals(kichThuoc.getTrangThai());
//
//            if ((hasValueSearch && (maMatches || tenMatches)) || (hasStatus && statusMatches)) {
//                if ((hasValueSearch && !(maMatches || tenMatches)) || (hasStatus && !statusMatches)) {
//                    continue;
//                }
//                listKichThuoc.add(kichThuoc);
//            }
//        }
//
//        return listKichThuoc;
//    }
//}
