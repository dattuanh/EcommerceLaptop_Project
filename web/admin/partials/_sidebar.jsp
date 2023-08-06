<%-- 
    Document   : _sidebar
    Created on : Jun 17, 2023, 1:51:57 AM
    Author     : MSI
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<!--<head>
    <style>
        .icon {
            margin-right: 5px;
            height: 31px;
            width: 31px;
            background: rgba(108, 114, 147, 0.2);
            border-radius: 20px;
        }
    </style>
</head>-->

<nav class="sidebar sidebar-offcanvas" id="sidebar">
    <div class="sidebar-brand-wrapper d-none d-lg-flex align-items-center justify-content-center fixed-top">
        <a class="sidebar-brand brand-logo" href="Dashboard"><img src="images/logo/logo.png" alt="logo" /></a>
        <a class="sidebar-brand brand-logo-mini" href="Dashboard"><img src="images/logo/logo.png" alt="logo" /></a>
    </div>
    <c:set var = "a" scope = "session" value = "${sessionScope.account}" />
    <ul class="nav">
        <li class="nav-item profile">
            <div class="profile-desc">
                <div class="profile-pic">
                    <div class="count-indicator">
                        <img class="img-xs rounded-circle " src="images/Account/${a.image}" alt="">
                        <span class="count bg-success"></span>
                    </div>
                    <div class="profile-name">
                        <h5 class="mb-0 font-weight-normal" style="color: white">${a.fullName}</h5>
                        <span>Admin</span>
                    </div>
                </div>
                <a href="#" id="profile-dropdown" data-toggle="dropdown"><i class="mdi mdi-dots-vertical"></i></a>
                <div class="dropdown-menu dropdown-menu-right sidebar-dropdown preview-list" aria-labelledby="profile-dropdown">
                    <a href="#" class="dropdown-item preview-item">
                        <div class="preview-thumbnail">
                            <div class="preview-icon bg-dark rounded-circle">
                                <i class="mdi mdi-settings text-primary"></i>
                            </div>
                        </div>
                        <div class="preview-item-content">
                            <p class="preview-subject ellipsis mb-1 text-small">Account settings</p>
                        </div>
                    </a>
                    <div class="dropdown-divider"></div>
                    <a href="#" class="dropdown-item preview-item">
                        <div class="preview-thumbnail">
                            <div class="preview-icon bg-dark rounded-circle">
                                <i class="mdi mdi-onepassword  text-info"></i>
                            </div>
                        </div>
                        <div class="preview-item-content">
                            <p class="preview-subject ellipsis mb-1 text-small">Change Password</p>
                        </div>
                    </a>
                    <div class="dropdown-divider"></div>
                    <a href="#" class="dropdown-item preview-item">
                        <div class="preview-thumbnail">
                            <div class="preview-icon bg-dark rounded-circle">
                                <i class="mdi mdi-calendar-today text-success"></i>
                            </div>
                        </div>
                        <div class="preview-item-content">
                            <p class="preview-subject ellipsis mb-1 text-small">To-do list</p>
                        </div>
                    </a>
                </div>
            </div>
        </li>
        <li class="nav-item nav-category">
            <span class="nav-link">Navigation</span>
        </li>
        <li class="nav-item menu-items">
            <a class="nav-link" href="Dashboard">
                <span class="menu-icon">
                    <i class="mdi mdi-speedometer"></i>
                </span>
                <span class="menu-title">Dashboard</span>
            </a>
        </li>
        
        <li class="nav-item menu-items">
            <a class="nav-link" data-toggle="collapse" href="#account" aria-expanded="false" aria-controls="ui-basic">
                <span class="menu-icon">
                    <i class="mdi mdi-account"></i>
                </span>
                <span class="menu-title">Quản lý tài khoản</span>
                <i class="menu-arrow"></i>
            </a>
            <div class="collapse" id="account">
                <ul class="nav flex-column sub-menu">
                    <li class="nav-item"> <a class="nav-link" href="AccountManagement">Account Management</a></li>
                    <li class="nav-item"> <a class="nav-link" href="CustomerManagement">Customer Management</a></li>
                </ul>
            </div>
        </li>
        
        <li class="nav-item menu-items">
            <a class="nav-link" href="OrderManagement">
                <span class="menu-icon">
                    <i class="mdi mdi-cart"></i>
                </span>
                <span class="menu-title">Quản lý đơn hàng</span>
            </a>
        </li>
        
        <li class="nav-item menu-items">
            <a class="nav-link" data-toggle="collapse" href="#news" aria-expanded="false" aria-controls="ui-basic">
                <span class="menu-icon">
                    <i class="mdi mdi-newspaper"></i>
                </span>
                <span class="menu-title">Quản lý tin tức</span>
                <i class="menu-arrow"></i>
            </a>
            <div class="collapse" id="news">
                <ul class="nav flex-column sub-menu">
                    <li class="nav-item"> <a class="nav-link" href="NewsGroupManagement">NewsGroup Management</a></li>
                    <li class="nav-item"> <a class="nav-link" href="NewsManagement">News Management</a></li>
                </ul>
            </div>
        </li>
        
        <li class="nav-item menu-items">
            <a class="nav-link" data-toggle="collapse" href="#warranty" aria-expanded="false" aria-controls="ui-basic">
                <span class="menu-icon">
                    <i class="mdi mdi-shield"></i>
                </span>
                <span class="menu-title">Quản lý bảo hành</span>
                <i class="menu-arrow"></i>
            </a>
            <div class="collapse" id="warranty">
                <ul class="nav flex-column sub-menu">
                    <li class="nav-item"> <a class="nav-link" href="WarrantiesManagement">Warranty Management</a></li>
                    <li class="nav-item"> <a class="nav-link" href="ErrorsManagement">Error Management</a></li>
                </ul>
            </div>
        </li>

        <li class="nav-item menu-items">
            <a class="nav-link" data-toggle="collapse" href="#product" aria-expanded="false" aria-controls="ui-basic">
                <span class="menu-icon">
                    <i class="mdi mdi-laptop"></i>
                </span>
                <span class="menu-title">Quản lý Sản phẩm</span>
                <i class="menu-arrow"></i>
            </a>
            <div class="collapse" id="product">
                <ul class="nav flex-column sub-menu">
                    <li class="nav-item"> <a class="nav-link" href="CategoryManagement">Category Management</a></li>
                    <li class="nav-item"> <a class="nav-link" href="SeriesManagement">ProductSeri Management</a></li>
                    <li class="nav-item"> <a class="nav-link" href="ProductManagement">Product Search</a></li>
                </ul>
            </div>
        </li>
        
        <li class="nav-item menu-items">
            <a class="nav-link" data-toggle="collapse" href="#ui-basic" aria-expanded="false" aria-controls="ui-basic">
                <span class="menu-icon">
                    <i class="mdi mdi-monitor"></i>
                </span>
                <span class="menu-title">Quản lý giao diện</span>
                <i class="menu-arrow"></i>
            </a>
            <div class="collapse" id="ui-basic">
                <ul class="nav flex-column sub-menu">
                    <li class="nav-item"> <a class="nav-link" href="DisplayManagement">Navigation Bar</a></li>
                    <li class="nav-item"> <a class="nav-link" href="FooterManagement">Footer</a></li>
                    <li class="nav-item"> <a class="nav-link" href="CompanyManagement">Company Information</a></li>
                </ul>
            </div>
        </li>
    </ul>
</nav>


