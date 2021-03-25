package com.anteeone.iconapp.ui.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.anteeone.iconapp.R
import com.anteeone.iconapp.data.repositories.ApiRepositoryImpl
import com.anteeone.iconapp.domain.usecases.GetIconListUsecaseImpl
import com.anteeone.iconapp.ui.adapters.IconRecyclerViewAdapter
import com.anteeone.iconapp.ui.viewmodel.IconListViewModel
import com.anteeone.iconapp.ui.viewmodel.ViewModelFactory

class ItemListFragment : Fragment() {

    lateinit var mViewModel: IconListViewModel
    lateinit var mListAdapter: IconRecyclerViewAdapter
    lateinit var mRecyclerView: RecyclerView

    var mViewModelFactory: ViewModelFactory = ViewModelFactory(GetIconListUsecaseImpl(ApiRepositoryImpl()))

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mViewModel = ViewModelProvider(this,mViewModelFactory).get(IconListViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.icon_item_list, container, false)
        initMembers(view)
        subscribeOnViewModel()
        return view
    }

    private fun initMembers(view: View){

        mListAdapter = IconRecyclerViewAdapter{iconId ->
            replaceFragment(ItemDetailFragment.newInstance(iconId),"detail")
            Log.println(Log.DEBUG,"anteetag","touched on item view")
        }
        mRecyclerView = (view.findViewById(R.id.list) as RecyclerView).also {
            it.adapter = mListAdapter
        }
    }

    private fun subscribeOnViewModel(){
        with(mViewModel){
            mIconList.observe(viewLifecycleOwner, Observer {
                mListAdapter.submitData(it.iconList)
                Log.println(Log.DEBUG,"anteetag", "values size is ${mIconList.value?.iconList?.size.toString()}")
            })

        }
    }

    private fun replaceFragment(fragment: Fragment,backStackId:String){
        fragmentManager?.apply {
            beginTransaction()
                .replace(R.id.fragment_container,fragment)
                .addToBackStack(backStackId)
                .commit()
        }
    }
}