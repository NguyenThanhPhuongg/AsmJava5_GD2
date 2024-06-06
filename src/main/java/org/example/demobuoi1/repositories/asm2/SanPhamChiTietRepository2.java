package org.example.demobuoi1.repositories.asm2;

import org.example.demobuoi1.entity.KhachHang;
import org.example.demobuoi1.entity.SanPhamChiTiet;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface SanPhamChiTietRepository2 extends JpaRepository<SanPhamChiTiet,Integer> {
    @Query("SELECT spct FROM SanPhamChiTiet spct WHERE"
            + "(LOWER(spct.maSPCT) LIKE LOWER(CONCAT('%', :keyword, '%')) OR "
            + "LOWER(spct.mauSac.ten) LIKE LOWER(CONCAT('%', :keyword, '%')) OR "
            + "LOWER(spct.kichThuoc.ten) LIKE LOWER(CONCAT('%', :keyword, '%')) OR "
            + "LOWER(spct.sanPham.ten) LIKE LOWER(CONCAT('%', :keyword, '%'))) "
            + "AND (spct.trangThai = :status OR :status IS NULL)")
    Page<SanPhamChiTiet> search(@Param("keyword") String keyword,
                           @Param("status") Integer status,
                           Pageable pageable);
}
