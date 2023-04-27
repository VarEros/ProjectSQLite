package ni.edu.uca.projectsqlite

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import ni.edu.uca.projectsqlite.database.CityVM
import ni.edu.uca.projectsqlite.database.CityVMFactory
import ni.edu.uca.projectsqlite.databinding.FragmentSecondBinding

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class SecondFragment : Fragment() {

    private var _binding: FragmentSecondBinding? = null
    private val cityViewModel: CityVM by viewModels {
        CityVMFactory((context  as CityApplication).repository)
    }

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSecondBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.buttonSave.setOnClickListener {
            val replyIntent = Intent()
            if (TextUtils.isEmpty(binding.etNombre.text)) {
                requireActivity().setResult(Activity.RESULT_CANCELED, replyIntent)
            } else {
                val word = binding.etNombre.text.toString()
                replyIntent.putExtra(EXTRA_REPLY, word)
                requireActivity().setResult(Activity.RESULT_OK, replyIntent)
            }
            requireActivity().finish()
        }
    }

    companion object {
        const val EXTRA_REPLY = "com.example.android.wordlistsql.REPLY"
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}