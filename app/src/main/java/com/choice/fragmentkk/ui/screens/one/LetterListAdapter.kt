package com.choice.fragmentkk.ui.screens.one

import android.graphics.Path.Direction
import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.accessibility.AccessibilityNodeInfo
import androidx.annotation.RequiresApi
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.choice.clickergame.R
import com.choice.clickergame.databinding.ItemViewBinding

class LetterListAdapter : RecyclerView.Adapter<LetterListAdapter.LetterViewHolder>() {

    private val list = ('A').rangeTo('Z').toList()

    inner class LetterViewHolder(
        private val binding: ItemViewBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        val button = binding.buttonItem
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LetterViewHolder {
        LetterViewHolder(
            ItemViewBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        ).let { view ->
            view.itemView.accessibilityDelegate = Accessibility
            return view
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: LetterViewHolder, position: Int) {
        val item = list[position]
        holder.button.text = item.toString()

        holder.button.setOnClickListener {
            val action = LetterListFragmentDirections.actionLetterListFragmentToWordListFragment(
                letter = holder.button.text.toString()
            )

            it.findNavController().navigate(action)
        }
    }

    companion object Accessibility : View.AccessibilityDelegate() {
        override fun onInitializeAccessibilityNodeInfo(
            host: View,
            info: AccessibilityNodeInfo
        ) {
            super.onInitializeAccessibilityNodeInfo(host, info)
            val customString = host.context?.getString(R.string.look_up_words)
            val customClick =
                AccessibilityNodeInfo.AccessibilityAction(
                    AccessibilityNodeInfo.ACTION_CLICK,
                    customString
                )
            info.addAction(customClick)
        }
    }
}