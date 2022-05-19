import PqrsCard from "@/components/PqrsCard";
import { useEffect, useState } from "react";
import { PQRS } from "@/models/PQRS";
import pqrsService from "@/services/pqrsService";
import useModal from "@/hooks/useModal";
import PqrsCreationForm from "@/components/PqrsCreationForm";
import { Page } from "@/types/Page";
import { Pagination } from "react-bootstrap";

const HomeView = () => {
  const [pqrsPage, setPqrsPage] = useState<Page<PQRS>>();
  const { modal, showModal, hideModal } = useModal(
    <PqrsCreationForm
      onSubmit={async (e, title, description) => {
        e.preventDefault();
        await pqrsService.create({ description, title });
        hideModal();
      }}
    />,
    {
      title: "Crear una PQRS",
    }
  );

  useEffect(() => {
    pqrsService.getAll().then((response) => setPqrsPage(response));
  }, []);

  const paginationItems = () =>
    Array(pqrsPage?.totalPages)
      .fill(0)
      .map((value, index) => (
        <Pagination.Item
          key={index}
          active={index + 1 === (pqrsPage?.number ?? 0) + 1}
        >
          {index + 1}
        </Pagination.Item>
      ));

  return (
    <main className="px-5 py-5">
      {modal}
      <PQRSList list={pqrsPage?.content ?? []} />
      <Pagination>{paginationItems()}</Pagination>
      <CreateButton onClick={() => showModal()} />
    </main>
  );
};

const CreateButton = ({ onClick }: { onClick: () => void }) => {
  return (
    <div
      style={{ height: "10vh", width: "10vh", bottom: 30, right: 30 }}
      className="position-fixed rounded-circle bg-primary d-flex justify-content-center align-items-center"
      onClick={onClick}
    >
      <i className="bi bi-plus text-white fs-1"></i>
    </div>
  );
};

const PQRSList = ({ list }: { list: PQRS[] }) => (
  <>
    {list.map((pqrs) => (
      <div className="my-5" key={pqrs.id}>
        <PqrsCard pqrs={pqrs} />
      </div>
    ))}
  </>
);

export default HomeView;
