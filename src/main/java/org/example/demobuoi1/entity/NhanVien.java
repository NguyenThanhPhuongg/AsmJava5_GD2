package org.example.demobuoi1.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
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
@Table(name = "NhanVien")
public class NhanVien {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @NotBlank(message = "Tên không được để trống")
    @Column(name = "ten")
    private String ten;

    @NotBlank(message = "Mã nhân viên không được để trống")
    @Column(name = "ma")
    private String maNV;

    @NotBlank(message = "Tên đăng nhập không được để trống")
    @Size(max = 10, message = "Tên đăng nhập không được vượt quá 10 ký tự")
    @Column(name = "tenDangNhap")
    private String tenDangNhap;

    @NotBlank(message = "Mật khẩu không được để trống")
    @Size(min = 6, message = "Mật khẩu tối thiểu 6 ký tự")
    @Column(name = "matKhau")
    private String matKhau;

    @Column(name = "trangThai")
    private int trangThai;

    @Column(name = "quyen")
    private String quyen;

    @OneToMany(mappedBy = "nhanVien", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<HoaDon> listHD;
}
