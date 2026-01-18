import axios from "axios";
import type {
  ElectricityData,
  FilteredElectricityData,
  PagedResponse,
} from "../types/commonTypes";

class BaseApi {
  async getElectricityData(page: number) {
    const response = await axios.get(
      "http://localhost:8080/api/electricity/allData",
      {
        auth: {
          username: "academy",
          password: "academy",
        },
        params: { page: page },
      },
    );
    return response?.data as PagedResponse<ElectricityData>;
  }

  async getElectricityDataFilteredByDate(page: number) {
    const response = await axios.get(
      "http://localhost:8080/api/electricity/grouped",
      {
        auth: {
          username: "academy",
          password: "academy",
        },
        params: { page: page },
      },
    );
    return response?.data as PagedResponse<FilteredElectricityData>;
  }
}

const api = new BaseApi();
export default api;
