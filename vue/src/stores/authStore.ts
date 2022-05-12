import { defineStore } from "pinia";

export const ACCESS_TOKEN = "access_token";
export const REFRESH_TOKEN = "refresh_token";

export const useAuthStore = defineStore("tokenManager", {
  actions: {
    setAccessToken(token: string) {
      return localStorage.setItem(ACCESS_TOKEN, token);
    },
    setRefreshToken(token: string) {
      return localStorage.setItem(REFRESH_TOKEN, token);
    },
  },
  getters: {
    isAuthenticated(): boolean {
      return this.getAccessToken !== null && this.getRefreshToken !== null;
    },
    getAccessToken: () => localStorage.getItem(ACCESS_TOKEN),
    getRefreshToken: () => localStorage.getItem(REFRESH_TOKEN),
  },
});
