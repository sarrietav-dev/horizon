import { createAsyncThunk, createSlice } from "@reduxjs/toolkit";
import authService from "@/services/authService";
import { Credentials } from "@/types/Credentials";
import { RootState } from "@/stores";

const ACCESS_TOKEN = "accessToken";
const REFRESH_TOKEN = "refreshToken";
const ROLE = "role";

const localStorageAccessToken = localStorage.getItem(ACCESS_TOKEN);
const localStorageRefreshToken = localStorage.getItem(REFRESH_TOKEN);

interface AuthStoreInitialState {
  accessToken: string | null;
  refreshToken: string | null;
  userRole: string | null;
  isAuth: boolean;
}

const initialState: AuthStoreInitialState = {
  accessToken: localStorageAccessToken,
  refreshToken: localStorageRefreshToken,
  userRole: null,
  isAuth: localStorageAccessToken !== null && localStorageRefreshToken !== null,
};

export const authSlice = createSlice({
  name: "auth",
  initialState,
  reducers: {
    deleteTokens(state) {
      state.accessToken = null;
      state.refreshToken = null;
      state.userRole = null;
      localStorage.removeItem(ACCESS_TOKEN);
      localStorage.removeItem(REFRESH_TOKEN);
      localStorage.removeItem(ROLE);
      state.isAuth = false;
    },
  },
  extraReducers: (builder) => {
    builder.addCase(authenticateUser.fulfilled, (state, action) => {
      const { accessToken, refreshToken, role } = action.payload;
      state.accessToken = accessToken;
      localStorage.setItem(ACCESS_TOKEN, accessToken);
      state.refreshToken = refreshToken;
      localStorage.setItem(REFRESH_TOKEN, refreshToken);
      state.userRole = role;
      localStorage.setItem(ROLE, role);
      state.isAuth = true;
    });
  },
});

export const isAuth = (state: RootState) =>
  state.auth.accessToken !== null && state.auth.refreshToken !== null;

export default authSlice.reducer;

export const authenticateUser = createAsyncThunk(
  "auth/authenticateUser",
  async (credentials: Credentials) => {
    return await authService.authenticateUser(credentials);
  }
);
