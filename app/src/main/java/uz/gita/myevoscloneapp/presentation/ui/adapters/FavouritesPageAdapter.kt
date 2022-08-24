package uz.gita.myevoscloneapp.presentation.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.widget.AppCompatButton
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import uz.gita.myevoscloneapp.R
import uz.gita.myevoscloneapp.model.data.FoodData
import uz.gita.myevoscloneapp.utils.gone
import uz.gita.myevoscloneapp.utils.visible

class FavouritesPageAdapter : ListAdapter<FoodData, FavouritesPageAdapter.VH>(DiffItem) {
    private var clickListener: ((Int, FoodData) -> Unit)? = null
    fun setClickListener(block: (Int, FoodData) -> Unit) {
        clickListener = block
    }

    private var countChangedListener: ((FoodData, Int) -> Unit)? = null
    fun setCountChangedListener(block: (FoodData, Int) -> Unit) {
        countChangedListener = block
    }

    object DiffItem : DiffUtil.ItemCallback<FoodData>() {
        override fun areItemsTheSame(oldItem: FoodData, newItem: FoodData): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: FoodData, newItem: FoodData): Boolean {
            return oldItem == newItem
        }
    }

    inner class VH(view: View) : RecyclerView.ViewHolder(view) {
        private val imageFood: ImageView = view.findViewById(R.id.img_favourite_food)
        private val nameFood: TextView = view.findViewById(R.id.name_favourite_food)
        private val priceFood: TextView = view.findViewById(R.id.price_favourite_food)
        private val textCount: TextView = view.findViewById(R.id.txtCountInFavourite)
        private val btnAddFood: AppCompatButton = view.findViewById(R.id.btnAddFoodInFavourite)
        private val layoutIncDec: FrameLayout = itemView.findViewById(R.id.layoutIncDecInFavourite)
        private val btnInc: ImageButton = view.findViewById(R.id.btnIncInFavourite)
        private val btnDec: ImageButton = view.findViewById(R.id.btnDecInFavourite)
        private val btnDelete: ImageButton = view.findViewById(R.id.btnDelete)

        init {
            itemView.setOnClickListener {
                clickListener?.invoke(absoluteAdapterPosition, getItem(absoluteAdapterPosition))
            }
        }

        fun bind() {
            val food = getItem(absoluteAdapterPosition)
            Glide.with(imageFood.context)
                .load(food.imageURL)
                .placeholder(R.drawable.ic_place_holder)
                .error(R.drawable.ic_error)
                .into(imageFood)

            textCount.text = "${food.count}x"
            when (food.count) {
                0 -> {
                    btnAddFood.visible()
                    layoutIncDec.gone()
                }
                1 -> {
                    btnAddFood.gone()
                    layoutIncDec.visible()
                    btnDec.gone()
                    btnDelete.visible()
                }
                else -> {
                    btnAddFood.gone()
                    layoutIncDec.visible()
                    btnDec.visible()
                    btnDelete.gone()
                }
            }

            nameFood.text = food.name
            priceFood.text = "${food.cost} so'm"

            btnDelete.setOnClickListener {
                textCount.text = "${food.count - 1}x"
                food.count -= 1
                btnAddFood.visible()
                layoutIncDec.gone()
                countChangedListener?.invoke(food, food.count)
            }

            btnAddFood.setOnClickListener {
                it.gone()
                textCount.text = "${food.count + 1}x"
                food.count += 1
                layoutIncDec.visible()
                btnDelete.visible()
                btnDec.gone()
                countChangedListener?.invoke(food, food.count)
            }
            btnInc.setOnClickListener {
                textCount.text = "${food.count + 1}x"
                food.count += 1
                btnDelete.gone()
                btnDec.visible()
                countChangedListener?.invoke(food, food.count)
            }
            btnDec.setOnClickListener {
                if (food.count != 0) {
                    textCount.text = "${food.count - 1}x"
                    food.count -= 1
                    if (food.count == 1) {
                        btnDelete.visible()
                        btnDec.gone()
                    } else if (food.count == 0) {
                        btnAddFood.visible()
                        layoutIncDec.gone()
                    }
                    countChangedListener?.invoke(food, food.count)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH =
        VH(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_favourite_foods, parent, false)
        )

    override fun onBindViewHolder(holder: VH, position: Int) = holder.bind()
}