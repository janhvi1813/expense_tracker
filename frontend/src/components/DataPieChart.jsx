import React, { useState, useEffect } from "react";
import {
  PieChart,
  Pie,
  Cell,
  Tooltip,
  Legend,
  ResponsiveContainer,
} from "recharts";
import { Box, Text } from "@chakra-ui/react";

const DataPieChart = () => {
  const [categorizedData, setCategorizedData] = useState([]);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState(null);
  const [endAngle, setEndAngle] = useState(-90); // Start from a single line

  const COLORS = [
    "#FF6384",
    "#36A2EB",
    "#FFCE56",
    "#4CAF50",
    "#FF9800",
    "#9C27B0",
    "#00BCD4",
  ];

  useEffect(() => {
    const fetchData = async () => {
      try {
        const response = await fetch(
          "http://localhost:8080/blockchain/getCategorizedAmount"
        );

        if (!response.ok) {
          throw new Error(`Http Error! Status: ${response.status}`);
        }

        const data = await response.json();
        setCategorizedData(data);
        setTimeout(() => setEndAngle(270), 300); // Animate from line to full circle
      } catch (err) {
        setError(err.message);
      } finally {
        setLoading(false);
      }
    };

    fetchData();
  }, []);

  if (loading)
    return (
      <Text fontSize="lg" fontWeight="bold" color="gray.700" mt={4}>
        Loading Chart Data...
      </Text>
    );
  if (error)
    return (
      <Text fontSize="lg" fontWeight="bold" color="red.500" mt={4}>
        Error Loading Data: {error}
      </Text>
    );
  if (!categorizedData.length)
    return (
      <Text fontSize="lg" fontWeight="bold" color="gray.700" mt={4}>
        No Data Available
      </Text>
    );

  return (
    <Box
      display="flex"
      flexDirection="column"
      alignItems="center"
      width="100%"
      height="100%"
      mt={6}
      mb={6}
      p={4}
    >
      <ResponsiveContainer width="100%" height={400}>
        <PieChart>
          <Pie
            data={categorizedData}
            dataKey="amount"
            nameKey="category"
            cx="50%"
            cy="50%"
            startAngle={-90}
            endAngle={endAngle} // Animate drawing effect
            outerRadius={120}
            fill="#8884d8"
            label={({ name, percent }) =>
              `${name.charAt(0).toUpperCase() + name.slice(1)}: ${(
                percent * 100
              ).toFixed(0)}%`
            }
          >
            {categorizedData.map((entry, index) => (
              <Cell
                key={`cell-${index}`}
                fill={COLORS[index % COLORS.length]}
              />
            ))}
          </Pie>
          <Tooltip
            formatter={(value, name) => [
              `₹${value}`,
              name.charAt(0).toUpperCase() + name.slice(1),
            ]}
          />
          <Legend
            formatter={(value) =>
              value.charAt(0).toUpperCase() + value.slice(1)
            }
            wrapperStyle={{
              fontSize: "14px",
              fontWeight: "bold",
              color: "#555",
              marginTop: "10px",
            }}
          />
        </PieChart>
      </ResponsiveContainer>
    </Box>
  );
};

export default DataPieChart;
