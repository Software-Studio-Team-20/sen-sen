import android.os.Bundle
import android.view.*
import android.widget.TextView
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
        if (id > 0) {
            viewModel.receive(id).observe(this.viewLifecycleOwner) { item ->
                voiceDataItem = item
                bindVoiceData(voiceDataItem)
            }
        } else {
            binding.voicePackAddButton.setOnClickListener {
                addVoiceData()
            }
            binding.voicePackCancelButton.setOnClickListener {
                findNavController().navigate(
                    R.id.action_editVoicePackFragment_to_voiceSettingsListFragment
                )
            }
        }
    }

    private fun addVoiceData() {
        /*if (isValidEntry()) {
            /*viewModel.addVoiceData(
                
            )
            findNavController().navigate(
                R.id.action_addVoicePackFragment_to_voiceSettingsListFragment
            )*/
        }*/
    }

    private fun bindVoiceData(item: VoiceDataItem) {
        binding.apply {
            voicePackNameInput.setText(item.name, TextView.BufferType.SPANNABLE)
            goodStartUrl.text = item.goodStartURL
            goodPauseUrl.text = item.goodPauseURL
            goodResumeUrl.text = item.goodResumeURL
            goodFinishUrl.text = item.goodFinishURL
            badStartUrl.text = item.badStartURL
            badPauseUrl.text = item.badPauseURL
            badResumeUrl.text = item.badResumeURL
            badFinishUrl.text = item.badFinishURL

            voicePackAddButton.setOnClickListener {

            }

            voicePackCancelButton.setOnClickListener {
                //findNavController().navigate(R.id.actionEdi)
            }
        }
    }

    /*private fun isValidEntry() = viewModel.isValidEntry(
        binding..text.toString()
    )*/
}
