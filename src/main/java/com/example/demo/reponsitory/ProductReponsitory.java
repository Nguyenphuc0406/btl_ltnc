package com.example.demo.reponsitory;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.entity.Category;
import com.example.demo.entity.Product;

@Repository
public interface ProductReponsitory extends JpaRepository<Product, Integer> {
//	@Transactional
////	@Modifying
//	@Query(value = "SELECT pd.productId, pd.productName, pd.category, pd.productImage,pd.productPrice,pd.description, pd.quantity  From Product pd WHERE pd.productId =:productId")
//	Product findByProductId(int productId);
	Product findByProductId(int productId);
	//List<Product> findByCategory(Integer productId, Pageable pageable);
	List<Product> findByCategory(Category category);
//	@Transactional
//	@Modifying
//	@Query(value="SELECT * From Product pd WHERE pd.category =?1")
//	List<Product> findByCategory(int category);

	@Transactional
	@Modifying
	@Query(value = "UPDATE Product pd SET pd.productName = ?1, pd.category = ?2, pd.productImage=?3, pd.productPrice =?4, pd.description = ?5, pd.quantity =?6 WHERE pd.productId = ?7")
	void updateProductByProductId(String productName, Category category, String productImage, int productPrice,
			String description,int quantity, int productId);

	@Transactional
	@Modifying
	@Query(value = "DELETE from Product pd  WHERE pd.productId = :productId")
	void deleteProductByProductId(@Param("productId") int productId);
	
	 
}
