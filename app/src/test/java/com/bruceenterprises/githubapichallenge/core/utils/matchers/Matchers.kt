package com.bruceenterprises.githubapichallenge.core.utils.matchers

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers
import com.bruceenterprises.githubapichallenge.R
import com.bruceenterprises.githubapichallenge.presentation.ui.repositoriesList.GithubRepositoriesAdapter
import org.hamcrest.Matcher
import org.hamcrest.TypeSafeMatcher

fun verifyRepositoryCard(
    recyclerViewId: Int,
    position: Int,
    targetViewId: Int,
    repoName: String,
    repoId: Int,
    repoOwnerId: Int,
    repoOwner: String,
    repoDescriptionId: Int,
    repoDescription: String,
    starsCountId: Int,
    starsCount: String,
    forksCountId: Int,
    forksCount: String,
) {

    Espresso.onView(ViewMatchers.withId(recyclerViewId))
        .perform(RecyclerViewActions.scrollToPosition<GithubRepositoriesAdapter.RepositoryViewHolder>(targetViewId))

    Espresso.onView(withRecyclerView(R.id.recyclerView).atPositionOnView(position, repoId))
        .check(ViewAssertions.matches(ViewMatchers.withText(repoName)))

    Espresso.onView(withRecyclerView(R.id.recyclerView).atPositionOnView(position, repoOwnerId))
        .check(ViewAssertions.matches(ViewMatchers.withText(repoOwner)))

    Espresso.onView(withRecyclerView(R.id.recyclerView).atPositionOnView(position, repoDescriptionId))
        .check(ViewAssertions.matches(ViewMatchers.withText(repoDescription)))

    Espresso.onView(withRecyclerView(R.id.recyclerView).atPositionOnView(position, starsCountId))
        .check(ViewAssertions.matches(ViewMatchers.withText(starsCount)))

    Espresso.onView(withRecyclerView(R.id.recyclerView).atPositionOnView(position, forksCountId))
        .check(ViewAssertions.matches(ViewMatchers.withText(forksCount)))
}


fun withRecyclerView(recyclerViewId: Int): RecyclerViewMatcher {
    return RecyclerViewMatcher(recyclerViewId)
}

class RecyclerViewMatcher(private val recyclerViewId: Int) {
    fun atPositionOnView(position: Int, targetViewId: Int): Matcher<View> {
        return object : TypeSafeMatcher<View>() {
            override fun describeTo(description: org.hamcrest.Description) {
                description.appendText("with id: $recyclerViewId at position: $position on view: $targetViewId")
            }

            override fun matchesSafely(item: View): Boolean {
                val recyclerView = item.rootView.findViewById<RecyclerView>(recyclerViewId)
                val viewHolder = recyclerView.findViewHolderForAdapterPosition(position)
                    ?: return false
                val targetView = viewHolder.itemView.findViewById<View>(targetViewId)
                return item == targetView
            }
        }
    }
}
