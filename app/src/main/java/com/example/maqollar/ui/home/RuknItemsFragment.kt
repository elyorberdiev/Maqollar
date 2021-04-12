package com.example.maqollar.ui.home

import android.content.ContentValues.TAG
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.Navigation
import com.example.maqollar.R
import com.example.maqollar.adapters.MaqolRvAdapter
import com.example.maqollar.db.AppDatabase
import com.example.maqollar.functions.loadImagesFunc
import com.example.maqollar.models.Maqol
import com.example.maqollar.models.Rukn
import com.example.maqollar.viewmodels.RuknItemsViewModel
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.like.LikeButton
import com.like.OnLikeListener
import kotlinx.android.synthetic.main.bottom_sheet_layout.view.*
import kotlinx.android.synthetic.main.fragment_rukn_items.view.*


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"
private const val TAG = "log"

class RuknItemsFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(TAG, "onCreate: ")
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    lateinit var root: View
    lateinit var maqolRvAdapter: MaqolRvAdapter
    lateinit var viewModel: RuknItemsViewModel


    override fun onCreateView(

        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        root = inflater.inflate(R.layout.fragment_rukn_items, container, false)
        val proverbDao = AppDatabase.getDatabase(root.context).proverbDao()
        val rukn = arguments?.getSerializable("data") as Rukn
        val position = arguments?.getInt("position") as Int
        val imageList = loadImagesFunc()

        viewModel = ViewModelProviders.of(this)[RuknItemsViewModel::class.java]
        viewModel.init(rukn)
        maqolRvAdapter = MaqolRvAdapter(object : MaqolRvAdapter.OnMaqolItemClickListener {
            override fun onClick(maqol: Maqol) {
                val bottomSheetDialog = BottomSheetDialog(root.context, R.style.MyBottomSheetTheme)
                val view = LayoutInflater.from(root.context)
                    .inflate(R.layout.bottom_sheet_layout, null, false)
                val maqolById = proverbDao.getMaqolById(maqol.id!!)
                if (maqolById != null) {
                    view.sheet_favorite.isLiked = true
                }
                view.english.text = maqol.english
                view.uzbek.text = maqol.uzbek
                view.russian.text = maqol.russian
                view.sheet_category.visibility = View.VISIBLE
                view.sheet_category.text = rukn.name
                bottomSheetDialog.setContentView(view)
                bottomSheetDialog.show()
                view.sheet_download.setOnClickListener {
                    val bundle = Bundle()
                    bundle.putSerializable("key",maqol)
                    Navigation.findNavController(root).navigate(R.id.downloadFragment,bundle)
                    bottomSheetDialog.dismiss()
                }
                view.sheet_favorite.setOnLikeListener(object : OnLikeListener {
                    override fun liked(likeButton: LikeButton?) {
                        proverbDao.insertProverb(maqol)
                    }

                    override fun unLiked(likeButton: LikeButton?) {
                        proverbDao.deleteProverb(maqol)
                    }

                })
                view.sheet_share.setOnClickListener {
                    val sharing = Intent(Intent.ACTION_SEND)
                    sharing.type = "text/plain"
                    val shareBody =
                        "${R.string.app_name}\nhttps://play.google.com/store/apps/details?id=uz.mobiler.maqollar"
                    sharing.putExtra(Intent.EXTRA_TEXT, shareBody)
                    startActivity(sharing)
                    bottomSheetDialog.dismiss()
                }
            }

        })
        viewModel.ruknItemList?.observe(viewLifecycleOwner, Observer {
            maqolRvAdapter.submitList(it)
//            Log.d(TAG, "onCreateView: ${it.toString()}")
        })
        root.ruknItem_rv.adapter = maqolRvAdapter

        root.icon111.setOnClickListener {
            Navigation.findNavController(root).popBackStack()
        }

        return root
    }


    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment RuknItemsFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            RuknItemsFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }

    /*

     */
}