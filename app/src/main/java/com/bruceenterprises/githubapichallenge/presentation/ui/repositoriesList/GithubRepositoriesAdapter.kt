package com.bruceenterprises.githubapichallenge.presentation.ui.repositoriesList

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.bruceenterprises.githubapichallenge.R
import com.bruceenterprises.githubapichallenge.databinding.RepositoriesItemBinding
import com.bruceenterprises.githubapichallenge.domain.models.Repository
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions

class GithubRepositoriesAdapter(
    private val repositoryList: List<Repository>,
    private val onClick: (String, String) -> Unit
) :
    RecyclerView.Adapter<GithubRepositoriesAdapter.RepositoryViewHolder>() {

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
        val repo = repositoryList[position]

        with(holder.binding) {
            repoName.text = repo.name
            repoOwner.text = repo.ownerName
            repoDescription.text = repo.description
            starsCount.text = "Stars: ${repo.stars}"
            forksCount.text = "forks: ${repo.forksCount}"
            Glide.with(holder.itemView.context)
                .load(repo.ownerAvatarUrl)
                .apply(RequestOptions.circleCropTransform())
                .placeholder(R.drawable.github_repository_owner_icon)
                .error(R.drawable.github_repository_owner_icon)
                .into(repositoryOwnerImage)
            itemContainer.setOnClickListener {
                onClick(
                    repo.ownerName,
                    repo.name
                )
            }
        }
    }

    override fun getItemCount(): Int {
        return repositoryList.size
    }
}
