package ddd.buyornot.add_vote.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import dagger.hilt.android.AndroidEntryPoint
import ddd.buyornot.add_vote.viewmodel.AddNewVoteViewModel

@AndroidEntryPoint
class AddNewVoteActivity : ComponentActivity() {

    private val viewModel by viewModels<AddNewVoteViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            AddNewVoteScreen(viewModel)
        }
    }
}
