import React, { useState } from "react";
import Display from "./Display";
import Button from "./Button";

const Calculator = () => {
  const [input, setInput] = useState("");
  const [result, setResult] = useState(null);

  const handleButtonClick = async (label) => {
    if (label === "=") {
      try {
        const response = await fetch(
          `http://localhost:8080/api/calculator/evaluate?expression=${encodeURIComponent(
            input
          )}`
        );

        const data = await response.json();

        if (response.ok) {
          setResult(data.result); //set result on success
          setInput(""); // Clear input
        } else {
          setResult(data.error); // Display error message
        }
      } catch (error) {
        setResult("Error: Unable to connect to server"); // Network or server error
      }
    } else if (label === "C") {
      setInput(""); // Clear input
      setResult(null);
    } else {
      setInput(input + label); // append button label to input
    }
  };

  const buttons = [
    ["7", "8", "9", "/"],
    ["4", "5", "6", "*"],
    ["1", "2", "3", "-"],
    ["0", ".", "=", "+"],
    ["C"],
  ];

  return (
    <div className="calculator p-4 max-w-sm mx-auto bg-white rounded-lg shadow-lg">
      <Display value={result !== null ? result : input || "0"} />
      <div className="buttons grid grid-cols-4 gap-2">
        {buttons.flat().map((button) => (
          <Button key={button} label={button} onClick={handleButtonClick} />
        ))}
      </div>
    </div>
  );
};

export default Calculator;
