import { ButtonGroup, IconButton, Typography } from "@mui/material";
import FirstPageIcon from "@mui/icons-material/FirstPage";
import KeyboardArrowLeft from "@mui/icons-material/KeyboardArrowLeft";
import KeyboardArrowRight from "@mui/icons-material/KeyboardArrowRight";
import LastPageIcon from "@mui/icons-material/LastPage";

interface PaginationComponentProps {
  currentPage: number;
  totalPages: number;
  setSearchParams: (params: { page: string }) => void;
}

function PaginationComponent(props: PaginationComponentProps) {
  return (
    <>
      <ButtonGroup variant="contained" sx={{ bgcolor: "white" }}>
        <IconButton
          disabled={props.currentPage === 1}
          onClick={() => {
            props.setSearchParams({ page: "1" });
          }}
        >
          <FirstPageIcon />
        </IconButton>
        <IconButton
          disabled={
            props.currentPage === 1 || props.currentPage > props.totalPages
          }
          onClick={() => {
            props.setSearchParams({ page: (props.currentPage - 1).toString() });
          }}
        >
          <KeyboardArrowLeft />
        </IconButton>
        <Typography
          style={{ margin: "0 15px", alignSelf: "center", color: "black" }}
        >
          {" "}
          Page {props.currentPage} of {props.totalPages}{" "}
        </Typography>
        <IconButton
          disabled={props.currentPage >= props.totalPages}
          onClick={() => {
            props.setSearchParams({ page: (props.currentPage + 1).toString() });
          }}
        >
          <KeyboardArrowRight />
        </IconButton>
        <IconButton
          disabled={props.currentPage === props.totalPages}
          onClick={() => {
            props.setSearchParams({ page: props.totalPages.toString() });
          }}
        >
          <LastPageIcon />
        </IconButton>
      </ButtonGroup>
    </>
  );
}

export default PaginationComponent;
