import { PQRS } from "@/models/PQRS";
import moment from "moment";
import { useAppDispatch, useAppSelector } from "@/hooks/redux-hooks";
import { changeStatus } from "@/stores/reducers/pqrs.store";
import { PqrsStatus } from "@/models/PqrsStatus";
import PqrsDetails from "@/components/PqrsDetails";

type PqrsCardProps = {
  pqrs: PQRS;
};

const PqrsCard = ({ pqrs }: PqrsCardProps) => {
  const isAdmin = useAppSelector(
    (state) => state.auth.userRole === "ROLE_ADMIN"
  );

  const { modal, showModal } = PqrsDetails({ pqrs });

  const dispatch = useAppDispatch();

  return (
    <>
      {modal}
      <div className="card" onClick={showModal}>
        <div className="card-header d-flex justify-content-between">
          <h6>Creado por: {pqrs.personId}</h6>
          <div className="d-flex align-items-center gap-1 lg-gap-3">
            <h6>Status: {pqrs.status}</h6>
            <StatusModifierButton
              onClick={() =>
                dispatch(
                  changeStatus({
                    id: pqrs.id,
                    status: new PqrsStatus(pqrs.status).next(),
                  })
                )
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
          Creado en: {moment(pqrs.creationDate).format("YYYY-MM-DD - HH:mm:ss")}
        </div>
      </div>
    </>
  );
};

const StatusModifierButton = ({
  status,
}: {
  status: string | "PENDING" | "APPROVED" | "IN_PROGRESS" | "CLOSED";
  onClick: () => void;
}): JSX.Element => {
  const cases: { [key: string]: { color: string; child: JSX.Element } } = {
    PENDING: {
      color: "bg-warning",
      child: <i className="bi bi-clock"></i>,
    },
    APPROVED: {
      color: "bg-success",
      child: <i className="bi bi-check-circle"></i>,
    },
    IN_PROGRESS: {
      color: "bg-primary",
      child: <i className="bi bi-clipboard2-check"></i>,
    },
    CLOSED: {
      color: "bg-danger",
      child: <i className="bi bi-x-circle"></i>,
    },
  };

  return (
    <CircleButton color={cases[status].color}>
      {cases[status].child}
    </CircleButton>
  );
};

const CircleButton = ({
  children,
  color,
}: {
  children: JSX.Element;
  color: string;
}) => (
  <div
    style={{ height: "4vh", width: "4vh", cursor: "pointer" }}
    className={`rounded-circle ${color} d-flex justify-content-center align-items-center text-light`}
  >
    {children}
  </div>
);

export default PqrsCard;
