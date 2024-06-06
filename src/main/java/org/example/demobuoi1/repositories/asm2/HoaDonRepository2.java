package org.example.demobuoi1.repositories.asm2;

import org.example.demobuoi1.entity.HoaDon;
import org.example.demobuoi1.entity.KhachHang;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HoaDonRepository2 extends JpaRepository<HoaDon,Integer> {

    List<HoaDon> findAllByTrangThai(int trangThai);
    @Query("SELECT hd FROM HoaDon hd WHERE"
            + "(LOWER(hd.khachHang.ten) LIKE LOWER(CONCAT('%', :keyword, '%')) OR "
            + "LOWER(hd.nhanVien.ten) LIKE LOWER(CONCAT('%', :keyword, '%'))) "
            + "AND (hd.trangThai = :status OR :status IS NULL)")
    Page<HoaDon> search(@Param("keyword") String keyword,
                           @Param("status") Integer status,
                           Pageable pageable);
}
