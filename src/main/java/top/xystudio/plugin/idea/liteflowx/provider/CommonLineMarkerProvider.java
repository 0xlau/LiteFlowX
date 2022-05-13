package top.xystudio.plugin.idea.liteflowx.provider;

import com.intellij.codeInsight.daemon.RelatedItemLineMarkerInfo;
import com.intellij.codeInsight.daemon.RelatedItemLineMarkerProvider;
import com.intellij.codeInsight.navigation.NavigationGutterIconBuilder;
import com.intellij.codeInsight.navigation.NavigationGutterIconRenderer;
import com.intellij.psi.PsiElement;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;
import java.util.Collection;
import java.util.Optional;

public abstract class CommonLineMarkerProvider<T extends PsiElement> extends RelatedItemLineMarkerProvider {

    @Override
    protected void collectNavigationMarkers(@NotNull PsiElement element, @NotNull Collection<? super RelatedItemLineMarkerInfo<?>> result) {
        if (!isLiteflowFile(element) || !isTargetElement(element)){
            return;
        }
        Optional<? extends PsiElement[]> processResult = apply((T)element);
        if (processResult.isPresent()){
            PsiElement[] arrays = processResult.get();
            if (arrays.length == 0) {
                return;
            }
            RelatedItemLineMarkerInfo<PsiElement> lineMarkerInfo = NavigationGutterIconBuilder
                    .create(getIcon())
                    .setNamer(psiElement -> psiElement.getContainingFile().getVirtualFile().getName())
                    .setTooltipTitle(getTooltip(arrays[0], element))
                    .setTargets(arrays)
                    .createLineMarkerInfo(element);
            result.add(lineMarkerInfo);
        }
    }

    @NotNull
    public abstract String getTooltip(PsiElement array, @NotNull PsiElement element);

    /**
     * Apply optional.
     * @param element
     * @return the optional
     */
    public abstract Optional<? extends PsiElement[]> apply(@NotNull T element);

    /**
     * Is the Liteflow File.
     * @param element
     * @return the boolean
     */
    public abstract boolean isLiteflowFile(@NotNull PsiElement element);

    /**
     * Is the target element.
     * @param element
     * @return the boolean
     */
    public abstract boolean isTargetElement(@NotNull PsiElement element);

    /**
     * Gets Icon.
     * @return then icon
     */
    @Override
    @NotNull
    public abstract Icon getIcon();

    @Override
    public abstract String getName();
}
