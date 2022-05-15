import { useAuth } from "@/context/authContext";
import { Navigate, useLocation } from "react-router-dom";

type RequiredAuthProps = { children: JSX.Element };

const RequiredAuth = ({ children }: RequiredAuthProps) => {
  const auth = useAuth();
  const location = useLocation();

  if (!auth.isAuth())
    return <Navigate to="/login" replace state={{ from: location }} />;

  return children;
};

export default RequiredAuth;
