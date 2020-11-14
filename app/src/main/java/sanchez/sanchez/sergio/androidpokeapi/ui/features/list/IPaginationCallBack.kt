package sanchez.sanchez.sergio.androidpokeapi.ui.features.list

interface IPaginationCallBack {

    /**
     * on Load Next Page
     */
    fun onLoadNextPage()

    /**
     * Is Pagination Enabled
     */
    fun isPaginationEnabled(): Boolean
}