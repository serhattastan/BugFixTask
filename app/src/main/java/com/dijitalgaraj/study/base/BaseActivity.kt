package com.dijitalgaraj.study.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.viewbinding.ViewBinding
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach

abstract class BaseActivity<out ViewModel : BaseViewModel<BaseActionState>, Binding : ViewBinding>(
    viewModelClass: Class<ViewModel>,
) : AppCompatActivity() {

    protected lateinit var binding: Binding

    protected val viewModel: ViewModel by lazy {
        ViewModelProvider(this)[viewModelClass]
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = getViewBinding()
        setContentView(binding.root)

        init()

        with(viewModel) {
            actionState.map { it.getContentIfNotHandled() }.onEach(::renderActionState)
                .launchIn(lifecycleScope)
        }
    }

    abstract fun init()

    abstract fun renderActionState(actionState: BaseActionState?)

    abstract fun getViewBinding(): Binding
}