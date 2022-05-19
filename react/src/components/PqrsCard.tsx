import { PQRS } from "@/models/PQRS";
import moment from "moment";

type PqrsCardProps = {
  pqrs: PQRS;
};

const PqrsCard = ({ pqrs }: PqrsCardProps) => (
  <div className="card">
    <div className="card-header d-flex justify-content-between">
      <h6>Created by: {pqrs.personId}</h6>
      <h6>Status: {pqrs.status}</h6>
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

export default PqrsCard;
