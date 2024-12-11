import React, { useState } from "react";
import { useNavigate } from "react-router-dom";
import { auth } from "../Firebase";
import { signInWithEmailAndPassword } from "firebase/auth";

const LoginPage: React.FC = () => {
  const navigate = useNavigate();
  const [email, setEmail] = useState<string>("");
  const [password, setPassword] = useState<string>("");
  const [errorMessage, setErrorMessage] = useState<string>("");

  const handleLogin = async () => {
    try {
      const user = await signInWithEmailAndPassword(auth, email, password);
      console.log("Sign in successful:", user);
      navigate("/home");
    } catch (err: any) {
      setErrorMessage(err.message || "An error occurred.");
    }
  };

  return (
    <div className="flex items-center justify-center min-h-screen bg-mint-green">
      <div className="w-full max-w-md p-8 bg-white rounded-lg shadow-lg">
        <h1 className="text-2xl font-bold text-center text-gray-700 mb-6">
          Welcome to Stock Simulator
        </h1>
        <p className="text-center text-gray-500 mb-6">
          A place where you can practice trading in real-time!
        </p>
        <form className="space-y-4" onSubmit={(e) => e.preventDefault()}>
          <div>
            <label className="block text-gray-600 mb-1">Email:</label>
            <input
              type="text"
              name="email"
              value={email}
              onChange={(e) => setEmail(e.target.value)}
              className="w-full p-2 border border-gray-300 rounded-lg focus:outline-none focus:ring-2 focus:ring-mint-green"
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
          {errorMessage && (
            <p className="text-red-500 text-sm mt-2">{errorMessage}</p>
          )}
          <button
            type="button"
            onClick={handleLogin}
            className="w-full bg-mint-green text-black py-2 px-4 rounded-lg border hover:bg-green-500 transition"
          >
            Login
          </button>
        </form>
        <button
          onClick={() => navigate("/signup")}
          className="w-full text-mint-green text-center py-2 px-4 rounded-lg border border-mint-green hover:bg-green-100 transition mt-4"
        >
          Sign Up
        </button>
      </div>
    </div>
  );
};

export default LoginPage;
