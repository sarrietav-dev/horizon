import axios from "axios";
import { useAuthStore } from "@/stores/authStore";

/**
 * Used to reduce code duplication.
 * If the base url for the api changes,
 * there's only one place where it needs to be changed.
 */
const axiosInstance = axios.create({
  baseURL: "/api",
});

axiosInstance.interceptors.request.use(
  (req) => {
    const auth = useAuthStore();

    if (req.headers) {
      req.headers.Authorization = `Bearer ${auth.accessToken}`;
    }
  },
  (error) => Promise.reject(error)
);

axiosInstance.interceptors.response.use(
  (res) => res,
  async (error) => {
    const auth = useAuthStore();

    const originalRequest = error.config;

    if (error.response) {
      if (error.status === 403 && originalRequest._retry) {
        originalRequest._retry = true;

        const response = await axiosInstance.get("/auth/token", {
          headers: {
            Authorization: auth.refreshToken,
          },
        });

        const { headers, status } = response;

        if (status === 200) {
          const { accessToken } = headers;
          auth.setAccessToken(accessToken);
        }
      }
    }
  }
);

export default axiosInstance;
