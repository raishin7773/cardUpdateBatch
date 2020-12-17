package com.raishin.cardbatch.bean

import com.opencsv.bean.CsvBindByName
import com.opencsv.bean.CsvBindByPosition

class DatasBean {

    @CsvBindByName(column = "\uFEFFid", required = false)
    val id: Long = 0

    @CsvBindByName(column = "ot", required = false)
    val ot: Long = 0

    @CsvBindByName(column = "alias", required = false)
    val alias: Long = 0

    @CsvBindByName(column = "setcode", required = false)
    val setcode: Long = 0

    @CsvBindByName(column = "type", required = false)
    val type: Long = 0

    @CsvBindByName(column = "atk", required = false)
    val atk: Long = 0

    @CsvBindByName(column = "def", required = false)
    val def: Long = 0

    @CsvBindByName(column = "level", required = false)
    val level: Long = 0

    @CsvBindByName(column = "race", required = false)
    val race: Long = 0

    @CsvBindByName(column = "attribute", required = false)
    val attribute: Long = 0

    @CsvBindByName(column = "category", required = false)
    val category: Long = 0

}