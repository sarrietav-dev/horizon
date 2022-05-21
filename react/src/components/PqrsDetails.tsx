import useModal from "@/hooks/useModal";
import { PQRS } from "@/models/PQRS";
import { PqrsStatus } from "@/models/PqrsStatus";
import { useAppDispatch } from "@/hooks/redux-hooks";
import { changeStatus } from "@/stores/reducers/pqrs.store";

interface PqrsProps {
  pqrs: PQRS;
}

const PqrsDetails = ({ pqrs }: PqrsProps) => {
  const ModalBody = () => (
    <>
      <h5>Descripción</h5>
      <p>{pqrs.description}</p>
    </>
  );

  const { modal, showModal } = useModal(<ModalBody />, {
    title: pqrs.title,
    modalFooter: <ModalFooter pqrs={pqrs} />,
  });

  return { modal, showModal };
};

const ModalFooter = ({ pqrs }: PqrsProps) => {
  const nextStatus = new PqrsStatus(pqrs.status).next();
  const dispatch = useAppDispatch();

  return (
    <div className="d-flex justify-content-between w-100">
      <p className="d-flex align-items-center text-center mb-0">
        Estado actual: {pqrs.status}
      </p>
      {pqrs.status !== "CLOSED" && (
        <button
          className="btn btn-primary"
          onClick={() =>
            dispatch(changeStatus({ id: pqrs.id, status: nextStatus }))
          }
        >
          Cambiar estado a {nextStatus}
        </button>
      )}
    </div>
  );
};

export default PqrsDetails;
