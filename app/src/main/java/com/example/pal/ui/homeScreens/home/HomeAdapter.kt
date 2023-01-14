package com.example.pal.ui.homeScreens.home

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.pal.data.models.Pet
import com.example.pal.databinding.HomeItemBinding

class HomeAdapter(private val callBack: PetsListener) : RecyclerView.Adapter<HomeAdapter.PetViewHolder>(){

    private val pets = ArrayList<Pet>()

    @SuppressLint("NotifyDataSetChanged")
    fun setPets(pets: Collection<Pet>) {
        this.pets.clear()
        this.pets.addAll(pets)
        notifyDataSetChanged()

    }

    interface PetsListener {
        fun onPetClicked(index: Int)
    }

    inner class PetViewHolder(private val binding: HomeItemBinding) :
        RecyclerView.ViewHolder(binding.root), View.OnClickListener {

        init {
            binding.root.setOnClickListener(this)
        }

        // set all the
        fun bind(pet: Pet) {
            binding.itemSex.text = pet.sex
            binding.itemAge.text = pet.age
            binding.itemBreed.text = pet.breed
            binding.itemName.text = pet.name

            Glide.with(binding.root).load(pet.pic).centerCrop().into(binding.itemPic)

        }

        override fun onClick(p0: View?) {
            callBack.onPetClicked(adapterPosition)
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PetViewHolder = PetViewHolder (
            HomeItemBinding.inflate(LayoutInflater.from(parent.context),
            parent,
            false
        )
    )

    override fun onBindViewHolder(holder: PetViewHolder, position: Int) =
        holder.bind(pets[position])

    override fun getItemCount() = pets.size

}