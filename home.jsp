<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Welcome to Our Food Ordering System</title>
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
            background: url('bg_3.jpeg') no-repeat center center fixed;
            background-size: cover;
            height: 100vh;
            width: 100%;
            animation: fadeIn 2s ease-in-out; /* Fade-in animation for the background */
        }

        /* Transparent Box */
        .header {
            position: absolute;
            top: 50%;
            left: 50%;
            transform: translate(-50%, -50%);
            text-align: center;
            color: white;
            font-family: "Montserrat", sans-serif;
            z-index: 1;
            padding: 30px;
            border-radius: 18px;
            background: rgba(0, 0, 0, 0.5);
            width: 100%;
            max-width: 900px;
            animation: slideIn 1.5s ease-out; /* Slide-in animation for the header */
        }

        .header h1 {
            font-weight: 300;
            font-size: 46px;
        }

        .header h2 {
            font-weight: 600;
            font-size: 60px;
        }

        .header p {
            font-weight: 300;
            font-size: 20px;
            line-height: 1.6;
            color: #f8f9fa;
            font-family: "Open Sans", sans-serif;
            margin-top: 20px;
            letter-spacing: 0.5px;
        }

        /* Button styles */
        .button, .logout-button {
            display: inline-block;
            border-radius: 1px;
            background-color: #32CD32;
            border: none;
            color: red;
            text-align: center;
            font-size: 25px;
            padding: 10px;
            width: 281px;
            transition: all 0.5s;
            cursor: pointer;
            margin: 5px;
            opacity: 0;
            animation: fadeInButton 2s ease forwards; /* Fade-in animation for buttons */
        }

        .button span, .logout-button span {
            cursor: pointer;
            display: inline-block;
            position: relative;
            transition: 0.5s;
        }

        .button span:after, .logout-button span:after {
            content: '\00bb';
            position: absolute;
            opacity: 0;
            top: 0;
            right: -20px;
            transition: 0.5s;
        }

        .button:hover span, .logout-button:hover span {
            padding-right: 25px;
        }

        .button:hover span:after, .logout-button:hover span:after {
            opacity: 1;
            right: 0;
        }

        /* Logout button */
        .logout-button {
            background-color: #ff6347;
            font-size: 20px;
            width: 200px;
        }

        canvas {
            position: fixed;
            top: 0;
            left: 0;
        }

        /* Keyframes for animations */
        @keyframes fadeIn {
            0% {
                opacity: 0;
            }
            100% {
                opacity: 1;
            }
        }

        @keyframes slideIn {
            0% {
                opacity: 0;
                transform: translate(-50%, -60%);
            }
            100% {
                opacity: 1;
                transform: translate(-50%, -50%);
            }
        }

        @keyframes fadeInButton {
            0% {
                opacity: 0;
                transform: translateY(20px);
            }
            100% {
                opacity: 1;
                transform: translateY(0);
            }
        }
    </style>
</head>
<body>
    <div class="bg">
        <div class="header">
            <h1><b>Welcome Back!</b></h1>
            <h1>Home Of Delicious Foods!</h1>
            <p>Indulge in a world of flavors where every dish is crafted with passion and precision.
                From the sizzling aroma of our gourmet entrees to the delicate sweetness of freshly baked treats,
                each bite is a symphony of taste, designed to elevate your senses. Whether you're longing for a hearty,
                soul-soothing meal or craving a burst of sweetness, we serve more than just food — we serve experiences.
                Every plate is a journey, and every flavor tells a story. Let us satisfy not just your hunger, 
                but your deepest cravings for culinary perfection.<br><br>
                ORDER, PAY AND GET FOOD DELIVERED AT YOUR DOOR! <br><br></p>

            <a href="catagories.html" class="button"><span>Order Now</span></a>
           
        </div>
    </div>
    <canvas id="canvas"></canvas>
</body>
</html>
