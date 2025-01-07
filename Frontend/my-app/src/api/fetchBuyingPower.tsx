import React from "react";
import { db, auth } from "../Firebase";
import { getDoc, doc } from "firebase/firestore";
import axios from "axios";

const fetchBuyingPower = async () => {
  try {
    const user = auth.currentUser;

    if (!user) {
      throw new Error("No user is currently signed in.");
    }

    // Fetch the user's document from Firestore
    const userDoc = await getDoc(doc(db, "users", user.uid));

    if (!userDoc.exists()) {
      throw new Error("No such user document!");
    }

    // Get the custom ID for the portfolio
    const userId = userDoc.data().customId;
    console.log("Custom ID:", userId);

    // Fetch the portfolio data using the custom ID
    const portfolioResponse = await axios.get(`http://localhost:8080/portfolios/${userId}`);

    // Access the buyingPower field from the API response
    const buyingPower = portfolioResponse.data.buyingPower;
    console.log("Buying Power:", buyingPower);

    return buyingPower;
  } catch (err: any) {
    console.error("Error fetching buying power:", err.message);
    return null; // Return null or handle the error appropriately
  }
};

export default fetchBuyingPower;
