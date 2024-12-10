import React, { useState } from 'react';
import { useNavigate } from 'react-router-dom';
import { auth, db } from '../Firebase';
import axios from 'axios';
import { createUserWithEmailAndPassword } from 'firebase/auth';
import { doc, setDoc } from "firebase/firestore";

interface UserPayload {
  firstName: string;
  lastName: string;
  email: string;
  dateOfBirth: string;
}

const SignUpPage: React.FC = () => {
  const navigate = useNavigate();
  
  const [email, setEmail] = useState<string>('');
  const [password, setPassword] = useState<string>('');
  const [firstName, setFirstName] = useState<string>('');
  const [lastName, setLastName] = useState<string>('');
  const [dateOfBirth, setDateOfBirth] = useState<string>('');
  const [errorMessage, setErrorMessage] = useState<string>('');
  const [confirmPassword, setConfirmPassword] = useState<string>('');

  const handleSignUp = async (e: React.FormEvent<HTMLFormElement>) => {
    e.preventDefault();
    if (email === '' || password === '' || firstName === '' || lastName === '' || dateOfBirth === '') {
        setErrorMessage("Insufficient Information");
        return;
    }
    if (password !== confirmPassword) {
      setErrorMessage("Password is not the same");
      return;
    }
    try {
      const userCredential = await createUserWithEmailAndPassword(auth, email, password);
      const firebaseUser = userCredential.user;

      const userPayload: UserPayload = {
        firstName,
        lastName,
        email,
        dateOfBirth,
      };
      try {
        const createUser = await axios.post("http://localhost:8080/users", userPayload);
        await setDoc(doc(db, "users", firebaseUser.uid), {
          customId: createUser.data.id,
        });
        await axios.post(`http://localhost:8080/portfolios/${createUser.data.id}`);
        navigate('/');
        alert("Sign Up Successful");
      } catch(err: any) {
        setErrorMessage(err.message || "An error occurred.");
      }
    } catch (err: any) {
      setErrorMessage(err.message || "An error occurred.");
    }
  };
  
  return (
    <div className="flex items-center justify-center min-h-screen bg-mint-green">
      <div className="w-full max-w-md p-8 bg-white rounded-lg shadow-lg">
        <h1 className="text-2xl font-bold text-center text-gray-700 mb-6">Sign Up</h1>
        <form onSubmit={handleSignUp} className="space-y-4">
          <div>
            <label className="block text-gray-600 mb-1">Email:</label>
            <input
              type="email"
              name="email"
              value={email}
              onChange={(e) => setEmail(e.target.value)}
              className="w-full p-2 border border-gray-300 rounded-lg focus:outline-none focus:ring-2"
            />
          </div>
          <div>
            <label className="block text-gray-600 mb-1">Password:</label>
            <input
              type="password"
              name="password"
              value={password}
              onChange={(e) => setPassword(e.target.value)}
              className="w-full p-2 border border-gray-300 rounded-lg focus:outline-none focus:ring-2 focus:ring-mint-green"
            />
          </div>
          <div>
            <label className="block text-gray-600 mb-1">Retype Password:</label>
            <input
              type="password"
              name="confirmPassword"
              value={confirmPassword}
              onChange={(e) => setConfirmPassword(e.target.value)}
              className="w-full p-2 border border-gray-300 rounded-lg focus:outline-none focus:ring-2 focus:ring-mint-green"
            />
          </div>
          <div>
            <label className="block text-gray-600 mb-1">First Name:</label>
            <input
              type="text"
              name="firstName"
              value={firstName}
              onChange={(e) => setFirstName(e.target.value)}
              className="w-full p-2 border border-gray-300 rounded-lg focus:outline-none focus:ring-2 focus:ring-mint-green"
            />
          </div>
          <div>
            <label className="block text-gray-600 mb-1">Last Name:</label>
            <input
              type="text"
              name="lastName"
              value={lastName}
              onChange={(e) => setLastName(e.target.value)}
              className="w-full p-2 border border-gray-300 rounded-lg focus:outline-none focus:ring-2 focus:ring-mint-green"
            />
          </div>
          <div>
            <label className="block text-gray-600 mb-1">Date of Birth:</label>
            <input
              type="date"
              name="dateOfBirth"
              value={dateOfBirth}
              onChange={(e) => setDateOfBirth(e.target.value)}
              className="w-full p-2 border border-gray-300 rounded-lg focus:outline-none focus:ring-2 focus:ring-mint-green"
            />
          </div>
          {errorMessage && (
            <p className="text-red-500 text-sm mt-2">{errorMessage}</p>
          )}
          <div className="flex justify-between items-center">
            <button
              type="submit"
              className="w-full text-black py-2 px-4 border border-mint-green rounded-lg hover:bg-green-500 transition"
            >
              Register
            </button>
          </div>
          <button
            type="button"
            onClick={() =>  { setErrorMessage(''); navigate('/'); }}
            className="w-full text-mint-green text-center py-2 px-4 rounded-lg border border-mint-green hover:bg-green-100 transition mt-2"
          >
            Back to Login
          </button>
        </form>
      </div>
    </div>
  );
};

export default SignUpPage;
