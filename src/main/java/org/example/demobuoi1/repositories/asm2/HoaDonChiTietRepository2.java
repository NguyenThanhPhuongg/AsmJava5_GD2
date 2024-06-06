package org.example.demobuoi1.repositories.asm2;

import org.example.demobuoi1.entity.HoaDonChiTiet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HoaDonChiTietRepository2 extends JpaRepository<HoaDonChiTiet,Integer> {
}
