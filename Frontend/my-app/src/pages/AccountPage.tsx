import React, { useEffect, useState } from 'react';
import Navbar from '../components/NavBar';
import fetchUser from '../api/fetchUser';

const AccountPage: React.FC = () => {
  const [firstName, setFirstName] = useState('');
  const [lastName, setLastName] = useState('');
  const [dateOfBirth, setDateOfBirtch] = useState('');
  const [email, setEmail] = useState('');

  useEffect(() => {
    const displayUser = async () => {
      const user = (await fetchUser()).data;
      setFirstName(user.firstName);
      setLastName(user.lastName);
      setDateOfBirtch(user.dateOfBirth);
      setEmail(user.email);
    };
    displayUser();
  }, []);

  return (
    <div className="min-h-screen bg-gray-50">
      <Navbar />
      <div className="max-w-4xl mx-auto mt-10 p-6 bg-white shadow-md rounded-lg">
        <h1 className="text-2xl font-semibold text-gray-800 mb-6">Account Details</h1>
        <div className="space-y-4">
          <div className="flex items-center">
            <span className="font-medium text-gray-600 w-32">First Name:</span>
            <p className="text-gray-800">{firstName}</p>
          </div>
          <div className="flex items-center">
            <span className="font-medium text-gray-600 w-32">Last Name:</span>
            <p className="text-gray-800">{lastName}</p>
          </div>
          <div className="flex items-center">
            <span className="font-medium text-gray-600 w-32">Date of Birth:</span>
            <p className="text-gray-800">{dateOfBirth}</p>
          </div>
          <div className="flex items-center">
            <span className="font-medium text-gray-600 w-32">Email:</span>
            <p className="text-gray-800">{email}</p>
          </div>
        </div>
      </div>
    </div>
  );
};

export default AccountPage;
