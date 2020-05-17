package com.example.mvpcraeture.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup.LayoutParams.MATCH_PARENT
import android.view.ViewGroup.LayoutParams.WRAP_CONTENT
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.afollestad.materialdialogs.DialogBehavior
import com.afollestad.materialdialogs.LayoutMode
import com.afollestad.materialdialogs.MaterialDialog
import com.afollestad.materialdialogs.ModalDialog
import com.afollestad.materialdialogs.bottomsheets.BottomSheet
import com.afollestad.materialdialogs.customview.customView
import com.afollestad.materialdialogs.customview.getCustomView
import com.example.mvpcraeture.R
import com.example.mvpcraeture.adapter.AvatarAdapter
import com.example.mvpcraeture.adapter.AvatarList
import com.example.mvpcraeture.adapter.AvatarListener
import com.example.mvpcraeture.application.MyApp
import com.example.mvpcraeture.creaturehelper.AttributeStore
import com.example.mvpcraeture.creaturehelper.AttributeType
import com.example.mvpcraeture.creaturehelper.AttributeValue
import com.example.mvpcraeture.creaturehelper.CreatureGenerator
import com.example.mvpcraeture.presenter.CreatureContract
import com.example.mvpcraeture.presenter.CreaturePresenter
import com.example.mvpcraeture.repository.CreateRepositoryImpl
import com.example.mvpcraeture.room.CreatureDAO
import com.google.android.material.bottomsheet.BottomSheetDialog
import kotlinx.android.synthetic.main.activity_creature.*
import kotlinx.android.synthetic.main.view_all_creature.*
import javax.inject.Inject

class CreatureActivity : AppCompatActivity(), CreatureContract.View {

    private val TAG = CreatureActivity::class.java.name

    @Inject
    @JvmField
    var creatureDAO: CreatureDAO? = null

    @Inject
    @JvmField
    var presenter: CreaturePresenter ?= null

    private var adapter: AvatarAdapter? = null

    private val listener = object : AvatarListener {
        override fun onAvatarClicked(avatar: Int) {
            presenter?.drawableSelected(avatar)
            tv_tap_avatar.isVisible = false
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_creature)

        (this.applicationContext as MyApp).appComponent?.injectCreature(this)

        presenter?.setView(this)

        configureClickListeners()
        configureSpinners()
        configureSpinnerListener()
        configureEditText()
        configureUI()
    }

    private fun configureClickListeners() {
        tv_tap_avatar.setOnClickListener {
            showCustomViewDialog(BottomSheet(LayoutMode.WRAP_CONTENT))
        }

        bt_save.setOnClickListener {
            presenter?.saveCreature()
            startActivity(Intent(this, MainActivity::class.java))
        }
    }


    private fun showCustomViewDialog(dialogBehavior: DialogBehavior = ModalDialog) {
        val dialog = MaterialDialog(this, dialogBehavior).show {
            customView(R.layout.pop_up_avatar_dialog, scrollable = true, horizontalPadding = true)
            negativeButton(android.R.string.cancel)
            negativeButton {
                dismiss()
            }
        }

        val customView = dialog.getCustomView()
        val rvView = customView.findViewById<RecyclerView>(R.id.rv_avatar)
        val adapter = AvatarAdapter(AvatarList().avatarList()).apply {
            this.avatarListener = listener
        }
        rvView.adapter = adapter
    }

    private fun showCustom() {
        val view = LayoutInflater.from(this).inflate(R.layout.pop_up_avatar_dialog, null)
        val dialog = AlertDialog.Builder(this).setView(view).create()
        val window = dialog.window
        window?.setGravity(Gravity.BOTTOM)
        window?.setLayout(MATCH_PARENT, WRAP_CONTENT)
        val rvView = view.findViewById<RecyclerView>(R.id.rv_avatar)
        val adapter = AvatarAdapter(AvatarList().avatarList()).apply {
            this.avatarListener = listener
        }
        rvView.adapter = adapter
        dialog.show()
    }

    private fun configureUI() {
        tv_tap_avatar.isVisible = !presenter!!.isDrawableSelected()
    }

    private fun configureSpinners() {
        sp_intelligence.adapter = ArrayAdapter<AttributeValue>(
            this,
            R.layout.support_simple_spinner_dropdown_item, AttributeStore.INTELLIGENCE
        )

        sp_endurance.adapter = ArrayAdapter<AttributeValue>(
            this, R.layout.support_simple_spinner_dropdown_item,
            AttributeStore.ENDURANCE
        )

        sp_strength.adapter = ArrayAdapter<AttributeValue>(
            this, R.layout.support_simple_spinner_dropdown_item, AttributeStore.STRENGTH
        )
    }

    private fun configureSpinnerListener() {
        sp_intelligence.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(p0: AdapterView<*>?) {
            }

            override fun onItemSelected(p0: AdapterView<*>?, view: View?, position: Int, id: Long) {
                presenter?.addAttributeSelected(AttributeType.INTELLIGENCE, position)
            }
        }

        sp_endurance.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(p0: AdapterView<*>?) {
            }

            override fun onItemSelected(p0: AdapterView<*>?, view: View?, position: Int, p3: Long) {
                presenter?.addAttributeSelected(AttributeType.ENDURANCE, position)
            }
        }

        sp_strength.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(p0: AdapterView<*>?) {
            }

            override fun onItemSelected(p0: AdapterView<*>?, view: View?, position: Int, p3: Long) {
                presenter?.addAttributeSelected(AttributeType.STRENGTH, position)
            }
        }
    }

    private fun configureEditText() {
        et_name.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(p0: Editable?) {
            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(ch: CharSequence?, p1: Int, p2: Int, p3: Int) {
                presenter?.updateName(ch.toString())
            }
        })
    }

    override fun showHitPoints(hitPoints: String) {
        tv_hit_points_value.text = hitPoints
    }

    override fun showAvatarDrawable(resourceID: Int) {
        iv_avatar.setImageResource(resourceID)
        iv_avatar.visibility = View.VISIBLE

        Log.d(TAG, "The drawable resource is: $resourceID")
    }

    override fun showCreatureSaved() {
        Toast.makeText(this, "Creature was saved", Toast.LENGTH_LONG).show()
    }

    override fun showCreatureSaveError() {
        Toast.makeText(this, "Error saving creature", Toast.LENGTH_LONG).show()
    }

    override fun onDestroy() {
        super.onDestroy()
        adapter?.removeListener()
    }
}
