import React from "react";
import { Snackbar, Alert, Slide } from "@mui/material";

function SlideUp(props) {
  return <Slide {...props} direction="up" />;
}

export default function Toast({ open, message, severity = "success", onClose }) {
  return (
    <Snackbar
      open={open}
      autoHideDuration={4000}
      onClose={onClose}
      TransitionComponent={SlideUp}
      anchorOrigin={{ vertical: "bottom", horizontal: "center" }}
    >
      <Alert
        onClose={onClose}
        severity={severity}
        variant="filled"
        sx={{ borderRadius: 2, fontWeight: 500 }}
      >
        {message}
      </Alert>
    </Snackbar>
  );
}
