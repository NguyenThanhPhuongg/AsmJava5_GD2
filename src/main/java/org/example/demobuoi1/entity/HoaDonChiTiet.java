package org.example.demobuoi1.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "HoaDonChiTiet")
public class HoaDonChiTiet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Integer id;

    @Column(name = "SoLuong")
    private Integer soLuong;

    @Column(name = "DonGia")
    private BigDecimal donGia;
    //Them  thời gian
    @Column(name = "ThoiGian")
    private Date ngayMuaHang;

    @Column(name = "TrangThai")
    private Integer trangThai;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "IdHoaDon", referencedColumnName = "ID")
    private HoaDon hoaDon;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "IdSPCT", referencedColumnName = "ID")
    private SanPhamChiTiet sanPhamChiTiet;

}
