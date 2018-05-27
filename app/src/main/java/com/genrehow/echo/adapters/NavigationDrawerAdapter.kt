package com.genrehow.echo.adapters

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView
import com.genrehow.echo.R
import com.genrehow.echo.activities.MainActivity
import com.genrehow.echo.fragments.FavouriteFragment
import com.genrehow.echo.fragments.MainScreenFragment

class NavigationDrawerAdapter(_contentList:ArrayList<String>,_getImages:IntArray,_context:Context): RecyclerView.Adapter<NavigationDrawerAdapter.NavViewHolder>(){

    var contentList: ArrayList<String>?=null
    var getImages: IntArray?=null
    var mContext: Context?=null
    init {
        this.contentList=contentList
        this.getImages=getImages
        this.mContext=mContext
    }

    override fun onBindViewHolder(holder: NavViewHolder, position: Int, payloads: MutableList<Any>) {
        holder?.icon_GET?.setBackgroundResource(getImages?.get(position) as Int)
        holder?.text_GET?.setText(contentList?.get(position))
        holder?.contentHolder?.setOnClickListener({
            if(position==0){
                val mainScreenFragment= MainScreenFragment()
                (mContext as MainActivity).supportFragmentManager
                        .beginTransaction()
                        .replace(R.id.details_fragment,mainScreenFragment)
                        .commit()
            }else if(position==1){
                val favouriteFragment= MainScreenFragment()
                (mContext as MainActivity).supportFragmentManager
                        .beginTransaction()
                        .replace(R.id.details_fragment,favouriteFragment)
                        .commit()
            }
            else if (position==2){
                val settingFragment= MainScreenFragment()
                (mContext as MainActivity).supportFragmentManager
                        .beginTransaction()
                        .replace(R.id.details_fragment,settingFragment)
                        .commit()
            }else {
                val aboutUsFragment= MainScreenFragment()
                (mContext as MainActivity).supportFragmentManager
                        .beginTransaction()
                        .replace(R.id.details_fragment,aboutUsFragment)
                        .commit()
            }
        })
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NavViewHolder {
        var itemView = LayoutInflater.from(parent?.context)
                .inflate(R.layout.row_custom_navigationdrawer,parent,false)
        val returnThis = NavViewHolder(itemView)
        return returnThis
    }

    override fun getItemCount(): Int {
        return (contentList as ArrayList).size
    }

    override fun onBindViewHolder(holder: NavViewHolder, position: Int) {

    }

    class NavViewHolder(itemView: View?) : RecyclerView.ViewHolder(itemView){
        var icon_GET: ImageView?=null
        var text_GET: TextView?=null
        var contentHolder: RelativeLayout?=null
        init {
            icon_GET=itemView?.findViewById(R.id.icon_navdrawer)
            text_GET=itemView?.findViewById(R.id.text_navdrawer)
            contentHolder=itemView?.findViewById(R.id.navdrawer_item_content_holder)
        }
    }
}