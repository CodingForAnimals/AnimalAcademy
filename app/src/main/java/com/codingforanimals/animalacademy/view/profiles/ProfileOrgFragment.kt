package com.codingforanimals.animalacademy.view.profiles

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import androidx.core.content.res.ResourcesCompat
import androidx.fragment.app.Fragment
import androidx.transition.TransitionManager
import com.codingforanimals.animalacademy.R
import com.codingforanimals.animalacademy.contract.main.MainContract
import com.codingforanimals.animalacademy.contract.profiles.ProfileOrgContract
import com.codingforanimals.animalacademy.helpers.utils.Utils
import com.codingforanimals.animalacademy.presenter.profiles.ProfileOrgPresenter

class ProfileOrgFragment : Fragment(), ProfileOrgContract.View {

    private var rootLayout: ViewGroup? = null
    private var enterEditModeBtn: Button? = null
    private var cancelEditModeBtn: Button? = null
    private var saveEditModeBtn: Button? = null
    private var descriptionEdtxt: EditText? = null
    private var locationEdtxt: EditText? = null
    private var membersBtn: Button? = null
    private var contactBtn: Button? = null
    private var membersChkBox: CheckBox? = null
    private var contactChkBox: CheckBox? = null

    private var presenter: ProfileOrgPresenter? = null


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.profile_org, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bindViews(view)
        setButtonResizers()
        setClickListeners()
        presenter?.discardChanges()
        presenter?.enableDisableBtns()
    }


    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is MainContract.View) presenter = ProfileOrgPresenter(this, context)
    }

    override fun enterExitEditMode(enter: Boolean) {
        rootLayout?.let { TransitionManager.beginDelayedTransition(it) }
        changeButtonsVisibility(enter)
        changeClickabilty(listOf(descriptionEdtxt, locationEdtxt), enter)
    }

    override fun resetValues(description: String, location: String, showMembers: Boolean, showContact: Boolean) {
        descriptionEdtxt?.setText(description)
        locationEdtxt?.setText(location)
        membersChkBox?.isChecked = showMembers
        contactChkBox?.isChecked = showContact

    }

    @SuppressLint("ClickableViewAccessibility")
    override fun enableDisableBtns(showMembers: Boolean, showContact: Boolean) {
        membersBtn?.isSelected = showMembers
        membersChkBox?.isChecked = showMembers
        contactBtn?.isSelected = showContact
        contactChkBox?.isChecked = showContact
        enableDisableBtnTouch(membersBtn, showMembers)
        enableDisableBtnTouch(contactBtn, showContact)
    }

    private fun enableDisableBtnTouch(view: View?, enableTouch: Boolean) {
        if (!enableTouch) {
            view?.background =
                ResourcesCompat.getDrawable(resources, R.drawable.bg_accentoff_corners_filled, null)
            view?.setOnTouchListener(null)
        } else {
            view?.background =
                ResourcesCompat.getDrawable(resources, R.drawable.bg_accent_corners_filled, null)
            view?.setOnTouchListener(Utils.getResizerOnTouchListener(view))
        }
    }

    private fun bindViews(view: View) {
        rootLayout = view.findViewById(R.id.profile_org_root)
        enterEditModeBtn = view.findViewById(R.id.edit_mode_btn)
        cancelEditModeBtn = view.findViewById(R.id.cancel_edit_btn)
        saveEditModeBtn = view.findViewById(R.id.save_edit_btn)
        descriptionEdtxt = view.findViewById(R.id.desc)
        locationEdtxt = view.findViewById(R.id.location_txt)
        membersBtn = view.findViewById(R.id.members_btn)
        contactBtn = view.findViewById(R.id.contact_btn)
        membersChkBox = view.findViewById(R.id.checkbox_members)
        contactChkBox = view.findViewById(R.id.checkbox_contact)
    }

    private fun changeButtonsVisibility(editMode: Boolean) {
        val views = listOf(cancelEditModeBtn, saveEditModeBtn, membersChkBox, contactChkBox)
        if (editMode) {
            Utils.makeVisibleInvisible(views, listOf(enterEditModeBtn))
        } else {
            Utils.makeVisibleInvisible(listOf(enterEditModeBtn), views)
        }
    }

    private fun changeClickabilty(views: List<View?>, editMode: Boolean) =
        views.forEach {
            it?.isEnabled = editMode
            it?.isSelected = editMode
        }


    @SuppressLint("ClickableViewAccessibility")
    private fun setButtonResizers() {
        enterEditModeBtn?.setOnTouchListener(Utils.getResizerOnTouchListener(enterEditModeBtn))
        cancelEditModeBtn?.setOnTouchListener(Utils.getResizerOnTouchListener(cancelEditModeBtn))
        saveEditModeBtn?.setOnTouchListener(Utils.getResizerOnTouchListener(saveEditModeBtn))
        membersBtn?.setOnTouchListener(Utils.getResizerOnTouchListener(membersBtn))
        contactBtn?.setOnTouchListener(Utils.getResizerOnTouchListener(contactBtn))
    }

    private fun setClickListeners() {
        enterEditModeBtn?.setOnClickListener { presenter?.enterEditMode() }
        cancelEditModeBtn?.setOnClickListener {
            presenter?.discardChanges()
        }
        saveEditModeBtn?.setOnClickListener {
            presenter?.saveChanges(
                getTxt(descriptionEdtxt),
                getTxt(locationEdtxt),
                membersChkBox?.isChecked!!,
                contactChkBox?.isChecked!!
            )
        }
        membersBtn?.setOnClickListener { showMembers() }
    }

    private fun showMembers() {
        MembersDialogFragment().show(childFragmentManager, null)
    }

    private fun getTxt(editText: EditText?): String {
        editText?.let { return it.editableText.toString().trim() }
        return ""
    }
}