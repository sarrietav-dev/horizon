import axiosInstance from "@/utils/axiosInstance";
import { PQRS } from "@/models/PQRS";

const resourcePath = "/pqrs";

const getAll = async () => {
  const response = await axiosInstance.get<PQRS[]>(resourcePath);
  return response.data;
};

const create = async ({
  title,
  description,
}: {
  title: string;
  description: string;
}) => {
  const response = await axiosInstance.post<PQRS>(resourcePath + "/", {
    title,
    description,
  });

  return response.data;
};

export default { getAll, create };
