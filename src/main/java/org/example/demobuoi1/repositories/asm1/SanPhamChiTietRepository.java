package org.example.demobuoi1.repositories.asm1;

import org.example.demobuoi1.entity.SanPham;
import org.example.demobuoi1.entity.SanPhamChiTiet;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class SanPhamChiTietRepository {
//    private List<SanPhamChiTiet> list;
//    public SanPhamChiTietRepository() {
//        list = new ArrayList<>();
//        list.add(new SanPhamChiTiet(1, "SPCT01", 1, 1, 1,10,200.000,0));
//        list.add(new SanPhamChiTiet(2, "SPCT02", 2, 2, 2,20,2400.000,1));
//        list.add(new SanPhamChiTiet(3, "SPCT03", 3, 3, 3,20,2400.000,0));
//        list.add(new SanPhamChiTiet(4, "SPCT04", 4, 4, 4,20,2400.000,1));
//    }
//    public List<SanPhamChiTiet> findAll() {
//        return list;
//    }
//    public Page<SanPhamChiTiet> findAllPage(Pageable pageable){
//        int pageSize = pageable.getPageSize();
//        int pageNumber = pageable.getPageNumber();
//        int startIndex = pageNumber * pageSize;
//        int endIndex = Math.min(startIndex + pageSize, list.size());
//        List<SanPhamChiTiet> pageContent = list.subList(startIndex, endIndex);
//        return new PageImpl<>(pageContent, pageable, list.size());
//    }
//    public List<SanPhamChiTiet> findByIdSP(int id) {
//        List<SanPhamChiTiet> kq = new ArrayList<>();
//        for(SanPhamChiTiet s : list) {
//            if(s.getIdSP() == id) {
//                kq.add(s);
//            }
//        }
//        return kq;
//    }
//    public void add(SanPhamChiTiet sanPhamChiTiet){
//        sanPhamChiTiet.setId(list.size()+1);
//        list.add(sanPhamChiTiet);
//    }
//    public void deleteById(int id){
//        for (int i = 0; i < list.size(); i++) {
//            SanPhamChiTiet sanPhamChiTiet = list.get(i);
//            if (sanPhamChiTiet.getId() == id) {
//                list.remove(i);
//                break;
//            }
//        }
//    }
//    public SanPhamChiTiet findById(int id) {
//        for (int i = 0; i < list.size(); i++) {
//            SanPhamChiTiet sanPhamChiTiet = list.get(i);
//            if (sanPhamChiTiet.getId() == id) {
//                return sanPhamChiTiet;
//            }
//        }
//        return null;
//    }
//    public void update(SanPhamChiTiet sanPhamChiTiet) {
//        for (int i = 0; i < list.size(); i++) {
//            SanPhamChiTiet spct = list.get(i);
//            if (spct.getId() == sanPhamChiTiet.getId()) {
//                list.set(i, sanPhamChiTiet);
//                break;
//            }
//        }
//    }
//    public void updateSoLuong(int soLuong, int idSPCT){
//        for (int i = 0; i < list.size(); i++) {
//            SanPhamChiTiet spct = list.get(i);
//            if (spct.getId() == idSPCT) {
//                spct.setSoLuong(spct.getSoLuong() - soLuong);
//                break;
//            }
//        }
//    }
//    public  boolean exitByMa(String ma){
//        return list.stream().anyMatch(sp -> sp.getMaSPCT().equals(ma));
//    }
}
