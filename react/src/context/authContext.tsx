import React from "react";
import {authenticateUser} from "@/services/authService";
import {Credentials} from "@/types/Credentials";
import tokenService from "@/services/tokenService";
import useTokens from "@/hooks/useTokens";


interface AuthContextType {
  isAuth: () => boolean;
  signIn: (credentials: Credentials, callback: VoidFunction) => void
  signOut: (callback: VoidFunction) => void;
}

const AuthContext = React.createContext<AuthContextType>(null!);

type AuthProviderProps = { children: React.ReactNode };

const AuthProvider = ({children}: AuthProviderProps) => {
  const {tokens, setTokens} = useTokens();

  const isAuth = () => tokens.refreshToken !== null && tokens.accessToken !== null;

  const signIn = async (credentials: Credentials, callback: VoidFunction) => {
    const {accessToken, refreshToken} = await authenticateUser(credentials);

    setTokens({accessToken, refreshToken});

    callback();
  }

  const signOut = (callback: VoidFunction) => {
    tokenService.deleteTokens();

    callback();
  };

  const value: AuthContextType = {isAuth, signIn, signOut}

  return <AuthContext.Provider value={value}>{children}</AuthContext.Provider>
}

export const useAuth = () => React.useContext(AuthContext);

export default AuthProvider;