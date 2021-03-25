package com.anteeone.iconapp.ui.fragments

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import com.anteeone.iconapp.R
import com.anteeone.iconapp.data.repositories.ApiRepositoryImpl
import com.anteeone.iconapp.domain.usecases.GetIconDetailUsecaseImpl
import com.anteeone.iconapp.domain.usecases.interfaces.GetIconDetailUsecase
import com.anteeone.iconapp.ui.viewmodel.ItemDetailViewModel
import com.anteeone.iconapp.ui.viewmodel.ViewModelFactory
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_detail_fragment.*

class ItemDetailFragment : Fragment() {

    private lateinit var mViewModel: ItemDetailViewModel
    private lateinit var mViewModelFactory: ViewModelFactory

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.item_detail_fragment, container, false)
        mViewModelFactory = ViewModelFactory(GetIconDetailUsecaseImpl(ApiRepositoryImpl()),arguments!!.getInt("iconid"))
        mViewModel = ViewModelProvider(this,mViewModelFactory).get(ItemDetailViewModel::class.java)
        subscribeOnViewModel()
        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
    }

    companion object {
        fun newInstance(iconId: Int) = ItemDetailFragment().also {
            val args = Bundle()
            args.putInt("iconid",iconId)
            it.arguments = args
        }
    }

    private fun subscribeOnViewModel(){
        with(mViewModel){
            mIconDetail.observe(viewLifecycleOwner, Observer {
                icon_detail_id.text = it.iconId.toString()
                icon_detail_author.text = it.authorName.toString()
                icon_detail_is_premium.text = it.isPremium.toString()
                Picasso.get().load(it.previewUrl).into(icon_detail_logo)
            })
        }

    }

}