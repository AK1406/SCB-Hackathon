package com.scb.gamify

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class CategoryFragment : Fragment() {

    private var recyclerView: RecyclerView? = null
    private var categoryItem: ArrayList<CategoryModel>? = null
    private var gridLayoutManager: GridLayoutManager? = null
    private var categoryAdapter: CategoryAdapter? = null

    private var categoryList: ArrayList<CategoryModel> = ArrayList()

    companion object {
        fun newInstance(): Fragment {
            return CategoryFragment()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_category, container, false)
        setHasOptionsMenu(true)
        // Inflate the layout for this fragment
        recyclerView = view.findViewById(R.id.courseSection)
        categoryItem = ArrayList()
        categoryItem = setCategory()
        val myContext = activity as FragmentActivity
        val column = context?.let { calNoOfColumns(it) }

        categoryAdapter = context?.let {
            CategoryAdapter(it, categoryList) { categoryId ->
                val fragment = CourseFragment.newInstance()
                val bundle = Bundle()
                bundle.putString("CATEGORY_ID", categoryId) //send category id
                fragment.arguments = bundle
                val fragmentManager: FragmentManager = myContext.supportFragmentManager
                val fragmentTransaction = fragmentManager.beginTransaction()
                fragmentTransaction.replace(R.id.frameLayout, fragment)
                fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                fragmentTransaction.addToBackStack(null)
                fragmentTransaction.commit()
            }
        }
        gridLayoutManager =
            GridLayoutManager(context, column!!, LinearLayoutManager.VERTICAL, false)
        recyclerView?.layoutManager = gridLayoutManager
        recyclerView?.setHasFixedSize(true)
        recyclerView?.adapter = categoryAdapter

        return view
    }

//list of courses
    private fun setCategory(): ArrayList<CategoryModel> {

        categoryList.add(CategoryModel("html", "HTML", R.drawable.html))
        categoryList.add(CategoryModel("css", "CSS", R.drawable.css))


        return categoryList

    }

    //auto calculate no. of columns
    private fun calNoOfColumns(context: Context): Int {
        val displayMetrics = context.resources.displayMetrics
        val dpWidth = displayMetrics.widthPixels / displayMetrics.density
        return (dpWidth / 180).toInt()
    }

}