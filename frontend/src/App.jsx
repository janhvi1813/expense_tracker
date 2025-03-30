import React from "react";
import DataPieChart from "./components/DataPieChart";
import { Box, HStack, VStack } from "@chakra-ui/react";
import DataTable from "./components/DataTable";
import Hero from "./components/Hero";
import Progressbar from "./components/Progressbar";
import AiReport from "./components/AiReport";
const App = () => {
  return (
    <>
      <Hero />
      <Progressbar />
      <HStack
        justifyContent={"center"}
        width={"100%"}
        spacing={4}
        minH={"80vh"}
        alignItems={"center"}
      >
        <DataPieChart />
        <DataTable />
      </HStack>
      <AiReport />
    </>
  );
};

export default App;
