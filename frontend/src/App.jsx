import React from "react";
import DataPieChart from "./components/DataPieChart";
import { Box, HStack } from "@chakra-ui/react";
import DataTable from "./components/DataTable";
import Hero from "./components/Hero";
import Progressbar from "./components/Progressbar";
import ValidateButton from "./components/ValidateButton";

const App = () => {
  return (
    <>
    <Box bg="#3E3F5B" minH="100vh" p={4}>
      <Hero />
        <Box bg="#3E3F5B">
        <HStack>
        <Progressbar/>
        <ValidateButton/>
        </HStack>
        </Box>
      <HStack
        justifyContent={"center"}
        minHeight={"100vh"}
        width={"100%"}
        spacing={4}
        alignItems={"center"}
      >
        <DataPieChart />
        <DataTable />
        </HStack>
        </Box>
    </>
  );
};

export default App;
