package com.anelcc.coronavirustrack.ui

import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.anelcc.coronavirustrack.BR
import com.anelcc.coronavirustrack.CountryDetailsActivity
import com.anelcc.coronavirustrack.MainActivity
import com.anelcc.coronavirustrack.R
import com.anelcc.coronavirustrack.databinding.CountryItemBinding


public class CountryAdapter(var countryViewModel: MutableList<CountryViewModel>) : RecyclerView.Adapter<CountryAdapter.ViewHolder>(),
    HandlerClickListener {

    private val COUNTRY_KEY: String = "COUNTRY_KEY"
    val CODE_REQUEST = 100
    private var context: Context? = null

    override fun getItemViewType(position: Int) = R.layout.country_item
    //this method is returning the view for each item in the list
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        context = parent.context;
        val layoutInflater = LayoutInflater.from(context)
        val itemBinding = DataBindingUtil.inflate<CountryItemBinding>(layoutInflater, viewType, parent, false)
        return ViewHolder(itemBinding)
    }

    //this method is binding the data on the list
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(countryViewModel.get(position));
        holder.itemBinding.setHandlerClickListener(this)
       // holder.itemBinding(countryViewModel[position])
    }
    //this method is giving the size of the list
    override fun getItemCount(): Int {
        return countryViewModel.size
    }

    class ViewHolder(val itemBinding: CountryItemBinding) : RecyclerView.ViewHolder(itemBinding.root) {
        fun bind(data: Any) {
            itemBinding.setVariable(BR.countryViewModel, data)
            itemBinding.executePendingBindings()
        }
        /*fun bind(countryViewModel: CountryViewModel, handlerClickListener: HandlerClickListener) {
            itemBinding.countryViewModel = countryViewModel
            itemBinding.handlerClickListener = handlerClickListener
            itemBinding.executePendingBindings()
        }*/
    }

    public fun setData(newData: MutableList<CountryViewModel>) {
        countryViewModel = newData
        notifyDataSetChanged()
    }

    override fun itemClicked(viewModel: CountryViewModel?) {
        val intent = Intent(context, CountryDetailsActivity::class.java)
        intent.putExtra(COUNTRY_KEY, viewModel.toString())
        (context as MainActivity).startActivityForResult(intent, CODE_REQUEST)
    }
}


