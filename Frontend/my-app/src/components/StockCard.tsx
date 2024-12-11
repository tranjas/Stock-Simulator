import React from 'react';

interface CardProps {
  title: string;
  description: string;
  buttonText?: string;
  onButtonClick?: () => void;
}

const StockCard: React.FC<CardProps> = ({ title, description, buttonText, onButtonClick }) => {
  return (
    <div className="bg-white shadow-md rounded-lg overflow-hidden w-80">
      {/* Card Content */}
      <div className="p-4">
        <h3 className="text-lg font-semibold text-gray-800">{title}</h3>
        <p className="text-gray-600 mt-2">{description}</p>
      </div>

      {/* Optional Button */}
      {buttonText && onButtonClick && (
        <div className="bg-gray-100 p-4 flex justify-end">
          <button
            onClick={onButtonClick}
            className="px-4 py-2 bg-mint-green text-white rounded-lg hover:bg-mint-green-dark transition"
          >
            {buttonText}
          </button>
        </div>
      )}
    </div>
  );
};

export default StockCard;
