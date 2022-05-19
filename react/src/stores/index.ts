import { configureStore } from "@reduxjs/toolkit";
import pqrsSlice from "@/stores/reducers/PqrsStore";

export const store = configureStore({
  reducer: {
    pqrs: pqrsSlice,
  },
  devTools: true,
});

export type RootState = ReturnType<typeof store.getState>;
export type AppDispatch = typeof store.dispatch;
