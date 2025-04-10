package com.github.lennypu.cuboxmemo.settings;

import com.intellij.openapi.components.PersistentStateComponent;
import com.intellij.openapi.components.Service;
import com.intellij.openapi.components.State;
import com.intellij.openapi.components.Storage;
import com.intellij.util.xmlb.XmlSerializerUtil;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Service
@State(
    name = "com.github.lennypu.cuboxmemo.settings.CuboxMemoSettings",
    storages = @Storage("CuboxMemoSettings.xml")
)
public final class CuboxMemoSettings implements PersistentStateComponent<CuboxMemoSettings> {
    private String apiUrl = "";
    private String tags = "note"; // Default tag

    public String getApiUrl() {
        return apiUrl;
    }

    public void setApiUrl(String apiUrl) {
        this.apiUrl = apiUrl;
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    @Nullable
    @Override
    public CuboxMemoSettings getState() {
        return this;
    }

    @Override
    public void loadState(@NotNull CuboxMemoSettings state) {
        XmlSerializerUtil.copyBean(state, this);
    }
}
