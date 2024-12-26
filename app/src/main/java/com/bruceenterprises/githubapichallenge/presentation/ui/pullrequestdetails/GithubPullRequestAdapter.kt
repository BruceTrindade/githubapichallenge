package com.bruceenterprises.githubapichallenge.presentation.ui.pullrequestdetails

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bruceenterprises.githubapichallenge.R
import com.bruceenterprises.githubapichallenge.databinding.PullrequestItemBinding
import com.bruceenterprises.githubapichallenge.domain.models.PullRequest
import com.bruceenterprises.githubapichallenge.utils.formatToBrazilianDate
import com.bruceenterprises.githubapichallenge.utils.shortDescription
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions

class GithubPullRequestAdapter(private val context: Context) :
    PagingDataAdapter<PullRequest, GithubPullRequestAdapter.PullRequestViewHolder>(DiffCallback) {

    class PullRequestViewHolder(val binding: PullrequestItemBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PullRequestViewHolder {
        val binding = PullrequestItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return PullRequestViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PullRequestViewHolder, position: Int) {
        val pullRequest = getItem(position)
        with(holder.binding) {
            pullRequest?.let { repo ->
                prTitle.text = repo.title
                prTitle.contentDescription = context.getString(R.string.title_pr) + repo.title

                prOwner.text = repo.authorName

                if (repo.body.isNullOrBlank()) {
                    prDescription.visibility = View.GONE
                } else {

                    prDescription.visibility = View.VISIBLE
                    prDescription.text = repo.body
                    prDescription.contentDescription =
                        context.getString(R.string.description_pr) + repo.body.shortDescription()
                }

                prDate.text = repo.createdAt.formatToBrazilianDate()
                prDate.contentDescription =
                    context.getString(R.string.created_at) + repo.createdAt.formatToBrazilianDate()

                Glide.with(holder.itemView.context)
                    .load(repo.authorAvatarUrl)
                    .apply(RequestOptions.circleCropTransform())
                    .placeholder(R.drawable.github_repository_owner_icon)
                    .error(R.drawable.github_repository_owner_icon)
                    .into(prOwnerImage)

                prDivider.visibility = if (position == itemCount - 1) View.GONE else View.VISIBLE
            } ?: { itemContainer.visibility = View.GONE }
        }
    }

    object DiffCallback : DiffUtil.ItemCallback<PullRequest>() {
        override fun areItemsTheSame(oldItem: PullRequest, newItem: PullRequest): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: PullRequest, newItem: PullRequest): Boolean {
            return oldItem == newItem
        }
    }
}
