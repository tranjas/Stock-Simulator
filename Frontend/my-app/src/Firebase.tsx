// Import the functions you need from the SDKs you need
import { initializeApp } from "firebase/app";
import { getAnalytics } from "firebase/analytics";
import { getAuth } from "firebase/auth";
import { getFirestore } from "firebase/firestore";
// TODO: Add SDKs for Firebase products that you want to use
// https://firebase.google.com/docs/web/setup#available-libraries

// Your web app's Firebase configuration
// For Firebase JS SDK v7.20.0 and later, measurementId is optional
const firebaseConfig = {
  apiKey: "AIzaSyD-ge5NS5SW7AuhJtn_YsYL5cRCSByyG9U",
  authDomain: "stocksimulator-72308.firebaseapp.com",
  projectId: "stocksimulator-72308",
  storageBucket: "stocksimulator-72308.firebasestorage.app",
  messagingSenderId: "854997219725",
  appId: "1:854997219725:web:fc5f797703199f561a65c4",
  measurementId: "G-2BHCVJDC6N"
};

// Initialize Firebase
const app = initializeApp(firebaseConfig);
const analytics = getAnalytics(app);

export const auth = getAuth(app);
export const db = getFirestore(app);