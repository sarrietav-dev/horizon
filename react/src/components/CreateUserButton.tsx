import { Button, Form } from "react-bootstrap";
import useModal from "@/hooks/useModal";
import userService from "@/services/userService";
import User from "@/models/User";
import { useState } from "react";
import moment from "moment";

const CreateUserButton = () => {
  const handleSubmit = async (user: User) => {
    await userService.createUser(user);
  };

  const { modal, showModal } = useModal(
    <CreateUserModal onSubmit={handleSubmit} />,
    {
      title: "Crear usuario",
    }
  );

  return (
    <>
      {modal}
      <Button variant="info" onClick={showModal}>
        Create user
      </Button>
    </>
  );
};

const CreateUserModal = ({ onSubmit }: { onSubmit: (user: User) => void }) => {
  const [user, setUser] = useState<User>({
    email: "",
    password: "",
    role: "",
    userData: { name: "", surname: "", phoneNumber: "", birthDate: new Date() },
  });

  return (
    <Form
      onSubmit={(e) => {
        e.preventDefault();

        if (user) onSubmit(user);
      }}
    >
      <Form.Group>
        <Form.Label>Email</Form.Label>
        <Form.Control
          onChange={(e) =>
            setUser((oldState) => ({ ...oldState, email: e.target.value }))
          }
          type="email"
          placeholder="Email"
        />
      </Form.Group>

      <Form.Group>
        <Form.Label>Contraseña</Form.Label>
        <Form.Control
          onChange={(e) =>
            setUser((oldState) => ({ ...oldState, password: e.target.value }))
          }
          type="password"
          placeholder="Contraseña"
        />
      </Form.Group>

      <Form.Group>
        <Form.Label>Rol</Form.Label>
        <Form.Select
          onChange={(e) =>
            setUser((oldState) => ({ ...oldState, role: e.target.value }))
          }
          placeholder="Rol"
        >
          <option value="ROLE_RESIDENT">Residente</option>
          <option value="ROLE_ADMIN">Admin</option>
          <option value="ROLE_PROPRIETARY">Propietario</option>
        </Form.Select>
      </Form.Group>

      <Form.Group>
        <Form.Label>Nombres</Form.Label>
        <Form.Control
          onChange={(e) =>
            setUser((oldState) => ({
              ...oldState,
              userData: { ...oldState.userData, name: e.target.value },
            }))
          }
          type="text"
          placeholder="Nombres"
        />
      </Form.Group>

      <Form.Group>
        <Form.Label>Apellidos</Form.Label>
        <Form.Control
          onChange={(e) =>
            setUser((oldState) => ({
              ...oldState,
              userData: { ...oldState.userData, surname: e.target.value },
            }))
          }
          type="text"
          placeholder="Apellidos"
        />
      </Form.Group>

      <Form.Group>
        <Form.Label>Numero de teléfono</Form.Label>
        <Form.Control
          onChange={(e) =>
            setUser((oldState) => ({
              ...oldState,
              userData: { ...oldState.userData, phoneNumber: e.target.value },
            }))
          }
          type="text"
          placeholder="Numero de telefono"
        />
      </Form.Group>

      <Form.Group>
        <Form.Label>Fecha de nacimiento</Form.Label>
        <Form.Control
          onChange={(e) =>
            setUser((oldState) => ({
              ...oldState,
              userData: {
                ...oldState.userData,
                birthDate: moment(e.target.value).toDate(),
              },
            }))
          }
          type="date"
          placeholder="Fecha de nacimiento"
        />
      </Form.Group>

      <Button type="submit" variant="primary">
        Crear
      </Button>
    </Form>
  );
};

export default CreateUserButton;
