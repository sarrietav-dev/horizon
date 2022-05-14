import axios from "axios";

/**
 * Used to reduce code duplication.
 * If the base url for the api changes,
 * there's only one place where it needs to be changed.
 */
export const axiosInstance = axios.create({
  baseURL: "/api",
});
