package com.github.lennypu.cuboxmemo.settings;

import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.options.Configurable;
import org.jetbrains.annotations.Nls;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;
import java.awt.*;

public class CuboxMemoSettingsConfigurable implements Configurable {
    private JPanel mainPanel;
    private JTextField apiUrlTextField;
    private JTextField tagsTextField;

    @Nls(capitalization = Nls.Capitalization.Title)
    @Override
    public String getDisplayName() {
        return "Cubox Memo";
    }

    @Nullable
    @Override
    public JComponent createComponent() {
        mainPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.insets = new Insets(5, 5, 5, 5);
        mainPanel.add(new JLabel("API URL:"), gbc);
        
        apiUrlTextField = new JTextField(30);
        gbc.gridx = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1.0;
        mainPanel.add(apiUrlTextField, gbc);
        
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.weightx = 0.0;
        mainPanel.add(new JLabel("Tags (comma-separated):"), gbc);
        
        tagsTextField = new JTextField(30);
        gbc.gridx = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1.0;
        mainPanel.add(tagsTextField, gbc);
        
        // Add some instructions
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        gbc.insets = new Insets(15, 5, 5, 5);
        mainPanel.add(new JLabel("Example: Enter tags separated by commas, like 'note,important,work'"), gbc);
        
        loadSettings();
        
        return mainPanel;
    }

    @Override
    public boolean isModified() {
        CuboxMemoSettings settings = getSettings();
        return !apiUrlTextField.getText().equals(settings.getApiUrl()) || 
               !tagsTextField.getText().equals(settings.getTags());
    }

    @Override
    public void apply() {
        CuboxMemoSettings settings = getSettings();
        settings.setApiUrl(apiUrlTextField.getText());
        settings.setTags(tagsTextField.getText());
    }

    @Override
    public void reset() {
        loadSettings();
    }

    private void loadSettings() {
        CuboxMemoSettings settings = getSettings();
        apiUrlTextField.setText(settings.getApiUrl());
        tagsTextField.setText(settings.getTags());
    }

    private CuboxMemoSettings getSettings() {
        return ApplicationManager.getApplication().getService(CuboxMemoSettings.class);
    }
}
