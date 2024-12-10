import React from "react";

const Button = ({ label, onClick}) => {
    return (
        <button
        className="button bg-gray-200 text-black p-4 m-2 rounded-lg shadow-md hover:bg-gray-300"
        onClick={() => onClick(label)}
        
        >
            {label}
        </button>
    )
}

export default Button;