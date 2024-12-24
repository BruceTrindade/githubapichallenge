package com.bruceenterprises.githubapichallenge.presentation.ui.pullrequestdetails

import android.opengl.Visibility
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bruceenterprises.githubapichallenge.R
import com.bruceenterprises.githubapichallenge.databinding.PullrequestItemBinding
import com.bruceenterprises.githubapichallenge.domain.models.PullRequest
import com.bruceenterprises.githubapichallenge.utils.formatToBrazilianDate
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions

class GithubPullRequestAdapter(
    private val pullRequestList: List<PullRequest>,
) :
    RecyclerView.Adapter<GithubPullRequestAdapter.PullRequestViewHolder>() {

    class PullRequestViewHolder(val binding: PullrequestItemBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PullRequestViewHolder {
        val binding = PullrequestItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false,
        )
        return PullRequestViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PullRequestViewHolder, position: Int) {
        val repo = pullRequestList[position]

        with(holder.binding) {
            prTitle.text = repo.title
            prOwner.text = "por ${repo.authorName}"
            if(repo.body.isNullOrBlank()) prDescription.visibility = View.GONE
            else prDescription.text = repo.body
            prDate.text = repo.createdAt.formatToBrazilianDate()
            Glide.with(holder.itemView.context)
                .load(repo.authorAvatarUrl)
                .apply(RequestOptions.circleCropTransform())
                .placeholder(R.drawable.github_repository_owner_icon)
                .error(R.drawable.github_repository_owner_icon)
                .into(prOwnerImage)
            if(position == (pullRequestList.size - 1)) prDivider.visibility = View.GONE
        }

    }

    override fun getItemCount(): Int {
        return pullRequestList.size
    }
}
