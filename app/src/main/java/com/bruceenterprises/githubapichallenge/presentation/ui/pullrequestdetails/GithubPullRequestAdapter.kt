package com.bruceenterprises.githubapichallenge.presentation.ui.pullrequestdetails

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.bruceenterprises.githubapichallenge.R
import com.bruceenterprises.githubapichallenge.databinding.RepositoriesItemBinding
import com.bruceenterprises.githubapichallenge.domain.models.PullRequest
import com.bruceenterprises.githubapichallenge.domain.models.Repository
import com.bruceenterprises.githubapichallenge.utils.formatToBrazilianDate
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions

class GithubPullRequestAdapter(
    private val pullRequestList: List<PullRequest>,
    private val onClick: () -> Unit
) :
    RecyclerView.Adapter<GithubPullRequestAdapter.RepositoryViewHolder>() {

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
        val repo = pullRequestList[position]

        with(holder.binding) {
            repoName.text = repo.title
            repoOwner.text = repo.authorName
            repoDescription.text = repo.body
            starsCount.text = repo.createdAt.formatToBrazilianDate()
            Glide.with(holder.itemView.context)
                .load(repo.authorAvatarUrl)
                .apply(RequestOptions.circleCropTransform())
                .placeholder(R.drawable.github_repository_owner_icon)
                .error(R.drawable.github_repository_owner_icon)
                .into(repositoryOwnerImage)
            itemContainer.setOnClickListener {
                onClick()
            }
        }
    }

    override fun getItemCount(): Int {
        return pullRequestList.size
    }
}
