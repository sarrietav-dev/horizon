import { defineStore } from "pinia";

export const ACCESS_TOKEN = "access_token";
export const REFRESH_TOKEN = "refresh_token";

export const useAuthStore = defineStore("tokenManager", {
  state: () => ({
    accessToken: "",
    refreshToken: "",
  }),
  actions: {
    setAccessToken(token: string) {
      this.accessToken = token;
      return localStorage.setItem(ACCESS_TOKEN, token);
    },
    setRefreshToken(token: string) {
      this.refreshToken = token;
      return localStorage.setItem(REFRESH_TOKEN, token);
    },
  },
  getters: {
    isAuthenticated(): boolean {
      return this.accessToken !== null && this.refreshToken !== null;
    },
  },
});
