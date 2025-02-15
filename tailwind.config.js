/** @type {import('tailwindcss').Config} */
module.exports = {
  content: ["./src/main/resources/**/*.{html,js}"],
  theme: {
    extend: {},
  },
  safelist: [
    { pattern: /.*/ }, // <-- This forces all Tailwind classes to be included
  ],
  plugins: [],
  darkMode: "selector",
}

