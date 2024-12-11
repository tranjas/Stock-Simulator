import React from 'react'
import Navbar from '../components/NavBar'
import DisplayStocks from '../components/DisplayStocks'


const HomePage: React.FC = () => {
  return (
    <div>
      <Navbar />
      <DisplayStocks />

    </div>
  )
}

export default HomePage