package ddd.buyornot.postpage.ui.bottomsheet

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import ddd.buyornot.findActivity
import ddd.buyornot.postpage.viewmodel.ShareViewModel

@Composable
@ExperimentalMaterial3Api
fun PostPageNavHost(
    navHostController: NavHostController,
    viewModel: ShareViewModel
) {
    NavHost(
        navController = navHostController,
        startDestination = PostPageNavigationRoute.Default.route
    ) {
        composable(PostPageNavigationRoute.Default.route) {
            val context = LocalContext.current

            WritePostPageDefaultBottomSheet(
                onDismissRequest = {
                    context.findActivity().finish()
                },
                onClickClose = {
                    navHostController.navigate(PostPageNavigationRoute.Close.route)
                },
                onClickPoll = {
                    navHostController.navigate(PostPageNavigationRoute.Post.route)
                },
                onClickArchive = {
                    navHostController.navigate(PostPageNavigationRoute.ArchiveDone.route)
                }
            )
        }
        composable(PostPageNavigationRoute.Post.route) {
            WritePostPagePostListBottomSheet(
                viewModel = viewModel,
                onDismissRequest = {
                    navHostController.popBackStack()
                },
                onClickClose = {
                    navHostController.navigate(PostPageNavigationRoute.Close.route)
                },
                onClickNewPost = {
                    navHostController.navigate(PostPageNavigationRoute.NewPost.route)
                },
                onClickAddItem = {
                    navHostController.navigate("${PostPageNavigationRoute.Description.route}/투표 올리기")

                }
            )
        }

        composable(PostPageNavigationRoute.NewPost.route) {
            WritePostPageNewPostBottomSheet(
                viewModel = viewModel,
                onDismissRequest = {
                    navHostController.popBackStack()
                },
                onClickNext = {
                    navHostController.navigate("${PostPageNavigationRoute.Description.route}/새 투표 만들기")
                }
            )
        }

        composable(
            route = "${PostPageNavigationRoute.Description.route}/{title}",
            arguments = listOf(
                navArgument("title") {
                    type = NavType.StringType
                }
            )
        ) {
            val title = it.arguments?.getString("title") ?: "새 투표 만들기"
            PostPageContentBottomSheet(
                title = title,
                viewModel = viewModel,
                onDismissRequest = {
                    navHostController.popBackStack()
                },
                onClickNext = {
                    navHostController.navigate("${PostPageNavigationRoute.PostDone.route}/${if (title == "새 투표 만들기") "새로운 투표를 만들었어요!" else "투표가 완성됐어요!"}}")
                }
            )
        }

        composable(
            route = "${PostPageNavigationRoute.PostDone.route}/{title}",
            arguments = listOf(
                navArgument("title") {
                    type = NavType.StringType
                }
            )
        ) {
            val context = LocalContext.current

            WritePostPageDoneBottomSheet(
                title = it.arguments?.getString("title") ?: "새로운 투표를 만들었어요!",
                onDismissRequest = {
                    context.findActivity().finish()
                }
            )
        }

        composable(PostPageNavigationRoute.ArchiveDone.route) {
            ArchivingSuccessBottomSheet(
                onDismissRequest = {
                    navHostController.popBackStack()
                }
            )
        }

        composable(PostPageNavigationRoute.Close.route) {
            WritePostPageSaveAlertBottomSheet(
                onDismissRequest = {
                    navHostController.popBackStack()
                }
            )
        }
    }
}

sealed interface PostPageNavigationRoute {
    val route: String
        get() = this::class.java.name

    object Default : PostPageNavigationRoute
    object ArchiveDone : PostPageNavigationRoute

    object Post : PostPageNavigationRoute
    object NewPost : PostPageNavigationRoute
    object Description : PostPageNavigationRoute
    object PostDone : PostPageNavigationRoute

    object Close : PostPageNavigationRoute

}
