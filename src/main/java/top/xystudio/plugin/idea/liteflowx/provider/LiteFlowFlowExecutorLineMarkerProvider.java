package top.xystudio.plugin.idea.liteflowx.provider;

import com.intellij.codeInsight.daemon.RelatedItemLineMarkerInfo;
import com.intellij.codeInsight.daemon.RelatedItemLineMarkerProvider;
import com.intellij.codeInsight.navigation.NavigationGutterIconBuilder;
import com.intellij.openapi.project.Project;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiExpression;
import com.intellij.psi.PsiLiteralExpression;
import com.intellij.psi.impl.source.tree.java.PsiLiteralExpressionImpl;
import com.intellij.psi.impl.source.tree.java.PsiMethodCallExpressionImpl;
import icons.LiteFlowIcons;
import org.jetbrains.annotations.NotNull;
import top.xystudio.plugin.idea.liteflowx.common.constant.Clazz;
import top.xystudio.plugin.idea.liteflowx.common.metadata.LiteFlowChainMetadata;
import top.xystudio.plugin.idea.liteflowx.service.JavaService;
import top.xystudio.plugin.idea.liteflowx.service.LiteFlowChainService;

import java.util.Arrays;
import java.util.Collection;
import java.util.Objects;

public class LiteFlowFlowExecutorLineMarkerProvider extends RelatedItemLineMarkerProvider {

    private final String[] methodKeywords = {"invoke", "invokeInAsync", "invoke2Resp", "invoke2RespInAsync", "execute2Resp", "execute2RespWithRid", "execute2Future", "execute2FutureWithRid", "execute"};

    @Override
    protected void collectNavigationMarkers(@NotNull PsiElement element,
                                            @NotNull Collection<? super RelatedItemLineMarkerInfo<?>> result) {
        Project project = element.getProject();
        JavaService javaService = project.getService(JavaService.class);
        LiteFlowChainService liteFlowChainService = project.getService(LiteFlowChainService.class);

        if (!(element instanceof PsiMethodCallExpressionImpl psiMethodCallExpression)) {
            return;
        }

        try {
            String canonicalText = Objects.requireNonNull(Objects.requireNonNull(psiMethodCallExpression.getMethodExpression().getQualifierExpression()).getType()).getCanonicalText();
            String referenceName = psiMethodCallExpression.getMethodExpression().getReferenceName();

            if (!canonicalText.equals(Clazz.FlowExecutor)) {
                return;
            }
            if (!Arrays.stream(methodKeywords).toList().contains(referenceName)) {
                return;
            }
            if (psiMethodCallExpression.getArgumentList().getExpressionCount() == 0) {
                return;
            }

            String chainId;
            PsiExpression expression = psiMethodCallExpression.getArgumentList().getExpressions()[0];
            if (expression instanceof PsiLiteralExpressionImpl) {
                chainId = expression.getText().replaceAll("\"", "");
            } else {
                return;
            }

            LiteFlowChainMetadata chainMetadata = liteFlowChainService.getLiteFlowChainMetadataById(chainId);
            if (chainMetadata == null) {
                return;
            }

            NavigationGutterIconBuilder<PsiElement> builder =
                    NavigationGutterIconBuilder.create(LiteFlowIcons.FLOWEXECUTE_ICON).setTooltipTitle(chainMetadata.getPsiTarget().getContainingFile().getName()).setTarget(chainMetadata.getPsiTarget());
            result.add(builder.createLineMarkerInfo(element));
        }catch (NullPointerException ignored){

        }
    }

}
