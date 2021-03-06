package com.example.gadsleaderboard.adapters

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.example.gadsleaderboard.R
import com.example.gadsleaderboard.ui.learning.LearningFragment
import com.example.gadsleaderboard.ui.skilliq.SkillIQFragment

private val TAB_TITLES = arrayOf(
    R.string.tab_text_learning,
    R.string.tab_text_skill_iq
)

class SectionsPagerAdapter(private val context: Context, fm: FragmentManager) :
    FragmentPagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

    override fun getItem(position: Int): Fragment {
        return when (position) {
            0 -> LearningFragment()
            1 -> SkillIQFragment()
            else -> LearningFragment()
        }
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return context.resources.getString(TAB_TITLES[position])
    }

    override fun getCount() = 2
}
