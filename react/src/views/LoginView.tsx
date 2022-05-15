import TheLoginForm from "@/components/TheLoginForm";
import {useNavigate} from "react-router-dom";
import {FormEvent} from "react";
import {useAuth} from "@/context/authContext";

const LoginView = () => {
  const navigate = useNavigate();
  const auth = useAuth();

  const handleSubmit = async (e: FormEvent, email: string, password: string) => {
    e.preventDefault();

    auth.signIn({email, password}, () =>
      navigate("/", {replace: true})
    );
  }

  return <main className="d-flex justify-content-center align-items-center vh-100">
    <TheLoginForm onSubmit={handleSubmit}/>
  </main>;
}

export default LoginView;