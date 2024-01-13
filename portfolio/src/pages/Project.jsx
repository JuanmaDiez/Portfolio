import { useParams } from "react-router-dom";

function Project() {
  const params = useParams();
  return <div>This is project number {params.id}</div>;
}

export default Project;
