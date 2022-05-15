import React, {useState} from "react";
import {authenticateUser} from "@/services/authService";
import {Credentials} from "@/types/Credentials";
import tokenService from "@/services/tokenService";


interface AuthContextType {
  isAuth: boolean;
  signIn: (credentials: Credentials, callback: VoidFunction) => void
  signOut: (callback: VoidFunction) => void;
}

const AuthContext = React.createContext<AuthContextType>(null!);

type AuthProviderProps = { children: React.ReactNode };

const AuthProvider = ({children}: AuthProviderProps) => {
  const [isAuth, setIsAuth] = useState(false);

  const signIn = async (credentials: Credentials, callback: VoidFunction) => {
    const {accessToken, refreshToken} = await authenticateUser(credentials);

    tokenService.accessToken = accessToken;
    tokenService.refreshToken = refreshToken;

    setIsAuth(true);

    callback();

  }

  const signOut = (callback: VoidFunction) => {
    tokenService.deleteTokens();

    setIsAuth(false);

    callback();
  };

  const value: AuthContextType = {isAuth, signIn, signOut}

  return <AuthContext.Provider value={value}>{children}</AuthContext.Provider>
}

export const useAuth = () => React.useContext(AuthContext);

export default AuthProvider;