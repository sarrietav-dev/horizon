import User from "@/models/User";
import axiosInstance from "@/utils/axiosInstance";
import moment from "moment";

const createUser = async (user: User) => {
  await axiosInstance.post<User>("/user", {
    ...user,
    userData: {
      ...user.userData,
      birthDate: moment(user.userData.birthDate).format("YYYY-MM-DD"),
    },
  });
};

export default {
  createUser,
};
