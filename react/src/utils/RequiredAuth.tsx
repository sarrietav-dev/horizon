import { Navigate, useLocation } from "react-router-dom";
import { useAppSelector } from "@/hooks/redux-hooks";

type RequiredAuthProps = { children: JSX.Element };

const RequiredAuth = ({ children }: RequiredAuthProps) => {
  const auth = useAppSelector((state) => state.auth);
  const location = useLocation();

  if (!auth.isAuth)
    return <Navigate to="/login" replace state={{ from: location }} />;

  return children;
};

export default RequiredAuth;
