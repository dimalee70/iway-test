package kz.v.auth.presentation.ui.register

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import kz.v.auth.databinding.FragmentRegisterBinding
import kz.v.dep_inject.TokenInterceptor
import kz.v.ui_components.ResultState
import kz.v.ui_components.base.BaseFragment
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel
import timber.log.Timber

class RegisterFragment : BaseFragment() {
    private val tokenInterceptor: TokenInterceptor by inject()
    private lateinit var binding: FragmentRegisterBinding
    private val viewModel: RegisterViewModel by viewModel()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Timber.i("onCreateView")
        binding = FragmentRegisterBinding.inflate(
            inflater,
            container,
            false
        ).apply {
            lifecycleOwner = this@RegisterFragment.viewLifecycleOwner
            viewModel = this@RegisterFragment.viewModel
        }
        viewModel.auth()

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        Timber.i("onViewCreated")
        super.onViewCreated(view, savedInstanceState)
        viewModel.authResponse.observe(viewLifecycleOwner, Observer(::onAuthChanged))
        viewModel.createResponse.observe(viewLifecycleOwner, Observer(::onCreateChanged))
        viewModel.loading.observe(viewLifecycleOwner, Observer(::onLoadingChanged))
    }

    private fun onLoadingChanged(data: Boolean) {
        Timber.i("onLoadingChanged, $data")
        if (data)
            showProgress()
        else
            closeProgress()
    }

    private fun onCreateChanged(data: ResultState<Boolean>) {
        Timber.i("onCreateChanged, $data")
        if (data is ResultState.Success)
            openSuccessDialog()
        else if (data is ResultState.Error)
            openErrorDialog()
    }

    private fun openErrorDialog() {
        Timber.i("openErrorDialog")
        showError()
    }

    private fun openSuccessDialog() {
        Timber.i("openSuccessPage")
        showSuccess()
    }

    private fun onAuthChanged(data: ResultState<AuthResponseUi>) {
        if (data is ResultState.Success) {
            setToken(data.data)
        }
    }

    private fun setToken(data: AuthResponseUi) {
        Timber.i("setToken, $data")
        tokenInterceptor.token = data.token
    }
}