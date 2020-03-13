package com.viach.navigationInViewModel.view.nemain

import android.os.Bundle
import androidx.navigation.NavController
import com.viach.navigationInViewModel.R
import com.viach.navigationInViewModel.view.BaseActivity
import timber.log.Timber

class NeMainActivity : BaseActivity<NeMainViewModel>() {

    override val navController: NavController
        get() = TODO("Not yet implemented")

    override val observeBackEvents = false

    override val viewModelClass = NeMainViewModel::class.java

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_nemain)
    }

    override fun onResult(result: Any, requestCode: String) {
        Timber.d("$result $requestCode")
    }
}