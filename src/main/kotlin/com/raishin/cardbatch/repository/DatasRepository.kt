package com.raishin.cardbatch.repository

import com.raishin.cardbatch.domain.Datas
import com.raishin.cardbatch.mapper.DatasMapper
import org.apache.ibatis.annotations.Delete
import org.apache.ibatis.annotations.Mapper
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Repository


interface DatasRepository {
    fun insert(record: Datas)
    fun delete()
}