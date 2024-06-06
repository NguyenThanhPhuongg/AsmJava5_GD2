package org.example.demobuoi1.repositories.asm1;

import org.example.demobuoi1.entity.MauSac;
import org.example.demobuoi1.entity.SanPham;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Random;

@Repository
public class SanPhamRepository {
//    private List<SanPham> list;
//    public SanPhamRepository() {
//        this.list = new ArrayList<>();
//        list.add(new SanPham(1,"SP001", "Chân váy tenis", 1));
//        list.add(new SanPham(2,"SP002", "Váy baby doll", 0));
//        list.add(new SanPham(3,"SP003", "Váy hai dây", 1));
//        list.add(new SanPham(4,"SP004", "Quần jeans", 0));
//
//    }
//    public List<SanPham> findAll() {
//        return list;
//    }
//    public Page<SanPham> findAllPage(Pageable pageable){
//        int pageSize = pageable.getPageSize();
//        int pageNumber = pageable.getPageNumber();
//        int startIndex = pageNumber * pageSize;
//        int endIndex = Math.min(startIndex + pageSize, list.size());
//        List<SanPham> pageContent = list.subList(startIndex, endIndex);
//        return new PageImpl<>(pageContent, pageable, list.size());
//    }
//    public void add(SanPham sanPham){
//        sanPham.setId( this.list.size() + 1 );
//        this.list.add(sanPham);
//    }
//    public void deleteById(int id){
//        for (int i = 0; i < this.list.size(); i++) {
//            SanPham sanPham = this.list.get(i);
//            if (sanPham.getId() == id) {
//                this.list.remove(i);
//                break;
//            }
//        }
//    }
//
//    public SanPham findById(int id) {
//        for (int i = 0; i < this.list.size(); i++) {
//            SanPham sanPham = this.list.get(i);
//            if (sanPham.getId() == id) {
//                return sanPham;
//            }
//        }
//        return null;
//    }
//    public void update(SanPham sanPham) {
//        for (int i = 0; i < this.list.size(); i++) {
//            SanPham sp = this.list.get(i);
//            if (Objects.equals(sp.getId(), sanPham.getId())) {
//                this.list.set(i, sanPham);
//                break;
//            }
//        }
//    }
//    public  boolean exitByMa(String ma){
//        return list.stream().anyMatch(sp -> sp.getMa().equals(ma));
//    }
//    public List<SanPham> findByMaVaStatus(String valueSearch, Integer status) {
//        List<SanPham> listSanPham = new ArrayList<>();
//        if (list == null) {
//            return listSanPham;
//        }
//
//        boolean hasValueSearch = valueSearch != null && !valueSearch.isEmpty();
//        boolean hasStatus = status != null;
//
//        for (SanPham sanPham : list) {
//            boolean maMatches = hasValueSearch && valueSearch.equals(sanPham.getMa());
//            boolean tenMatches = hasValueSearch && valueSearch.equals(sanPham.getTen());
//            boolean statusMatches = hasStatus && status.equals(sanPham.getTrangThai());
//
//            if ((hasValueSearch && (maMatches || tenMatches)) || (hasStatus && statusMatches)) {
//                if ((hasValueSearch && !(maMatches || tenMatches)) || (hasStatus && !statusMatches)) {
//                    continue;
//                }
//                listSanPham.add(sanPham);
//            }
//        }
//
//        return listSanPham;
//    }
}
