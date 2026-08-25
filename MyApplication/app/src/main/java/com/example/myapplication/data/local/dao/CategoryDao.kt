package com.example.myapplication.data.local.dao

import androidx.room.*
import com.example.myapplication.data.local.entity.CategoryEntity
import com.example.myapplication.domain.model.CategoryWithBookCount
import kotlinx.coroutines.flow.Flow

@Dao
interface CategoryDao {

    @Query("""
    SELECT category.*, COUNT(book.id) AS amountBook
    FROM category
    LEFT JOIN book ON book.categoryId = category.id
    GROUP BY category.id
""")
    fun getCategoriesWithBookCount(): Flow<List<CategoryWithBookCount>>

    @Query("SELECT * FROM category ORDER BY categoryName ASC")
    fun observeCategories(): Flow<List<CategoryEntity>>


    @Query("SELECT * FROM category")
    fun getAllCategories(): Flow<List<CategoryEntity>>

    @Query("SELECT * FROM category WHERE id = :id")
    suspend fun getCategoryById(id: Int): CategoryEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCategory(category: CategoryEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCategories(categories: List<CategoryEntity>)

    @Update
    suspend fun updateCategory(category: CategoryEntity)

    @Delete
    suspend fun deleteCategory(category: CategoryEntity)

    @Query("DELETE FROM category")
    suspend fun deleteAllCategories()
}