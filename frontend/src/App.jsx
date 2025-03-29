import React from "react";
import DataPieChart from "./components/DataPieChart";
import { Box, HStack } from "@chakra-ui/react";
import DataTable from "./components/DataTable";

const App = () => {
  return (
    <>
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
