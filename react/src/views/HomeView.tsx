import PqrsCard from "@/components/PqrsCard";
import { useEffect, useState } from "react";
import { PQRS } from "@/models/PQRS";
import pqrsService from "@/services/pqrsService";

const HomeView = () => {
  const [pqrsList, setPqrsList] = useState<PQRS[]>([]);

  useEffect(() => {
    pqrsService.getAll().then((response) => setPqrsList(response));
  }, []);

  return pqrsList.map((pqrs) => <PqrsCard pqrs={pqrs} key={pqrs.id} />);
};

export default HomeView;
