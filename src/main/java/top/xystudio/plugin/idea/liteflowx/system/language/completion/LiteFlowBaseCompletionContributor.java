package top.xystudio.plugin.idea.liteflowx.system.language.completion;

import com.intellij.codeInsight.completion.*;
import com.intellij.codeInsight.lookup.LookupElement;
import com.intellij.codeInsight.lookup.LookupElementBuilder;
import com.intellij.openapi.editor.Document;
import com.intellij.patterns.PlatformPatterns;
import com.intellij.psi.PsiElement;
import com.intellij.util.ProcessingContext;
import org.jetbrains.annotations.NotNull;
import top.xystudio.plugin.idea.liteflowx.system.language.LiteFlowLanguage;

public class LiteFlowBaseCompletionContributor extends CompletionContributor {



    public LiteFlowBaseCompletionContributor(){

        InsertHandler<LookupElement> parenHandler = (context, item) -> {
            Document document = context.getDocument();
            int selectionEndOffset = context.getSelectionEndOffset();
            document.insertString(selectionEndOffset, "()");
            selectionEndOffset += 1;
            context.getEditor().getCaretModel().getCurrentCaret().moveToOffset(selectionEndOffset);
        };

        InsertHandler<LookupElement> parenQuoteHandler = (context, item) -> {
            Document document = context.getDocument();
            int selectionEndOffset = context.getSelectionEndOffset();
            document.insertString(selectionEndOffset, "(\"\")");
            selectionEndOffset += 2;
            context.getEditor().getCaretModel().getCurrentCaret().moveToOffset(selectionEndOffset);
        };

        InsertHandler<LookupElement> switchHandler = (context, item) -> {
            Document document = context.getDocument();
            int selectionEndOffset = context.getSelectionEndOffset();
            document.insertString(selectionEndOffset, "().TO()");
            selectionEndOffset += 1;
            context.getEditor().getCaretModel().getCurrentCaret().moveToOffset(selectionEndOffset);
        };

        InsertHandler<LookupElement> loopHandler = (context, item) -> {
            Document document = context.getDocument();
            int selectionEndOffset = context.getSelectionEndOffset();
            document.insertString(selectionEndOffset, "().DO()");
            selectionEndOffset += 1;
            context.getEditor().getCaretModel().getCurrentCaret().moveToOffset(selectionEndOffset);
        };

        extend(CompletionType.BASIC, PlatformPatterns.psiElement().withLanguage(LiteFlowLanguage.INSTANCE),
            new CompletionProvider<>() {
                @Override
                protected void addCompletions(@NotNull CompletionParameters parameters, @NotNull ProcessingContext context, @NotNull CompletionResultSet resultSet) {

                    PsiElement position = parameters.getPosition();

                    if (position.getText().equals("IntellijIdeaRulezzz")){
                        resultSet.addElement(LookupElementBuilder.create("id").withInsertHandler(parenHandler));
                        resultSet.addElement(LookupElementBuilder.create("any").withInsertHandler(parenHandler));
                        resultSet.addElement(LookupElementBuilder.create("must").withInsertHandler(parenHandler));
                        resultSet.addElement(LookupElementBuilder.create("ignoreError").withInsertHandler(parenHandler));
                        resultSet.addElement(LookupElementBuilder.create("threadPool").withInsertHandler(parenHandler));
                        resultSet.addElement(LookupElementBuilder.create("maxWaitSeconds").withInsertHandler(parenHandler));
                        resultSet.addElement(LookupElementBuilder.create("maxWaitMilliseconds").withInsertHandler(parenHandler));
                        resultSet.addElement(LookupElementBuilder.create("retry").withInsertHandler(parenHandler));
                        resultSet.addElement(LookupElementBuilder.create("parallel").withInsertHandler(parenHandler));
                        resultSet.addElement(LookupElementBuilder.create("TO").withInsertHandler(parenHandler));
                        resultSet.addElement(LookupElementBuilder.create("DO").withInsertHandler(parenHandler));
                        resultSet.addElement(LookupElementBuilder.create("tag").withInsertHandler(parenHandler));
                        resultSet.addElement(LookupElementBuilder.create("data").withInsertHandler(parenHandler));
                        resultSet.addElement(LookupElementBuilder.create("ELIF").withInsertHandler(parenHandler));
                        resultSet.addElement(LookupElementBuilder.create("ELSE").withInsertHandler(parenHandler));
                        resultSet.addElement(LookupElementBuilder.create("BREAK").withInsertHandler(parenHandler));
                        resultSet.addElement(LookupElementBuilder.create("DEFAULT").withInsertHandler(parenHandler));
                    }else{
                        resultSet.addElement(LookupElementBuilder.create("CATCH").withInsertHandler(parenHandler));
                        resultSet.addElement(LookupElementBuilder.create("IF").withInsertHandler(parenHandler));
                        resultSet.addElement(LookupElementBuilder.create("AND").withInsertHandler(parenHandler));
                        resultSet.addElement(LookupElementBuilder.create("OR").withInsertHandler(parenHandler));
                        resultSet.addElement(LookupElementBuilder.create("NOT").withInsertHandler(parenHandler));
                        resultSet.addElement(LookupElementBuilder.create("THEN").withInsertHandler(parenHandler));
                        resultSet.addElement(LookupElementBuilder.create("SER").withInsertHandler(parenHandler));
                        resultSet.addElement(LookupElementBuilder.create("WHEN").withInsertHandler(parenHandler));
                        resultSet.addElement(LookupElementBuilder.create("PAR").withInsertHandler(parenHandler));
                        resultSet.addElement(LookupElementBuilder.create("FINALLY").withInsertHandler(parenHandler));
                        resultSet.addElement(LookupElementBuilder.create("PRE").withInsertHandler(parenHandler));
                        resultSet.addElement(LookupElementBuilder.create("SWITCH").withInsertHandler(switchHandler));
                        resultSet.addElement(LookupElementBuilder.create("FOR").withInsertHandler(loopHandler));
                        resultSet.addElement(LookupElementBuilder.create("ITERATOR").withInsertHandler(loopHandler));
                        resultSet.addElement(LookupElementBuilder.create("WHILE").withInsertHandler(loopHandler));
                        resultSet.addElement(LookupElementBuilder.create("NODE").withInsertHandler(parenQuoteHandler));
                    }

                }
            });
    }

}
