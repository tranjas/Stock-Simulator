import React, { useState, useEffect } from "react";
import fetchAllStocks from "../api/fetchAllStocks";

const DisplayStocks: React.FC = () => {
  const [stockData, setStockData] = useState<any>(null);
  const [loading, setLoading] = useState<boolean>(true);
  const [error, setError] = useState<string | null>(null);

  useEffect(() => {
    const fetchData = async () => {
      try {
        const data = await fetchAllStocks(); // Fetch data
        setStockData(data); // Update state with the fetched data
      } catch (e: any) {
        setError(e.message); // Handle error
      } finally {
        setLoading(false); // Stop loading state
      }
    };

    fetchData();
  }, []);

  if (loading) return <p>Loading...</p>;
  if (error) return <p>Error: {error}</p>;

  // ticker, name
  return (
    <div>
    <p>Stock List</p>
      {/* <ul>
        {stockData.map((stock: any) => (
          <li key={stock.id}>
            <h2>{stock.name}</h2>
            <p>{stock.ticker}</p>
          </li>
          ))}
        </ul> */}
    </div>

  );
};

export default DisplayStocks;
