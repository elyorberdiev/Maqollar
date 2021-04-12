package com.example.maqollar.ui.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.Navigation
import com.example.maqollar.R
import com.example.maqollar.adapters.RuknRvAdapter
import com.example.maqollar.models.Rukn
import com.example.maqollar.retrofit.ApiClient
import com.example.maqollar.retrofit.ApiService
import kotlinx.android.synthetic.main.fragment_rukn.view.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"


class RuknFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    lateinit var root: View
    lateinit var ruknRvAdapter: RuknRvAdapter
    lateinit var allCategoryList: ArrayList<Rukn>
    lateinit var listImage:ArrayList<Int>
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        root = inflater.inflate(R.layout.fragment_rukn, container, false)

        loadImages()
        val service = ApiClient.getRetrofit().create(ApiService::class.java)
        allCategoryList = ArrayList()
        service.getAllCategories(100).enqueue(object : Callback<List<Rukn>> {
            override fun onResponse(call: Call<List<Rukn>>, response: Response<List<Rukn>>) {
                if (response.isSuccessful) {
                    val arrayList:ArrayList<Rukn> = response.body() as ArrayList<Rukn>
                    for (i in arrayList.indices) {
                        if (arrayList[i].parent == 0 && arrayList[i].id != 1) {
                            allCategoryList.add(arrayList[i])
                        }
                    }
                    ruknRvAdapter = RuknRvAdapter(allCategoryList,listImage,object :RuknRvAdapter.OnRuknItemClickListener{
                        override fun onClick(rukn: Rukn,position:Int) {
                            val bundle = Bundle()
                            bundle.putSerializable("data",rukn)
                            bundle.putInt("position",position)

                            Navigation.findNavController(root).navigate(R.id.ruknItemsFragment,bundle)
                        }

                    })
                    root.rukn_rv.adapter = ruknRvAdapter
                }

            }

            override fun onFailure(call: Call<List<Rukn>>, t: Throwable) {
                Toast.makeText(root.context, "${t.message}", Toast.LENGTH_SHORT).show()
            }

        })



        return root
    }

    private fun loadImages() {
        listImage = ArrayList()
        listImage.add(R.drawable.cat_image1)
        listImage.add(R.drawable.cat_image2)
        listImage.add(R.drawable.cat_image3)
        listImage.add(R.drawable.cat_image4)
        listImage.add(R.drawable.cat_image5)
        listImage.add(R.drawable.cat_image6)
        listImage.add(R.drawable.cat_image7)
        listImage.add(R.drawable.cat_image8)
        listImage.add(R.drawable.cat_image9)
        listImage.add(R.drawable.cat_image10)
        listImage.add(R.drawable.cat_image11)
        listImage.add(R.drawable.cat_image12)
        listImage.add(R.drawable.cat_image13)
        listImage.add(R.drawable.cat_image14)
        listImage.add(R.drawable.cat_image15)
        listImage.add(R.drawable.cat_image16)
        listImage.add(R.drawable.cat_image17)
        listImage.add(R.drawable.cat_image18)
        listImage.add(R.drawable.cat_image19)
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment RuknFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            RuknFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }

    /*
         dataViewModel = ViewModelProviders.of(this)[DataViewModel::class.java]
        ruknRvAdapter = RuknRvAdapter()
        dataViewModel.categoryList?.observe(viewLifecycleOwner, Observer {
            ruknRvAdapter.submitList(it)
        })
        root.rukn_rv.adapter = ruknRvAdapter
     */
}