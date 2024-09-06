package top.xystudio.plugin.idea.liteflowx.system.provider;

import com.intellij.openapi.project.Project;
import com.intellij.psi.PsiClass;
import com.intellij.psi.PsiFile;
import com.intellij.psi.PsiJavaFile;
import com.intellij.psi.PsiTreeChangeAdapter;
import com.intellij.psi.PsiTreeChangeEvent;
import org.jetbrains.annotations.NotNull;

public class JavaFileChangeListener extends PsiTreeChangeAdapter {

    private final Project project;

    public JavaFileChangeListener(Project project) {
        this.project = project;
    }

    @Override
    public void beforeChildAddition(@NotNull PsiTreeChangeEvent event) {
        handleEvent(event);
    }

    @Override
    public void beforeChildRemoval(@NotNull PsiTreeChangeEvent event) {
        handleEvent(event);
    }

    @Override
    public void childRemoved(@NotNull PsiTreeChangeEvent event) {
        handleEvent(event);
    }

    @Override
    public void childReplaced(@NotNull PsiTreeChangeEvent event) {
        handleEvent(event);
    }

    @Override
    public void childAdded(@NotNull PsiTreeChangeEvent event) {
        handleEvent(event);
    }

    @Override
    public void childrenChanged(@NotNull PsiTreeChangeEvent event) {
        handleEvent(event);
    }

    private void handleEvent(@NotNull PsiTreeChangeEvent event) {
        PsiFile file = event.getFile();
        if (file instanceof PsiJavaFile) {
            PsiJavaFile javaFile = (PsiJavaFile) file;
            for (PsiClass psiClass : javaFile.getClasses()) {
                JavaFileIconCache.clearCache(project, psiClass);
            }
        }
    }

}
