import { Page } from "@/types/Page";
import { PQRS } from "@/models/PQRS";
import { createSlice, PayloadAction } from "@reduxjs/toolkit";

type InitialStateType = Page<PQRS>;

const initialState: InitialStateType = {
  totalPages: 1,
  numberOfElements: 0,
  content: [],
  empty: true,
  first: true,
  last: false,
  number: 0,
  size: 0,
  sort: {
    empty: true,
    sorted: true,
    unsorted: false,
  },
  totalElements: 0,
};

export const pqrsSlice = createSlice({
  name: "pqrsPage",
  initialState,
  reducers: {
    setPage(state, action: PayloadAction<Page<PQRS>>) {
      state = action.payload;
    },
  },
});

export const { setPage } = pqrsSlice.actions;
export default pqrsSlice.reducer;
