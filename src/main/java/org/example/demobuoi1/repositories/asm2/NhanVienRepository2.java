package org.example.demobuoi1.repositories.asm2;

import org.example.demobuoi1.entity.KhachHang;
import org.example.demobuoi1.entity.NhanVien;
import org.example.demobuoi1.entity.SanPham;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface NhanVienRepository2 extends JpaRepository<NhanVien,Integer> {
    List<NhanVien> findAllByOrderByIdDesc();
    NhanVien findAllByTenDangNhap(String tenDangNhap);
    NhanVien findByTen(String ten);
    @Query("SELECT nv FROM NhanVien nv WHERE"
            + "(LOWER(nv.ten) LIKE LOWER(CONCAT('%', :keyword, '%')) OR "
            + "LOWER(nv.maNV) LIKE LOWER(CONCAT('%', :keyword, '%')) OR "
            + "LOWER(nv.tenDangNhap) LIKE LOWER(CONCAT('%', :keyword, '%'))) "
            + "AND (nv.trangThai = :status OR :status IS NULL)")
    Page<NhanVien> search(@Param("keyword") String keyword,
                           @Param("status") Integer status,
                           Pageable pageable);
}
