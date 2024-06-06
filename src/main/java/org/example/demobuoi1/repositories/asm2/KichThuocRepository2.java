package org.example.demobuoi1.repositories.asm2;

import org.example.demobuoi1.entity.KichThuoc;
import org.example.demobuoi1.entity.MauSac;
import org.example.demobuoi1.entity.NhanVien;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface KichThuocRepository2 extends JpaRepository<KichThuoc,Integer> {
    List<KichThuoc> findAllByOrderByIdDesc();
    List<KichThuoc> findAllByTrangThai(int trangThai);
    @Query("SELECT kt FROM KichThuoc kt WHERE"
            + "(LOWER(kt.ten) LIKE LOWER(CONCAT('%', :keyword, '%')) OR "
            + "LOWER(kt.ma) LIKE LOWER(CONCAT('%', :keyword, '%'))) "
            + "AND (kt.trangThai = :status OR :status IS NULL)")
    Page<KichThuoc> search(@Param("keyword") String keyword,
                        @Param("status") Integer status,
                        Pageable pageable);
}
