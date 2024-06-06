//package org.example.demobuoi1.repositories.asm1;
//
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
//public class MauSacRepository {
//    List<MauSac> list;
//
//    public MauSacRepository() {
//        list = new ArrayList<MauSac>();
//        list.add(new MauSac(1, "MS01", "Hồng", 1));
//        list.add(new MauSac(2, "MS02", "Xanh", 1));
//        list.add(new MauSac(3, "MS03", "Trắng", 1));
//        list.add(new MauSac(4, "MS04 ", "Tím", 0));
//    }
//
//    public List<MauSac> findAll() {
//        return list;
//    }
//
//    public Page<MauSac> findAllPage(Pageable pageable) {
//        int pageSize = pageable.getPageSize();
//        int pageNumber = pageable.getPageNumber();
//        int startIndex = pageNumber * pageSize;
//        int endIndex = Math.min(startIndex + pageSize, list.size());
//        List<MauSac> pageContent = list.subList(startIndex, endIndex);
//        return new PageImpl<>(pageContent, pageable, list.size());
//    }
//
//    public void add(MauSac mauSac) {
//        mauSac.setId(list.size() + 1);
//        list.add(mauSac);
//    }
//
//
//    public void deleteById(int id) {
//        for (int i = 0; i < list.size(); i++) {
//            MauSac mauSac = list.get(i);
//            if (mauSac.getId() == id) {
//                list.remove(i);
//                break;
//            }
//        }
//    }
//
//    public MauSac findById(int id) {
//        for (int i = 0; i < list.size(); i++) {
//            MauSac mauSac = list.get(i);
//            if (mauSac.getId() == id) {
//                return mauSac;
//            }
//        }
//        return null;
//    }
//
//    public void update(MauSac mauSac) {
//        for (int i = 0; i < list.size(); i++) {
//            MauSac ms = list.get(i);
//            if (ms.getId() == mauSac.getId()) {
//                list.set(i, mauSac);
//                break;
//            }
//        }
//    }
//
//    public  boolean exitByMa(String ma){
//        return list.stream().anyMatch(sp -> sp.getMa().equals(ma));
//    }
//
//    public List<MauSac> findByMaVaStatus(String valueSearch, Integer status) {
//        List<MauSac> listMauSac = new ArrayList<>();
//        if (list == null) {
//            return listMauSac;
//        }
//
//        boolean hasValueSearch = valueSearch != null && !valueSearch.isEmpty();
//        boolean hasStatus = status != null;
//
//        for (MauSac mauSac : list) {
//            boolean maMatches = hasValueSearch && valueSearch.equals(mauSac.getMa());
//            boolean tenMatches = hasValueSearch && valueSearch.equals(mauSac.getTen());
//            boolean statusMatches = hasStatus && status.equals(mauSac.getTrangThai());
//
//            if ((hasValueSearch && (maMatches || tenMatches)) || (hasStatus && statusMatches)) {
//                if ((hasValueSearch && !(maMatches || tenMatches)) || (hasStatus && !statusMatches)) {
//                    continue;
//                }
//                listMauSac.add(mauSac);
//            }
//        }
//
//        return listMauSac;
//    }
//
//
//}
//
//
//
//
