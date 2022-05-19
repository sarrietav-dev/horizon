import { Page } from "@/types/Page";
import { PQRS } from "@/models/PQRS";
import { createAsyncThunk, createSlice, PayloadAction } from "@reduxjs/toolkit";
import pqrsService from "@/services/pqrsService";

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
  extraReducers: (builder) => {
    builder.addCase(
      getPqrsPage.fulfilled,
      (state, action: PayloadAction<Page<PQRS>>) => {
        state = action.payload;
      }
    );
  },
});

export default pqrsSlice.reducer;

/**
 * Fetches the given page from the server.
 *
 * If the page number isn't provided, it defaults to 0.
 */
export const getPqrsPage = createAsyncThunk(
  "pqrsPage/getPqrsPage",
  async (pageNumber?: number) => {
    return await pqrsService.getAll(pageNumber);
  }
);
