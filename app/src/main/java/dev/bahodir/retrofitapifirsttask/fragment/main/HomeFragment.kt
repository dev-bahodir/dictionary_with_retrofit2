package dev.bahodir.retrofitapifirsttask.fragment.main

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.speech.RecognizerIntent
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.addTextChangedListener
import dev.bahodir.retrofitapifirsttask.R
import dev.bahodir.retrofitapifirsttask.adapter.RVAdapter
import dev.bahodir.retrofitapifirsttask.databinding.FragmentHomeBinding
import dev.bahodir.retrofitapifirsttask.retrofit.APIClient
import dev.bahodir.retrofitapifirsttask.retrofit.APIService
import dev.bahodir.retrofitapifirsttask.user.UserOneItem
import io.reactivex.Observable
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [HomeFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class HomeFragment : Fragment() {
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

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private lateinit var rvAdapter: RVAdapter

    @SuppressLint("CheckResult")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentHomeBinding.inflate(inflater, container, false)

        binding.voice.setOnClickListener {
            openVoiceDialog()
        }
        rvAdapter = RVAdapter()
        binding.rvHome.adapter = rvAdapter

        onCreateObservable()
            .subscribe {
                try {
                    APIClient.getRetrofit().create(APIService::class.java)
                        .getAPIService(it).enqueue(object : Callback<UserOneItem> {
                            override fun onResponse(
                                call: Call<UserOneItem>,
                                response: Response<UserOneItem>,
                            ) {
                                if (response.isSuccessful) {
                                    Log.d("TAG", "onResponse: ${response.body()}")
                                    val list = mutableListOf<UserOneItem>()
                                    list.add(UserOneItem(response.body()?.meanings!!,
                                        response.body()?.origin!!,
                                        response.body()?.phonetic!!,
                                        response.body()?.phonetics!!, response.body()?.word!!
                                    ))

                                    rvAdapter.submitList(list)
                                    binding.flag.text = response.body()?.word
                                }
                                else {
                                    Toast.makeText(requireContext(), "not found", Toast.LENGTH_SHORT).show()
                                }
                            }

                            override fun onFailure(call: Call<UserOneItem>, t: Throwable) {

                            }

                        })
                } catch (e: Exception) {
                    Toast.makeText(requireContext(), "error", Toast.LENGTH_SHORT).show()
                }
            }

        binding.shareTo.setOnClickListener {
            val sendIntent: Intent = Intent().apply {
                action = Intent.ACTION_SEND
                putExtra(Intent.EXTRA_TEXT, "This is my text to send.")
                type = "text/plain"
            }

            val shareIntent = Intent.createChooser(sendIntent, null)
            startActivity(shareIntent)
        }

        return binding.root
    }

    private fun onCreateObservable(): Observable<String> {
        return Observable.create { emitter ->
            binding.searchEdit.addTextChangedListener {
                emitter.onNext(it.toString())
            }
        }
    }

    private fun openVoiceDialog() {
        val intent = Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH)
        intent.putExtra(
            RecognizerIntent.EXTRA_LANGUAGE_MODEL,
            RecognizerIntent.LANGUAGE_MODEL_FREE_FORM
        )
        startActivityForResult(intent, 200)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 200 && resultCode == AppCompatActivity.RESULT_OK) {
            val list: ArrayList<String>? =
                data?.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS)
            val voice = list?.get(0)
            if (voice?.contains(" ") == true) {
                Toast.makeText(requireContext(), "Only word is required!", Toast.LENGTH_SHORT)
                    .show()
            } else {
                binding.searchEdit.setText(voice)
                binding.flag.text = voice
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment HomeFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            HomeFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}