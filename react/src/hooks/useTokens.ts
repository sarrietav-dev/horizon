import { useState } from "react";

const useTokens = () => {
  const localStorageAccessToken = localStorage.getItem("accessToken");
  const localStorageRefreshToken = localStorage.getItem("refreshToken");

  const [accessToken, setAccessToken] = useState(localStorageAccessToken);
  const [refreshToken, setRefreshToken] = useState(localStorageRefreshToken);

  type SetTokensWrapperProps = { accessToken: string; refreshToken: string };
  const setTokensWrapper = (props: SetTokensWrapperProps) => {
    if (props.accessToken) {
      localStorage.setItem("accessToken", props.accessToken);
      setAccessToken(props.accessToken);
    }
    if (props.refreshToken) {
      localStorage.setItem("refreshToken", props.refreshToken);
      setRefreshToken(props.refreshToken);
    }
  };

  return {
    tokens: { accessToken, refreshToken },
    setTokens: setTokensWrapper,
  };
};

export default useTokens;
