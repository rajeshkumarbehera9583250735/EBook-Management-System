<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false" %>

<!-- Top blue bar -->
<div class="container-fluid" style="height: 10px; background-color: #303f9f"></div>

<!-- Branding and login/logout buttons -->
<div class="container-fluid p-3 btn-light">
    <div class="row">
        <div class="col-md-3 text-success">
            <h3><i class="fa-solid fa-book"></i> E-Book Store</h3>
        </div>
        <div class="col-md-6"></div>
        <div class="col-md-3">
            <c:if test="${not empty userobj}">
                <a href="#" class="btn btn-success"><i class="fas fa-user"></i> ${userobj.name}</a>
                <a data-toggle="modal" data-target="#exampleModalCenter" class="btn btn-primary text-white">
                    <i class="fa-solid fa-right-to-bracket"></i> Logout
                </a>
            </c:if>
            <c:if test="${empty userobj}">
                <a href="<%= request.getContextPath() %>/login.jsp" class="btn btn-success">
                    <i class="fa-solid fa-right-to-bracket"></i> Login
                </a>
                <a href="<%= request.getContextPath() %>/register.jsp" class="btn btn-primary text-white">
                    <i class="fa-solid fa-user-plus"></i> Register
                </a>
            </c:if>
        </div>
    </div>
</div>

<!-- Logout modal -->
<div class="modal fade" id="exampleModalCenter" tabindex="-1" role="dialog" aria-labelledby="exampleModalCenterTitle" aria-hidden="true">
    <div class="modal-dialog modal-dialog-centered" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title">E-Books Management System</h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <div class="modal-body text-center">
                <h4>Do you want to logout?</h4>
                <button type="button" class="btn btn-secondary" data-dismiss="modal">No</button>
                <a href="<%= request.getContextPath() %>/logout" class="btn btn-primary text-white">Yes</a>
            </div>
        </div>
    </div>
</div>

<!-- Main Navigation -->
<nav class="navbar navbar-expand-lg navbar-dark bg-dark">
    <a class="navbar-brand" href="<%= request.getContextPath() %>/admin/Home.jsp"><i class="fa-solid fa-house"></i></a>
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent">
        <span class="navbar-toggler-icon"></span>
    </button>

    <div class="collapse navbar-collapse" id="navbarSupportedContent">
        <ul class="navbar-nav mr-auto">
            <li class="nav-item">
                <a class="nav-link" href="<%= request.getContextPath() %>/admin/Home.jsp">Home</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="<%= request.getContextPath() %>/contact.jsp">Contact Us</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="<%= request.getContextPath() %>/about.jsp">About Us</a>
            </li>
        </ul>
    </div>
</nav>