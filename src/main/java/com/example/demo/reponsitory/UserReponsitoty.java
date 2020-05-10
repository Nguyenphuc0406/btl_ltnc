package com.example.demo.reponsitory;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.User;

public interface UserReponsitoty extends JpaRepository<User,Integer> {
	User findByUserName(String userName);

//    Users findByToken(String token);
//
//    @Query("select new com.uet.shop.mvc.model.dto.UserOrderDTO(u.userId,u.userName,count(o.staffId) )" +
//            " from Users u " +
//            "inner join UserRole ur on u.userId = ur.users.userId " +
//            "left join Orders o on u.userId = o.staffId " +
//            "where ur.role.roleId = 2" +
//            " group by o.staffId,u.userId,u.userName " +
//            "order by count(o.staffId)")
//    List<UserOrderDTO> getStaffMinOrder(Pageable pageable);
}
