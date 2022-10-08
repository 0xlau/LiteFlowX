package top.xystudio.plugin.idea.liteflowx.service;

import com.intellij.openapi.components.ServiceManager;
import com.intellij.openapi.module.Module;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.vfs.LocalFileSystem;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.psi.search.FilenameIndex;
import com.intellij.psi.search.GlobalSearchScope;
import org.jetbrains.annotations.NotNull;

import java.io.Serializable;

public class FileService implements Serializable {

    private Project project;

    public FileService(Project project) {
        this.project = project;
    }

    public static FileService getInstance(@NotNull Project project) {
        return ServiceManager.getService(project, FileService.class);
    }

    public VirtualFile getFileInResourcePath(Module module, String path){
        String[] paths = path.split("/");
        String fileName = paths[paths.length - 1];
        return FilenameIndex.getVirtualFilesByName(project, fileName, GlobalSearchScope.moduleScope(module))
                .stream().filter((item) -> item.getPath().endsWith("/resources/" + path)).findFirst().orElse(null);
    }

    public VirtualFile getFileInAbsolutePath(String file){
        return LocalFileSystem.getInstance().refreshAndFindFileByPath(file);
    }

}
