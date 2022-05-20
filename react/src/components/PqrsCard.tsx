import { PQRS } from "@/models/PQRS";
import moment from "moment";
import { useAppDispatch, useAppSelector } from "@/hooks/redux-hooks";
import { changeStatus } from "@/stores/reducers/pqrs.store";

type PqrsCardProps = {
  pqrs: PQRS;
};

const PqrsCard = ({ pqrs }: PqrsCardProps) => {
  const isAdmin = useAppSelector(
    (state) => state.auth.userRole === "ROLE_ADMIN"
  );

  const dispatch = useAppDispatch();

  return (
    <div className="card">
      <div className="card-header d-flex justify-content-between">
        <h6>Created by: {pqrs.personId}</h6>
        <div className="d-flex align-items-center gap-1 lg-gap-3">
          <h6>Status: {pqrs.status}</h6>
          <StatusModifierButton
            onClick={() =>
              dispatch(changeStatus({ id: pqrs.id, status: pqrs.status }))
            }
            status={pqrs.status}
          />
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
  onClick,
}: {
  status: string | "PENDING" | "APPROVED" | "IN_PROGRESS" | "CLOSED";
  onClick: () => void;
}): JSX.Element => {
  const cases: { [key: string]: { color: string; child: JSX.Element } } = {
    PENDING: {
      color: "bg-success",
      child: <i className="bi bi-check-circle"></i>,
    },
    APPROVED: {
      color: "bg-primary",
      child: <i className="bi bi-clipboard2-check"></i>,
    },
    IN_PROGRESS: {
      color: "bg-danger",
      child: <i className="bi bi-x-circle"></i>,
    },
    CLOSED: {
      color: "",
      child: <></>,
    },
  };

  return (
    <CircleButton color={cases[status].color} onClick={onClick}>
      {cases[status].child}
    </CircleButton>
  );
};

const CircleButton = ({
  children,
  color,
  onClick,
}: {
  children: JSX.Element;
  color: string;
  onClick: () => void;
}) => (
  <div
    style={{ height: "3vh", width: "3vh", cursor: "pointer" }}
    className={`rounded-circle ${color} d-flex justify-content-center align-items-center text-light`}
    onClick={onClick}
  >
    {children}
  </div>
);

export default PqrsCard;
