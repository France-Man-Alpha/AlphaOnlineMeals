<%-- 
    Document   : signin
    Created on : Sep 9, 2024, 12:54:27 PM
    Author     : HP ELITEBOOK
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Login </title>
    <style>
        /* Import and Reset */
        * {
            margin: 0;
            padding: 0;
            box-sizing: border-box;
            color: #fff;
        }

        /* Background */
        .bg {
            background: url('/bg_3.jpeg') no-repeat center center fixed;
            background-size: cover;
            height: 100vh;
            width: 100%;
        }
        
        .form-container {
            position: absolute;
            top: 50%;
            left: 50%;
            transform: translate(-50%, -50%);
            background: rgba(0, 0, 0, 0.3); /* Semi-transparent background */
            padding: 20px;
            border-radius: 10px;
            width: 80%;
            max-width: 600px;
        }

        .form-container .header {
            font-family: "Montserrat", sans-serif;
            color: #fff;
            text-align: center;
            margin-bottom: 20px;
        }

        .form-group {
            margin-bottom: 15px;
        }

        .form-control {
            width: 100%;
            padding: 10px;
            border-radius: 5px;
            border: 1px solid #ccc;
            background-color: rgba(255, 255, 255, 0.2); /* Transparent background */
            color: #fff; /* Text color to ensure readability */
        }

        .form-control::placeholder {
            color: #ddd; /* Placeholder text color */
        }

        .btn-success {
            background-color: #32CD32;
            border: none;
            color: #FFFFFF;
            padding: 10px;
            border-radius: 5px;
            cursor: pointer;
            width: 100%;
        }

        .btn-success:hover {
            background-color: #28a745;
        }

        .checkbox label {
            color: #fff;
            display: block;
            margin: 10px 0;
        }

        .register {
            color: #fff;
            text-decoration: none;
        }

        .register:hover {
            text-decoration: underline;
        }
        
        /* Admin Button */
        .admin-button {
    position: absolute;
    top: 10px;
    right: 10px; /* Changed from left to right */
    background-color: red;
    border: none;
    color: white;
    padding: 10px 20px;
    font-size: 18px;
    border-radius: 5px;
    cursor: pointer;
    z-index: 2;
} 
        
        
        
        
        
        
        
        
        
        
        
        
        
        
    </style>
</head>
<body>
    
    <!-- Admin Button -->
    <a href="admin_login.html" class="admin-button">Admin</a>
    <div class="bg">
        <div class="form-container">
            <h1 class="header">Login To Your Account!</h1>
           <form action="SigninServlet" method="post">
                <div class="form-group">
                    <label for="InputEmail">Email address</label>
                    <input type="email" name="email" class="form-control" id="InputEmail" placeholder="Email" required>
                </div>
                <div class="form-group">
                    <label for="InputPassword">Password</label>
                    <input type="password" name="password" class="form-control" id="InputPassword" placeholder="Password" required>
                </div>
                <div class="checkbox">
                    <label>
                        <input type="checkbox"> Remember Me
                    </label>
                </div>
                <button type="submit" class="btn-success">Submit</button>
                <div align="center">New user? <a class="register" href="signup.html"><b><i>Register here</i></b></a></div>
            </form>
        </div>
    </div>
</body>
</html>
