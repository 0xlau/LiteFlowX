package top.xystudio.plugin.idea.liteflowx.util;

import com.intellij.ide.highlighter.JavaFileType;
import com.intellij.json.JsonFileType;
import com.intellij.json.psi.JsonFile;
import com.intellij.openapi.fileTypes.FileType;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.roots.ProjectFileIndex;
import com.intellij.psi.PsiFile;
import com.intellij.psi.PsiManager;

import java.util.ArrayList;
import java.util.Collection;

public class FileUtils {

    public static Collection<JsonFile> getAllJsonFiles(Project project){
        Collection<JsonFile> result = new ArrayList<>();
        ProjectFileIndex.getInstance(project).iterateContent(file -> {
            if (file.getFileType() == JsonFileType.INSTANCE) {
                result.add((JsonFile) PsiManager.getInstance(project).findFile(file));
            }
            return true;
        }, filter -> !filter.isDirectory());
        return result;
    }

}
