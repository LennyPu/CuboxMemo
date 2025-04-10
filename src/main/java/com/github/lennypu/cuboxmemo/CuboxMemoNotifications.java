package com.github.lennypu.cuboxmemo;

import com.intellij.notification.NotificationGroupManager;
import com.intellij.notification.NotificationType;
import com.intellij.openapi.project.Project;

public class CuboxMemoNotifications {
    
    public static void notify(Project project, String content, NotificationType type) {
        NotificationGroupManager.getInstance()
            .getNotificationGroup("Cubox Memo Notification Group")
            .createNotification(content, type)
            .notify(project);
    }
}
