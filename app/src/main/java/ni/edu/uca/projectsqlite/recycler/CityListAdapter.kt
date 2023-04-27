package ni.edu.uca.projectsqlite.recycler

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import ni.edu.uca.projectsqlite.R
import ni.edu.uca.projectsqlite.database.City

class CityListAdapter : ListAdapter<City, CityListAdapter.CityViewHolder>(NamesComparator()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CityViewHolder {
        return CityViewHolder.create(parent)
    }

    override fun onBindViewHolder(holder: CityViewHolder, position: Int) {
        val current = getItem(position)
        holder.bind(current.name)
    }

    class CityViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val cityItemView: TextView = itemView.findViewById(R.id.textView)

        fun bind(text: String?) {
            cityItemView.text = text
        }

        companion object {
            fun create(parent: ViewGroup): CityViewHolder {
                val view: View = LayoutInflater.from(parent.context)
                    .inflate(R.layout.item_city, parent, false)
                return CityViewHolder(view)
            }
        }
    }

    class NamesComparator : DiffUtil.ItemCallback<City>() {
        override fun areItemsTheSame(oldItem: City, newItem: City): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(oldItem: City, newItem: City): Boolean {
            return oldItem.name == newItem.name
        }
    }
}