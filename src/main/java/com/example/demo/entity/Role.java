package com.example.demo.entity;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.EqualsAndHashCode;
import lombok.ToString;

@Entity
@Table(name = "role")
public class Role {
	@Id
	@Column(name = "role_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int roleId;
	@Column(name = "role_name")
	private String roleName;
//	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
//	// Quan hệ n-n với đối tượng ở dưới (Person) (1 địa điểm có nhiều người ở)
//	@EqualsAndHashCode.Exclude // không sử dụng trường này trong equals và hashcode
//	@ToString.Exclude // Khoonhg sử dụng trong toString()
//
//	@JoinTable(name = "user_role", // Tạo ra một join Table tên là "user_role"
//			joinColumns = @JoinColumn(name = "role_id"), // TRong đó, khóa ngoại chính là role_id trỏ tới class hiện tại
//															// (Role)
//			inverseJoinColumns = @JoinColumn(name = "user_id") // Khóa ngoại thứ 2 trỏ tới thuộc tính ở dưới (USer)
//	)
//	private Collection<User> users;

	@OneToMany(fetch = FetchType.EAGER, mappedBy = "role", cascade = CascadeType.ALL)
	private List<UserRole> userRoles = new ArrayList<>();
	@Column(name = "description")
	private String description;

	public int getRoleId() {
		return roleId;
	}

	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public List<UserRole> getUserRoles() {
		return userRoles;
	}

	public void setUserRoles(List<UserRole> userRoles) {
		this.userRoles = userRoles;
	}

	public String getDescription() {
		return description;
	}

//	public Collection<User> getUsers() {
//		return users;
//	}
//
//	public void setUsers(Collection<User> users) {
//		this.users = users;
//	}

	public void setDescription(String description) {
		this.description = description;
	}

}
