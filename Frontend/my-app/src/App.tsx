import React from 'react';
import { Routes, Route, BrowserRouter } from 'react-router-dom';
import LoginPage from './pages/LoginPage';
import SignUpPage from './pages/SignUpPage';
import './index.css';
import HomePage from './pages/HomePage';
import AccountPage from './pages/AccountPage';
import PortfolioPage from './pages/PortfolioPage';

function App() {
  return (
    <div className="App">
      <BrowserRouter>
        <Routes>
          <Route path="/" element={<LoginPage/>} />
          <Route path="/signup" element={<SignUpPage/>} />
          <Route path="/home" element={<HomePage/>} />
          <Route path="/account" element={<AccountPage/>} />
          <Route path="/portfolio" element={<PortfolioPage/>} />
        </Routes>
      </BrowserRouter>
    </div>
  );
}

export default App;
