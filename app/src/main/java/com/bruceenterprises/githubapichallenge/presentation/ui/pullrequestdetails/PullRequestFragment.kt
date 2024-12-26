package com.bruceenterprises.githubapichallenge.presentation.ui.pullrequestdetails

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.paging.LoadState
import androidx.recyclerview.widget.LinearLayoutManager
import com.bruceenterprises.githubapichallenge.R
import com.bruceenterprises.githubapichallenge.databinding.FragmentSecondBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class PullRequestFragment : Fragment() {

    private var _binding: FragmentSecondBinding? = null
    private val binding get() = _binding!!

    private val viewModel: GithubPullRequestViewModel by activityViewModels()

     private lateinit var adapter: GithubPullRequestAdapter

     override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
         adapter = GithubPullRequestAdapter(this.requireContext())
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentSecondBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val args = PullRequestFragmentArgs.fromBundle(requireArguments())

        viewModel.fetchPullRequests(args.ownerRepository, args.repository)

        repositoryInformation(args.ownerRepository, args.repository)

        binding.recyclerViewPR.layoutManager = LinearLayoutManager(requireContext())
        binding.recyclerViewPR.adapter = adapter

         viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.RESUMED) {
                adapter.loadStateFlow.collectLatest { loadStates ->
                    val isLoading = loadStates.refresh is LoadState.Loading
                    if (!isLoading) binding.prLoad.visibility = View.GONE
                }
            }
        }

        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.RESUMED) {
                viewModel.pullRequests.collectLatest { pagingData ->
                    adapter.submitData(pagingData)
                }
            }
        }

    }

    private fun repositoryInformation(ownerRepository: String, repository: String) {
        with(binding.repositoryInformations) {
            text = "${ownerRepository} / ${repository}"
            contentDescription = "${context.getString(R.string.repository)} ${repository}, ${context.getString(R.string.user)} $ownerRepository"
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding.recyclerViewPR.adapter = null
        _binding = null
    }
}
