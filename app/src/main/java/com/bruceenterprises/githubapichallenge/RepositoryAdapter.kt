package com.bruceenterprises.githubapichallenge

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bruceenterprises.githubapichallenge.databinding.RepositoriesItemBinding
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions

class RepositoryAdapter(private val repositoryList: List<Repository>) :
    RecyclerView.Adapter<RepositoryAdapter.RepositoryViewHolder>() {

    class RepositoryViewHolder(val binding: RepositoriesItemBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RepositoryViewHolder {
        val binding = RepositoriesItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false,
        )
        return RepositoryViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RepositoryViewHolder, position: Int) {
        val repo = repositoryList[position]

        holder.binding.repoName.text = repo.name
        holder.binding.repoOwner.text = repo.owner.login
        holder.binding.repoDescription.text = repo.description
        holder.binding.starsCount.text = "Stars: ${repo.stars}"
        holder.binding.forksCount.text = "forks: ${repo.forksCount}"
        Glide.with(holder.itemView.context)
            .load(repo.owner.avatarUrl)
            .apply(RequestOptions.circleCropTransform())
            .placeholder(R.drawable.github_repository_owner_icon)
            .error(R.drawable.github_repository_owner_icon)
            .into(holder.binding.repositoryOwnerImage)
    }

    override fun getItemCount(): Int {
        return repositoryList.size
    }
}
