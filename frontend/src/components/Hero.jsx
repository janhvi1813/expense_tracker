import { Flex, Box, Text } from "@chakra-ui/react";
import React from "react";
const Hero = () => {
  return (
    <>
      <Flex justifyContent={"center"} maxH={"100vh"} maxW={"100%"}>
        <Box>
          <Text fontWeight={"bold"} fontSize={"4xl"} color={"teal.500"}>
            ALL YOUR EXPENSES BELOW
          </Text>
        </Box>
      </Flex>
    </>
  );
};

export default Hero;
