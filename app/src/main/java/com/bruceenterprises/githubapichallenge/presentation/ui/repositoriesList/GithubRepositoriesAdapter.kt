package com.bruceenterprises.githubapichallenge.presentation.ui.repositoriesList

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bruceenterprises.githubapichallenge.R
import com.bruceenterprises.githubapichallenge.databinding.RepositoriesItemBinding
import com.bruceenterprises.githubapichallenge.domain.models.Repository
import com.bruceenterprises.githubapichallenge.utils.shortDescription
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions

class GithubRepositoriesAdapter(
    private val onClick: ((String, String) -> Unit)? = null
) :
    PagingDataAdapter<Repository, GithubRepositoriesAdapter.RepositoryViewHolder>(DiffCallback) {

    class RepositoryViewHolder(val binding: RepositoriesItemBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RepositoryViewHolder {
        val binding = RepositoriesItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false,
        )
        return RepositoryViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RepositoryViewHolder, position: Int) {
        val repo = getItem(position)

        with(holder.binding) {
            repo?.let {
                repoName.text = repo.name
                repoName.contentDescription = "Nome do repositório: ${repo.name}"
                repoOwner.text = repo.ownerName
                repoOwner.contentDescription = "Nome do usuário: ${repo.ownerName}"
                repoDescription.text = repo.description
                repoDescription.contentDescription = "Descrição do repositório:${repo.description?.shortDescription()}"
                starsCount.text = "Estrelas: ${repo.stars}"
                forksCount.text = "Forks: ${repo.forksCount}"
                Glide.with(holder.itemView.context)
                    .load(repo.ownerAvatarUrl)
                    .apply(RequestOptions.circleCropTransform())
                    .placeholder(R.drawable.github_repository_owner_icon)
                    .error(R.drawable.github_repository_owner_icon)
                    .into(repositoryOwnerImage)
                itemContainer.setOnClickListener {
                    onClick?.let { click ->
                        click(
                            repo.ownerName,
                            repo.name
                        )
                    }
                }
                repoDivider.visibility = if (position == itemCount - 1) View.GONE else View.VISIBLE
            } ?: { itemContainer.visibility = View.GONE }
        }
    }

    object DiffCallback : DiffUtil.ItemCallback<Repository>() {
        override fun areItemsTheSame(oldItem: Repository, newItem: Repository): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Repository, newItem: Repository): Boolean {
            return oldItem == newItem
        }
    }
}
