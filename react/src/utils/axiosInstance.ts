import axios from "axios";
import tokenService from "@/services/tokenService";

const baseURL = "/api";

/**
 * Used to reduce code duplication.
 * If the base url for the api changes,
 * there's only one place where it needs to be changed.
 */
const axiosInstance = axios.create({
  baseURL,
});

axiosInstance.interceptors.request.use(
  (req) => {
    const auth = tokenService;

    if (req.headers) {
      req.headers.Authorization = `Bearer ${auth.accessToken}`;
    }
  },
  (error) => Promise.reject(error)
);

axiosInstance.interceptors.response.use(
  (res) => res,
  async (error) => {
    if (error.response.status !== 401) {
      return Promise.reject(error);
    }

    try {
      const response = await axios.get("/auth/token", {
        headers: {Authorization: `Bearer ${tokenService.refreshToken}`},
        baseURL,
      });

      const {access_token} = response.headers;

      tokenService.accessToken = access_token;

      error.response.config.headers["Authorization"] = `Bearer ${access_token}`;

      return axiosInstance(error.response.config);
    } catch (error) {
      tokenService.deleteTokens();
      window.location.replace("/login");
      return Promise.reject(error);
    }
  }
);

export default axiosInstance;
