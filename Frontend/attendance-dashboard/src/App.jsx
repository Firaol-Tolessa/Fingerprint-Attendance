import React, { useState } from "react";
import {
  Container,
  Box,
  Button,
  Grid,
  Card,
  CardContent,
  Typography,
  CircularProgress,
  TextField, // 1. Import TextField
} from "@mui/material";

export default function App() {
  const [employees, setEmployees] = useState([]);
  const [page, setPage] = useState(0);
  const [hasMore, setHasMore] = useState(false);
  const [loading, setLoading] = useState(false);

  // 2. Add new state for the filter input
  const [filterId, setFilterId] = useState("");
  // This state "latches" the filter when you click search
  const [activeFilterId, setActiveFilterId] = useState("");

  /**
   * Main fetch function.
   * @param {boolean} loadMore - Is this a "load more" action?
   * @param {string} currentFilter - The filter to apply for this fetch.
   */
  const fetchEmployees = async (loadMore = false, currentFilter) => {
    setLoading(true);
    try {
      // 3. Reset page to 0 if it's a new search, otherwise use the next page
      const currentPage = loadMore ? page : 0;

      // 4. Build the URL dynamically
      let url = `http://localhost:8080/api/attendance?page=${currentPage}&size=2`;
      if (currentFilter) {
        url += `&employeeId=${currentFilter}`;
      }
      
      const res = await fetch(url);
      if (!res.ok) throw new Error(`HTTP ${res.status}`);

      const data = await res.json();

      // 5. If it's a new search, replace employees. If loading more, append.
      setEmployees((prev) =>
        loadMore ? [...prev, ...data.content] : data.content
      );
      
      // 6. Update pagination state
      setPage(data.pageable.pageNumber + 1); // Set the *next* page to fetch
      setHasMore(!data.last);

    } catch (err) {
      console.error("Error fetching employees:", err);
    } finally {
      setLoading(false);
    }
  };

  // 7. Handle "Get Employees" click
  const handleSearch = () => {
    // "Latch" the filter value from the text box
    setActiveFilterId(filterId);
    // Fetch the first page with the new filter
    fetchEmployees(false, filterId);
  };

  // 8. Handle "Load More" click
  const handleLoadMore = () => {
    // Fetch the next page using the "latched" filter
    fetchEmployees(true, activeFilterId);
  };

  return (
    <Container maxWidth="md" sx={{ mt: 4 }}>
      {/* Header */}
      <Box display="flex" justifyContent="space-between" alignItems="center" mb={2}>
        <Typography variant="h5" fontWeight={600}>
          Employee Dashboard
        </Typography>
        <Button
          variant="contained"
          onClick={handleSearch} // Use new handler
          disabled={loading}
        >
          {loading && !hasMore ? "Loading..." : "Get Employees"}
        </Button>
      </Box>

      {/* 9. Add the Filter Text Box */}
      <Box mb={3}>
        <TextField
          label="Filter by Employee ID"
          variant="outlined"
          fullWidth
          value={filterId}
          onChange={(e) => setFilterId(e.target.value)}
          disabled={loading}
        />
      </Box>

      {/* Employee Cards */}
      <Grid container spacing={2}>
        {employees.map((emp, index) => (
          <Grid item xs={12} sm={6} md={4} key={`${emp.eventHash}-${index}`}>
            <Card
              variant="outlined"
              sx={{
                borderRadius: 2,
                "&:hover": { boxShadow: 3 },
                transition: "0.2s",
              }}
            >
              <CardContent>
                <Typography variant="h6">Employee: {emp.employeeId}</Typography>
                <Typography color="text.secondary" fontSize={14}>
                  Device: {emp.deviceId}
                </Typography>
                <Typography color="text.secondary" fontSize={13} mt={1}>
                  {/* Handle potential invalid date string from parsing error */}
                  Time: {new Date(emp.timestamp).toLocaleString()}
                </Typography>
                <Typography
                  color="text.secondary"
                  fontSize={12}
                  mt={1}
                  sx={{ wordBreak: "break-all" }}
                >
                  Hash: {emp.eventHash}
                </Typography>
              </CardContent>
            </Card>
          </Grid>
        ))}
      </Grid>

      {/* Load More */}
      {employees.length > 0 && (
        <Box mt={3} textAlign="center">
          {hasMore ? (
            <Button
              variant="outlined"
              onClick={handleLoadMore} // Use new handler
              disabled={loading}
            >
              {loading ? <CircularProgress size={20} /> : "Load More"}
            </Button>
          ) : (
            !loading && (
              <Typography color="text.secondary" mt={1}>
                No more records
              </Typography>
            )
          )}
        </Box>
      )}
    </Container>
  );
}