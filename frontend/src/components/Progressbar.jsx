import { useState, useEffect } from "react";
import { Progress, Stack, Text, Container, Box } from "@chakra-ui/react";
import axios from "axios";

const Progressbar = () => {
  const [monthlyLimit, setMonthlyLimit] = useState(5000);
  const [totalExpenses, setTotalExpenses] = useState(0);

  useEffect(() => {
    const fetchTotalExpenses = async () => {
      try {
        const response = await axios.get(
          "http://localhost:8080/blockchain/totalspent"
        );
        setTotalExpenses(response.data || 0);
      } catch (error) {
        console.error("Error fetching total expenses:", error);
      }
    };
    fetchTotalExpenses();
  }, []);

  const percentage = Math.min((totalExpenses / monthlyLimit) * 100, 100);
  const isExceeded = totalExpenses > monthlyLimit;

  const colorPalettes = ["green"];
  return (
    <>
      <Container
        display="flex"
        justifyContent="center"
        alignItems="center"
        mt={50}
        mb={-150}
        bg="white"
      >
        <Text fontSize="2xl" fontWeight="bold" mb={4} color="#14B8A6">
          Expense Tracker
        </Text>

        <Stack gap="2" align="flex-start">
          {colorPalettes.map((colorPalette) => (
            <Stack
              align="center"
              key={colorPalette}
              direction="row"
              gap="10"
              px="4"
            >
              <Progress.Root
                value={percentage}
                width="400px"
                defaultValue={40}
                variant="outline"
                colorPalette={isExceeded ? "red" : "green"}
                transition="width 1s ease-in-out, background-color 0.5s ease-in-out"
              >
                <Progress.Track>
                  <Progress.Range />
                </Progress.Track>
              </Progress.Root>
            </Stack>
          ))}
        </Stack>
        <div className="p-6 max-w-md mx-auto bg-white shadow-lg rounded-lg">
          <div className="flex justify-between text-lg font-semibold mb-3">
            <Text fontSize="2xl" fontWeight="medium" color="red" mb={1}>
              Limit: ₹{monthlyLimit}
            </Text>
            <Text fontSize="2xl" fontWeight="medium" mb={4} color="green">
              Spent: ₹{totalExpenses}
            </Text>
          </div>
          {isExceeded && (
            <Box
              mt={4}
              p={3}
              bg="red.500"
              color="white"
              fontWeight="bold"
              borderRadius="md"
            >
              ⚠️ You have exceeded your monthly limit by ₹
              {totalExpenses - monthlyLimit}!
            </Box>
          )}
        </div>
      </Container>
    </>
  );
};

export default Progressbar;
