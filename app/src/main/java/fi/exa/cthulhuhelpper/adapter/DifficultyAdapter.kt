package fi.exa.cthulhuhelpper.adapter

import android.content.Context
import android.widget.ArrayAdapter
import fi.exa.cthulhuhelpper.model.Difficulty

class DifficultyAdapter(context: Context, resource: Int): ArrayAdapter<String>(context, resource){
    private val dataset = Difficulty.values().map { it.name }.toMutableList()
    var hasCustom = false

    fun setCustomDifficulty(){
        if(!hasCustom){
            dataset.add("Custom")
            notifyDataSetChanged()
            hasCustom = true
        }

    }

    fun difficultySelected(position: Int): Boolean{
        if(position == 4){
            return false
        }
        if(hasCustom){
            dataset.removeAt(dataset.size-1)
            notifyDataSetChanged()
            hasCustom = false
        }
        return true
    }

    override fun getItem(position: Int): String {
        return dataset[position]
    }

    override fun getCount(): Int {
        return dataset.size
    }
}