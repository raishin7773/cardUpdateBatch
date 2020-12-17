package com.raishin.cardbatch.bean
import com.opencsv.bean.CsvBindByName
import com.opencsv.bean.CsvBindByPosition

class TextsBean {

    //　CSVの先頭にBOMというテキストの始まりを示す不可視のマークがある
    @CsvBindByName(column = "\uFEFFid", required = true)
    val id: Long = 0

    @CsvBindByName(column = "name", required = true)
    val name: String? = ""

    @CsvBindByName(column = "desc", required = true)
    val desc: String? = ""
}