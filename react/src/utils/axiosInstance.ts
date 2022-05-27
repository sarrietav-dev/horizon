import axios from "axios";
import tokenService from "@/services/tokenService";

const baseURL = "https://horizon-java-app-udc.herokuapp.com/api";

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
    try {
      if (req.headers) {
        req.headers.Authorization = `Bearer ${tokenService.accessToken}`;
      }
      return req;
    } catch (e) {
      return req;
    }
  },
  (error) => Promise.reject(error)
);

axiosInstance.interceptors.response.use(
  (res) => res,
  async (error) => {
    if (!error.response) {
      return Promise.reject(error);
    }

    if (error.response.status !== 401) {
      return Promise.reject(error);
    }

    try {
      const response = await axios.get("/auth/token", {
        headers: { Authorization: `Bearer ${tokenService.refreshToken}` },
        baseURL,
      });

      const { access_token } = response.headers;

      tokenService.accessToken = access_token;

      return axiosInstance(error.config);
    } catch (error) {
      tokenService.deleteTokens();
      window.location.replace("/login");
      return Promise.reject(error);
    }
  }
);

export default axiosInstance;
