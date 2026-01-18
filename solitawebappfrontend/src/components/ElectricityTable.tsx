import Table from "@mui/material/Table";
import TableBody from "@mui/material/TableBody";
import TableCell from "@mui/material/TableCell";
import TableContainer from "@mui/material/TableContainer";
import TableHead from "@mui/material/TableHead";
import TableRow from "@mui/material/TableRow";
import Paper from "@mui/material/Paper";
import type { FilteredElectricityData } from "../types/commonTypes";
import PlayArrowIcon from "@mui/icons-material/PlayArrow";
import { Box, CircularProgress, IconButton } from "@mui/material";

interface ElectricityTableProps {
  data: FilteredElectricityData[];
  isLoading: boolean;
}

export default function ElectricityTable(props: ElectricityTableProps) {
  const calculateTotalConsumption = (row: FilteredElectricityData) => {
    return row.Content.map((e) => e.consumptionAmount).reduce(
      (acc, val) => acc + val,
      0,
    );
  };

  const calculateTotalProduction = (row: FilteredElectricityData) => {
    return row.Content.map((e) => e.productionAmount).reduce(
      (acc, val) => acc + val,
      0,
    );
  };

  const calculateAveragePrice = (row: FilteredElectricityData) => {
    return (
      row.Content.map((e) => e.hourlyPrice).reduce((acc, val) => acc + val, 0) /
      row.Content.length
    );
  };

  return (
    <>
      {props.isLoading && (
        <Box sx={{ display: "flex" }}>
          <CircularProgress />
        </Box>
      )}
      <TableContainer component={Paper} sx={{ margin: 2 }}>
        <Table sx={{ minWidth: 650 }}>
          <TableHead>
            <TableRow>
              <TableCell align="left">Date</TableCell>
              <TableCell align="right">Total consumption</TableCell>
              <TableCell align="right">Total production</TableCell>
              <TableCell align="right">Average price</TableCell>
              <TableCell align="right" />
            </TableRow>
          </TableHead>
          <TableBody>
            {props.data.map((row) => (
              <TableRow
                key={row.Date}
                sx={{ "&:last-child td, &:last-child th": { border: 0 } }}
              >
                <TableCell align="left">{row.Date}</TableCell>
                <TableCell align="right">
                  {calculateTotalConsumption(row).toFixed(2)}
                </TableCell>
                <TableCell align="right">
                  {calculateTotalProduction(row).toFixed(2)}
                </TableCell>
                <TableCell align="right">
                  {calculateAveragePrice(row).toFixed(2)}
                </TableCell>
                <TableCell align="right">
                  {
                    <IconButton>
                      <PlayArrowIcon />
                    </IconButton>
                  }
                </TableCell>
              </TableRow>
            ))}
          </TableBody>
        </Table>
      </TableContainer>
    </>
  );
}
