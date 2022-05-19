import { configureStore } from "@reduxjs/toolkit";
import pqrsSlice from "@/stores/reducers/pqrs.store";
import authSlice from "@/stores/reducers/auth.store";

export const store = configureStore({
  reducer: {
    pqrs: pqrsSlice,
    auth: authSlice,
  },
  devTools: true,
});

export type RootState = ReturnType<typeof store.getState>;
export type AppDispatch = typeof store.dispatch;
