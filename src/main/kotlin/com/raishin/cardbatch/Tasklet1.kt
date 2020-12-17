package com.raishin.cardbatch

import com.opencsv.bean.CsvToBeanBuilder
import com.raishin.cardbatch.bean.DatasBean
import com.raishin.cardbatch.bean.TextsBean
import com.raishin.cardbatch.domain.Datas
import com.raishin.cardbatch.mapper.DatasMapper
import com.raishin.cardbatch.service.CardBatchService
import org.springframework.batch.core.StepContribution
import org.springframework.batch.core.scope.context.ChunkContext
import org.springframework.batch.core.step.tasklet.Tasklet
import org.springframework.batch.repeat.RepeatStatus
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import org.springframework.stereotype.Service
import java.nio.file.Files
import java.nio.file.Paths
import kotlin.jvm.Throws


@Component
class Tasklet1 : Tasklet {
    @Autowired
    lateinit var service: CardBatchService

    @Throws(Exception::class)
    override fun execute(contribution: StepContribution, chunkContext: ChunkContext): RepeatStatus? {
        csvParse()
        return RepeatStatus.FINISHED
    }

    private fun csvParse() {

//        var csvToBean: CsvToBean<DatasBean>? = null
        var datasBeanlist: List<DatasBean> = mutableListOf()
        val path = "src/main/resources/csv/datas.csv"
        Files.newBufferedReader(Paths.get(path)).use {
            try {
                val csvToBean = CsvToBeanBuilder<DatasBean>(it)
                        .withType(DatasBean::class.java)
                        .build()
                datasBeanlist = csvToBean!!.parse()
            } catch (e: Exception) {
                throw Exception("csv読み込みエラー")
            }
        }

        val path2 = "src/main/resources/csv/texts.csv"
        var textsBeanList: List<TextsBean> = mutableListOf()
        Files.newBufferedReader(Paths.get(path2)).use {
            try {
                val csvToBean = CsvToBeanBuilder<TextsBean>(it)
                        .withType(TextsBean::class.java)
                        .build()
                textsBeanList = csvToBean!!.parse()
            } catch (e: Exception) {
                throw Exception("csv読み込みエラー")
            }
        }

        val recordlist = mutableListOf<Datas>()
        datasBeanlist.forEach { datasbean ->
            val texts = textsBeanList.filter { it.id == datasbean.id }
            val record = Datas()
            record.id = datasbean.id.toInt()
            record.ot = datasbean.ot.toInt()
            record.alias = datasbean.alias.toInt()
            record.setcode = datasbean.setcode.toInt()
            record.type = datasbean.type.toInt()
            record.atk = datasbean.atk.toInt()
            record.def = datasbean.def.toInt()
            record.sum = record.atk + record.def
            record.level = datasbean.level.toInt()
            record.race = datasbean.race.toInt()
            record.attribute = datasbean.attribute.toInt()
            record.category = datasbean.category.toInt()
            record.name = texts[0].name
            record.description = texts[0].desc
            recordlist.add(record)
        }
        service.delete()

        recordlist.forEach { service.insert(it) }

    }
}