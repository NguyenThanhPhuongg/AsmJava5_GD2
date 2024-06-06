package org.example.demobuoi1.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
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
@Table(name = "MauSac")

public class MauSac {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Integer id;
    @NotBlank(message = "ma khong duoc de trong")
    @Column(name = "Ma")
    private String ma;
    @NotBlank(message = "ten khong duoc de trong")
    @Column(name = "Ten")
    private String ten;
    @Column(name = "TrangThai")
    private int trangThai;

    @OneToMany(mappedBy = "mauSac" ,fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    List<SanPhamChiTiet> listSPCT;

}
