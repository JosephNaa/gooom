package js.pekah.gooom_app.ui.login.fragment

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import js.pekah.gooom_app.R
import js.pekah.gooom_app.databinding.FragmentJoinBinding
import js.pekah.gooom_app.model.dto.User
import js.pekah.gooom_app.ui.login.LoginActivity
import js.pekah.gooom_app.util.RetrofitUtil
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

private const val TAG = "JoinFragment_gooom"
class JoinFragment : Fragment(), CoroutineScope {
    private lateinit var loginActivity: LoginActivity
    lateinit var binding: FragmentJoinBinding
    private lateinit var job: Job

    private var checkedId = false

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
        binding = FragmentJoinBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnConfirm.setOnClickListener {
            val id = binding.editTextJoinID.text.toString()

            if (id == "") {
                Toast.makeText(context, "아이디를 입력해주세요.", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            launch {
                val result = RetrofitUtil.userService.isAvailable(id)
                Log.d(TAG, "onViewCreated: $result")
                Log.d(TAG, "onViewCreated: ${result.body()}")

                val available = result.body()!!.available
                if (available) {
                    checkedId = true
                    Toast.makeText(context, "사용가능한 아이디입니다", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(context, "중복된 아이디입니다", Toast.LENGTH_SHORT).show()
                }
            }
        }

        binding.btnJoin.setOnClickListener {
            val id = binding.editTextJoinID.text.toString()
            val pw = binding.editTextJoinPW.text.toString()
            val email = binding.editTextJoinEmail.text.toString()

            if (id != "" && pw != "" && email != "") {
                if (checkedId) {
                    val user = User(id, pw, email)
                    launch {
                        val result = RetrofitUtil.userService.join(user)
                        val check = result.body()

                        Log.d(TAG, "onViewCreated: $result")
                        Log.d(TAG, "onViewCreated: $check")
                        
                        if (check?.success == true) {
                            Toast.makeText(context, check.message, Toast.LENGTH_SHORT).show()
                            loginActivity.openFragment(3)
                        }
                    }
                } else {
                    Toast.makeText(context, "아이디 중복확인을 해주세요", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }


}