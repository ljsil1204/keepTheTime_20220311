package com.example.keepthetime_20220311

import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.DatePicker
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import com.example.keepthetime_20220311.databinding.ActivityEditAppointmentBinding
import java.text.SimpleDateFormat
import java.util.*

class EditAppointmentActivity : BaseActivity() {

    lateinit var binding : ActivityEditAppointmentBinding

//    약속 시간 일 /시를 저장해줄 Calendor. (월 값이 0~11로 움직이게 맞춰져있다.)
    val mSelectedAppointmentDateTime = Calendar.getInstance() // 기본값 : 현재 일시

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_edit_appointment)
        setupEvents()
        setValues()
    }

    override fun setupEvents() {

        binding.txtDate.setOnClickListener {

            val dsl = object : DatePickerDialog.OnDateSetListener{

                override fun onDateSet(p0: DatePicker?, year: Int, month: Int, dayOfMonth: Int) {

//                    연/월/일은, JAVA / Kptlin 언어의 기준으로 (0~11)월으로 월 값을 줌. (사람은 1~12월)
//                    주는 그대로 Calendar에 set하게 되면, 올바른 월로 세팅됨.

                    mSelectedAppointmentDateTime.set(year, month, dayOfMonth) // 연월일 한번에 세팅 함수.

//                    약속 일자의 문구를 22/03/08 등의 형식으로 바꿔서 보여주자.
//                    SimpleDateFormat을 화룡 => 월값도 알아서 보정.

                    val sdf = SimpleDateFormat("yy/MM/dd")

                    binding.txtDate.text = sdf.format(mSelectedAppointmentDateTime.time)

                }

            }

            val dpd = DatePickerDialog(
                mContext,
                dsl,
                mSelectedAppointmentDateTime.get(Calendar.YEAR),
                mSelectedAppointmentDateTime.get(Calendar.MONTH),
                mSelectedAppointmentDateTime.get(Calendar.DAY_OF_MONTH)
            ).show()

        }

    }

    override fun setValues() {

    }
}