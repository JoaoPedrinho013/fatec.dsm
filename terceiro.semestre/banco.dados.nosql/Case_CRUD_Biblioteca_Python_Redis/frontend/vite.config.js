import { defineConfig } from "vite";
import react from "@vitejs/plugin-react";

export default defineConfig({
  plugins: [react()],
  server: {
    port: 3000,
    proxy: {
      "/livros": "http://localhost:5000",
      "/auth": "http://localhost:5000",
      "/usuarios": "http://localhost:5000",
    },
  },
});
