package com.app.bubbledemoapp.ui.mainmodule

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.app.bubbledemoapp.R
import com.app.bubbledemoapp.databinding.ActivityMainBinding
import kheliya.application.core.presentation.base.BaseActivity
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : BaseActivity<ActivityMainBinding, MainViewModel>(), MainNavigator {
    var activityMainBinding: ActivityMainBinding? = null
    val mainViewModel:MainViewModel by viewModel()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityMainBinding = getViewDataBinding()

    }

    override fun getLayoutId(): Int {
        return R.layout.activity_main

    }

    override fun getViewModel(): MainViewModel {
        return  mainViewModel

    }

    override fun setUp(savedInstanceState: Bundle?) {

    }

    override fun clickOnProceed() {
        val intent = Intent(this, AllItemListActivity::class.java)
        startActivity(intent)
        finish()
    }
}