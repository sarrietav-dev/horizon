import { Page } from "@/types/Page";
import { PQRS } from "@/models/PQRS";
import { createAsyncThunk, createSlice, PayloadAction } from "@reduxjs/toolkit";
import pqrsService from "@/services/pqrsService";

type InitialStateType = {
  page: Page<PQRS>;
};

const initialState: InitialStateType = {
  page: {
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
  },
};

export const pqrsSlice = createSlice({
  name: "pqrsPage",
  initialState,
  reducers: {
    setPage(state, action: PayloadAction<Page<PQRS>>) {
      state.page = action.payload;
    },
  },
  extraReducers: (builder) => {
    builder
      .addCase(
        getPqrsPage.fulfilled,
        (state, action: PayloadAction<Page<PQRS>>) => {
          state.page = action.payload;
        }
      )
      .addCase(createPqrs.fulfilled, (state, action) => {
        if (state.page.last && state.page.content.length < 5) {
          state.page.content.push(action.payload);
        } else if (state.page.content.length === 5) {
          state.page.totalPages += 1;
        }
      })
      .addCase(changeStatus.fulfilled, (state, action) => {
        const pqrsIndex = state.page.content.findIndex(
          (pqrs) => pqrs.id === action.payload.id
        );

        state.page.content[pqrsIndex].status = action.payload.status;
      });
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

export const createPqrs = createAsyncThunk(
  "pqrsPage/createPqrs",
  async ({ title, description }: { title: string; description: string }) =>
    await pqrsService.create({
      title,
      description,
    })
);

export const changeStatus = createAsyncThunk(
  "pqrsPage/changeStatus",
  async ({ id, status }: { id: number; status: string }) =>
    await pqrsService.changeStatus(id, status)
);
