package js.pekah.gooom_app.ui.login.fragment

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import js.pekah.gooom_app.R
import js.pekah.gooom_app.config.ApplicationClass
import js.pekah.gooom_app.databinding.FragmentLoginBinding
import js.pekah.gooom_app.model.dto.User
import js.pekah.gooom_app.ui.base.BaseFragment
import js.pekah.gooom_app.ui.login.LoginActivity
import js.pekah.gooom_app.util.RetrofitUtil
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

private const val TAG = "LoginFragment_gooom"
class LoginFragment : Fragment(), CoroutineScope {
    private lateinit var loginActivity: LoginActivity
    lateinit var binding: FragmentLoginBinding

    private var checkId = false

    private lateinit var job: Job

    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main + job

    override fun onAttach(context: Context) {
        super.onAttach(context)
        loginActivity = context as LoginActivity
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        job = Job()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        var id = view.findViewById<EditText>(R.id.editTextLoginID)
        var password = view.findViewById<EditText>(R.id.editTextLoginPW)

        binding.btnLogin.setOnClickListener{
            launch {
                login(id.text.toString(), password.text.toString())
            }
        }

        binding.btnJoin.setOnClickListener {
            loginActivity.openFragment(2)
        }
    }

    private suspend fun login(loginId: String, loginPassword: String) {
        val user = User(loginId, loginPassword)
        var result = RetrofitUtil.userService.login(user)
        Log.d(TAG, "login: $result")
        Log.d(TAG, "login: ${result.body()}")
        if (result.code() == 200) {
            ApplicationClass.sharedPreferencesUtil.addUser(user)
            loginActivity.openFragment(1)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        job.cancel()
    }
}