import TheLoginForm from "@/components/TheLoginForm";
import { useNavigate } from "react-router-dom";
import { FormEvent } from "react";
import { authenticateUser } from "@/stores/reducers/auth.store";
import { useAppDispatch } from "@/hooks/redux-hooks";

const LoginView = () => {
  const navigate = useNavigate();
  const dispatch = useAppDispatch();

  const handleSubmit = async (
    e: FormEvent,
    email: string,
    password: string
  ) => {
    e.preventDefault();
    await dispatch(authenticateUser({ email, password }));
    navigate("/", { replace: true });
  };

  return (
    <main className="d-flex justify-content-center align-items-center vh-100">
      <TheLoginForm onSubmit={handleSubmit} />
    </main>
  );
};

export default LoginView;
