package org.example.demobuoi1.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "SanPham")
public class SanPham {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Integer id;

    @Column(name = "Ma")
    @NotBlank(message = "Ma khong duoc de trong")
    private String ma;

    @Column(name = "ten")
    @NotBlank(message = "ten khong duoc de trong")
    private String ten;

    @Column(name = "trangThai")
    @NotNull(message = "trang thai khong duoc de trong")
    @Digits(integer = 1, fraction = 0)
    private int trangThai;

    @OneToMany(mappedBy = "sanPham", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<SanPhamChiTiet> ListSPCT;
}
