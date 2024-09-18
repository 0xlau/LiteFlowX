package top.xystudio.plugin.idea.liteflowx.system.provider;

import com.intellij.openapi.project.Project;
import com.intellij.psi.PsiClass;
import com.intellij.psi.util.CachedValue;
import com.intellij.psi.util.CachedValueProvider;
import com.intellij.psi.util.CachedValuesManager;
import com.intellij.psi.util.PsiModificationTracker;
import org.jetbrains.annotations.NotNull;

import javax.swing.Icon;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author hongzhida
 * @date 2024-08-16
 */
public class JavaFileIconCache {
    private static final Map<Project, CachedValue<Map<PsiClass, Icon>>> projectCache = new ConcurrentHashMap<>();

    public static CachedValue<Map<PsiClass, Icon>> getCache(@NotNull Project project) {
        return projectCache.computeIfAbsent(project, p-> CachedValuesManager.getManager(project).createCachedValue(() -> {
            // Create and return the initial cache value
            return CachedValueProvider.Result.create(new HashMap<>(), PsiModificationTracker.NEVER_CHANGED);
        }));
    }

    public static void clearCache(Project project, PsiClass psiClass) {
        Map<PsiClass, Icon> map = getCache(project).getValue();
        map.remove(psiClass);
    }

    public static void updateCache(@NotNull Project project, PsiClass psiClass, Icon value) {
        CachedValue<Map<PsiClass, Icon>> cache = getCache(project);
        Map<PsiClass, Icon> map = cache.getValue();
        map.put(psiClass, value);
    }

    public static Icon getFromCache(@NotNull Project project, PsiClass psiClass) {
        Map<PsiClass, Icon> map = getCache(project).getValue();
        return map.get(psiClass);
    }

}
