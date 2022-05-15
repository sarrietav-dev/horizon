import React from 'react'
import {BrowserRouter, Route, Routes} from "react-router-dom";
import HomeView from "./views/HomeView";
import LoginView from "./views/LoginView";
import AuthProvider from "./context/authContext";
import RequiredAuth from "@/utils/RequiredAuth";

function App() {
  return (
    <AuthProvider>
      <BrowserRouter>
        <Routes>
          <Route path="/" element={
            <RequiredAuth>
              <HomeView/>
            </RequiredAuth>
          }/>
          <Route path="/login" element={<LoginView/>}/>
        </Routes>
      </BrowserRouter>
    </AuthProvider>
  )
}

export default App
