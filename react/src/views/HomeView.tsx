import PqrsCard from "@/components/PqrsCard";
import { useEffect, useState } from "react";
import { PQRS } from "@/models/PQRS";
import pqrsService from "@/services/pqrsService";
import useModal from "@/hooks/useModal";
import PqrsCreationForm from "@/components/PqrsCreationForm";

const HomeView = () => {
  const [pqrsList, setPqrsList] = useState<PQRS[]>([]);
  const { modal, showModal } = useModal(
    <PqrsCreationForm onSubmit={() => 0} />,
    {
      title: "Crear una PQRS",
    }
  );

  useEffect(() => {
    pqrsService
      .getAll()
      .then((response) => setPqrsList(Array.isArray(response) ? response : []));
  }, []);

  return (
    <main className="px-5 py-5">
      {modal}
      {pqrsList.map((pqrs) => (
        <PqrsCard pqrs={pqrs} key={pqrs.id} />
      ))}
      <CreateButton onClick={() => showModal()} />
    </main>
  );
};

const CreateButton = ({ onClick }: { onClick: () => void }) => {
  return (
    <div
      style={{ height: "10vh", width: "10vh", bottom: 30, right: 30 }}
      className="position-absolute rounded-circle bg-primary d-flex justify-content-center align-items-center"
      onClick={onClick}
    >
      <i className="bi bi-plus text-white fs-1"></i>
    </div>
  );
};

export default HomeView;
