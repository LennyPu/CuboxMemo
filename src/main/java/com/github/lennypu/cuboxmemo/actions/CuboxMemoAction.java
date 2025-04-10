package com.github.lennypu.cuboxmemo.actions;

import com.github.lennypu.cuboxmemo.services.CuboxMemoService;
import com.intellij.notification.NotificationGroupManager;
import com.intellij.notification.NotificationType;
import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.ide.CopyPasteManager;
import com.intellij.openapi.project.DumbAware;
import com.intellij.openapi.project.Project;
import org.jetbrains.annotations.NotNull;

import java.awt.datatransfer.DataFlavor;

public class CuboxMemoAction extends AnAction implements DumbAware {

    @Override
    public void actionPerformed(@NotNull AnActionEvent e) {
        Project project = e.getProject();
        if (project == null) return;
        
        String clipboardContent = getCopyPasteManager().getContents(DataFlavor.stringFlavor);
        if (clipboardContent == null) return;

        CuboxMemoService service = ApplicationManager.getApplication().getService(CuboxMemoService.class);
        
        service.sendToApi(clipboardContent, 
            () -> showNotification(project, "Content successfully sent to Cubox", NotificationType.INFORMATION),
            errorMessage -> showNotification(project, "Error sending to Cubox: " + errorMessage, NotificationType.ERROR)
        );
    }

    private CopyPasteManager getCopyPasteManager() {
        return CopyPasteManager.getInstance();
    }

    private void showNotification(Project project, String content, NotificationType type) {
        NotificationGroupManager.getInstance()
            .getNotificationGroup("Cubox Memo Notification Group")
            .createNotification(content, type)
            .notify(project);
    }
}
