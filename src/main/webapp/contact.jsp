<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page isELIgnored="false" %>
<!DOCTYPE html>
<html>
<head>
    <title>Contact Us - E-Books</title>
    <%@ include file="admin/all_css.jsp" %>
</head>
<body style="background-color: #f0f1f2;">
    <%@ include file="admin/navbar.jsp" %>

    <div class="container mt-5">
        <h2 class="text-center">Contact Us</h2>
        <div class="row justify-content-center mt-4">
            <div class="col-md-6">
                <div class="card shadow-sm">
                    <div class="card-body">
                        <p><strong>Email:</strong> supportrajeshkumarbeheracool@gmail.com</p>
                        <p><strong>Phone:</strong> +91-9583250735</p>
                        <p><strong>Address:</strong> 106/B, Tihidi, Bhadrak, Odisha, India</p>
                        <hr>
                        <h5>Feedback / Query</h5>
                        <form method="post" action="#">
                            <div class="form-group">
                                <label>Name</label>
                                <input type="text" class="form-control" required>
                            </div>
                            <div class="form-group">
                                <label>Email</label>
                                <input type="email" class="form-control" required>
                            </div>
                            <div class="form-group">
                                <label>Message</label>
                                <textarea class="form-control" rows="4" required></textarea>
                            </div>
                            <button class="btn btn-primary">Send</button>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>

    <%@ include file="admin/footer.jsp" %>
</body>
</html>
