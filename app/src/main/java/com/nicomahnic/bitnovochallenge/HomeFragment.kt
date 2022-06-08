package com.nicomahnic.bitnovochallenge

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.nicomahnic.bitnovochallenge.adapter.WindowAdapter
import com.nicomahnic.bitnovochallenge.databinding.FragmentHomeBinding
import com.nicomahnic.bitnovochallenge.models.Castle
import com.nicomahnic.bitnovochallenge.models.Visitor


class HomeFragment : Fragment(R.layout.fragment_home) {

    private lateinit var binding: FragmentHomeBinding
    private lateinit var adapter: WindowAdapter

    val castleWindows = Castle()
    var idVisitor: Int = 1

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentHomeBinding.bind(view)

        binding.btnPlay.setOnClickListener(btnPlayListener)
        binding.btnReset.setOnClickListener(btnResetListener)
        binding.btnStep.setOnClickListener(btnStepListener)

        binding.switchWindow.setOnClickListener(switchListener)

        castleWindows.createWindows(64)

        binding.rvWindows.layoutManager = GridLayoutManager(requireContext(), 4)
        adapter = WindowAdapter(castleWindows.windows)
        binding.rvWindows.adapter = adapter

    }

    private val switchListener = object : View.OnClickListener{
        override fun onClick(p0: View?) {
            if(binding.switchWindow.isChecked)
                binding.tvSwitch.text = "Windows start open"
            else
                binding.tvSwitch.text = "Windows start closed"
            
            castleWindows.createWindows(64, binding.switchWindow.isChecked)
            reset()

            binding.rvWindows.layoutManager = GridLayoutManager(requireContext(), 4)
            adapter = WindowAdapter(castleWindows.windows)
            binding.rvWindows.adapter = adapter
        }
    }

    private val btnStepListener = object : View.OnClickListener{
        override fun onClick(p0: View?) {
            if(idVisitor <= 64)
                castleWindows.shiftVisitor(Visitor(idVisitor++))
            else
                castleWindows.flushVisitor()

            adapter.notifyDataSetChanged()

            binding.tvRule1Value.text = castleWindows.getVisitorWhoWon().size.toString()
            binding.tvRule2Value.text = castleWindows.getVisitorWhoWon2().size.toString()
        }
    }

    private val btnResetListener = object : View.OnClickListener{
        override fun onClick(p0: View?) {
            reset()
        }
    }

    private fun reset(){
        castleWindows.reset()
        idVisitor = 1
        adapter.notifyDataSetChanged()

        binding.tvRule1Value.text = castleWindows.getVisitorWhoWon().size.toString()
        binding.tvRule2Value.text = castleWindows.getVisitorWhoWon2().size.toString()
    }

    private fun setButtonClickable(state: Boolean){
        binding.btnPlay.isClickable = state
        binding.btnReset.isClickable = state
        binding.btnStep.isClickable = state
    }

    private val btnPlayListener = object : View.OnClickListener{
        override fun onClick(p0: View?) {
            setButtonClickable(false)
            castleWindows.reset()
            idVisitor = 1

            for( idVisitor in (1..64) ) {
                castleWindows.shiftVisitor(Visitor(idVisitor))
            }
            for( idVisitor in (1..65) ) {
                castleWindows.flushVisitor()
            }

            Log.d("NM", "${castleWindows.getWindowStatus()}")
            Log.d("NM", "${castleWindows.getVisitorWhoWon()}")
            Log.d("NM", "${castleWindows.getVisitorWhoWon2()}")

            binding.tvRule1Value.text = castleWindows.getVisitorWhoWon().size.toString()
            binding.tvRule2Value.text = castleWindows.getVisitorWhoWon2().size.toString()

            adapter.notifyDataSetChanged()

            setButtonClickable(true)
        }

    }
}