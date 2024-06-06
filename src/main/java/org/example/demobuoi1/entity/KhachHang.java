package org.example.demobuoi1.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "KhachHang")
public class KhachHang {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Integer id;

    @Column(name = "Ten")
    @NotBlank(message = "ten khong duoc de trong")
    private String ten;

    @Column(name = "SDT")
    @NotBlank(message = "sdt khong duoc de trong")
//    @Pattern(regexp = "0[0-9]{7}", message = "sdt phai bat dau bang so 0 va gom 7 chu so")
    private String sdt;

    @Column(name = "Ma")
    @NotBlank(message = "ma khong duoc de trong")
    private String maKH;

    @Column(name = "TrangThai")
    private int trangThai;

    @OneToMany(mappedBy = "khachHang", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<HoaDon> listHD;
}
