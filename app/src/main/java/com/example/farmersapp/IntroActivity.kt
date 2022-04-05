package com.example.farmersapp

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager.widget.ViewPager
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayout.BaseOnTabSelectedListener

class IntroActivity : AppCompatActivity() {
    private lateinit var screenPager: ViewPager
    var introViewPagerAdapter: IntroViewPagerAdapter? = null
    private lateinit var tabIndicator: TabLayout
    private lateinit var btnNext: Button
    var position = 0
    private lateinit var btnGetStarted: Button
    var btnAnim: Animation? = null
    private lateinit var tvSkip: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        // make the activity on full screen
//        requestWindowFeature(Window.FEATURE_NO_TITLE)
//        window.setFlags(
//            WindowManager.LayoutParams.FLAG_FULLSCREEN,
//            WindowManager.LayoutParams.FLAG_FULLSCREEN
//        )
        setContentView(R.layout.activity_intro)

        // ini views
        btnNext = findViewById(R.id.btn_next)
        btnGetStarted = findViewById(R.id.btn_get_started)
        tabIndicator = findViewById(R.id.tab_indicator)
        btnAnim = AnimationUtils.loadAnimation(applicationContext, R.anim.button_animation)
        tvSkip = findViewById(R.id.tv_skip)


        // when this activity is about to be launch we need to check if its openened before or not
        if (restorePrefData()) {
            val mainActivity = Intent(applicationContext, MainActivity::class.java)
            startActivity(mainActivity)
            finish()
        }


        // fill list screen
        val mList: MutableList<ScreenItem> = ArrayList()

        //changed here
        mList.add(
            ScreenItem(
                "Fresh Fruits",
                "Sweets and pie will make you cry, Fruits and Vegetables give you the edge",
                R.drawable.fruitsintro
            )
        )
        mList.add(
            ScreenItem(
                "Fresh Vegetables",
                "Have fruits & vegetables if you want to lead a fruitful life",
                R.drawable.vegintro
            )
        )
        mList.add(ScreenItem("Fast Delivery", "Extraordinary Service.", R.drawable.img2))


        // setup viewpager
        screenPager = findViewById(R.id.screen_viewpager)
        introViewPagerAdapter = IntroViewPagerAdapter(this, mList)
        screenPager.adapter = introViewPagerAdapter

        // setup tablayout with viewpager
        tabIndicator.setupWithViewPager(screenPager)

        // Get Started button click listener
        btnGetStarted.setOnClickListener(View.OnClickListener {
            val mainActivity = Intent(applicationContext, MainActivity::class.java)
            startActivity(mainActivity)
            savePrefsData()
            finish()
        })


        // next button click Listner
        btnNext.setOnClickListener(View.OnClickListener {
            position = screenPager.currentItem
            if (position < mList.size) {
                position++
                screenPager.setCurrentItem(position)
            }
            if (position == mList.size - 1) { // when we rech to the last screen

                // TODO : show the GETSTARTED Button and hide the indicator and the next button
                loaddLastScreen()
            }
        })


        // skip button click listener
        tvSkip.setOnClickListener(View.OnClickListener { screenPager.currentItem = mList.size })


        // tablayout add change listener
        tabIndicator.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener{
            override fun onTabSelected(tab: TabLayout.Tab?) {
                if (tab!!.position == mList.size - 1) {
                    loaddLastScreen()
                }
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
                TODO("Not yet implemented")
            }

            override fun onTabReselected(tab: TabLayout.Tab?) {
                TODO("Not yet implemented")
            }

        })
}

private fun savePrefsData() {
    val pref = applicationContext.getSharedPreferences("myPrefs", MODE_PRIVATE)
    val editor = pref.edit()
    editor.putBoolean("isIntroOpnend", true)
    editor.apply()
}

private fun restorePrefData(): Boolean {
    val pref = applicationContext.getSharedPreferences(
        "myPrefs",
        MODE_PRIVATE
    )
    return pref.getBoolean("isIntroOpnend", false)
}

// show the GETSTARTED Button and hide the indicator and the next button
private fun loaddLastScreen() {
    btnNext.visibility = View.INVISIBLE
    btnGetStarted!!.visibility = View.VISIBLE
    tvSkip!!.visibility = View.INVISIBLE
    tabIndicator!!.visibility = View.INVISIBLE
    // TODO : ADD an animation the getstarted button
    // setup animation
    btnGetStarted!!.animation = btnAnim
}
}