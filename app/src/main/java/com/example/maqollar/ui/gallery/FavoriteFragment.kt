package com.example.maqollar.ui.gallery

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.navigation.Navigation
import com.example.maqollar.R
import com.example.maqollar.adapters.RuknRvItemAdapter
import com.example.maqollar.db.AppDatabase
import com.example.maqollar.models.Maqol
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.like.LikeButton
import com.like.OnLikeListener
import kotlinx.android.synthetic.main.bottom_sheet_layout.view.*
import kotlinx.android.synthetic.main.fragment_favorite.view.*

class FavoriteFragment : Fragment() {

    lateinit var root:View
    lateinit var ruknRvItemAdapter: RuknRvItemAdapter

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View {

        root = inflater.inflate(R.layout.fragment_favorite, container, false)
        val proverbDao = AppDatabase.getDatabase(root.context).proverbDao()

        ruknRvItemAdapter = RuknRvItemAdapter(object :RuknRvItemAdapter.OnRuknItemClickListener{
            override fun onClick(maqol: Maqol) {
                val bottomSheetDialog = BottomSheetDialog(root.context, R.style.MyBottomSheetTheme)
                val view = LayoutInflater.from(root.context)
                    .inflate(R.layout.bottom_sheet_layout, null, false)
                view.english.text = maqol.english
                view.uzbek.text = maqol.uzbek
                view.russian.text = maqol.russian
                view.sheet_favorite.isLiked = true
                bottomSheetDialog.setContentView(view)
                bottomSheetDialog.show()
                view.sheet_download.setOnClickListener {
                    val bundle = Bundle()
                    bundle.putSerializable("key",maqol)
                    Navigation.findNavController(root).navigate(R.id.downloadFragment,bundle)
                    bottomSheetDialog.dismiss()
                }
                view.sheet_favorite.setOnLikeListener(object :OnLikeListener{
                    override fun liked(likeButton: LikeButton?) {
                        proverbDao.insertProverb(maqol)
                    }

                    override fun unLiked(likeButton: LikeButton?) {
                        proverbDao.deleteProverb(maqol)
                    }

                })
                view.sheet_share.setOnClickListener {
                    Toast.makeText(root.context, "share", Toast.LENGTH_SHORT).show()
                }
            }

        })

        proverbDao.getData().observe(viewLifecycleOwner, Observer {
            if (it.isEmpty()) {
                root.null_text.visibility = View.VISIBLE
                root.favorite_rv.visibility = View.INVISIBLE
            }else{
                root.null_text.visibility = View.INVISIBLE
                root.favorite_rv.visibility = View.VISIBLE
            }
            ruknRvItemAdapter.submitList(it)

        })

        root.favorite_rv.adapter = ruknRvItemAdapter

        root.favorite_back_btn.setOnClickListener {
            Navigation.findNavController(root).popBackStack()
        }

        return root
    }
}