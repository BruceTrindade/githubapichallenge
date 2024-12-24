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
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.bruceenterprises.githubapichallenge.R
import com.bruceenterprises.githubapichallenge.databinding.FragmentSecondBinding
import com.bruceenterprises.githubapichallenge.presentation.ui.repositoriesList.GithubRepositoriesAdapter
import com.bruceenterprises.githubapichallenge.presentation.ui.repositoriesList.GithubViewModel
import com.bruceenterprises.githubapichallenge.utils.ResultState
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class SecondFragment : Fragment() {

    private var _binding: FragmentSecondBinding? = null
    private val binding get() = _binding!!

    private val viewModel: GithubPullRequestViewModel by activityViewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        lifecycleScope.launch {
            viewModel.fetchPullRequest("octocat", "Hello-World")
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.repositories.collect { state ->
                    when (state) {
                        is ResultState.Loading -> {

                        }
                        is ResultState.Success -> {
                            val adapter = GithubPullRequestAdapter(state.data) {
                                findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment)
                            }
                            binding.recyclerViewPR.layoutManager = LinearLayoutManager(requireContext())
                            binding.recyclerViewPR.adapter = adapter
                        }
                        is ResultState.Error -> {
                        }
                    }
                }

            }
        }
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

//        binding.buttonSecond.setOnClickListener {
//            findNavController().navigate(R.id.action_SecondFragment_to_FirstFragment)
//        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
