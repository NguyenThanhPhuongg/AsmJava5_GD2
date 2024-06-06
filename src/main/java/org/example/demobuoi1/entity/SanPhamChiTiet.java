package org.example.demobuoi1.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "SanPhamChiTiet")
public class SanPhamChiTiet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Integer id;

    @Column(name = "MaSPCT")
    @NotBlank(message = "MaSPCT không được để trống")
    @Size(max = 10, message = "MaSPCT không được vượt quá 10 ký tự")
    private String maSPCT;

    @Column(name = "SoLuong")
    @NotNull(message = "So luong không được để trống")
    @Min(value = 1, message = "So luong phải lớn hơn 0")
    private int soLuong;

    @Column(name = "DonGia")
    @NotNull(message = "Don gia không được để trống")
    @Positive(message = "Don gia phải là số dương")
    private double donGia;

    @Column(name = "TrangThai")
    private int trangThai;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "IdSanPham", referencedColumnName = "ID")
    private SanPham sanPham;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "IdMauSac", referencedColumnName = "ID")
    private MauSac mauSac;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "IdKichThuoc", referencedColumnName = "ID")
    private KichThuoc kichThuoc;

    @OneToMany(mappedBy = "sanPhamChiTiet" ,fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    List<HoaDonChiTiet> listHDCT;


}
