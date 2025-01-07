import React from "react";
import { auth, db } from "../Firebase";
import { doc, getDoc } from "firebase/firestore";
const updateBuyingPower = async (Amount: Number) => {
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
  } catch (err: any) {
    console.log(err);
  }
};

export default updateBuyingPower;
