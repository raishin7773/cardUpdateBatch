package com.raishin.cardbatch.repository

import com.raishin.cardbatch.domain.Datas
import com.raishin.cardbatch.domain.DatasExample
import com.raishin.cardbatch.mapper.DatasMapper
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Repository

@Repository
class DatasRepositoryImpl : DatasRepository{
    @Autowired
    private lateinit var datasMapper: DatasMapper

    override fun insert(record: Datas) {
        datasMapper.insert(record)
    }

    override fun delete() {
        val example = DatasExample().apply {
            createCriteria()
        }
        datasMapper.deleteByExample(example)
    }
}