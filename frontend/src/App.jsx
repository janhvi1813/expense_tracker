import React from "react";
import DataPieChart from "./components/DataPieChart";
import { Box, HStack } from "@chakra-ui/react";
import DataTable from "./components/DataTable";
import Hero from "./components/Hero";
import Progressbar from "./components/Progressbar";
import ValidateButton from "./components/ValidateButton";
import AiReport from "./components/AiReport";

const App = () => {
  return (
    <>
    <Box  minH="100vh" p={4}>
      <Hero />

        <Box>
        <HStack>
        <Progressbar/>
        <ValidateButton/>
        </HStack>
        </Box>

      <HStack
        justifyContent={"center"}
        width={"100%"}
        spacing={4}
        minH={"100vh"}
        alignItems={"center"}
      >
        <DataPieChart />
        <DataTable />
        </HStack>
        <AiReport />
        </Box>
      

    </>
  );
};

export default App;
