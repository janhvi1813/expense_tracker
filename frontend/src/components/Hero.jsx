import { Flex, Box, Text } from "@chakra-ui/react";
import React from "react";
const Hero = () => {
  return (
    <>
      <Flex justifyContent={"center"} maxH={"100vh"} maxW={"100%"}>
        <Box  width="115" height="51">
          <Text color="#F6F1DE" textStyle="4xl" fontWeight={"bold"}>Your financial trends, simplified!</Text>
        </Box>
      </Flex>
    </>
  );
};

export default Hero;
