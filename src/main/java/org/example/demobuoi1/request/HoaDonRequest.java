package org.example.demobuoi1.request;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class HoaDonRequest {

    private Integer id;

    private Integer idNhanVien;

    private Date ngayMuaHang;

    private Integer trangThai;

    private Integer idKhachHang;


}
