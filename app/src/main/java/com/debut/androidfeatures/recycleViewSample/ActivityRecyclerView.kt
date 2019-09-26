package com.debut.androidfeatures.recycleViewSample

import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.debut.androidfeatures.R
import kotlinx.android.synthetic.main.activity_recycler_view.*

/**
 * Created by Shivam sharma on 26 september
 */
class ActivityRecyclerView : AppCompatActivity() {
    private var mRowsArrayList: ArrayList<String?>? = null
    private var mAdapter: RecycleViewAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recycler_view)
        mRowsArrayList = ArrayList()
        populateData()
        setUpRecyclerView()
    }

    /**
     * This method will initialize the adapter
     */
    private fun setUpRecyclerView() {
        val layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
        recyclerView.layoutManager = layoutManager
        mAdapter = RecycleViewAdapter(mRowsArrayList, onItemSelected)
        recyclerView.adapter = mAdapter
        mAdapter?.setRecyclerView(layoutManager, recyclerView)
    }


    /**
     * This method will create data list
     */
    private fun populateData() {
        var i = 0
        while (i < 10) {
            mRowsArrayList?.add("Item $i")
            i++
        }
    }

    /**
     * Its a callback for load more
     */
    private val onItemSelected = { pos: Int, action: Int ->
        loadMore()
        mAdapter?.noMoreLoading(true)
        Unit
    }


    /**
     * This method will add @null in the list on last position
     * to show loader for 2000 milliseconds, then create 10 more records and
     * add them to the list
     */
    private fun loadMore() {
        mRowsArrayList?.add(null)
        mAdapter?.notifyItemInserted(mRowsArrayList?.size ?: 0 - 1)

        val handler = Handler()
        handler.postDelayed({
            mRowsArrayList?.size?.minus(1)?.let { mRowsArrayList?.removeAt(it) }
            val scrollPosition = mRowsArrayList?.size ?: 0
            mAdapter?.notifyItemRemoved(scrollPosition)
            var currentSize = scrollPosition
            val nextLimit = currentSize + 10

            while (currentSize - 1 < nextLimit) {
                mRowsArrayList?.add("Item $currentSize")
                currentSize++
            }

            mAdapter?.notifyDataSetChanged()
            mAdapter?.noMoreLoading(false)
        }, 1000)
    }
}
