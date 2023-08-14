package ddd.buyornot.postpage.bottomsheet

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import ddd.buyornot.findActivity

@Composable
@ExperimentalMaterial3Api
fun PostPageNavHost(
    navHostController: NavHostController,
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
                    navHostController.navigate(PostPageNavigationRoute.Archive.route)
                }
            )
        }
        composable(PostPageNavigationRoute.Post.route) {
            WritePostPagePostListBottomSheet(
                onDismissRequest = {
                    navHostController.popBackStack()
                },
                onClickClose = {
                    navHostController.navigate(PostPageNavigationRoute.Close.route)
                }
            )
        }

        composable(PostPageNavigationRoute.NewPost.route) {
            WritePostPageNewPostBottomSheet(
                onDismissRequest = {
                    navHostController.popBackStack()
                }
            )
        }

        composable(PostPageNavigationRoute.Archive.route) {
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
    object Archive : PostPageNavigationRoute

    object Post : PostPageNavigationRoute
    object NewPost : PostPageNavigationRoute

    object Close : PostPageNavigationRoute

}
