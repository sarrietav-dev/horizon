import TheLoginForm from "@/components/TheLoginForm";
import {authenticateUser} from "@/services/auth";
import {useNavigate} from "react-router-dom";
import {FormEvent} from "react";
import tokenService from "@/services/tokenService";

const LoginView = () => {
  const navigate = useNavigate();

  const handleSubmit = async (e: FormEvent, email: string, password: string) => {
    e.preventDefault();

    const {accessToken, refreshToken} = await authenticateUser(email, password);

    tokenService.accessToken = accessToken;
    tokenService.refreshToken = refreshToken;

    navigate("/", {replace: true});
  }

  return <main className="d-flex justify-content-center align-items-center vh-100">
    <TheLoginForm onSubmit={handleSubmit}/>
  </main>;
}

export default LoginView;