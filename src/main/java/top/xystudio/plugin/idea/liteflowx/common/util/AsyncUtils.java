package top.xystudio.plugin.idea.liteflowx.common.util;

import com.intellij.openapi.application.ModalityState;
import com.intellij.openapi.application.ReadAction;
import com.intellij.openapi.project.Project;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.concurrency.CancellablePromise;

import javax.annotation.PreDestroy;
import java.util.concurrent.*;
import java.util.function.Consumer;

public class AsyncUtils {

    private static final ExecutorService EXECUTOR = new ThreadPoolExecutor(
            1,
            5,
            5L,
            TimeUnit.SECONDS,
            new LinkedBlockingQueue<>()
    );

    private AsyncUtils(){}

    @NotNull
    public static <T> CancellablePromise<T> runRead(@NotNull Project project, @NotNull Callable<T> background, @NotNull Consumer<T> consumer) {
        return runRead(project, background, ModalityState.defaultModalityState(), consumer);
    }

    @NotNull
    public static <T> CancellablePromise<T> runRead(@NotNull Project project, @NotNull Callable<T> background, @NotNull ModalityState state, @NotNull Consumer<T> consumer) {
        return ReadAction.nonBlocking(background)
                .inSmartMode(project)
                .finishOnUiThread(state, consumer)
                .submit(EXECUTOR);
    }

    @PreDestroy
    public void dispose() {
        EXECUTOR.shutdown();
    }


}
