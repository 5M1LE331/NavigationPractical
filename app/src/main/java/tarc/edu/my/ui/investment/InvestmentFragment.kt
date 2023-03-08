package tarc.edu.my.ui.investment

import android.app.DatePickerDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import tarc.edu.my.R
import tarc.edu.my.databinding.FragmentGalleryBinding
import tarc.edu.my.databinding.FragmentInvestmentBinding
import java.text.SimpleDateFormat
import java.util.*
import java.util.Calendar.*


/**
 * A simple [Fragment] subclass.
 * Use the [InvestmentFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class InvestmentFragment : Fragment() {
    private var _binding: FragmentInvestmentBinding? = null
    private val binding get() = _binding!!
    private var calendar: Calendar = getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        //binding = FragmentInvestmentBinding.inflate(layoutInflater)
        _binding = FragmentInvestmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.buttonCalculate.setOnClickListener {
            val account_balance = binding.editTextBalanceAccount1.text.toString().toInt()
        }
        binding.buttonReset.setOnClickListener {

        }

        val dateSetListener = DatePickerDialog.OnDateSetListener{view,year,month,day ->
            calendar.set(YEAR,year)
            calendar.set(MONTH,month)
            calendar.set(DAY_OF_YEAR,day)
            //update UI
            updateDateView()
        }

        binding.buttonDOB.setOnClickListener {
            DatePickerDialog(it.context,
            dateSetListener,
            calendar.get(YEAR),
            calendar.get(MONTH),
            calendar.get(DAY_OF_YEAR)).show()
        }
    }
    private fun updateDateView(){
        val myFormat = "dd/MM/yyyy"
        val sdf = SimpleDateFormat(myFormat, Locale.UK)
        binding.buttonDOB.text = sdf.format(calendar.time)

        val endDate = Calendar.getInstance()
        val age = dayBetween(calendar,endDate).div(365)
        binding.textViewAge.text = age.toString()
    }

    private fun dayBetween(startDate: Calendar, endDate: Calendar): Long{
        val date = startDate.clone() as Calendar
        var days: Long = 0
        while (date.before(endDate)){
            date.add(DAY_OF_MONTH, 1)
            days++
        }
        return days
    }
}