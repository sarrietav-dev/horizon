import { FormProps } from "@/types/FormProps";
import { useState } from "react";

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
          />
        </div>
        <div className="mb-3">
          <label htmlFor="description">Descripcion</label>
          <textarea
            name="description"
            id="description"
            className="form-control"
            value={description}
            onChange={(e) => setDescription(e.target.value)}
          />
        </div>
      </form>
    </div>
  );
};

export default PqrsCreationForm;
