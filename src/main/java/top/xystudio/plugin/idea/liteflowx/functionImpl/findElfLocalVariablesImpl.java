package top.xystudio.plugin.idea.liteflowx.functionImpl;

import com.intellij.openapi.project.Project;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiFile;
import com.intellij.psi.util.PsiTreeUtil;
import top.xystudio.plugin.idea.liteflowx.system.language.psi.LiteFlowCodes;
import top.xystudio.plugin.idea.liteflowx.system.language.psi.LiteFlowStatement;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BiFunction;

public class findElfLocalVariablesImpl implements BiFunction<Project, String, List<? extends PsiElement>> {

    private final PsiFile liteFlowFile;

    public findElfLocalVariablesImpl(PsiFile liteFlowFile){
        this.liteFlowFile = liteFlowFile;
    }

    @Override
    public List<? extends PsiElement> apply(Project project, String name) {

        List<LiteFlowStatement> result = new ArrayList<>();

        LiteFlowCodes liteFlowCodes = PsiTreeUtil.getChildOfType(this.liteFlowFile, LiteFlowCodes.class);
        if (liteFlowCodes == null){
            return result;
        }
        for (LiteFlowStatement liteFlowStatement : liteFlowCodes.getStatementList()) {
            try{
                if(liteFlowStatement.getAssignStatement() != null &&
                        name.equals(liteFlowStatement.getAssignStatement().getAssignExpress().getRefExpress().getTypeRef().getText())){
                    result.add(liteFlowStatement);
                }
            }catch (Exception ignored){ }
        }
        return result;

    }
}
