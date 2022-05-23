import { Button, Form, FormControl } from "react-bootstrap";

interface TheSearchBarProps {
  value: string;
  onChange: (value: string) => void;
  onSubmit: (query: string) => void;
}

const TheSearchBar = ({ onSubmit, onChange, value }: TheSearchBarProps) => {
  return (
    <Form
      className="d-flex"
      onSubmit={(e) => {
        e.preventDefault();
        onSubmit(value);
      }}
    >
      <FormControl
        type="search"
        placeholder="Search"
        className="me-2"
        aria-label="Search"
        value={value}
        onChange={(e) => onChange(e.target.value)}
      />
      <Button type="submit" variant="success">
        Buscar
      </Button>
    </Form>
  );
};

export default TheSearchBar;
