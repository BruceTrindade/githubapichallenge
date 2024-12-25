package com.bruceenterprises.githubapichallenge.presentation.ui.pullrequestdetails

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.bruceenterprises.githubapichallenge.databinding.FragmentSecondBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class SecondFragment : Fragment() {

    private var _binding: FragmentSecondBinding? = null
    private val binding get() = _binding!!

    private val viewModel: GithubPullRequestViewModel by activityViewModels()

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

        val args = SecondFragmentArgs.fromBundle(requireArguments())

        lifecycleScope.launch {
            viewModel.getPagedPullRequests(args.ownerRepository, args.repository)
                .collectLatest { pagingData ->
                    val adapter = GithubPullRequestAdapter()
                    binding.recyclerViewPR.layoutManager = LinearLayoutManager(requireContext())
                    binding.recyclerViewPR.adapter = adapter
                    adapter.submitData(pagingData)
                }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
