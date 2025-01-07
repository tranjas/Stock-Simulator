import React, { useEffect, useState } from 'react';
import { useNavigate } from 'react-router-dom';
import fetchBuyingPower from '../api/fetchBuyingPower';

const Navbar: React.FC = () => {
  const[buyingPower, setBuyingPower] = useState('');
  const navigate = useNavigate();

  useEffect( ()=> {
    const fetchData = async () => {
      const data = await fetchBuyingPower();
      setBuyingPower(data);
    }
    fetchData();
  }, [])

  return (
    <nav className="bg-mint-green shadow-md mb-40">
      <div className="container mx-auto px-4 py-4 flex justify-between items-center">
        {/* Logo */}
        <div
          className="text-2xl font-bold text-gray-800 cursor-pointer"
          onClick={() => navigate('/')}
        >
          Stock Simulator
        </div>
        <div >
          <p className='text-gray-700'>Buying Power: {buyingPower}</p>
        </div>
        {/* Links */}
        <div className="space-x-6">
          <button
            className="text-gray-700 hover:text-gray-900 transition"
            onClick={() => navigate('/home')}
          >
            Home
          </button>
          <button
            className="text-gray-700 hover:text-gray-900 transition"
            onClick={() => navigate('/account')}
          >
            Account
          </button>
          <button
            className="text-gray-700 hover:text-gray-900 transition"
            onClick={() => navigate('/portfolio')}
          >
            Portfolio
          </button>
        </div>
      </div>
    </nav>
  );
};

export default Navbar;
