package com.example.forage.ui.fragment

import android.app.AlertDialog
import android.content.DialogInterface
import android.media.RingtoneManager
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.forage.BaseApplication
import com.example.forage.R
import com.example.forage.databinding.FragmentEditVoicePackBinding
import com.example.forage.model.VoiceDataItem
import com.example.forage.ui.fragment.EditVoicePackFragmentArgs
import com.example.forage.ui.viewmodel.VoiceDataViewModel
import com.example.forage.ui.viewmodel.VoiceDataViewModelFactory
import xyz.aprildown.ultimateringtonepicker.RingtonePickerDialog
import xyz.aprildown.ultimateringtonepicker.UltimateRingtonePicker

class EditVoicePackFragment : Fragment() {
    private var _binding: FragmentEditVoicePackBinding?= null
    private val binding get() = _binding!!

    private val navigationArgs: EditVoicePackFragmentArgs by navArgs()
    private lateinit var voiceDataItem: VoiceDataItem

    private val viewModel: VoiceDataViewModel by activityViewModels(){
        VoiceDataViewModelFactory(
            (activity?.application as BaseApplication).voiceDataDatabase.voiceDataDao()
        )
    }

    val settings = UltimateRingtonePicker.Settings(
        systemRingtonePicker = UltimateRingtonePicker.SystemRingtonePicker(
            customSection = UltimateRingtonePicker.SystemRingtonePicker.CustomSection(),
            defaultSection = UltimateRingtonePicker.SystemRingtonePicker.DefaultSection(),
            ringtoneTypes = listOf(
                RingtoneManager.TYPE_RINGTONE,
                RingtoneManager.TYPE_NOTIFICATION,
                RingtoneManager.TYPE_ALARM
            )
        ),
        deviceRingtonePicker = UltimateRingtonePicker.DeviceRingtonePicker(
            deviceRingtoneTypes = listOf(
                UltimateRingtonePicker.RingtoneCategoryType.All,
                UltimateRingtonePicker.RingtoneCategoryType.Artist,
                UltimateRingtonePicker.RingtoneCategoryType.Album,
                UltimateRingtonePicker.RingtoneCategoryType.Folder
            )
        )
    )

    var url1: String = "res/raw/bad_delete.m4a"
    var url2: String = "res/raw/bad_delete.m4a"
    var url3: String = "res/raw/bad_delete.m4a"
    var url4: String = "res/raw/bad_delete.m4a"
    var url5: String = "res/raw/bad_delete.m4a"
    var url6: String = "res/raw/bad_delete.m4a"
    var url7: String = "res/raw/bad_delete.m4a"
    var url8: String = "res/raw/bad_delete.m4a"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentEditVoicePackBinding.inflate(inflater, container, false)
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
        val id = navigationArgs.id
        //if (id > 0) {
        viewModel.receive(id).observe(this.viewLifecycleOwner) { item ->
            voiceDataItem = item
        }
        //}

        binding.apply {
            voicePackNameInput.setText(voiceDataItem.name)
            goodStartUrl.text = voiceDataItem.goodStartURL
            goodPauseUrl.text = voiceDataItem.goodPauseURL
            goodResumeUrl.text = voiceDataItem.goodResumeURL
            goodFinishUrl.text = voiceDataItem.goodFinishURL
            badStartUrl.text = voiceDataItem.badStartURL
            badPauseUrl.text = voiceDataItem.badPauseURL
            badResumeUrl.text = voiceDataItem.badResumeURL
            badFinishUrl.text = voiceDataItem.badFinishURL

            url1 = voiceDataItem.goodStartURL.toString()
            url2 = voiceDataItem.goodPauseURL.toString()
            url3 = voiceDataItem.goodResumeURL.toString()
            url4 = voiceDataItem.goodFinishURL.toString()
            url5 = voiceDataItem.badStartURL.toString()
            url6 = voiceDataItem.badPauseURL.toString()
            url7 = voiceDataItem.badResumeURL.toString()
            url8 = voiceDataItem.badFinishURL.toString()

            /* On click add button, navigate to addHabitFragment */
            selectVoice1.setOnClickListener {
                RingtonePickerDialog.createEphemeralInstance(
                    settings = settings,
                    dialogTitle = "Voice for good start",
                    listener = object : UltimateRingtonePicker.RingtonePickerListener {
                        override fun onRingtonePicked(ringtones: List<UltimateRingtonePicker.RingtoneEntry>) {
                            url1 = ringtones[0].uri.toString()
                            goodStartUrl.text = url1
                        }
                    }
                ).show(childFragmentManager, null)
            }
            selectVoice2.setOnClickListener {
                RingtonePickerDialog.createEphemeralInstance(
                    settings = settings,
                    dialogTitle = "Voice for good pause",
                    listener = object : UltimateRingtonePicker.RingtonePickerListener {
                        override fun onRingtonePicked(ringtones: List<UltimateRingtonePicker.RingtoneEntry>) {
                            url2 = ringtones[0].uri.toString()
                            goodPauseUrl.text = url2
                        }
                    }
                ).show(childFragmentManager, null)
            }
            selectVoice3.setOnClickListener {
                RingtonePickerDialog.createEphemeralInstance(
                    settings = settings,
                    dialogTitle = "Voice for good resume",
                    listener = object : UltimateRingtonePicker.RingtonePickerListener {
                        override fun onRingtonePicked(ringtones: List<UltimateRingtonePicker.RingtoneEntry>) {
                            url3 = ringtones[0].uri.toString()
                            goodResumeUrl.text = url3
                        }
                    }
                ).show(childFragmentManager, null)
            }
            selectVoice4.setOnClickListener {
                RingtonePickerDialog.createEphemeralInstance(
                    settings = settings,
                    dialogTitle = "Voice for good finish",
                    listener = object : UltimateRingtonePicker.RingtonePickerListener {
                        override fun onRingtonePicked(ringtones: List<UltimateRingtonePicker.RingtoneEntry>) {
                            url4 = ringtones[0].uri.toString()
                            binding.goodFinishUrl.text = url4
                        }
                    }
                ).show(childFragmentManager, null)
            }
            selectVoice5.setOnClickListener {
                RingtonePickerDialog.createEphemeralInstance(
                    settings = settings,
                    dialogTitle = "Voice for bad start",
                    listener = object : UltimateRingtonePicker.RingtonePickerListener {
                        override fun onRingtonePicked(ringtones: List<UltimateRingtonePicker.RingtoneEntry>) {
                            url5 = ringtones[0].uri.toString()
                            badStartUrl.text = url5
                        }
                    }
                ).show(childFragmentManager, null)
            }
            selectVoice6.setOnClickListener {
                RingtonePickerDialog.createEphemeralInstance(
                    settings = settings,
                    dialogTitle = "Voice for bad pause",
                    listener = object : UltimateRingtonePicker.RingtonePickerListener {
                        override fun onRingtonePicked(ringtones: List<UltimateRingtonePicker.RingtoneEntry>) {
                            url6 = ringtones[0].uri.toString()
                            badPauseUrl.text = url6
                        }
                    }
                ).show(childFragmentManager, null)
            }
            selectVoice7.setOnClickListener {
                RingtonePickerDialog.createEphemeralInstance(
                    settings = settings,
                    dialogTitle = "Voice for bad resume",
                    listener = object : UltimateRingtonePicker.RingtonePickerListener {
                        override fun onRingtonePicked(ringtones: List<UltimateRingtonePicker.RingtoneEntry>) {
                            url7 = ringtones[0].uri.toString()
                            badResumeUrl.text = url5
                        }
                    }
                ).show(childFragmentManager, null)
            }
            selectVoice8.setOnClickListener {
                RingtonePickerDialog.createEphemeralInstance(
                    settings = settings,
                    dialogTitle = "Voice for bad finish",
                    listener = object : UltimateRingtonePicker.RingtonePickerListener {
                        override fun onRingtonePicked(ringtones: List<UltimateRingtonePicker.RingtoneEntry>) {
                            url8 = ringtones[0].uri.toString()
                            badFinishUrl.text = url8
                        }
                    }
                ).show(childFragmentManager, null)
            }
            voicePackAddButton.setOnClickListener {
                updateVoice(id)
            }
            voicePackCancelButton.setOnClickListener {
                findNavController().navigate(
                    R.id.action_editVoicePackFragment_to_voiceSettingsListFragment
                )
            }
        }
    }

    private fun updateVoice(id : Long) {
        if (isValidEntry()) {
            viewModel.updateVoiceData(
                id, binding.voicePackNameInput.text.toString(),
                url1, url2, url3, url4, url5, url6, url7, url8
            )
            findNavController().navigate(
                R.id.action_editVoicePackFragment_to_voiceSettingsListFragment
            )
        } else {
            val dialogBuilder = AlertDialog.Builder(requireActivity())
            dialogBuilder.setMessage("Please make sure that you have added all voice pack and also give them a name!")
                // if the dialog is cancelable
                .setCancelable(false)
                .setNegativeButton("Opps...", DialogInterface.OnClickListener {
                        dialog, id -> dialog.dismiss()
                })
                .setPositiveButton("Ok!", DialogInterface.OnClickListener {
                        dialog, id -> dialog.dismiss()
                })

            val alert = dialogBuilder.create()
            alert.setTitle("Something went wrong!")
            alert.setIcon(R.drawable.ic_baseline_notifications_none_24)
            alert.show()
        }
    }

    private fun isValidEntry() : Boolean {
        return binding.voicePackNameInput.text.toString().isNotBlank()
                && url1.isNotBlank()
                && url2.isNotBlank()
                && url3.isNotBlank()
                && url4.isNotBlank()
                && url5.isNotBlank()
                && url6.isNotBlank()
                && url7.isNotBlank()
                && url8.isNotBlank()
    }
}
