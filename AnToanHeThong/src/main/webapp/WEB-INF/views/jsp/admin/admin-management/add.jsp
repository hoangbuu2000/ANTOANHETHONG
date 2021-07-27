<%--
  Created by IntelliJ IDEA.
  User: Buu
  Date: 25/04/2021
  Time: 4:03 CH
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="page-wrapper">
    <div class="content">
        <div class="row">
            <div class="col-lg-8 offset-lg-2">
                <h4 class="page-title"><spring:message code="admin.add.page.title" /></h4>
            </div>
        </div>
        <form:form modelAttribute="admin" enctype="multipart/form-data" method="post">
            <div class="row">
                <div class="col-lg-8 offset-lg-2">
                    <c:if test="${message != null}">
                        <p class="alert alert-danger">${message}</p>
                    </c:if>
                    <form>
                        <div class="row">
                            <div class="col-sm-6">
                                <div class="form-group">
                                    <label><spring:message code="admin.add.label.first" /> <span class="text-danger">*</span></label>
                                    <form:input path="ten" cssClass="form-control"/>
                                    <form:errors path="ten" cssClass="text-danger"/>
                                </div>
                            </div>
                            <div class="col-sm-6">
                                <div class="form-group">
                                    <label><spring:message code="admin.add.label.last" /> <span class="text-danger">*</span></label>
                                    <form:input path="ho" cssClass="form-control"/>
                                    <form:errors path="ho" cssClass="text-danger"/>
                                </div>
                            </div>
                            <div class="col-sm-6">
                                <div class="form-group">
                                    <label><spring:message code="admin.add.label.username" /> <span class="text-danger">*</span></label>
                                    <form:input path="username" cssClass="form-control"/>
                                    <form:errors path="username" cssClass="text-danger"/>
                                </div>
                            </div>
                            <div class="col-sm-6">
                                <div class="form-group">
                                    <label><spring:message code="admin.add.label.email" /> <span class="text-danger">*</span></label>
                                    <form:input path="email" type="email" cssClass="form-control"/>
                                    <form:errors path="email" cssClass="text-danger"/>
                                </div>
                            </div>
                            <div class="col-sm-6">
                                <div class="form-group">
                                    <label><spring:message code="admin.add.label.password" /> <span class="text-danger">*</span></label>
                                    <form:password path="password" cssClass="form-control"/>
                                    <form:errors path="password" cssClass="text-danger"/>
                                </div>
                            </div>
                            <div class="col-sm-6">
                                <div class="form-group">
                                    <label><spring:message code="admin.add.label.confirmPsw" /> <span class="text-danger">*</span></label>
                                    <form:password path="confirmPassword" cssClass="form-control"/>
                                    <form:errors path="confirmPassword" cssClass="text-danger"/>
                                </div>
                            </div>
                            <div class="col-sm-6">
                                <div class="form-group">
                                    <label><spring:message code="admin.add.label.dob" /> <span class="text-danger">*</span></label>
                                    <div class="cal-icon">
                                        <form:input path="ngaySinh" cssClass="form-control datetimepicker" />
                                    </div>
                                    <form:errors path="ngaySinh" cssClass="text-danger"/>
                                </div>
                            </div>
                            <div class="col-sm-6">
                                <div class="form-group gender-select">
                                    <label class="gen-label"><spring:message code="admin.add.label.gender" /> <span class="text-danger">*</span></label>
                                    <div class="form-check-inline">
                                        <label class="form-check-label">
                                            <form:radiobutton path="gioiTinh" value="Nam" cssClass="form-check-input" />
                                            <spring:message code="admin.add.label.gender.male" />
                                        </label>
                                    </div>
                                    <div class="form-check-inline">
                                        <label class="form-check-label">
                                            <form:radiobutton path="gioiTinh" value="Nữ" cssClass="form-check-input" />
                                            <spring:message code="admin.add.label.gender.female" />
                                        </label>
                                    </div>
                                    <form:errors path="gioiTinh" cssClass="text-danger"/>
                                </div>
                            </div>
                            <div class="col-sm-6">
                                <div class="form-group">
                                    <label><spring:message code="admin.add.label.phone" /> <span class="text-danger">*</span></label>
                                    <form:input path="dienThoai" cssClass="form-control"/>
                                    <form:errors path="dienThoai" cssClass="text-danger"/>
                                </div>
                            </div>
                            <div class="col-sm-6">
                                <div class="form-group">
                                    <label><spring:message code="admin.add.label.joinDate" /> <span class="text-danger">*</span></label>
                                    <div class="cal-icon">
                                        <form:input path="ngayVaoLam" cssClass="form-control datetimepicker" />
                                    </div>
                                    <form:errors path="ngayVaoLam" cssClass="text-danger"/>
                                </div>
                            </div>
                            <div class="col-sm-6">
                                <div class="row">
                                    <div class="col-sm-12 col-md-12 col-lg-6">
                                        <div class="form-group">
                                            <label><spring:message code="admin.add.label.hometown" /></label>
                                            <form:select path="queQuan" cssClass="form-control select">
                                                <form:option value="Tien Giang" />
                                                <form:option value="Dong Thap" />
                                            </form:select>
                                            <form:errors path="queQuan" cssClass="text-danger"/>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="col-sm-6">
                                <div class="form-group">
                                    <label><spring:message code="admin.add.label.avatar" /></label>
                                    <div class="profile-upload">
                                        <div class="upload-img">
                                            <img alt="" src="assets/img/user.jpg">
                                        </div>
                                        <div class="upload-input">
                                            <form:input path="image" type="file" cssClass="form-control" />
                                        </div>
                                    </div>
                                    <form:errors path="image" cssClass="text-danger"/>
                                </div>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="display-block"><spring:message code="admin.add.label.status" /></label>
                            <div class="form-check form-check-inline">
                                <form:radiobutton path="active" cssClass="form-check-input" value="1"
                                                  id="doctor_active" name="status" checked="true"/>
                                <label class="form-check-label" for="doctor_active">
                                    <spring:message code="admin.add.label.status.active" />
                                </label>
                            </div>
                            <div class="form-check form-check-inline">
                                <form:radiobutton path="active" cssClass="form-check-input" value="0"
                                                  id="doctor_inactive" name="status"/>
                                <label class="form-check-label" for="doctor_inactive">
                                    <spring:message code="admin.add.label.status.inactive" />
                                </label>
                            </div>
                            <form:errors path="active" cssClass="text-danger"/>
                        </div>
                        <div class="m-t-20 text-center">
                            <button class="btn btn-primary submit-btn"><spring:message code="admin.add.form.submit" /></button>
                        </div>
                    </form>
                </div>
            </div>
        </form:form>
    </div>
    <div class="notification-box">
        <div class="msg-sidebar notifications msg-noti">
            <div class="topnav-dropdown-header">
                <span>Messages</span>
            </div>
            <div class="drop-scroll msg-list-scroll" id="msg_list">
                <ul class="list-box">
                    <li>
                        <a href="chat.html">
                            <div class="list-item">
                                <div class="list-left">
                                    <span class="avatar">R</span>
                                </div>
                                <div class="list-body">
                                    <span class="message-author">Richard Miles </span>
                                    <span class="message-time">12:28 AM</span>
                                    <div class="clearfix"></div>
                                    <span class="message-content">Lorem ipsum dolor sit amet, consectetur adipiscing</span>
                                </div>
                            </div>
                        </a>
                    </li>
                    <li>
                        <a href="chat.html">
                            <div class="list-item new-message">
                                <div class="list-left">
                                    <span class="avatar">J</span>
                                </div>
                                <div class="list-body">
                                    <span class="message-author">John Doe</span>
                                    <span class="message-time">1 Aug</span>
                                    <div class="clearfix"></div>
                                    <span class="message-content">Lorem ipsum dolor sit amet, consectetur adipiscing</span>
                                </div>
                            </div>
                        </a>
                    </li>
                    <li>
                        <a href="chat.html">
                            <div class="list-item">
                                <div class="list-left">
                                    <span class="avatar">T</span>
                                </div>
                                <div class="list-body">
                                    <span class="message-author"> Tarah Shropshire </span>
                                    <span class="message-time">12:28 AM</span>
                                    <div class="clearfix"></div>
                                    <span class="message-content">Lorem ipsum dolor sit amet, consectetur adipiscing</span>
                                </div>
                            </div>
                        </a>
                    </li>
                    <li>
                        <a href="chat.html">
                            <div class="list-item">
                                <div class="list-left">
                                    <span class="avatar">M</span>
                                </div>
                                <div class="list-body">
                                    <span class="message-author">Mike Litorus</span>
                                    <span class="message-time">12:28 AM</span>
                                    <div class="clearfix"></div>
                                    <span class="message-content">Lorem ipsum dolor sit amet, consectetur adipiscing</span>
                                </div>
                            </div>
                        </a>
                    </li>
                    <li>
                        <a href="chat.html">
                            <div class="list-item">
                                <div class="list-left">
                                    <span class="avatar">C</span>
                                </div>
                                <div class="list-body">
                                    <span class="message-author"> Catherine Manseau </span>
                                    <span class="message-time">12:28 AM</span>
                                    <div class="clearfix"></div>
                                    <span class="message-content">Lorem ipsum dolor sit amet, consectetur adipiscing</span>
                                </div>
                            </div>
                        </a>
                    </li>
                    <li>
                        <a href="chat.html">
                            <div class="list-item">
                                <div class="list-left">
                                    <span class="avatar">D</span>
                                </div>
                                <div class="list-body">
                                    <span class="message-author"> Domenic Houston </span>
                                    <span class="message-time">12:28 AM</span>
                                    <div class="clearfix"></div>
                                    <span class="message-content">Lorem ipsum dolor sit amet, consectetur adipiscing</span>
                                </div>
                            </div>
                        </a>
                    </li>
                    <li>
                        <a href="chat.html">
                            <div class="list-item">
                                <div class="list-left">
                                    <span class="avatar">B</span>
                                </div>
                                <div class="list-body">
                                    <span class="message-author"> Buster Wigton </span>
                                    <span class="message-time">12:28 AM</span>
                                    <div class="clearfix"></div>
                                    <span class="message-content">Lorem ipsum dolor sit amet, consectetur adipiscing</span>
                                </div>
                            </div>
                        </a>
                    </li>
                    <li>
                        <a href="chat.html">
                            <div class="list-item">
                                <div class="list-left">
                                    <span class="avatar">R</span>
                                </div>
                                <div class="list-body">
                                    <span class="message-author"> Rolland Webber </span>
                                    <span class="message-time">12:28 AM</span>
                                    <div class="clearfix"></div>
                                    <span class="message-content">Lorem ipsum dolor sit amet, consectetur adipiscing</span>
                                </div>
                            </div>
                        </a>
                    </li>
                    <li>
                        <a href="chat.html">
                            <div class="list-item">
                                <div class="list-left">
                                    <span class="avatar">C</span>
                                </div>
                                <div class="list-body">
                                    <span class="message-author"> Claire Mapes </span>
                                    <span class="message-time">12:28 AM</span>
                                    <div class="clearfix"></div>
                                    <span class="message-content">Lorem ipsum dolor sit amet, consectetur adipiscing</span>
                                </div>
                            </div>
                        </a>
                    </li>
                    <li>
                        <a href="chat.html">
                            <div class="list-item">
                                <div class="list-left">
                                    <span class="avatar">M</span>
                                </div>
                                <div class="list-body">
                                    <span class="message-author">Melita Faucher</span>
                                    <span class="message-time">12:28 AM</span>
                                    <div class="clearfix"></div>
                                    <span class="message-content">Lorem ipsum dolor sit amet, consectetur adipiscing</span>
                                </div>
                            </div>
                        </a>
                    </li>
                    <li>
                        <a href="chat.html">
                            <div class="list-item">
                                <div class="list-left">
                                    <span class="avatar">J</span>
                                </div>
                                <div class="list-body">
                                    <span class="message-author">Jeffery Lalor</span>
                                    <span class="message-time">12:28 AM</span>
                                    <div class="clearfix"></div>
                                    <span class="message-content">Lorem ipsum dolor sit amet, consectetur adipiscing</span>
                                </div>
                            </div>
                        </a>
                    </li>
                    <li>
                        <a href="chat.html">
                            <div class="list-item">
                                <div class="list-left">
                                    <span class="avatar">L</span>
                                </div>
                                <div class="list-body">
                                    <span class="message-author">Loren Gatlin</span>
                                    <span class="message-time">12:28 AM</span>
                                    <div class="clearfix"></div>
                                    <span class="message-content">Lorem ipsum dolor sit amet, consectetur adipiscing</span>
                                </div>
                            </div>
                        </a>
                    </li>
                    <li>
                        <a href="chat.html">
                            <div class="list-item">
                                <div class="list-left">
                                    <span class="avatar">T</span>
                                </div>
                                <div class="list-body">
                                    <span class="message-author">Tarah Shropshire</span>
                                    <span class="message-time">12:28 AM</span>
                                    <div class="clearfix"></div>
                                    <span class="message-content">Lorem ipsum dolor sit amet, consectetur adipiscing</span>
                                </div>
                            </div>
                        </a>
                    </li>
                </ul>
            </div>
            <div class="topnav-dropdown-footer">
                <a href="chat.html">See all messages</a>
            </div>
        </div>
    </div>
</div>

<script>
    $("#ngayVaoLam").datetimepicker({
        format: 'DD/MM/YYYY',
        minDate: new Date()
    })
</script>