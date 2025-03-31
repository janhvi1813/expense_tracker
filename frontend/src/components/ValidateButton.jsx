import { Container, Button, Box, HStack, Text } from "@chakra-ui/react";
import React, { useState } from "react";
import { toast, ToastContainer } from "react-toastify";
import "react-toastify/dist/ReactToastify.css";

const ValidateButton = () => {
  const [isValidated, setIsValidated] = useState(false); 

  const handleValidation = async () => {
    try {
      const response = await fetch("http://localhost:8080/blockchain/validate");
      const data = await response.json();

      if (data === true) {
        setIsValidated(true); 
        toast.success("✅ Transaction is valid!");
      } else {
        setIsValidated(false);
        toast.error("❌ Transaction validation failed.");
      }
    } catch (error) {
      setIsValidated(false);
      toast.error("⚠️ Error fetching validation.");
    }
  };

  return (
    <>
      <Container display="flex" justifyContent="center" alignItems="center" mt={50} mb={-150}>
        <Box bg="#F6F1DE" w="100%" p="4" color="white" minH="17vh">
          <Text color="black" textStyle="3xl" fontWeight={"medium"}>Validate your transactions</Text>
          <HStack>
            <Button variant="outline" size="lg" onClick={handleValidation} rounded="14" bg="black" color="white" mt={2.5}>
              Validate Transaction
            </Button>
          </HStack>
          {/* ✅ Show this box only when validation is successful */}
          {isValidated && (
            <Box mt={2} p={3} bg="green.500" color="white" fontWeight="bold" borderRadius="md" >
              ✅ Your blockchain of transactions is validated!
            </Box>
          )}
           {!isValidated && (
            <Box mt={2} p={3} bg="red.400" color="white" fontWeight="bold" borderRadius="md" >
              ⚠️ Your blockchain of transactions is not validated!
            </Box>
          )}

        </Box>
      </Container>
      {/* Toast Message Container */}
      <ToastContainer position="top-right" autoClose={3000} />
    </>
  );
};

export default ValidateButton;
