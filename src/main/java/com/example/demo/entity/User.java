package com.example.demo.entity;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
import javax.validation.constraints.NotNull;

import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.ToString.Exclude;

@Entity
@Table(name = "user")
public class User {
	@Id
	@Column(name = "user_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int userId;

	@Column(name = "code")
	private String code;
//	@OneToMany(mappedBy = "users")
//	private List<UserRole> userRoles = new ArrayList<>();
	// mappedBy trỏ tới tên biến users ở trong Role.

//	@ManyToMany(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	// Quan hệ n-n với đối tượng ở dưới (Person) (1 địa điểm có nhiều người ở)
//	@EqualsAndHashCode.Exclude // không sử dụng trường này trong equals và hashcode
//	@ToString.Exclude // Khoonhg sử dụng trong toString()

//	@JoinTable(name = "user_role", // Tạo ra một join Table tên là "user_role"
//			joinColumns = @JoinColumn(name = "user_id"), // TRong đó, khóa ngoại chính là role_id trỏ tới class hiện tại
//															// (Role)
//			inverseJoinColumns = @JoinColumn(name = "role_id") // Khóa ngoại thứ 2 trỏ tới thuộc tính ở dưới (USer)
//	)
//
//	private Collection<Role> roles;
	@ManyToMany
	@JoinTable(name = "role_user", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
	private Set<Role> roles = new HashSet<>();
	@Column(name = "user_name")
	private String userName;
	@Column(name = "password")
	private String password;
	@Column(name = "token")
	private String token;
	@Column(name = "phone_number")
	private String phoneNumber;
	@Column(name = "address")
	private String address;

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getUserName() {
		return userName;
	}

	public Set<Role> getRoles() {
		return roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

}
