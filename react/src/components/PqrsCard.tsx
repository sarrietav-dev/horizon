import { PQRS } from "@/models/PQRS";
import moment from "moment";
import { useAppSelector } from "@/hooks/redux-hooks";

type PqrsCardProps = {
  pqrs: PQRS;
};

const PqrsCard = ({ pqrs }: PqrsCardProps) => {
  const isAdmin = useAppSelector(
    (state) => state.auth.userRole === "ROLE_ADMIN"
  );

  return (
    <div className="card">
      <div className="card-header d-flex justify-content-between">
        <h6>Created by: {pqrs.personId}</h6>
        <div className="d-flex align-items-center gap-1 lg-gap-3">
          <h6>Status: {pqrs.status}</h6>
          <StatusModifierButton status={pqrs.status} />
        </div>
      </div>
      <div className="card-body">
        <h5 className="card-title">{pqrs.title}</h5>
        <p className="card-text">{pqrs.description}</p>
      </div>
      <div className="card-footer">
        Created on: {moment(pqrs.creationDate).format("YYYY-MM-DD - HH:mm:ss")}
      </div>
    </div>
  );
};

const StatusModifierButton = ({
  status,
}: {
  status: string | "PENDING" | "APPROVED" | "IN_PROGRESS" | "CLOSED";
}): JSX.Element => {
  switch (status) {
    case "PENDING":
      return (
        <CircleButton color="bg-success">
          <i className="bi bi-check-circle"></i>
        </CircleButton>
      );
    case "APPROVED":
      return (
        <CircleButton color="bg-primary">
          <i className="bi bi-clipboard2-check"></i>
        </CircleButton>
      );
    case "IN_PROGRESS":
      return (
        <CircleButton color="bg-danger">
          <i className="bi bi-x-circle text-white"></i>
        </CircleButton>
      );
    default:
      return <></>;
  }
};

const CircleButton = ({
  children,
  color,
}: {
  children: JSX.Element;
  color: string;
}) => (
  <div
    style={{ height: "3vh", width: "3vh", cursor: "pointer" }}
    className={`rounded-circle ${color} d-flex justify-content-center align-items-center text-light`}
  >
    {children}
  </div>
);

export default PqrsCard;
