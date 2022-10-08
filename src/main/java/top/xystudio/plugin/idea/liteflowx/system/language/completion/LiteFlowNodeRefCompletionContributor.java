package top.xystudio.plugin.idea.liteflowx.system.language.completion;

import com.intellij.codeInsight.completion.*;
import com.intellij.codeInsight.lookup.LookupElementBuilder;
import com.intellij.icons.AllIcons;
import com.intellij.openapi.project.Project;
import com.intellij.patterns.PlatformPatterns;
import com.intellij.psi.PsiClass;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiMethod;
import com.intellij.psi.impl.source.xml.XmlTagImpl;
import com.intellij.psi.util.PsiTreeUtil;
import com.intellij.psi.xml.XmlTag;
import com.intellij.util.ProcessingContext;
import icons.LiteFlowIcons;
import org.jetbrains.annotations.NotNull;
import top.xystudio.plugin.idea.liteflowx.service.LiteFlowService;
import top.xystudio.plugin.idea.liteflowx.system.language.LiteFlowLanguage;
import top.xystudio.plugin.idea.liteflowx.system.language.psi.LiteFlowCodes;
import top.xystudio.plugin.idea.liteflowx.system.language.psi.LiteFlowLiteFlowNodeRef;
import top.xystudio.plugin.idea.liteflowx.system.language.psi.LiteFlowStatement;

public class LiteFlowNodeRefCompletionContributor extends CompletionContributor {

    public LiteFlowNodeRefCompletionContributor(){

        extend(CompletionType.BASIC, PlatformPatterns.psiElement().withLanguage(LiteFlowLanguage.INSTANCE),
            new CompletionProvider<>() {
                @Override
                protected void addCompletions(@NotNull CompletionParameters parameters, @NotNull ProcessingContext context, @NotNull CompletionResultSet resultSet) {

                    PsiElement position = parameters.getPosition();
                    Project project = position.getProject();

                    LiteFlowService liteFlowService = LiteFlowService.getInstance(project);

                    if (position.getParent() instanceof LiteFlowLiteFlowNodeRef){

                        /** 搜索局部变量 **/
                        LiteFlowCodes liteFlowCodes = PsiTreeUtil.getChildOfType(position.getContainingFile(), LiteFlowCodes.class);
                        for (LiteFlowStatement liteFlowStatement : liteFlowCodes.getStatementList()) {
                            try{
                                resultSet.addElement(
                                        LookupElementBuilder.create(liteFlowStatement.getAssignStatement().getAssignExpress().getRefExpress().getTypeRef().getText())
                                                .withIcon(AllIcons.General.InlineVariables)
                                                .withTypeText("Local Variable")
                                                .bold()
                                );
                            }catch (Exception e){
                                continue;
                            }
                        }

                        /** 搜索全部LiteFlowComponent */
                        for (PsiElement psiElement : liteFlowService.findAllLiteFlowComponent()) {
                            if (psiElement instanceof PsiClass){
                                String componentName = liteFlowService.getLiteFlowComponentNameByPsiClass((PsiClass) psiElement);
                                if (componentName != null){
                                    resultSet.addElement(
                                            JavaLookupElementBuilder.forClass((PsiClass) psiElement, componentName)
                                                    .withIcon(LiteFlowIcons.COMMON_COMPONENT_ICON)
                                                    .withTypeText("Component")
                                                    .bold()
                                    );
                                }
                            } else if (psiElement instanceof PsiMethod) {
                                String componentName = liteFlowService.getLiteFlowComponentNameByPsiMethod((PsiMethod) psiElement);
                                if (componentName != null){
                                    resultSet.addElement(
                                            LookupElementBuilder.create(componentName)
                                                    .withIcon(LiteFlowIcons.COMMON_COMPONENT_ICON)
                                                    .withTypeText("Component")
                                                    .bold()
                                    );
                                }
                            } else if (psiElement instanceof XmlTag) {
                                String componentName = liteFlowService.getLiteFlowComponentNameByXmlTag((XmlTag) psiElement);
                                if (componentName != null){
                                    resultSet.addElement(
                                            LookupElementBuilder.create(componentName)
                                                    .withIcon(LiteFlowIcons.COMMON_COMPONENT_ICON)
                                                    .withTypeText("Script-Component")
                                                    .bold()
                                    );
                                }
                            }
                        }
                        /** 搜索全部LiteFlowChain */
                        for (PsiElement element : liteFlowService.findAllLiteFlowChain()) {
                            String text = null;
                            if (element instanceof XmlTag) {
                                text = ((XmlTagImpl) element).getAttributeValue("name");
                            }
                            if (text != null) {
                                resultSet.addElement(
                                        LookupElementBuilder.create(element, text).withIcon(LiteFlowIcons.CHAIN_ICON).withTypeText("Chain").bold()
                                );
                            }
                        }
                    }
                }
            });
    }

}
