package com.example.library.repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.library.dto.response.CategoryResponse;
import com.example.library.entity.Book;
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
    	        c.createdBy.id,
    	        c.updatedAt,
    	        c.deleteAt,
    	        COUNT(b.id)
    	    )
    	    FROM Category c
    	    LEFT JOIN Book b ON b.category = c
    	    GROUP BY
    	        c.id,
    	        c.categoryCode,
    	        c.categoryName,
    	        c.createdBy,
    	        c.updatedAt,
    	        c.deleteAt
    	""")
    	List<CategoryResponse> findAllWithBookCount();

    List<Category> findByUpdatedAtGreaterThanEqual(LocalDateTime time);

	@Query("""
            SELECT c
            FROM Category c
            WHERE c.version <= :snapshotVersion
              AND c.id > :cursor
            ORDER BY c.id ASC
            """)
	List<Category> initCategory(
			@Param("cursor") long cursor,
			@Param("snapshotVersion") long snapshotVersion,
			Pageable pageable
	);

    

}