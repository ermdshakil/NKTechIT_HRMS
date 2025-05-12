<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Dashboard</title>
    <!-- Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <style>
        .dashboard-card {
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
            transition: transform 0.3s ease;
            margin-bottom: 2rem; /* Increased space between cards */
        }
        .dashboard-card:hover {
            transform: translateY(-5px);
        }
        .dashboard-title {
            font-size: 1.75rem;
            font-weight: bold;
            margin-bottom: 2rem; /* Increased space below title */
        }
        body {
            background-image: url("bg.jpg");
            background-repeat: no-repeat;
            background-size: cover;
            padding-bottom: 2rem; /* Add space at the bottom */
        }
        .btn {
            width: 100%; /* Make buttons full width */
        }
        .container-fluid{
        background-color: black;
        opacity: 0.9;
        border-radius: 30px;
        }
    </style>
</head>
<body>
    <!-- Main content -->
    <div class="container-fluid mt-4 text-center">
        <h2 class="dashboard-title text-center text-white pt-2">Admin Dashboard</h2>

        <div class="row">
            <!-- Employees Card -->
            <div class="col-md-4 mb-4">
                <form method="get" action="employeeData.html"> <!-- Redirect to the separate page -->
                    <div class="card dashboard-card p-4">
                        <div class="card-body">
                            <h5 class="card-title">Employees</h5>
                            <p class="card-text">Manage and view employee records.</p>
                            <button class="btn btn-primary" type="submit">View Employees</button>
                        </div>
                    </div>
                </form>
            </div>

            <!-- Departments Card -->
            <div class="col-md-4 mb-4">
            <form method="get" action="EmpDepartment.html">
                <div class="card dashboard-card p-4">
                    <div class="card-body">
                        <h5 class="card-title">Departments</h5>
                        <p class="card-text">Manage and view department information.</p>
                        <button class="btn btn-success" type="submit">View Departments</button>
                    </div>
                </div>
                </form>
            </div>

            <!-- Attendance Card -->
            <div class="col-md-4 mb-4">
            <form method="get" action="ComingSoon.html">
                <div class="card dashboard-card p-4">
                    <div class="card-body">
                        <h5 class="card-title">Attendance</h5>
                        <p class="card-text">Track employee attendance and leaves.</p>
                        <button class="btn btn-warning" type="submit">Track Attendance</button>
                    </div>
                </div>
                </form>
            </div>

            <!-- Payroll Card -->
            <div class="col-md-4 mb-4">
                <div class="card dashboard-card p-4">
                    <div class="card-body">
                        <h5 class="card-title">Payroll</h5>
                        <p class="card-text">Manage employee salaries and payroll.</p>
                        <a href="#" class="btn btn-danger">Manage Payroll</a>
                    </div>
                </div>
            </div>

            <!-- Insights Card -->
            <div class="col-md-4 mb-4">
                <div class="card dashboard-card p-4">
                    <div class="card-body">
                        <h5 class="card-title">HR Insights</h5>
                        <p class="card-text">Get data-driven insights and reports.</p>
                        <a href="#" class="btn btn-info">View Insights</a>
                    </div>
                </div>
            </div>

            <!-- Settings Card -->
            <div class="col-md-4 mb-4">
                <div class="card dashboard-card p-4">
                    <div class="card-body">
                        <h5 class="card-title">Performance</h5>
                        <p class="card-text">Performance of Employee based in userId.</p>
                        <a href="#" class="btn btn-secondary">Go to Performance</a>
                    </div>
                </div>
            </div>
        </div>
    </div>

    <!-- Bootstrap JS and dependencies -->
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.6/dist/umd/popper.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.min.js"></script>
</body>
</html>
