package com.app.bubbledemoapp.ui.mainmodule.bottomsheetmodule

import android.app.Application
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.app.bubbledemoapp.Utils.DialogUtils
import com.app.bubbledemoapp.core.network.webservice.RetrofitCallAPI
import com.app.bubbledemoapp.core.network.webservice.WebServiceAPI
import com.app.bubbledemoapp.data.room.DatabaseHelper
import com.app.bubbledemoapp.ui.mainmodule.bottomsheetmodule.model.ProductModelPojo
import com.google.firebase.firestore.FirebaseFirestore
import com.google.gson.Gson
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Response

class BottonSheetRepository {
    companion object {
        var bottonSheetRepository: BottonSheetRepository? = null
        var mContext: Application? = null
        @Synchronized
        @JvmStatic
        fun getInstance(application: Application): BottonSheetRepository {
            mContext = application
            if (bottonSheetRepository == null) bottonSheetRepository = BottonSheetRepository()
            return bottonSheetRepository!!
        }
    }

   /* fun salesRmDetails(plant_id: String): LiveData<ProductModelPojo> {

        val salesRmData = MutableLiveData<ProductModelPojo>()

        CoroutineScope(Dispatchers.Main).launch {
            val resultDef: Deferred<Response<ProductModelPojo>> =
                getSalesRmData(plant_id)
            try {
                val result: Response<ProductModelPojo> = resultDef.await()
                if (result.isSuccessful) {
                    val response = result.body()
                    response?.let {

                        if (response != null) {
                            if (response.flag.equals("1")) {
                                var salesRmList = ArrayList<SalesRMEntity>()
                                if (response.data!!.salesrm_list!!.size > 0) {
                                    val salesRmDetailsList = response.data?.salesrm_list
                                    salesRmData.value = response

                                    for (i in salesRmDetailsList!!.indices) {
                                        var salesRMEntity = SalesRMEntity()
                                        salesRMEntity.sales_rm_id =
                                            salesRmDetailsList.get(i).sales_rm_id.toString()
                                        salesRMEntity.sales_rm_name =
                                            salesRmDetailsList.get(i).sales_rm_name.toString()
                                        salesRMEntity.location_name =
                                            salesRmDetailsList.get(i).location_name.toString()
                                        salesRmList.add(salesRMEntity)
                                    }
                                    DatabaseHelper.getDatabase(mContext!!).interfaceDao()
                                        .saveSalesRM(salesRmList)
                                }

                            } else {
                                DialogUtils.stopProgressDialog()
                                Toast.makeText(mContext, "Something went wrong", Toast.LENGTH_SHORT)
                                    .show()
                            }


                        }

                    }
                } else {
                    DialogUtils.stopProgressDialog()
                    Toast.makeText(
                        mContext,
                        "Please Check Internet Connections.",
                        Toast.LENGTH_SHORT
                    )
                        .show()
                }
            } catch (ex: Exception) {
                resultDef.getCompletionExceptionOrNull()?.let {
                    println(resultDef.getCompletionExceptionOrNull()!!.message)
                }

            }
        }
        return salesRmData
    }*/

    fun getProductListData(db: FirebaseFirestore): LiveData<ProductModelPojo> {
        val tempProductModelPojo = MutableLiveData<ProductModelPojo>()
        val docRef = db!!.collection("product").document("tQifPlrfGDLfqx6ww7pd")
        docRef.get()
            .addOnSuccessListener { document ->
                if (document != null) {
                    var productModelPojo: ProductModelPojo? = ProductModelPojo("",
                        emptyList(),"","",emptyList()
                        ,"",emptyList())
                    productModelPojo = Gson().fromJson<ProductModelPojo>(
                        Gson().toJson(document.data),
                        ProductModelPojo::class.java
                    )

                    tempProductModelPojo.value = productModelPojo
                    System.out.println("Result="+productModelPojo)
                    //  (Gson().toJson(document.data))
                } else {
                    Log.d("TAG", "No such document")
                }
            }
            .addOnFailureListener { exception ->
                Log.d("TAG", "get failed with ", exception)
            }


        return tempProductModelPojo
    }


}
