/** @type {import('tailwindcss').Config} */
module.exports = {
  content: ["./src/**/*.{html,js,kt}"],
  theme: {
    extend: {
      backgroundColor: {
        // These are not used...
        // they exist only for reference
        // Access them using the following syntax:
        // bg-some-thing-1
        // bg-some-thing-2
        'some-thing-1': '#FFFFFF',
        'some-thing-2': '#2b2b2b',

      }
    },
  },
  plugins: [],
}
