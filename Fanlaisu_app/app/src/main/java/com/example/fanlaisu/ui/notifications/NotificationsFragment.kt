package com.example.fanlaisu.ui.notifications

import android.content.Intent//
import android.os.Bundle//
import android.view.LayoutInflater//
import android.view.View//
import android.view.ViewGroup//
import android.widget.TextView
import androidx.appcompat.app.AlertDialog//
import androidx.fragment.app.Fragment//
import androidx.lifecycle.Observer//
import androidx.lifecycle.ViewModelProvider//
import com.example.fanlaisu.*//
import com.example.fanlaisu.databinding.FragmentNotificationsBinding//
import kotlinx.android.synthetic.main.fragment_notifications.*//

class NotificationsFragment : Fragment() {

    private lateinit var notificationsViewModel: NotificationsViewModel
    private var _binding: FragmentNotificationsBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        notificationsViewModel =
            ViewModelProvider(this).get(NotificationsViewModel::class.java)

        _binding = FragmentNotificationsBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val textView: TextView = binding.textNotifications
        notificationsViewModel.text.observe(viewLifecycleOwner, Observer {
            textView.text = it
        })
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onStart() {
        super.onStart()


        username.text=Define.account_name

        button2.setOnClickListener {
            val intent = Intent(requireActivity(), xinyufen::class.java)
            startActivity(intent)
        }

        button3.setOnClickListener {
            val intent = Intent(requireActivity(), dingdan::class.java)
            startActivity(intent)
        }
        button4.setOnClickListener {
            val intent = Intent(requireActivity(), xiugai::class.java)
            startActivity(intent)
        }
        button5.setOnClickListener {
            val intent = Intent(requireActivity(), setting::class.java)
            startActivity(intent)
        }
        button6.setOnClickListener {
            AlertDialog.Builder(requireContext()).apply {
                setTitle("温馨提示！")
                setMessage("您确定退出登录？")
                setCancelable(false)
                setPositiveButton("ok") { dialog, which ->
                    val intent = Intent(requireActivity(), Login::class.java)
                    startActivity(intent)
                }
                setNegativeButton("no"){
                    dialog,which->
                }
                show()


            }
        }
    }
}