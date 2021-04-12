package com.example.maqollar.ui.slideshow

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CompoundButton
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.example.maqollar.MainActivity
import com.example.maqollar.R
import com.example.maqollar.sharedpreferences.MySharedPreferences
import kotlinx.android.synthetic.main.fragment_settings.view.*


class SettingsFragment : Fragment() {

    lateinit var root: View
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        root = inflater.inflate(R.layout.fragment_settings, container, false)
        MySharedPreferences.init(root.context)
        root.switch_icon.isChecked = MySharedPreferences.getTheme()
        root.setting_back_btn.setOnClickListener {
            Navigation.findNavController(root).navigateUp()
        }

       root.switch_icon.setOnCheckedChangeListener(object :CompoundButton.OnCheckedChangeListener{
           override fun onCheckedChanged(buttonView: CompoundButton?, isChecked: Boolean) {
               if (isChecked) {
                   MySharedPreferences.setTheme(true)
                   forRestartIntent()
               } else {
                   MySharedPreferences.setTheme(false)
                   forRestartIntent()
               }
           }

       })

        return root
    }

    private fun forRestartIntent() {
//        val intent = Intent(root.context, MainActivity::class.java)
//        intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK
//        startActivity(intent)
//        (activity as MainActivity).finish()
        (activity as MainActivity).recreate()
    }
}