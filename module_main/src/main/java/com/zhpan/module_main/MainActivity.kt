package com.zhpan.module_main

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.KeyEvent
import android.widget.RadioButton
import android.widget.RadioGroup

import androidx.viewpager.widget.ViewPager

import com.zhpan.library.activity.BaseActivity
import com.zhpan.module_main.adapter.AdapterFragmentPager


class MainActivity : BaseActivity() {
    //    @BindView(R2.id.rb_home)
    private var mRbHome: RadioButton? = null
    //    @BindView(R2.id.rb_find)
    private var mRbFind: RadioButton? = null
    //    @BindView(R2.id.rb_add)
    private var mRbAdd: RadioButton? = null
    //    @BindView(R2.id.rb_message)
    private var mRbMessage: RadioButton? = null
    //    @BindView(R2.id.rb_me)
    private var mRbMe: RadioButton? = null
    private var mRadioGroup: RadioGroup? = null
    private var mViewPager: ViewPager? = null
    //  退出时间间隔
    private var exitTime: Long = 0
    //  上一次RadioGroup选中的Id
    private var preCheckedId = R.id.rb_home

    private//        return UserInfoTools.getIsLogin(this);
    val isLogin: Boolean
        get() = true

    override fun onResume() {
        super.onResume()
        //        SharedPreferencesHelper.put(this, "isFirstIn", true);
    }

    override fun getLayoutId(): Int {
        return R.layout.activity_main
    }

    override fun initTitle() {

    }

    override fun initView(savedInstanceState: Bundle?) {
        mViewPager = findViewById(R.id.vp_fragment)
        mRadioGroup = findViewById(R.id.rg_tab)
        mRbFind = findViewById(R.id.rb_find)
        mRbAdd = findViewById(R.id.rb_home)
        mRbMe = findViewById(R.id.rb_me)
        mRbMessage = findViewById(R.id.rb_message)
        mRbHome = findViewById(R.id.rb_home)
        initData()
        setListener()
    }

    private fun initData() {
        val mAdapter = AdapterFragmentPager(supportFragmentManager)
        mViewPager!!.adapter = mAdapter
    }

    private fun setListener() {
        mRadioGroup!!.setOnCheckedChangeListener { _, checkedId ->
            if (checkedId == R.id.rb_home) {
                mViewPager!!.setCurrentItem(AdapterFragmentPager.PAGE_HOME, false)

            } else if (checkedId == R.id.rb_find) {
                mViewPager!!.setCurrentItem(AdapterFragmentPager.PAGE_FIND, false)

            } else if (checkedId == R.id.rb_add) {
                mViewPager!!.setCurrentItem(AdapterFragmentPager.PAGE_PUBLISH, false)

            } else if (checkedId == R.id.rb_message) {
                if (messageClicked()) {
                    return@setOnCheckedChangeListener
                }

            } else if (checkedId == R.id.rb_me) {
                if (meClicked()) {
                    return@setOnCheckedChangeListener
                }

            }
            preCheckedId = checkedId
        }

    }

    private fun meClicked(): Boolean {
        return if (isLogin) {
            mViewPager!!.setCurrentItem(AdapterFragmentPager.PAGE_ME, false)
            false
        } else {
            goToLogin()
            mRadioGroup!!.check(preCheckedId)
            true
        }
    }

    private fun messageClicked(): Boolean {
        return if (isLogin) {
            mViewPager!!.setCurrentItem(AdapterFragmentPager.PAGE_MESSAGE, false)
            false
        } else {
            goToLogin()
            mRadioGroup!!.check(preCheckedId)
            true
        }
    }

    private fun goToLogin() {}

    override fun onKeyDown(keyCode: Int, event: KeyEvent): Boolean {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.action == KeyEvent.ACTION_DOWN) {
            if (System.currentTimeMillis() - exitTime > 2000) {
                showToast("再按一次退出程序")
                exitTime = System.currentTimeMillis()
            } else {
                finish()
            }
            return true
        }
        return super.onKeyDown(keyCode, event)
    }

    companion object {

        fun start(context: Context) {
            context.startActivity(Intent(context, MainActivity::class.java))
        }
    }
}
