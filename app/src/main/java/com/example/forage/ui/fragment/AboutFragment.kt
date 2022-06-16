package com.example.forage.ui.fragment

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import com.example.forage.R
import com.example.forage.databinding.FragmentAboutBinding

class AboutFragment : Fragment() {
    private var _binding: FragmentAboutBinding?= null
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
        _binding = FragmentAboutBinding.inflate(inflater, container, false)
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
            licenseLink.setOnClickListener {
                val licensesUrl : Uri = Uri.parse("https://sites.google.com/gapp.nthu.edu.tw/sensen")
                val intent = Intent(Intent.ACTION_VIEW, licensesUrl)
                context?.startActivity(intent)
            }

            aboutLink.setOnClickListener {
                val aboutUrl : Uri = Uri.parse("https://sites.google.com/gapp.nthu.edu.tw/sensen")
                val intent = Intent(Intent.ACTION_VIEW, aboutUrl)
                context?.startActivity(intent)
            }
        }


    }
}