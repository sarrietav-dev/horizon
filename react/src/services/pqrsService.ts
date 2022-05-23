import axiosInstance from "@/utils/axiosInstance";
import { PQRS } from "@/models/PQRS";
import { Page } from "@/types/Page";

const resourcePath = "/pqrs";

const getAll = async (page?: number, title?: string) => {
  const response = await axiosInstance.get<Page<PQRS>>(resourcePath, {
    params: {
      page: page ?? 0,
      title,
    },
  });
  return response.data;
};

const create = async ({
  title,
  description,
}: {
  title: string;
  description: string;
}) => {
  const response = await axiosInstance.post<PQRS>(resourcePath, {
    title,
    description,
  });

  return response.data;
};

const changeStatus = async (id: number, status: string) => {
  const response = await axiosInstance.patch<PQRS>(
    `${resourcePath}/${id}/status`,
    {
      status,
    }
  );

  return response.data;
};

export default { getAll, create, changeStatus };
