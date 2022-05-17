import { FormEvent } from "react";

export interface FormProps {
  onSubmit: (e: FormEvent, ...args: string[]) => void;
}
