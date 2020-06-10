package com.example.demo.reponsitory;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.entity.OrderKhachHang;
@Transactional
public interface OrderKhachHangReponsitory extends JpaRepository<OrderKhachHang, Integer> {

}
