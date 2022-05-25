import PqrsCard from "@/components/PqrsCard";

import { PQRS } from "@/models/PQRS";
import useModal from "@/hooks/useModal";
import PqrsCreationForm from "@/components/PqrsCreationForm";
import { Container, Navbar, Pagination } from "react-bootstrap";
import { useAppDispatch, useAppSelector } from "@/hooks/redux-hooks";
import {
  createPqrs,
  getPqrsPage,
  getPqrsPageByTitle,
} from "@/stores/reducers/pqrs.store";
import { useEffect, useState } from "react";
import TheSearchBar from "@/components/TheSearchBar";
import CreateUserButton from "@/components/CreateUserButton";

const HomeView = () => {
  const dispatch = useAppDispatch();
  const { page: pqrsPage, resource } = useAppSelector((state) => state.pqrs);
  const [query, setQuery] = useState("");

  const { modal, showModal, hideModal } = useModal(
    <PqrsCreationForm
      onSubmit={async (e, title, description) => {
        e.preventDefault();
        await dispatch(createPqrs({ title, description }));
        hideModal();
      }}
    />,
    {
      title: "Crear una PQRS",
    }
  );

  useEffect(() => {
    dispatch(getPqrsPage());
  }, [dispatch]);

  const PaginationItems = () => (
    <>
      {Array(pqrsPage?.totalPages)
        .fill(0)
        .map((value, index) => (
          <Pagination.Item
            key={index}
            active={index + 1 === (pqrsPage?.number ?? 0) + 1}
            onClick={() =>
              resource === "/"
                ? dispatch(getPqrsPage(index))
                : dispatch(
                    getPqrsPageByTitle({
                      title: query,
                      pageNumber: index,
                    })
                  )
            }
          >
            {index + 1}
          </Pagination.Item>
        ))}
    </>
  );

  const handleSearchSubmit = (query: string) => {
    dispatch(getPqrsPageByTitle({ title: query }));
  };

  const searchBarProps = {
    onSubmit: handleSearchSubmit,
    value: query,
    onChange: setQuery,
  };

  return (
    <>
      <Navbar collapseOnSelect expand="lg" bg="primary" variant="dark">
        <Container fluid>
          <Navbar.Brand>Horizon</Navbar.Brand>
          <Navbar.Toggle
            aria-controls="navbarScroll"
            style={{ justifySelf: "flex-end" }}
          />
          <Navbar.Collapse id="navbarScroll">
            <CreateUserButton />
            <TheSearchBar {...searchBarProps} />
          </Navbar.Collapse>
        </Container>
      </Navbar>
      <main className="px-5">
        {modal}
        <PQRSList list={pqrsPage?.content ?? []} />
        <Pagination>
          <PaginationItems />
        </Pagination>
        <CreateButton onClick={() => showModal()} />
      </main>
    </>
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
