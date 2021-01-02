package com.codingforanimals.animalacademy.view.main

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import com.codingforanimals.animalacademy.R
import com.codingforanimals.animalacademy.contract.main.DonationsContract
import com.codingforanimals.animalacademy.helpers.utils.Utils
import kotlinx.android.synthetic.main.donations.*

class DonationsFragment : Fragment(), DonationsContract.View {

    private val DONATE_1_URL =
        "https://www.mercadopago.com.ar/checkout/v1/redirect?pref_id=227730221-4fdf84a3-5b72-4ea6-a47f-4c5b39b8e994"

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        setHasOptionsMenu(true)
        return inflater.inflate(R.layout.donations, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        donate_1.setOnTouchListener(Utils.getResizerOnTouchListener(donate_1))
        donate_1.setOnClickListener { donateAmount(DONATE_1_URL) }

        donate_2.setOnTouchListener(Utils.getResizerOnTouchListener(donate_2))
        donate_2.setOnClickListener { }


        donate_3.setOnTouchListener(Utils.getResizerOnTouchListener(donate_3))
        donate_3.setOnClickListener { }


        donate_4.setOnTouchListener(Utils.getResizerOnTouchListener(donate_4))
        donate_4.setOnClickListener { }

    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        menu.findItem(R.id.action_donate).isVisible = false
    }

    override fun donateAmount(url: String) {
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
        startActivity(intent)
    }
}