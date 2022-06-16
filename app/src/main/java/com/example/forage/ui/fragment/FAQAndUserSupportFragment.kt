package com.example.forage.ui.fragment

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import com.example.forage.R
import com.example.forage.databinding.FragmentFaqAndUserSupportBinding

class FAQAndUserSupportFragment : Fragment() {
    private var _binding: FragmentFaqAndUserSupportBinding?= null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentFaqAndUserSupportBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        menu?.findItem(R.id.action_menu).isVisible = false
        menu.setGroupVisible(R.id.menu_group, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.apply{
            question1.setOnClickListener {
                val question1Url : Uri = Uri.parse("https://sites.google.com/gapp.nthu.edu.tw/sensen")
                val intent = Intent(Intent.ACTION_VIEW, question1Url)
                context?.startActivity(intent)
            }
            question2.setOnClickListener {
                val question2Url : Uri = Uri.parse("https://sites.google.com/gapp.nthu.edu.tw/sensen")
                val intent = Intent(Intent.ACTION_VIEW, question2Url)
                context?.startActivity(intent)
            }
            question3.setOnClickListener {
                val question3Url : Uri = Uri.parse("https://sites.google.com/gapp.nthu.edu.tw/sensen")
                val intent = Intent(Intent.ACTION_VIEW, question3Url)
                context?.startActivity(intent)
            }
            question4.setOnClickListener {
                val question4Url : Uri = Uri.parse("https://sites.google.com/gapp.nthu.edu.tw/sensen")
                val intent = Intent(Intent.ACTION_VIEW, question4Url)
                context?.startActivity(intent)
            }
            moreHelp.setOnClickListener {
                val moreHelpUrl : Uri = Uri.parse("https://sites.google.com/gapp.nthu.edu.tw/sensen")
                val intent = Intent(Intent.ACTION_VIEW, moreHelpUrl)
                context?.startActivity(intent)
            }
        }
    }
}