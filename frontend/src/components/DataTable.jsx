import React, { useEffect, useState } from "react";
import { Table, Box } from "@chakra-ui/react";

const DataTable = () => {
  const [blockchain, setBlockchain] = useState([]);

  useEffect(() => {
    fetch("http://localhost:8080/blockchain/getBlockchain")
      .then((response) => response.json())
      .then((data) => setBlockchain(data))
      .catch((error) => console.error("Error fetching blockchain:", error));
  }, []);

  const mappedCategory = {
    swiggy: "food",
    zomato: "food",
    uber: "travel",
    ola: "travel",
    myntra: "shopping",
    amazon: "shopping",
    flipkart: "shopping",
    meesho: "shopping",
    netflix: "entertainment",
    hotstar: "entertainment",
    prime: "entertainment",
  };

  return (
    <Box
      bg="black"
      maxHeight="400px"
      overflowY="auto"
      width="100%"
      mt={6}
      mb={6} 
      p={4}
      borderRadius="lg"
      boxShadow="md"
      css={{
        "&::-webkit-scrollbar": {
          display: "none",
        },
        "-ms-overflow-style": "none",
        "scrollbar-width": "none", 
      }}
    >
      <Table.Root size="lg" variant="outline" maxW="100%">
        <Table.Header>
          <Table.Row>
            <Table.ColumnHeader>Vendor</Table.ColumnHeader>
            <Table.ColumnHeader>Category</Table.ColumnHeader>
            <Table.ColumnHeader textAlign="end">Amount</Table.ColumnHeader>
          </Table.Row>
        </Table.Header>
        <Table.Body>
          {blockchain.map((block, index) => (
            <Table.Row key={index}>
              <Table.Cell>{block.transaction.category}</Table.Cell>
              <Table.Cell>
                {mappedCategory[block.transaction.category.toLowerCase()] ||
                  "MISC"}
              </Table.Cell>
              <Table.Cell textAlign="end">
                {block.transaction.amount}
              </Table.Cell>
            </Table.Row>
          ))}
        </Table.Body>
      </Table.Root>
    </Box>
  );
};

export default DataTable;
