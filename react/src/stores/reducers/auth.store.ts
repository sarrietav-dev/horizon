import { createAsyncThunk, createSlice } from "@reduxjs/toolkit";
import authService from "@/services/authService";
import { Credentials } from "@/types/Credentials";

interface AuthStoreInitialState {
  accessToken: string | null;
  refreshToken: string | null;
  userRole: "ROLE_RESIDENT" | "ROLE_ADMIN" | "ROLE_PROPRIETARY" | null;
}

const initialState: AuthStoreInitialState = {
  accessToken: null,
  refreshToken: null,
  userRole: null,
};

export const authSlice = createSlice({
  name: "auth",
  initialState,
  reducers: {
    deleteTokens(state) {
      state.accessToken = null;
      state.refreshToken = null;
      localStorage.removeItem("accessToken");
      localStorage.removeItem("refreshToken");
    },
  },
  extraReducers: (builder) => {
    builder.addCase(authenticateUser.fulfilled, (state, action) => {
      const { accessToken, refreshToken } = action.payload;
      state.accessToken = accessToken;
      localStorage.setItem("accessToken", accessToken);
      state.refreshToken = refreshToken;
      localStorage.setItem("refreshToken", refreshToken);
    });
  },
});

export default authSlice.reducer;

export const authenticateUser = createAsyncThunk(
  "auth/authenticateUser",
  async (credentials: Credentials) => {
    return await authService.authenticateUser(credentials);
  }
);
