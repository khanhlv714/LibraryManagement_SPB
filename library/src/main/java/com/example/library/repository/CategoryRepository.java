package com.example.library.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.library.dto.response.CategoryResponse;
import com.example.library.entity.Category;

@Repository
public interface CategoryRepository
        extends JpaRepository<Category, Integer> {

    boolean existsByCategoryCode(String categoryCode);

    Optional<Category> findByCategoryCode(String categoryCode);
    @Query("""
    	    SELECT new com.example.library.dto.response.CategoryResponse(
    	        c.id,
    	        c.categoryCode,
    	        c.categoryName,
    	        c.createdBy,
    	        COUNT(b.id)
    	    )
    	    FROM Category c
    	    LEFT JOIN Book b ON b.category = c
    	    GROUP BY c.id, c.categoryCode, c.categoryName, c.createdBy
    	""")
    	List<CategoryResponse> findAllWithBookCount();
    

}