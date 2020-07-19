package com.app.bubbledemoapp.ui.mainmodule.bottomsheetmodule

import android.os.Bundle
import android.util.Log
import android.view.View
import com.app.bubbledemoapp.R
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
        val docRef = db!!.collection("product").document("tQifPlrfGDLfqx6ww7pd")
        docRef.get()
            .addOnSuccessListener { document ->
                if (document != null) {
                    var productModelPojo: ProductModelPojo? = ProductModelPojo()
                    productModelPojo = Gson().fromJson<ProductModelPojo>(
                        Gson().toJson(document.data),
                        ProductModelPojo::class.java
                    )
                    System.out.println("Result="+productModelPojo)
                    //                        String userData  = document.getData().toString();
                    (Gson().toJson(document.data))
                    Log.d("TAG", "DocumentSnapshot data: ${document.data}")
                } else {
                    Log.d("TAG", "No such document")
                }
            }
            .addOnFailureListener { exception ->
                Log.d("TAG", "get failed with ", exception)
            }


    }

}