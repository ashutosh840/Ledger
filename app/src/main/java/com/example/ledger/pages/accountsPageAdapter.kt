package com.example.ledger.pages

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Adapter
import com.example.ledger.Database.account
import com.example.ledger.R

class accountsPageAdapter(val itemClickListener: onItemClickListener): Adapter<accountsPageAdapter.myViewHolder>() {

    var allAccount = emptyList<account>()

    class myViewHolder(itemview: View) : RecyclerView.ViewHolder(itemview) {

        val description = itemview.findViewById<TextView>(R.id.description_text_cardview)
        val amount = itemview.findViewById<TextView>(R.id.amount_text_cardview)
        val date = itemview.findViewById<TextView>(R.id.date_text_cardview)
        val type = itemView.findViewById<TextView>(R.id.type_text_cardview)

        fun bind(account: account,clickListener:onItemClickListener,itemview: View){

            description.text=account.description
            amount.text=account.amount.toString()
            date.text=account.date
            type.text=account.type.toString()

            itemView.setOnClickListener() {

                clickListener.onItemClick(account,itemview)
            }



        }


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): myViewHolder {
        val inflatedView =
            LayoutInflater.from(parent.context)
                .inflate(R.layout.account_recycler_view, parent, false)
        return myViewHolder(inflatedView)

    }

    override fun getItemCount(): Int {

        return allAccount.size
    }

    override fun onBindViewHolder(holder: accountsPageAdapter.myViewHolder, position: Int) {

        val current = allAccount[position]
        holder.description.text = current.description
        holder.amount.text = current.amount.toString()
        holder.date.text = current.date
        holder.type.text = current.type.toString()

        holder.bind(current,itemClickListener,holder.itemView)


    }

    fun setAccount(allAccount:List<account>){
        this.allAccount=allAccount
        notifyDataSetChanged()
    }

    interface onItemClickListener{

        fun onItemClick(account: account,view:View)

    }






}