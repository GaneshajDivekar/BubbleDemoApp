package com.app.bubbledemoapp.ui.mainmodule.bottomsheetmodule

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import com.app.bubbledemoapp.R

import androidx.lifecycle.Observer
import com.app.bubbledemoapp.core.presentation.base.BaseFragment
import com.app.bubbledemoapp.databinding.FragmentBottomSheetBinding
import com.app.bubbledemoapp.ui.mainmodule.bottomsheetmodule.model.ProductModelPojo
import com.google.firebase.firestore.FirebaseFirestore
import com.google.gson.Gson
import org.koin.androidx.viewmodel.ext.android.viewModel

class BottomSheetFragment : BaseFragment<FragmentBottomSheetBinding, BottomSheetViewModel>(),BottomSheetNavigator {

    var fragmentBottomSheetBinding: FragmentBottomSheetBinding? = null
    val bottomSheetViewModel: BottomSheetViewModel by viewModel()
    var bottomSheetNavigator: BottomSheetNavigator? = null
    private var db: FirebaseFirestore? = null

    override fun getLayoutId(): Int {
       return R.layout.fragment_bottom_sheet
    }

    override fun getViewModel(): BottomSheetViewModel {
       return  bottomSheetViewModel
    }

    override fun setUp(view: View, savedInstanceState: Bundle?) {
        fragmentBottomSheetBinding = getViewDataBinding()
        fragmentBottomSheetBinding!!.bottomSheetCall = this
        bottomSheetNavigator = this
        db = FirebaseFirestore.getInstance()
        bottomSheetViewModel?.getProductList(db!!)
            ?.observe(this, Observer { productModelPojo ->
                if (productModelPojo != null) {

                } else {

                }
            })
    }

}