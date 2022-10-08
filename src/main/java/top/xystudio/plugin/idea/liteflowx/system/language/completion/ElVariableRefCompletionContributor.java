package top.xystudio.plugin.idea.liteflowx.system.language.completion;

import com.intellij.codeInsight.completion.*;
import com.intellij.codeInsight.lookup.LookupElementBuilder;
import com.intellij.icons.AllIcons;
import com.intellij.patterns.PlatformPatterns;
import com.intellij.psi.PsiElement;
import com.intellij.psi.util.PsiTreeUtil;
import com.intellij.util.ProcessingContext;
import org.jetbrains.annotations.NotNull;
import top.xystudio.plugin.idea.liteflowx.system.language.LiteFlowLanguage;
import top.xystudio.plugin.idea.liteflowx.system.language.psi.LiteFlowCodes;
import top.xystudio.plugin.idea.liteflowx.system.language.psi.LiteFlowElVariableRef;
import top.xystudio.plugin.idea.liteflowx.system.language.psi.LiteFlowStatement;

public class ElVariableRefCompletionContributor extends CompletionContributor {

    public ElVariableRefCompletionContributor(){

        extend(CompletionType.BASIC, PlatformPatterns.psiElement().withLanguage(LiteFlowLanguage.INSTANCE),
            new CompletionProvider<>() {
                @Override
                protected void addCompletions(@NotNull CompletionParameters parameters, @NotNull ProcessingContext context, @NotNull CompletionResultSet resultSet) {

                    PsiElement position = parameters.getPosition();

                    if (position.getParent() instanceof LiteFlowElVariableRef){

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
                    }
                }
            });
    }

}
