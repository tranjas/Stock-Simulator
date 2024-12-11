import axios from "axios";

const fetchAllStocks = async () => {
  const APIKEY = process.env.REACT_APP_POLY_API_KEY;

  if (!APIKEY) {
    throw new Error("API Key is missing. Please check your .env configuration.");
  }

  try {
    const { data } = await axios.get(
      `https://api.polygon.io/v3/reference/tickers?market=stocks&active=true&limit=100&apiKey=${APIKEY}`
    );
    return data; // Return raw JSON data
  } catch (err: any) {
    throw new Error(err?.message || "An error occurred while fetching data.");
  }
};

export default fetchAllStocks;
