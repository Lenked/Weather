package androidkotlin.training.city

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidkotlin.training.R
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView

class CityAdapter(private val cities: List<City>,
                  private val citiesListener: CityAdapter.CityItemListener)
    : RecyclerView.Adapter<CityAdapter.ViewHolder>(), View.OnClickListener {
    interface CityItemListener {
        fun onCitySelected(city: City)
        fun onCityDeleted(city: City)
    }

    class ViewHolder(itemView: View) :RecyclerView.ViewHolder(itemView) {
        val cardView = itemView.findViewById<CardView>(R.id.card_view)!!
        val cityNameView = itemView.findViewById<TextView>(R.id.name)!!
        val deleteView = itemView.findViewById<View>(R.id.delete)!!
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val viewItem = LayoutInflater.from(parent?.context)
            .inflate(R.layout.item_city, parent, false)
        return ViewHolder(viewItem)
    }

    override fun getItemCount(): Int = cities.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val city = cities[position]
        with(holder){
            cardView.tag = city
            cardView.setOnClickListener(this@CityAdapter)
            cityNameView.text = city.name
            deleteView.tag = city
            deleteView.setOnClickListener (this@CityAdapter)
        }
    }

    override fun onClick(view: View?) {
        when(view?.id) {
            R.id.card_view -> citiesListener.onCitySelected(view.tag as City)
            R.id.delete -> citiesListener.onCityDeleted(view.tag as City)
        }
    }
}