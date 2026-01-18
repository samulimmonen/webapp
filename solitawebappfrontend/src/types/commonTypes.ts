export interface ElectricityData {
  id: number;
  startTime: string;
  consumptionAmount: number;
  productionAmount: number;
  hourlyPrice: number;
  date: string;
}

export interface FilteredElectricityData {
  Date: string;
  Content: ElectricityData[];
}

export interface PagedResponse<T> {
  content: T[];
  currentPage: number;
  totalElements: number;
  size: number;
  pageSize: number;
  empty: boolean;
  totalPages: number;
}
