interface UserData {
  id?: number;
  name: string;
  surname: string;
  phoneNumber: string;
  birthDate: Date;
}

export default interface User {
  email: string;
  password: string;
  role: string;
  userData: UserData;
}
