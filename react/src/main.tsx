import React from 'react'
import ReactDOM from 'react-dom/client'

import "bootstrap/dist/css/bootstrap.css";
import "bootstrap-icons/font/bootstrap-icons.css";
import {BrowserRouter, Route, Routes} from "react-router-dom";

import HomeView from "@/views/HomeView";
import LoginView from "@/views/LoginView";

ReactDOM.createRoot(document.getElementById('root')!).render(
  <BrowserRouter>
    <Routes>
      <Route path="/" element={<HomeView/>}/>
      <Route path="/login" element={<LoginView/>}/>
    </Routes>
  </BrowserRouter>
)
