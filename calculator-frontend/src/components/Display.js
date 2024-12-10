import React from "react";

const Display = ({ value }) => {
  return (
    <div
      className="display p-2 text-lg bg-gray-800 text-right rounded-lg mb-4 text-white"
    >
      {value}
    </div>
  );
};

export default Display;
