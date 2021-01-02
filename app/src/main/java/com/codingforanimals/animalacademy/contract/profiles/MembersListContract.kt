package com.codingforanimals.animalacademy.contract.profiles

import android.widget.AdapterView
import com.codingforanimals.animalacademy.model.data.models.users.Member
import com.codingforanimals.animalacademy.presenter.profiles.members.OrgMembersViewHolder
import com.codingforanimals.animalacademy.presenter.recipes.suggestion.adapter.spinner.SpinnerAdapter

interface MembersListContract {

    interface View {
        fun bindMemberView(
            member: Member?,
            spinnerPosition: Int,
            adapter: SpinnerAdapter,
            onItemSelectedListener: AdapterView.OnItemSelectedListener,
            onTextChange: (String?) -> Unit
        )
        fun getSpinnerItemPosition(): Int?
        fun getSpinnerItemValue(): String?
    }

    interface Actions {
        fun bindMember(holder: OrgMembersViewHolder, position: Int)

    }
}