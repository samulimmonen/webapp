import "../App.css";
import apiSet from "../api/baseApi";
import { useState, useEffect } from "react";
import { useSearchParams } from "react-router-dom";
import type {
  FilteredElectricityData,
  PagedResponse,
} from "../types/commonTypes";
import CircularProgress from "@mui/material/CircularProgress";
import Box from "@mui/material/Box";
import PaginationComponent from "../components/PaginationComponent";
import ElectricityTable from "../components/ElectricityTable";

function MainPage() {
  const [electricityData, setElectricityData] =
    useState<PagedResponse<FilteredElectricityData>>();

  // React Routerin hakusensorit: käytetään ?page= -parametria
  const [searchParams, setSearchParams] = useSearchParams();

  // Alusta nykyinen sivunumero URL:stä, muuten localStorage tai 1
  const stored = localStorage.getItem("pageNumber");
  const currentPage = (() => {
    const urlPage = searchParams.get("page");
    if (urlPage && !Number.isNaN(parseInt(urlPage))) return parseInt(urlPage);
    return stored ? parseInt(stored) : 1;
  })();

  // Latauksen tila
  const [isLoading, setIsLoading] = useState(false);

  // Hakee dataa, kun sivunumero muuttuu
  useEffect(() => {
    const getData = async () => {
      try {
        setIsLoading(true);
        const data = await apiSet.getElectricityDataFilteredByDate(currentPage);
        setElectricityData(data);
      } catch (err) {
        console.error(err);
        setIsLoading(false);
      }
      setIsLoading(false);
    };

    getData();
  }, [currentPage]);

  return (
    <>
      {isLoading && !electricityData && (
        <Box sx={{ display: "flex" }}>
          <CircularProgress />
        </Box>
      )}
      {!isLoading && electricityData && (
        <>
          <Box sx={{ display: "flex" }}>
            <h2>Electricity Data</h2>
          </Box>
          {electricityData.content && (
            <ElectricityTable
              data={electricityData.content}
              isLoading={isLoading}
            />
          )}
          <PaginationComponent
            currentPage={currentPage}
            totalPages={electricityData?.totalPages - 1}
            setSearchParams={setSearchParams}
          />
        </>
      )}
    </>
  );
}

export default MainPage;
