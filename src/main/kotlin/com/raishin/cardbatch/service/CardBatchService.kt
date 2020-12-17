package com.raishin.cardbatch.service

import com.raishin.cardbatch.domain.Datas
import com.raishin.cardbatch.repository.DatasRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional
class CardBatchService {
    @Autowired
    private lateinit var repository: DatasRepository

    fun insert(record: Datas){
        repository.insert(record)
    }

    fun delete(){
        repository.delete()
    }
}