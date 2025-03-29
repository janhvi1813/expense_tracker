import React from "react";
import DataPieChart from "./components/DataPieChart";
import { Box, HStack ,VStack} from "@chakra-ui/react";
import DataTable from "./components/DataTable";
import Hero from "./components/Hero";
import Progressbar from "./components/Progressbar";

const App = () => {
  return (
    <>
      <Hero />
      <Progressbar/>
      
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
    </>
  );
};

export default App;
