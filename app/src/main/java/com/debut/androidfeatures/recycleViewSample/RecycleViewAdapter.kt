package com.debut.androidfeatures.recycleViewSample

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.debut.androidfeatures.Constants
import com.debut.androidfeatures.R
import kotlinx.android.synthetic.main.item_row.view.*


/**
 * Created by shivam sharma on 26 september
 */
class RecycleViewAdapter(private val mDataList: ArrayList<String?>?,
                         private val onItemAction: (Int, Int) -> Unit) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {


    private val DATA = 1
    private val LOADER = 2
    private var mContext: Context? = null
    private var mFirstVisibleItem: Int = 0
    private var mLinearLayoutManager: LinearLayoutManager? = null
    private var mVisibleItemCount: Int = 0
    private var mTotalItemCount: Int = 0
    private var mIsNoMoreLoading: Boolean = false
    private var mIsLastPage: Boolean = false

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        mContext = parent.context
        return when (viewType) {
            DATA -> {
                val view = LayoutInflater.from(parent.context).inflate(R.layout.item_row, parent, false)
                ViewHolderItem(view)
            }

            else -> {
                ViewHolderLoader(LayoutInflater.from(mContext).inflate(R.layout.item_loading, parent, false))
            }
        }
    }


    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is ViewHolderItem) {
            holder.bindItems(mDataList?.get(position))
        } else if (holder is ViewHolderLoader) {
            holder.bindItems()
        }
    }


    override fun getItemCount(): Int {
        return mDataList?.size ?: 0
    }

    override fun getItemViewType(position: Int): Int {
        return when {
            mDataList?.get(position) != null -> DATA
            else -> LOADER
        }

    }

    /**
     * This method will update whether to show load more or not
     */
    fun noMoreLoading(status: Boolean) {
        mIsNoMoreLoading = status
    }


    /**
     * This method will set on scroll listener on recycler view
     * and when user scrolls to bottom of list then it calls for more data
     * if it available
     */
    fun setRecyclerView(linearLayoutManager: LinearLayoutManager, recyclerView: RecyclerView) {
        mLinearLayoutManager = linearLayoutManager
        recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                if (dy > 0) {
                    mVisibleItemCount = linearLayoutManager.childCount
                    mTotalItemCount = linearLayoutManager.itemCount
                    mFirstVisibleItem = linearLayoutManager.findFirstVisibleItemPosition()

                    //If the recycler is not Loading and data is not of the end page then
                    //trigger the LoadMore event
                    if ((!mIsNoMoreLoading) && (!mIsLastPage)) {
                        if ((mFirstVisibleItem + mVisibleItemCount) >= mTotalItemCount
                            && mFirstVisibleItem >= 0
                            && mTotalItemCount >= 10
                        ) {
                            onItemAction.invoke(0, Constants.LOADMORE)
                            mIsNoMoreLoading = true
                        }
                    }
                }

            }

        })
    }

    internal inner class ViewHolderItem(itemView: View) : RecyclerView.ViewHolder(itemView) {
        //this will set Data on item
        fun bindItems(txt: String?) {
            itemView.tvItem.text = txt
        }
    }

    internal inner class ViewHolderLoader(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bindItems() {

        }
    }
}