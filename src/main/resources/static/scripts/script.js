console.log("Script loaded");

document.addEventListener("DOMContentLoaded", () => {
    const html = document.documentElement;
    const themeToggleButton = document.querySelector("#theme_change_button span");

    let currentTheme = localStorage.getItem("theme") || "light"; // Get saved theme or default to light
    applyTheme(currentTheme);

    // Event listener for theme change
    document.querySelector("#theme_change_button").addEventListener("click", () => {
        currentTheme = currentTheme === "dark" ? "light" : "dark";
        applyTheme(currentTheme);
    });

    function applyTheme(theme) {
        localStorage.setItem("theme", theme);
        html.classList.toggle("dark", theme === "dark"); // Efficient class toggle
        themeToggleButton.textContent = theme === "light" ? "Dark" : "Light";
    }
});
