package com.choice.fragmentkk.ui.screens.one

import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.choice.clickergame.R
import com.choice.clickergame.databinding.FragmentLetterlistBinding

class LetterListFragment : Fragment() {

    private var isLinearLayoutManager = true
    lateinit var recyclerView: RecyclerView

    private val bindingGame by lazy {
        FragmentLetterlistBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        return bindingGame.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        recyclerView = bindingGame.recyclerView
        chooseLayout()
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.layout_menu, menu)

        setIcon(menu.findItem(R.id.action_switch_layout))
    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when(item.itemId){
            R.id.action_switch_layout -> {
                isLinearLayoutManager = !isLinearLayoutManager
                chooseLayout()
                setIcon(item)
                true
            }

            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun chooseLayout() {
        recyclerView.apply {
            layoutManager = if (isLinearLayoutManager) {
                LinearLayoutManager(context)
            } else {
                GridLayoutManager(context, 4)
            }
            adapter = LetterListAdapter()
        }
    }

    private fun setIcon(item: MenuItem?){
        item?.let {
            item.icon =
                if(isLinearLayoutManager){
                    ContextCompat.getDrawable(requireContext(), R.drawable.ic_grid_layout)
                }else{
                    ContextCompat.getDrawable(requireContext(), R.drawable.ic_linear_layout)
                }
        }
    }

    companion object {
        fun newInstance() = LetterListFragment()
    }
}