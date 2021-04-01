package com.github.raininforest.gerberpcb.model

object DataServiceImpl : DataService {
    override fun getList(): GerberList {
        return getTestData()
    }

    override fun addItem(gerberUri: String) {
        TODO("Not yet implemented")
    }

    override fun removeItem(gerberName: String) {
        TODO("Not yet implemented")
    }

    override fun setItemVisibility(visible: Boolean) {
        TODO("Not yet implemented")
    }

    override fun clear() {
        TODO("Not yet implemented")
    }

    override fun removeListeners() {
        TODO("Not yet implemented")
    }

    private fun getTestData() = GerberList().apply {
        add(GerberItem("controller_top.gbr"))
        add(GerberItem("controller_bottom.gbr"))
        add(GerberItem("controller_topmask.gbr"))
        add(GerberItem("controller_topsilk.gbr"))
        add(GerberItem("controller_botmask.gbr"))
        add(GerberItem("controller_botsilk.gbr"))
        add(GerberItem("controller_topsilk.gbr"))
        add(GerberItem("controller_botmask.gbr"))
        add(GerberItem("controller_botsilk.gbr"))
    }

}