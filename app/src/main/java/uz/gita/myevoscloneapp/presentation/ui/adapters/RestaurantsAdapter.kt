package uz.gita.myevoscloneapp.presentation.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import uz.gita.myevoscloneapp.R
import uz.gita.myevoscloneapp.model.data.LocationData

class RestaurantsAdapter : ListAdapter<LocationData, RestaurantsAdapter.VH>(DiffItem) {

    private var clickListener: ((Int, LocationData) -> Unit)? = null
    fun setClickListener(block: (Int, LocationData) -> Unit) {
        clickListener = block
    }

    object DiffItem : DiffUtil.ItemCallback<LocationData>() {
        override fun areItemsTheSame(oldItem: LocationData, newItem: LocationData): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: LocationData, newItem: LocationData): Boolean {
            return oldItem == newItem
        }
    }

    inner class VH(view: View) : RecyclerView.ViewHolder(view) {
        private val placeName: TextView = view.findViewById(R.id.txt_place_name)
        private val placeAddress: TextView = view.findViewById(R.id.txt_place_address)
        private val workingTime: TextView = view.findViewById(R.id.txt_working_time)

        init {
            itemView.setOnClickListener {
                clickListener?.invoke(absoluteAdapterPosition, getItem(absoluteAdapterPosition))
            }
        }

        fun bind() {
            val location = getItem(absoluteAdapterPosition)
            placeName.text = location.name
            placeAddress.text = location.description
            workingTime.text = location.time
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH =
        VH(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_location, parent, false)
        )

    override fun onBindViewHolder(holder: VH, position: Int) = holder.bind()

}