import qs from "qs";
import { instance } from "@/utils/axiosInstance";
import { FailedAuthenticationException } from "@/services/exceptions/FailedAuthenticationException";

const RESOURCE_URL = "/auth";

/**
 * Request the authentication tokens from the server.
 * @returns The access and the refresh token.
 * @throws {FailedAuthenticationException} if the email or password is incorrect.
 */
export const authenticateUser = async (email: string, password: string) => {
  const queryString = qs.stringify({
    email,
    password,
  });

  const response = await instance.post(`${RESOURCE_URL}/login`, queryString, {
    headers: {
      "Content-Type": "application/x-www-form-urlencoded",
    },
  });

  const { headers, status } = response;

  if (status === 200) {
    const { accessToken, refreshToken } = headers;
    return {
      accessToken,
      refreshToken,
    };
  } else {
    throw new FailedAuthenticationException(
      "The authentication failed. Check your username and password"
    );
  }
};
