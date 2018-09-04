package com.fengniao.news.util

import java.text.SimpleDateFormat
import java.util.*


object DateUtils {

    /**
     * 日期转换为字符串
     *
     * @param format 日期格式  比如：yyyyMMdd
     * @param date   日期
     * @return
     */
    fun dateToString(format: String, date: Date): String {
        val mFormat = SimpleDateFormat(format)
        return mFormat.format(date)
    }

    fun longToString(format: String, date: Long): String {
        return dateToString(format, Date(date))
    }

    fun timestampToString(format: String, date: Long): String {
        val mFormat = SimpleDateFormat(format)
        return mFormat.format(date)
    }


}
