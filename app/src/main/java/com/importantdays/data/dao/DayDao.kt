package com.importantdays.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.importantdays.data.entity.DayEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface DayDao {
    @Query("SELECT * FROM days ORDER BY month ASC, day ASC")
    fun getAllDays(): Flow<List<DayEntity>>

    @Query("SELECT * FROM days WHERE month = :month ORDER BY day ASC")
    fun getDaysByMonth(month: Int): Flow<List<DayEntity>>

    @Query("SELECT * FROM days WHERE id = :id")
    suspend fun getDayById(id: Int): DayEntity?

    @Query("SELECT * FROM days WHERE title LIKE '%' || :query || '%' OR description LIKE '%' || :query || '%' ORDER BY month ASC, day ASC")
    fun searchDays(query: String): Flow<List<DayEntity>>

    @Query("SELECT * FROM days WHERE isFavorite = 1 ORDER BY month ASC, day ASC")
    fun getFavoriteDays(): Flow<List<DayEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertDay(day: DayEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertDays(days: List<DayEntity>)

    @Update
    suspend fun updateDay(day: DayEntity)

    @Delete
    suspend fun deleteDay(day: DayEntity)

    @Query("DELETE FROM days")
    suspend fun clearAllDays()
}
