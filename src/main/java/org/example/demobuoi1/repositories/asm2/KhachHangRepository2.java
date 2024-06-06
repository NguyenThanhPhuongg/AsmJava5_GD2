package org.example.demobuoi1.repositories.asm2;

import org.example.demobuoi1.entity.KhachHang;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface KhachHangRepository2 extends JpaRepository<KhachHang, Integer> {
    List<KhachHang> findAllByOrderByIdDesc();

    @Query("SELECT k FROM KhachHang k WHERE"
            + "(LOWER(k.ten) LIKE LOWER(CONCAT('%', :keyword, '%')) OR "
            + "LOWER(k.sdt) LIKE LOWER(CONCAT('%', :keyword, '%')) OR "
            + "LOWER(k.maKH) LIKE LOWER(CONCAT('%', :keyword, '%'))) "
            + "AND (k.trangThai = :status OR :status IS NULL)")
    Page<KhachHang> search(@Param("keyword") String keyword,
                           @Param("status") Integer status,
                           Pageable pageable);
}
