import {useState} from "react";
import Modal from "react-bootstrap/Modal";

/**
 * Handles the creation and visibility of a modal.
 *
 * The modal must be rendered inside the component that will be used, or it won't be rendered.
 *
 * @param children The component that will be rendered inside the modal.
 * @param config Extra parameters to customise the appearance of the modal like the title and the footer elements.
 * @return the modals and two methods to show and to hide the modal.
 *
 */
const useModal = (
  children: JSX.Element,
  config?: { title?: string; modalFooter?: JSX.Element }
) => {
  const [show, setShow] = useState(false);

  const showModal = () => {
    setShow(true);
  };

  const hideModal = () => {
    setShow(false);
  };

  const modal = (
    <Modal show={show} onHide={hideModal} centered scrollable backdrop>
      {config?.title && (
        <Modal.Header closeButton>
          <Modal.Title>{config.title}</Modal.Title>
        </Modal.Header>
      )}
      <Modal.Body>{children}</Modal.Body>
      {config?.modalFooter && <Modal.Footer>{config.modalFooter}</Modal.Footer>}
    </Modal>
  );

  return {
    modal,
    showModal,
    hideModal,
  };
};

export default useModal;
