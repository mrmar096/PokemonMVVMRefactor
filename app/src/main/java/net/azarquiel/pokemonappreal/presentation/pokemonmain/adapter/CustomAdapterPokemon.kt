package net.azarquiel.pokemonappreal.presentation.pokemonmain.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import net.azarquiel.pokemonappreal.databinding.RowpokemonBinding
import net.azarquiel.pokemonappreal.presentation.common.model.PokemonModel
import net.azarquiel.pokemonappreal.presentation.common.model.PokemonTypes


/**
 * Created by pacopulido on 02/11/17.
 */
class CustomAdapterPokemon(val context: Context,
                           val layout: Int,
                           val dataList: ArrayList<PokemonModel>,
                           val userClicked:(Long) ->Unit
): RecyclerView.Adapter<CustomAdapterPokemon.ViewHolder>(),Filterable {


    private var tempItems = java.util.ArrayList(dataList)
    private lateinit var originalData : List<PokemonModel>
    private var suggestions: MutableList<PokemonModel> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val itemBinding : RowpokemonBinding = RowpokemonBinding.inflate(layoutInflater, parent, false)
        return ViewHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = dataList[position]
        holder.bind(item)
        holder.binding.root.setOnClickListener {userClicked(item.id) }
    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    inner class ViewHolder(val binding:RowpokemonBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(dataItem: PokemonModel){
            binding.item = dataItem
            binding.executePendingBindings()
        }
    }

    override fun getFilter(): Filter {
        return nameFilter
    }

    private val nameFilter = object : Filter() {
        override fun convertResultToString(resultValue: Any): CharSequence {
            return (resultValue as PokemonModel).name
        }

        override fun performFiltering(constraint: CharSequence?): FilterResults {
            val filterResults = FilterResults()
            if (constraint != null){
                suggestions.clear()
                tempItems.forEach {
                    val description = it.name.toLowerCase()
                    val query = constraint.toString().toLowerCase()
                    if (description.contains(query)) {
                        suggestions.add(it)
                    }
                }
                filterResults.values = suggestions
                filterResults.count = suggestions.size
            }
            return filterResults

        }
        override fun publishResults(constraint: CharSequence?,
                                    results: FilterResults?) {
            if(results != null && results.count > 0) {
                val values  : MutableList<PokemonModel> = results.values as MutableList<PokemonModel>
                dataList.clear()
                dataList += values
                notifyDataSetChanged()
            }

        }

    }

    fun set(items:ArrayList<PokemonModel>){
        this.originalData = items
        updateList(items)
    }

    fun filterTypes(typePokemon: PokemonTypes) {
        val tempData = this.originalData
        updateList(tempData.filter {it.types.contains(typePokemon)}.toCollection(arrayListOf()))
    }

    private fun updateList(list: List<PokemonModel>) {
        this.dataList.clear()
        this.dataList.addAll(list)
        this.tempItems = ArrayList(dataList)
        notifyDataSetChanged()
    }


    fun refresh(){
        updateList(originalData)
    }

    fun filterByIds(list: ArrayList<Long>) {
        set(this.dataList.filter { list.contains(it.id) }.toCollection(arrayListOf()))
    }


}