package com.anelcc.coronavirustrack.ui.country

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.anelcc.coronavirustrack.BR
import com.anelcc.coronavirustrack.MainActivity
import com.anelcc.coronavirustrack.R
import com.anelcc.coronavirustrack.databinding.CountryItemBinding
import com.anelcc.coronavirustrack.ui.countrydetail.CountryDetailsActivity
import com.squareup.picasso.Picasso

class CountryAdapter(var countryViewModel: MutableList<CountryViewModel>) : RecyclerView.Adapter<CountryAdapter.ViewHolder>(),
    HandlerClickListener {

    //move to Constants class
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
            Picasso.with(itemBinding.countryFlag.context).load(itemBinding.countryViewModel!!.flag).into(itemBinding.countryFlag)
            itemBinding.executePendingBindings()
        }
    }

    override fun itemClicked(viewModel: CountryViewModel?) {
        val countryDetailsIntent = Intent(context, CountryDetailsActivity::class.java)
        countryDetailsIntent.putExtra(COUNTRY_KEY, viewModel!!.country)
        (context as MainActivity).startActivity(countryDetailsIntent)
    }
}


