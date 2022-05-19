package top.xystudio.plugin.idea.liteflowx.provider;

import com.intellij.codeInsight.daemon.RelatedItemLineMarkerInfo;
import com.intellij.codeInsight.daemon.RelatedItemLineMarkerProvider;
import com.intellij.codeInsight.navigation.NavigationGutterIconBuilder;
import com.intellij.openapi.editor.markup.GutterIconRenderer;
import com.intellij.psi.PsiElement;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;
import java.util.Collection;
import java.util.Optional;

/**
 * 公共 LineMarkerProvider，抽象出若干个逻辑
 * @param <T> 主要捕捉的目标元素类型
 * @author Coder-XiaoYi
 */
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
                    .setAlignment(GutterIconRenderer.Alignment.CENTER)
                    .setTooltipTitle(getTooltip(arrays[0], element))
                    .setTargets(arrays)
                    .createLineMarkerInfo(element);
            result.add(lineMarkerInfo);
        }
    }

    @NotNull
    public abstract String getTooltip(PsiElement to, @NotNull PsiElement from);

    /**
     * 获取跳转至目标元素的重要方法
     * @param element
     * @return the optional
     */
    public abstract Optional<? extends PsiElement[]> apply(@NotNull T element);

    /**
     * 判断是不是LiteFlow文件
     * @param element
     * @return the boolean
     */
    public abstract boolean isLiteflowFile(@NotNull PsiElement element);

    /**
     * 判断是不是目标元素
     * @param element
     * @return the boolean
     */
    public abstract boolean isTargetElement(@NotNull PsiElement element);

    /**
     * 定义LineMarker的图标
     * @return then icon
     */
    @Override
    @NotNull
    public abstract Icon getIcon();

    @Override
    public abstract String getName();
}
