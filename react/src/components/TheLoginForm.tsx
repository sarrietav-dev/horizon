import React, { FormEvent, useState } from "react";

interface TheLoginFormProps {
  onSubmit: (e: FormEvent, ...args: string[]) => void;
}

const TheLoginForm: React.FC<TheLoginFormProps> = ({ onSubmit }) => {
  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");

  return (
    <div>
      <div className="card-body">
        <h2 className="text-center my-3">Iniciar Sesion</h2>
        <form
          method="POST"
          className="container"
          onSubmit={(e) => onSubmit(e, email, password)}
        >
          <div className="row my-2">
            <div className="col">
              <input
                value={email}
                onChange={(e) => setEmail(e.target.value)}
                type="email"
                className="form-control"
              />
            </div>
          </div>
          <div className="row my-2">
            <div className="col">
              <input
                value={password}
                onChange={(e) => setPassword(e.target.value)}
                type="password"
                className="form-control"
              />
            </div>
          </div>
          <div className="d-flex justify-content-center align-items-center">
            <button className="btn btn-primary">Iniciar Sesion</button>
          </div>
        </form>
      </div>
    </div>
  );
};

export default TheLoginForm;
