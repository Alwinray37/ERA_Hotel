import { defineConfig } from 'vite'
import react from '@vitejs/plugin-react'

export default defineConfig({
  plugins: [react()],
  server: {
    port: 3000,       // your preferred port
    strictPort: true  // fail if port is already in use instead of switching
  }
})
