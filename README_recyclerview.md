**[BaseRecyclerViewAdapterHelper](https://github.com/CymChad/BaseRecyclerViewAdapterHelper)**  

Recyclerview  

    var rv =view.findViewById<RecyclerView>(R.id.rv)
    var layoutManager = LinearLayoutManager(context)
    layoutManager.orientation = OrientationHelper.VERTICAL
    rv.layoutManager = layoutManager

    rv.addItemDecoration(DividerItemDecoration(context, LinearLayoutManager.VERTICAL))

    var adapter = object : BaseQuickAdapter<String,BaseViewHolder>(R.layout.fragment_trading_buy) {
        override fun convert(helper: BaseViewHolder?, item: String?) {

        }

    }
    var headView = LayoutInflater.from(context).inflate(R.layout.fragment_trading_buy_head,null)
    adapter.addHeaderView(headView)
    rv.adapter = adapter

    adapter!!.onItemChildClickListener = BaseQuickAdapter.OnItemChildClickListener { adapter, view, position ->
        toast("itemclick"+position)
    }




