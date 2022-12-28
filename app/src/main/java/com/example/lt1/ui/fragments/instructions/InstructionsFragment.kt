package com.example.lt1.ui.fragments.instructions

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebViewClient
import com.example.lt1.R
import com.example.lt1.databinding.FragmentIngredientsBinding
import com.example.lt1.databinding.FragmentInstructionsBinding
import com.example.lt1.models.Result
import com.example.lt1.util.Constants


class InstructionsFragment : Fragment() {
    private var _binding:FragmentInstructionsBinding?=null
    private val binding get()=_binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding= FragmentInstructionsBinding.inflate(inflater,container,false)
        val view=binding.root
        val args = arguments
        val myBundle: Result? = args?.getParcelable(Constants.RECIPE_RESULT_KEY)
        binding.instructionsWebView.webViewClient=object :WebViewClient(){}
        val websiteUrl:String=myBundle!!.sourceUrl
        binding.instructionsWebView.loadUrl(websiteUrl)

        return view
    }


}