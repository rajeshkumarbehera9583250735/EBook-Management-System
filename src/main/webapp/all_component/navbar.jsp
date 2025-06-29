<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page isELIgnored="false" %>

<div class="container-fluid" style="height: 10px; background-color: #303f9f"></div>

<div class="container-fluid p-3 btn-light">
    <div class="row">
        <div class="col-md-3 text-success">
            <h3><i class="fa-solid fa-book"></i> E-Book Store</h3>
        </div>

        <div class="col-md-6">
            <form class="form-inline my-2 my-lg-0" method="post" action="search.jsp">
                <input class="form-control mr-sm-2" type="search" placeholder="Search" aria-label="Search" name="ch">
                <button class="btn btn-primary my-2 my-sm-0" type="submit">Search</button>
            </form>
        </div>

        <c:if test="${not empty userobj}">
            <div class="col-md-3">
                <a href="checkout.jsp"><i class="fas fa-cart-plus fa-2x"></i></a>
                <a href="setting_page.jsp" class="btn btn-success"><i class="fa-solid fa-user"></i> ${userobj.name}</a>
                <a data-toggle="modal" data-target="#exampleModalCenter" class="btn btn-primary text-white">
                    <i class="fa-solid fa-right-to-bracket"></i> Logout
                </a>
            </div>
        </c:if>

        <c:if test="${empty userobj}">
            <div class="col-md-3">
                <a href="login.jsp" class="btn btn-success"><i class="fa-solid fa-right-to-bracket"></i> Login</a>
                <a href="register.jsp" class="btn btn-primary text-white"><i class="fa-solid fa-user-plus"></i> Register</a>
            </div>
        </c:if>
    </div>
</div>

<nav class="navbar navbar-expand-lg navbar-dark bg-custom">
    <a class="navbar-brand" href="index.jsp"><i class="fa-solid fa-house"></i></a>
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent">
        <span class="navbar-toggler-icon"></span>
    </button>

    <div class="collapse navbar-collapse" id="navbarSupportedContent">
        <ul class="navbar-nav mr-auto">
            <li class="nav-item active">
                <a class="nav-link" href="index.jsp">Home</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="all_recent_books.jsp"><i class="fa-solid fa-book-open"></i> Recent Books</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="all_new_books.jsp"><i class="fa-solid fa-book-open"></i> New Books</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="all_old_books.jsp"><i class="fa-solid fa-book-open"></i> Old Books</a>
            </li>
        </ul>
        <form class="form-inline my-2 my-lg-0">
            <a href="setting_page.jsp" class="btn btn-light my-2 my-sm-0"><i class="fa-solid fa-gear"></i> Setting</a>
            <a href="contact.jsp" class="btn btn-light my-2 my-sm-0 ml-1"><i class="fa-solid fa-address-book"></i> Contact Us</a>
        </form>
    </div>
</nav>

<!-- Logout Modal -->
<div class="modal fade" id="exampleModalCenter" tabindex="-1" role="dialog" aria-labelledby="exampleModalCenterTitle">
    <div class="modal-dialog modal-dialog-centered" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title">E-Books Management System</h5>
                <button type="button" class="close" data-dismiss="modal"><span>&times;</span></button>
            </div>
            <div class="modal-body text-center">
                <h4>Do you want to logout?</h4>
                <button type="button" class="btn btn-secondary" data-dismiss="modal">No</button>
                <a href="logout" class="btn btn-primary text-white">Yes</a>
            </div>
        </div>
    </div>
</div>
