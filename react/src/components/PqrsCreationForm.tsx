import { FormProps } from "@/types/FormProps";
import { useState } from "react";
import { Button } from "react-bootstrap";

const PqrsCreationForm = ({ onSubmit }: FormProps) => {
  const [title, setTitle] = useState("");
  const [description, setDescription] = useState("");

  return (
    <div className="card">
      <form
        className="card-body"
        method="POST"
        onSubmit={(e) => onSubmit(e, title, description)}
      >
        <div className="mb-3">
          <label htmlFor="title">Titulo</label>
          <input
            type="text"
            name="title"
            id="title"
            className="form-control"
            value={title}
            onChange={(e) => setTitle(e.target.value)}
            required
          />
        </div>
        <div className="mb-3">
          <label htmlFor="description">Descripción</label>
          <textarea
            name="description"
            id="description"
            className="form-control"
            value={description}
            onChange={(e) => setDescription(e.target.value)}
            required
          />
        </div>
        <div className="d-flex justify-content-end">
          <Button type="submit">Crear</Button>
        </div>
      </form>
    </div>
  );
};

export default PqrsCreationForm;
