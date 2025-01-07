import React from 'react'
import { db, auth } from "../Firebase";
import { getDoc, doc } from "firebase/firestore";
import axios from "axios";

const fetchUser = async () => {
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
      const user_info = await axios.get(`http://localhost:8080/users/${userId}`);
      return user_info;
}

export default fetchUser;