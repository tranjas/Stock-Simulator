import React from "react";
import { db } from "../Firebase";
import { getDoc, doc } from "firebase/firestore";
import axios from "axios";

const fetchBuyingPower = async () => {
  try {
    const userDoc = await getDoc(doc(db, "users", "customId"));

    if (!userDoc.exists()) {
      throw new Error("No such user document!");
    }
    const userId = userDoc.data().customId;

    const portfolioResponse = await axios.get(`/portfolios/${userId}`);
    const buyingPower = portfolioResponse.data.buyingPower;
    console.log("Buying Power:", buyingPower);
    return buyingPower;
  } catch (err) {
    console.log(err);
  }
};

export default fetchBuyingPower;
