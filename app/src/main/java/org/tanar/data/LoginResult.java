package org.tanar.data;

import androidx.annotation.Nullable;

/**
 * Authentication result : success (user details) or error message.
 */
public class LoginResult {
    @Nullable
    private String displayName;
    @Nullable
    private Integer error;

    LoginResult(@Nullable Integer error) {
        this.error = error;
    }

    LoginResult(@Nullable String displayName) {
        this.displayName = displayName;
    }

    @Nullable
    public String getSuccess() {
        return displayName;
    }

    @Nullable
    public Integer getError() {
        return error;
    }
}