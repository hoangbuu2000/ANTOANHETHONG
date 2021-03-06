<%--
  Created by IntelliJ IDEA.
  User: Buu
  Date: 30/03/2021
  Time: 11:55 SA
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<c:if test="${param.lang != null && !param.lang.isEmpty()}">
    <c:set var="lang" value="lang=${param.lang}"/>
</c:if>
<div class="page-wrapper" style="min-height: 754px;">
    <div class="content">
        <div class="row">
            <div class="col-sm-4 col-3">
                <h4 class="page-title"><spring:message code="patient.index.page.title" /></h4>
            </div>
            <div class="col-sm-8 col-9 text-right m-b-20">
                <a href="<c:url value="/admin/patient/add?${lang}" />" class="btn btn btn-primary btn-rounded float-right"><i
                        class="fa fa-plus"></i> <spring:message code="patient.index.add" /></a>
            </div>
        </div>
        <div class="row filter-row">
            <div class="col-sm-6 col-md-3">
                <div class="form-group form-focus">
                    <label class="focus-label"><spring:message code="patient.index.table.name" /></label>
                    <input id="adName" name="adName" type="text" class="form-control floating">
                </div>
            </div>
            <div class="col-sm-6 col-md-3">
                <div class="form-group form-focus">
                    <label class="focus-label"><spring:message code="patient.index.table.phone" /></label>
                    <input id="adPhone" name="adPhone" type="text" class="form-control floating">
                </div>
            </div>
            <div class="col-sm-6 col-md-3">
                <a href="javascript:;" onclick="search1()" class="btn btn-success btn-block"> <spring:message code="admin.index.table.search" /> </a>
            </div>
        </div>
        <div class="row">
            <div class="col-md-12">
                <div class="table-responsive">
                    <div id="DataTables_Table_0_wrapper" class="dataTables_wrapper dt-bootstrap4 no-footer">
                        <div class="row">
                            <div class="col-sm-12">
                                <table class="table table-border table-striped custom-table datatable mb-0 dataTable no-footer"
                                       id="DataTables_Table_0" role="grid" aria-describedby="DataTables_Table_0_info">
                                    <thead>
                                    <tr role="row">
                                        <th class="sorting_asc" tabindex="0" aria-controls="DataTables_Table_0"
                                            rowspan="1" colspan="1" aria-sort="ascending"
                                            aria-label="Name: activate to sort column descending"
                                            style="width: 170.8px;"><spring:message code="patient.index.table.name" />
                                        </th>
                                        <th class="sorting" tabindex="0" aria-controls="DataTables_Table_0" rowspan="1"
                                            colspan="1" aria-label="Age: activate to sort column ascending"
                                            style="width: 41.2px;"><spring:message code="patient.index.table.age" />
                                        </th>
                                        <th class="sorting" tabindex="0" aria-controls="DataTables_Table_0" rowspan="1"
                                            colspan="1" aria-label="Address: activate to sort column ascending"
                                            style="width: 385.2px;"><spring:message code="patient.index.table.gender" />
                                        </th>
                                        <th class="sorting" tabindex="0" aria-controls="DataTables_Table_0" rowspan="1"
                                            colspan="1" aria-label="Phone: activate to sort column ascending"
                                            style="width: 105.2px;"><spring:message code="patient.index.table.phone" />
                                        </th>
                                        <th class="sorting" tabindex="0" aria-controls="DataTables_Table_0" rowspan="1"
                                            colspan="1" aria-label="Email: activate to sort column ascending"
                                            style="width: 231.6px;"><spring:message code="patient.index.table.email" />
                                        </th>
                                        <th class="text-right sorting" tabindex="0" aria-controls="DataTables_Table_0"
                                            rowspan="1" colspan="1"
                                            aria-label="Action: activate to sort column ascending"
                                            style="width: 65.2px;"><spring:message code="patient.index.table.action" />
                                        </th>
                                    </tr>
                                    </thead>
                                    <tbody>
                                    <c:forEach items="${patient}" var="p">
                                        <tr role="row" class="odd">
                                            <td><a href="<c:url value="/admin/patient/details/${p.id}?${lang}" />">${p.ten}</a></td>
                                            <td>${p.tuoi}</td>
                                            <td>${p.gioiTinh}</td>
                                            <td>${p.dienThoai}</td>
                                            <td>${p.email}</td>
                                            <td class="text-right">
                                                <div class="dropdown dropdown-action">
                                                    <a href="#" class="action-icon dropdown-toggle" data-toggle="dropdown"
                                                       aria-expanded="false"><i class="fa fa-ellipsis-v"></i></a>
                                                    <c:if test="${user.role.ten.equals('ROLE_ADMIN')}">
                                                        <div class="dropdown-menu dropdown-menu-right">
                                                            <a class="dropdown-item" href="<c:url value="/admin/patient/edit/${p.id}?${lang}" />"><i
                                                                    class="fa fa-pencil m-r-5"></i> <spring:message code="patient.index.table.action.edit" /></a>
                                                            <a class="dropdown-item" href="#" data-toggle="modal"
                                                               data-target="#delete_patient${p.id}"><i class="fa fa-trash-o m-r-5"></i>
                                                                <spring:message code="patient.index.table.action.delete" /></a>
                                                            <a class="dropdown-item" href="<c:url value="/admin/patient/add?oldPatient=${p.id}&${lang}" />"><i
                                                                    class="fa fa-repeat m-r-5"></i> <spring:message code="patient.index.table.action.reExamination" /></a>
                                                        </div>
                                                    </c:if>
                                                    <c:if test="${user.role.ten.equals('ROLE_DOCTOR') or user.role.ten.equals('ROLE_EMPLOYEE')}">
                                                        <div class="dropdown-menu dropdown-menu-right">
                                                            <a class="dropdown-item" href="<c:url value="/admin/patient/add?oldPatient=${p.id}&${lang}" />"><i
                                                                    class="fa fa-repeat m-r-5"></i> <spring:message code="patient.index.table.action.reExamination" /></a>
                                                        </div>
                                                    </c:if>
                                                </div>
                                                <div id="delete_patient${p.id}" class="modal fade delete-modal" role="dialog">
                                                    <div class="modal-dialog modal-dialog-centered">
                                                        <div class="modal-content">
                                                            <div class="modal-body text-center">
                                                                <h3><spring:message code="patient.index.table.action.delete.msg" /> (${p.ten})?</h3>
                                                                <div class="m-t-20"> <a href="#" class="btn btn-white" data-dismiss="modal"><spring:message code="patient.index.table.action.close" /></a>
                                                                    <form:form action="/admin/patient/delete/${p.id}?${lang}" method="post">
                                                                        <button type="submit" class="btn btn-danger"><spring:message code="patient.index.table.action.delete" /></button>
                                                                    </form:form>
                                                                </div>
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>
                                            </td>
                                        </tr>
                                    </c:forEach>

                                    </tbody>
                                </table>
                            </div>
                        </div>

                    </div>
                </div>
            </div>
        </div>
    </div>
    <div class="notification-box">
        <div class="msg-sidebar notifications msg-noti">
            <div class="topnav-dropdown-header">
                <span>Messages</span>
            </div>
            <div class="slimScrollDiv" style="position: relative; overflow: hidden; width: auto; height: 630px;">
                <div class="drop-scroll msg-list-scroll" id="msg_list"
                     style="overflow: hidden; width: auto; height: 630px;">
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
                <div class="slimScrollBar"
                     style="background: rgb(135, 135, 135); width: 4px; position: absolute; top: 0px; opacity: 0.4; display: none; border-radius: 0px; z-index: 99; right: 1px; height: 809px;"></div>
                <div class="slimScrollRail"
                     style="width: 4px; height: 100%; position: absolute; top: 0px; display: none; border-radius: 7px; background: rgb(51, 51, 51); opacity: 0.2; z-index: 90; right: 1px;"></div>
            </div>
            <div class="topnav-dropdown-footer">
                <a href="chat.html">See all messages</a>
            </div>
        </div>
    </div>
</div>

<script>
    function search1() {
        let name = $('#adName');
        let phone = $('#adPhone');
        $.getJSON('/api/searchPatient?name=' + name.val() + '&phone=' + phone.val()).done(function (task) {
            console.log("DONE: ", JSON.stringify(task));
            $('#DataTables_Table_0 tbody tr').remove();
            let ds = task;
            for (let i = 0; i < ds.length; i++) {
                console.log(ds[i].id);
                if (ds[i].id != null) {
                    $('#DataTables_Table_0').find('tbody').append(
                        '<tr role="row" class="odd">'+
                        '<td>'+
                        '<a href="/admin/patient/details/'+ds[i].id+'?${lang}">'+ds[i].ten+'</a></td>'+
                        '<td>'+ds[i].tuoi+'</td>'+
                        '<td>'+ds[i].gioiTinh+'</td>'+
                        '<td>'+ds[i].dienThoai+'</td>'+
                        '<td>'+ds[i].email+'</td>'+
                        '<td class="text-right">'+
                        '<div class="dropdown dropdown-action">'+
                        '<a href="#" class="action-icon dropdown-toggle" data-toggle="dropdown"'+
                        'aria-expanded="false"><i class="fa fa-ellipsis-v"></i></a>'+
                        <c:if test="${user.role.ten.equals('ROLE_ADMIN')}">
                        '<div class="dropdown-menu dropdown-menu-right">'+
                        '<a class="dropdown-item" href="/admin/patient/edit/'+ds[i].id+'?${lang}">'+
                        '<i class="fa fa-pencil m-r-5"></i>'+
                        '<spring:message code="patient.index.table.action.edit" />'+
                        '</a>'+
                        '<a class="dropdown-item" href="#" data-toggle="modal"'+
                        'data-target="#delete_patient'+ds[i].id+'">'+
                        '<i class="fa fa-trash-o m-r-5"></i>'+
                        '<spring:message code="patient.index.table.action.delete" />'+
                        '</a>'+
                        '<a class="dropdown-item" href="/admin/patient/add?oldPatient='+ds[i].id+'&${lang}">'+
                        '<i class="fa fa-repeat m-r-5"></i>'+
                        '<spring:message code="patient.index.table.action.reExamination" />'+
                        '</a></div>'+
                        </c:if>
                        <c:if test="${user.role.ten.equals('ROLE_DOCTOR') or user.role.ten.equals('ROLE_EMPLOYEE')}">
                        '<div class="dropdown-menu dropdown-menu-right">'+
                        '<a class="dropdown-item" href="/admin/patient/add?oldPatient='+ds[i].id+'&${lang}">'+
                        '<i class="fa fa-repeat m-r-5"></i>'+
                        '<spring:message code="patient.index.table.action.reExamination" />'+
                        '</a></div>'+
                        </c:if>
                        '</div>'+
                        '<div id="delete_patient'+ds[i].id+'" class="modal fade delete-modal" role="dialog">'+
                        '<div class="modal-dialog modal-dialog-centered">'+
                        '<div class="modal-content">'+
                        '<div class="modal-body text-center">'+
                        '<h3><spring:message code="patient.index.table.action.delete.msg" /> ('+ds[i].ten+')?</h3>'+
                        '<div class="m-t-20"> <a href="#" class="btn btn-white" data-dismiss="modal">'+
                        '<spring:message code="patient.index.table.action.close" />'+
                        '</a>'+
                        '<form action="/admin/patient/delete/'+ds[i].id+'?${lang}" method="post">'+
                        '<button type="submit" class="btn btn-danger">'+
                        '<spring:message code="patient.index.table.action.delete" /></button>'+
                        '</form>'+
                        '</div>'+
                        '</div>'+
                        '</div>'+
                        '</div>'+
                        '</div>'+
                        '</td>'+
                        '</tr>'
                    );
                }

            }
        });
    }
</script>
