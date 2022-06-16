package com.example.forage.ui.fragment

import android.app.AlertDialog
import android.content.DialogInterface
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.*
import android.widget.TextView
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.forage.R
import com.example.forage.databinding.FragmentSettingsListBinding
import com.example.forage.ui.adapter.SettingsAdapter
import com.example.forage.ui.adapter.VoiceDataAdapter

class SettingsListFragment : Fragment() {
    private var _binding: FragmentSettingsListBinding ?= null
    private val binding get() = _binding!!

    private lateinit var recyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSettingsListBinding.inflate(inflater, container, false)
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
        recyclerView = binding.settingsRecyclerView
        recyclerView.layoutManager = LinearLayoutManager(context)

        val adapter = SettingsAdapter { item ->
            val action = when(item.nameId) {
                R.string.setting_voice_setting -> SettingsListFragmentDirections.actionSettingsListFragmentToVoiceSettingsListFragment()
                R.string.setting_more_setting -> SettingsListFragmentDirections.actionSettingsListFragmentToMoreSettingsFragment()
                R.string.setting_tutorial -> SettingsListFragmentDirections.actionSettingsListFragmentToTutorialFragment()
                R.string.setting_about -> SettingsListFragmentDirections.actionSettingsListFragmentToAboutFragment()
                R.string.setting_faq_and_user_support -> SettingsListFragmentDirections.actionSettingsListFragmentToFAQAndUserSupportFragment()
                R.string.setting_rate_and_share -> {
                    val dialogBuilder = AlertDialog.Builder(requireActivity())
                    dialogBuilder
                        // if the dialog is cancelable
                        .setCancelable(false)
                        .setNegativeButton("Nope :(", DialogInterface.OnClickListener {
                                dialog, id -> badRating()
                        })
                        .setPositiveButton("I love it :)", DialogInterface.OnClickListener {
                                dialog, id -> goodRating()
                        })

                    val alert = dialogBuilder.create()
                    alert.setTitle("Do you enjoy the app?")
                    alert.setIcon(R.drawable.ic_baseline_insert_emoticon_24)
                    alert.show()

                    SettingsListFragmentDirections.actionSettingsListFragmentSelf()
                }
                else -> SettingsListFragmentDirections.actionSettingsListFragmentSelf()
            }
            findNavController().navigate(action)
        }

        recyclerView.adapter = adapter
        binding.apply {
            languageOpt1.setOnClickListener {
                languageOpt1.visibility = TextView.GONE
                languageOpt2.visibility = TextView.VISIBLE
            }

            languageOpt2.setOnClickListener {
                languageOpt1.visibility = TextView.VISIBLE
                languageOpt2.visibility = TextView.GONE
            }

            themeOpt1.setOnClickListener {
                themeOpt1.visibility = TextView.GONE
                themeOpt2.visibility = TextView.VISIBLE
            }

            themeOpt2.setOnClickListener {
                themeOpt1.visibility = TextView.VISIBLE
                themeOpt2.visibility = TextView.GONE
            }
        }
    }

    fun goodRating(){
        val dialogBuilder = AlertDialog.Builder(requireActivity())
        dialogBuilder.setMessage("We're happy to hear that! How about rating us on Google play?")
            // if the dialog is cancelable
            .setCancelable(false)
            .setNegativeButton("No, I decline...", DialogInterface.OnClickListener {
                    dialog, id -> dialog.dismiss()
            })
            .setPositiveButton("Of course!", DialogInterface.OnClickListener {
                    dialog, id -> rate()
            })

        val alert = dialogBuilder.create()
        alert.setTitle("Amazing!")
        alert.setIcon(R.drawable.ic_baseline_star_rate_24)
        alert.show()
    }

    fun badRating(){
        val dialogBuilder = AlertDialog.Builder(requireActivity())
        dialogBuilder.setMessage("Tell us about your experience or suggest how we can make it better.")
            // if the dialog is cancelable
            .setCancelable(false)
            .setNegativeButton("No, thanks...", DialogInterface.OnClickListener {
                    dialog, id -> dialog.dismiss()
            })
            .setPositiveButton("Give feedback!", DialogInterface.OnClickListener {
                    dialog, id -> email()
            })

        val alert = dialogBuilder.create()
        alert.setTitle("Your opinion matter to us!")
        alert.setIcon(R.drawable.ic_round_mail_outline_24)
        alert.show()
    }


    fun email(){
        val emailUrl : Uri = Uri.parse("https://sites.google.com/gapp.nthu.edu.tw/sensen")
        val intent = Intent(Intent.ACTION_VIEW, emailUrl)
        context?.startActivity(intent)
    }

    fun rate() {
        val rateUrl: Uri = Uri.parse("https://sites.google.com/gapp.nthu.edu.tw/sensen")
        val intent = Intent(Intent.ACTION_VIEW, rateUrl)
        context?.startActivity(intent)
    }
}