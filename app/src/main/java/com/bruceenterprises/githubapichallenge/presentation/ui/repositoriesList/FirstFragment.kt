package com.bruceenterprises.githubapichallenge.presentation.ui.repositoriesList

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import com.bruceenterprises.githubapichallenge.databinding.FragmentFirstBinding
import com.bruceenterprises.githubapichallenge.presentation.GithubViewModel
import com.bruceenterprises.githubapichallenge.presentation.ResultState
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class FirstFragment : Fragment() {

    private var _binding: FragmentFirstBinding? = null
    private val binding get() = _binding!!

    private val viewModel: GithubViewModel by activityViewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        lifecycleScope.launch {
            viewModel.fetchRepositories()
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                    viewModel.repositories.collect { state ->
                        when (state) {
                            is ResultState.Loading -> {

                            }
                            is ResultState.Success -> {
                                val adapter = GithubRepositoriesAdapter(state.data)
                                binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())
                                binding.recyclerView.adapter = adapter
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
        _binding = FragmentFirstBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
