package org.example.demobuoi1.configs;

import org.example.demobuoi1.entity.SanPham;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DemoConfigBean {
    @Bean
    public SanPham getSanPham(){
        SanPham sp = new SanPham();
        sp.setMa("SP1");
        sp.setTen("Ao baby tee");
        sp.setTrangThai(1);
        return sp;
    }
}
