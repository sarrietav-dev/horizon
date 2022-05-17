import { useState } from "react";
import styled from "styled-components";
import ReactDOM from "react-dom";

const Backdrop = styled.div`
  height: 100vh;
  width: 100vh;
  background-color: #000;
  opacity: 0.6;
  display: flex;
  justify-content: center;
  align-items: center;
  transition: all 0.3s ease-in-out;
  overflow: hidden;
  z-index: 999;
`;

const useModal = (children: JSX.Element) => {
  const modalElement = ReactDOM.createPortal(
    <Backdrop>{children}</Backdrop>,
    document.getElementById("modal-root")!
  );

  const [modal, setModal] = useState<JSX.Element | null>();

  const showModal = () => {
    setModal(modalElement);
  };

  const hideModal = () => {
    setModal(null);
  };

  return {
    modal,
    showModal,
    hideModal,
  };
};

export default useModal;
