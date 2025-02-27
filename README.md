# KeepInTouch (KIT) - Contact Manager  

🚀 **Start Your Journey to Seamless Connections!**  

KeepInTouch (KIT) is a modern contact management web application designed to help users organize, store, and access their contacts effortlessly. It provides an intuitive interface with robust authentication, validation, and search functionalities.

---

## 🌟 Features  

- **User Authentication**  
  - Traditional login/signup with email & password  
  - OAuth-based authentication (Google, GitHub)  
  - Secure password hashing & session management  

- **Contact Management**  
  - Add, view, update, and delete contacts  
  - Store profile pictures for contacts  
  - Pagination for better browsing  

- **Advanced Search & Filtering**  
  - Search contacts by name, email, phone, etc.  
  - Dynamic filters for quick access  

- **User-Friendly UI**  
  - Fully responsive, dark/light mode support  
  - Smooth & interactive UI with Tailwind CSS & Flowbite  
  - Tooltips & icons for enhanced user experience  

- **Security & Data Validation**  
  - Form validation using Spring Boot & Java annotations  
  - CSRF protection (with controlled exceptions)  
  - Role-based access control (Spring Security)  

---

## 🛠️ Tech Stack  

### **Backend:**  
- **Spring Boot** - Java-based backend framework  
- **Spring Security** - Authentication & Authorization  
- **Spring Data JPA (Hibernate)** - ORM & database management  
- **Thymeleaf** - Dynamic HTML rendering  

### **Frontend:**  
- **Thymeleaf** - Server-side template engine  
- **Tailwind CSS + Flowbite** - Modern, responsive UI  
- **JavaScript (ES6)** - Dynamic client-side interactions  

### **Database:**  
- **MySQL** - Relational database  
- **JPA/Hibernate** - ORM for database interaction  

---

## 📦 Installation
### **1. Clone the Repository**
```sh
git clone https://github.com/yourusername/KeepInTouch.git
cd KeepInTouch
```

2. Configure the Database
Update application.properties with your database credentials:
```properties
spring.datasource.url=jdbc:mysql://localhost:3306/kit
spring.datasource.username=root
spring.datasource.password=yourpassword
```

3. Run the Application
```sh
mvn spring-boot:run
The app will start at http://localhost:8080
```

---

## 🔥 Usage
### User Signup/Login
* Open http://localhost:8080/signup to create an account
* Login via http://localhost:8080/login
### Managing Contacts
* Navigate to http://localhost:8080/user/contacts
* Use the Add Contact page to save new contacts
* Update or delete contacts from the View Contacts page

---

## 💡 Upcoming Features
* Export contacts as CSV/PDF
* Integration with third-party APIs
* User profile & settings

---

## 🤝 Contributing
Contributions are welcome!
* Fork the repository
* Create a feature branch (`git checkout -b feature-name`)
* Commit your changes (`git commit -m 'Added new feature'`)
* Push to your branch (`git push origin feature-name`)
* Open a Pull Request

---

## 📜 License
* MIT License - Feel free to use & modify!

---

## 🌐 Live Demo
🚧 Coming Soon 🚧

---

## 📩 Contact
* For queries, feel free to reach out at devanshusachdev83@gmail.com
