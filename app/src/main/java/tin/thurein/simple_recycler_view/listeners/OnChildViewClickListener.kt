package tin.thurein.simple_recycler_view.listeners

interface OnChildViewClickListener: LoadMoreListener {
    fun onClick(position: Int)
}