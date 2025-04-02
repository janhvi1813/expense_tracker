import React, { useState, useRef, useEffect } from "react";
import axios from "axios";
import { Box, Button, Card, Text, Spinner } from "@chakra-ui/react";

const AiReport = () => {
  const [report, setReport] = useState("");
  const [loading, setLoading] = useState(false);
  const cardRef = useRef(null); // Reference for scrolling

  const fetchExpenseReport = async () => {
    setLoading(true);
    setReport(""); // Clear previous report while loading

    // Scroll down immediately when loading starts
    setTimeout(() => {
      if (cardRef.current) {
        cardRef.current.scrollIntoView({ behavior: "smooth", block: "start" });
      }
    }, 100);

    try {
      const response = await axios.get(
        "http://localhost:8080/api/getExpenseReport"
      );
      setReport(response.data);
    } catch (error) {
      console.error("Error fetching expense report:", error);
      setReport("Failed to generate report.");
    }
    setLoading(false);
  };

  useEffect(() => {
    if (cardRef.current) {
      cardRef.current.scrollIntoView({ behavior: "smooth", block: "start" });
    }
  }, [loading, report]); // Scrolls when loading starts and when report appears

  return (
    <>
      <Box
        maxW={"100%"}
        display="flex"
        justifyContent={"center"}
        alignItems={"center"}
        flexDirection={"column"}
        spaceY={"4"} // Keeping the spacing as you had it
      >
        <Text fontSize={"3xl"} fontWeight={"bold"} fontFamily={"heading"} color="#F6F1DE">
          AI-Generated Expense Summary
        </Text>
        <Button
          onClick={fetchExpenseReport}
          disabled={loading}
          variant={"subtle"}
          size={"xl"}
        >
          Generate
        </Button>
        <Box ref={cardRef} /> {/* Scroll reference */}
        {loading && <Spinner size="sm" />}
        {!loading && report && (
          <Card.Root ref={cardRef}>
            <Card.Body>
              <Text
                textAlign={"center"}
                fontSize={"xl"}
                fontFamily={"sans-serif"}
              >
                {report}
              </Text>
            </Card.Body>
          </Card.Root>
        )}
      </Box>
    </>
  );
};

export default AiReport;
