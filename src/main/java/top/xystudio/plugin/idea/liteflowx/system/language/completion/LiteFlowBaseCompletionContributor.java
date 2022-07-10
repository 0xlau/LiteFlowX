package top.xystudio.plugin.idea.liteflowx.system.language.completion;

import com.intellij.codeInsight.completion.*;
import com.intellij.codeInsight.lookup.LookupElement;
import com.intellij.codeInsight.lookup.LookupElementBuilder;
import com.intellij.openapi.editor.Document;
import com.intellij.openapi.project.Project;
import com.intellij.patterns.PlatformPatterns;
import com.intellij.psi.PsiClass;
import com.intellij.psi.PsiElement;
import com.intellij.util.ProcessingContext;
import icons.LiteFlowIcons;
import org.jetbrains.annotations.NotNull;
import top.xystudio.plugin.idea.liteflowx.service.LiteFlowService;
import top.xystudio.plugin.idea.liteflowx.system.language.LiteFlowLanguage;
import top.xystudio.plugin.idea.liteflowx.system.language.psi.LiteFlowLiteFlowNodeRef;

public class LiteFlowBaseCompletionContributor extends CompletionContributor {



    public LiteFlowBaseCompletionContributor(){

        InsertHandler<LookupElement> parenHandler = (context, item) -> {
            Document document = context.getDocument();
            int selectionEndOffset = context.getSelectionEndOffset();
            document.insertString(selectionEndOffset, "()");
            selectionEndOffset += 1;
            context.getEditor().getCaretModel().getCurrentCaret().moveToOffset(selectionEndOffset);
        };

        InsertHandler<LookupElement> switchHandler = (context, item) -> {
            Document document = context.getDocument();
            int selectionEndOffset = context.getSelectionEndOffset();
            document.insertString(selectionEndOffset, "().to()");
            selectionEndOffset += 1;
            context.getEditor().getCaretModel().getCurrentCaret().moveToOffset(selectionEndOffset);
        };

        extend(CompletionType.BASIC, PlatformPatterns.psiElement().withLanguage(LiteFlowLanguage.INSTANCE),
            new CompletionProvider<>() {
                @Override
                protected void addCompletions(@NotNull CompletionParameters parameters, @NotNull ProcessingContext context, @NotNull CompletionResultSet resultSet) {
                    resultSet.addElement(LookupElementBuilder.create("THEN").withInsertHandler(parenHandler));
                    resultSet.addElement(LookupElementBuilder.create("WHEN").withInsertHandler(parenHandler));
                    resultSet.addElement(LookupElementBuilder.create("FINALLY").withInsertHandler(parenHandler));
                    resultSet.addElement(LookupElementBuilder.create("PRE").withInsertHandler(parenHandler));
                    resultSet.addElement(LookupElementBuilder.create("SWITCH").withInsertHandler(switchHandler));
                }
            });
    }

}
