package com.dijitalgaraj.study.base

import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.window.OnBackInvokedDispatcher
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.viewbinding.ViewBinding
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import androidx.activity.OnBackPressedCallback
import androidx.navigation.fragment.findNavController

abstract class BaseFragment<out ViewModel : BaseViewModel<BaseActionState>, Binding : ViewBinding>(
    viewModelClass: Class<ViewModel>
) : Fragment() {

    private var _binding: Binding? = null
    val binding get() = _binding!!

    abstract val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> Binding

    protected val viewModel: ViewModel by lazy {
        ViewModelProvider(this)[viewModelClass]
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = bindingInflater(inflater, container, false)
        return binding.root
    }

    private var actionStateJob: Job? = null
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Geri tuşunu yönet
        handleBackButton()

        with(viewModel) {
            actionStateJob =
                actionState.map { it.getContentIfNotHandled() }.onEach(::renderActionState)
                    .launchIn(lifecycleScope)
        }
        init()
    }

    open fun init() {}

    abstract fun renderActionState(actionState: BaseActionState?)

    private fun handleBackButton() {
        // Android 13 ve üstü için OnBackInvokedCallback kullanıyoruz
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            requireActivity().onBackInvokedDispatcher.registerOnBackInvokedCallback(
                OnBackInvokedDispatcher.PRIORITY_DEFAULT
            ) {
                onBackPressed() // Geri tuşu yönetimi
            }
        } else {
            // Eski Android sürümleri için onBackPressedDispatcher kullanıyoruz
            requireActivity().onBackPressedDispatcher.addCallback(
                viewLifecycleOwner,
                object : OnBackPressedCallback(true) {
                    override fun handleOnBackPressed() {
                        onBackPressed()
                    }
                }
            )
        }
    }

    open fun onBackPressed() {
        val navController = findNavController()
        if (!navController.popBackStack()) {
            // Geri yığındaki son fragment ise, activity'yi sonlandır
            requireActivity().finish()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        actionStateJob?.cancel()
        _binding = null
    }
}