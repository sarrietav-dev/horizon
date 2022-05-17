import PqrsCard from "@/components/PqrsCard";
import { useEffect, useState } from "react";
import { PQRS } from "@/models/PQRS";
import pqrsService from "@/services/pqrsService";

const HomeView = () => {
  const [pqrsList, setPqrsList] = useState<PQRS[]>([]);

  useEffect(() => {
    pqrsService
      .getAll()
      .then((response) => setPqrsList(Array.isArray(response) ? response : []));
  }, []);

  return (
    <main className="px-5 py-5">
      {pqrsList.map((pqrs) => (
        <PqrsCard pqrs={pqrs} key={pqrs.id} />
      ))}
      <CreateButton onClick={() => 0} />
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
