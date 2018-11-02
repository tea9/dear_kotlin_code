package com.example.homepage.entity

import com.chad.library.adapter.base.entity.SectionEntity

/**
 * created by tea9 at 2018/9/18
 */
class MySection : SectionEntity<String> {
    var isMore: Boolean = false

    constructor(isHeader: Boolean, header: String, isMroe: Boolean) : super(isHeader, header) {
        this.isMore = isMroe
    }

    constructor(t: String) : super(t) {}
}