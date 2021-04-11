package com.anteeone.iconapp.ui.fragments

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import com.anteeone.iconapp.R
import com.anteeone.iconapp.data.network.IconApi
import com.anteeone.iconapp.data.repositories.ApiRepositoryImpl
import com.anteeone.iconapp.domain.usecases.GetIconDetailUsecaseImpl
import com.anteeone.iconapp.domain.usecases.interfaces.GetIconDetailUsecase
import com.anteeone.iconapp.ui.viewmodel.ItemDetailViewModel
import com.anteeone.iconapp.ui.viewmodel.ViewModelFactory
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_detail_fragment.*
import javax.inject.Inject
import javax.inject.Named

class ItemDetailFragment : Fragment() {

    @Inject
    @Named("detail")
    lateinit var mViewModelFactory: ViewModelFactory
    private lateinit var mViewModel: ItemDetailViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mViewModel = ViewModelProvider(this,mViewModelFactory).get(ItemDetailViewModel::class.java).also {
            it.itemId = requireArguments().getInt("iconid")
        }
        subscribeOnViewModel()
    }

    override fun onStart() {
        super.onStart()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.item_detail_fragment, container, false)
        return view
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