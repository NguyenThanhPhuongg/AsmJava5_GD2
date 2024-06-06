package org.example.demobuoi1.repositories.asm2;

import org.example.demobuoi1.entity.KhachHang;
import org.example.demobuoi1.entity.SanPham;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface SanPhamRepository2 extends JpaRepository<SanPham, Integer> {
    List<SanPham> findAllByOrderByIdDesc();
    List<SanPham> findAllByTrangThai(int trangThai);
    @Query("SELECT sp FROM SanPham sp WHERE"
            + "(LOWER(sp.ten) LIKE LOWER(CONCAT('%', :keyword, '%')) OR "
            + "LOWER(sp.ma) LIKE LOWER(CONCAT('%', :keyword, '%'))) "
            + "AND (sp.trangThai = :status OR :status IS NULL)")
    Page<SanPham> search(@Param("keyword") String keyword,
                           @Param("status") Integer status,
                           Pageable pageable);
}
