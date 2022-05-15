import axiosInstance from "@/utils/axiosInstance";
import { PQRS } from "@/models/PQRS";

const resourcePath = "/pqrs";

const getAll = async () => {
  const response = await axiosInstance.get<PQRS[]>(resourcePath);
  return response.data;
};

export default { getAll };
