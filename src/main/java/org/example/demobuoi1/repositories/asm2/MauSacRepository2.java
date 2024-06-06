package org.example.demobuoi1.repositories.asm2;

import org.example.demobuoi1.entity.KhachHang;
import org.example.demobuoi1.entity.KichThuoc;
import org.example.demobuoi1.entity.MauSac;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface MauSacRepository2 extends JpaRepository<MauSac,Integer> {
    List<MauSac> findAllByOrderByIdDesc();
    List<MauSac> findAllByTrangThai(int trangThai);
    @Query("SELECT ms FROM MauSac ms WHERE"
            + "(LOWER(ms.ten) LIKE LOWER(CONCAT('%', :keyword, '%')) OR "
            + "LOWER(ms.ma) LIKE LOWER(CONCAT('%', :keyword, '%'))) "
            + "AND (ms.trangThai = :status OR :status IS NULL)")
    Page<MauSac> search(@Param("keyword") String keyword,
                           @Param("status") Integer status,
                           Pageable pageable);
}
