package com.example.cachingviewinfragment

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.fragment.*
import java.util.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        button.setOnClickListener {
            openFragment()
        }
    }

    private fun openFragment(){
        val tag = System.currentTimeMillis().toString()
        supportFragmentManager.beginTransaction()
            .replace(R.id.container,CachingFragment.newInstance(),tag)
            .addToBackStack(tag)
            .commit()
    }

    override fun onBackPressed() {
        supportFragmentManager.popBackStack()
    }


     class CachingFragment : Fragment() {
        companion object {
            fun newInstance(): CachingFragment {
                return CachingFragment()
            }
        }

        private val textView by lazy { view?.findViewById<TextView>(R.id.textView) }

        override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
            return inflater.inflate(R.layout.fragment, container, false)
        }

        override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
            super.onViewCreated(view, savedInstanceState)
            view.findViewById<Button>(R.id.button).setOnClickListener {
                textView?.text = Date().toGMTString()
            }
        }

    }
}
