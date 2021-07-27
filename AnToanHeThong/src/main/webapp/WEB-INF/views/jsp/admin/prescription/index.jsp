<%--
  Created by IntelliJ IDEA.
  User: Buu
  Date: 08/04/2021
  Time: 10:45 SA
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<c:if test="${param.lang != null && !param.lang.isEmpty()}">
    <c:set var="lang" value="lang=${param.lang}"/>
</c:if>
<div class="page-wrapper" style="min-height: 754px;">
    <div class="content">
        <div class="row">
            <div class="col-sm-4 col-3">
                <h4 class="page-title"><spring:message code="prescription.index.page.title" /></h4>
            </div>
            <div class="col-sm-8 col-9 text-right m-b-20">
                <a href="<c:url value="/admin/prescription/addorupdate?${lang}" />" class="btn btn-primary float-right btn-rounded"><i class="fa fa-plus"></i> <spring:message code="prescription.index.add" /></a>
            </div>
        </div>
        <div class="row filter-row" style="margin-top: 100px">
            <div class="col-sm-6 col-md-3">
                <div class="form-group form-focus">
                    <label class="focus-label"><spring:message code="invoice.index.table.from" /></label>
                    <div class="cal-icon">
                        <input class="form-control floating datetimepicker" type="text">
                    </div>
                </div>
            </div>
            <div class="col-sm-6 col-md-3">
                <div class="form-group form-focus">
                    <label class="focus-label"><spring:message code="invoice.index.table.to" /></label>
                    <div class="cal-icon">
                        <input class="form-control floating datetimepicker" type="text">
                    </div>
                </div>
            </div>
            <div class="col-sm-6 col-md-3">
                <a href="javascript:;" onclick="searchPrescription('${param.q}')" class="btn btn-success btn-block"> <spring:message code="invoice.index.table.search" /> </a>
            </div>
        </div>
        <div class="row">
            <div class="col-md-12">
                <div class="table-responsive">
                    <table class="table table-border table-striped custom-table datatable mb-0 dataTable no-footer"
                           id="DataTables_Table_0" role="grid" aria-describedby="DataTables_Table_0_info">
                        <thead>
                        <tr>
                            <th style="min-width:200px;"><spring:message code="prescription.index.table.prescription.id" /></th>
                            <th><spring:message code="prescription.index.table.doctor.name" /></th>
                            <th><spring:message code="prescription.index.table.patient.name" /></th>
                            <th><spring:message code="prescription.index.table.disease.name" /></th>
                            <th style="min-width: 110px;"><spring:message code="prescription.index.table.date" /></th>
                            <th class="text-right"><spring:message code="prescription.index.table.action" /></th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach items="${prescriptions}" var="e">
                            <tr>
                                <td><a href="<c:url value="/admin/prescription/details?id=${e.id}&${lang}" />">${e.id}</a></td>
                                <td>
                                    <img width="28" height="28" src="<c:url value="${e.bacSi.image}" />" class="rounded-circle" alt="">
                                    <h2>${e.bacSi.ten}</h2>
                                </td>
                                <td>${e.benhNhan.ten}</td>
                                <td>${e.loaiBenh.tenBenh}</td>
                                <td><fmt:formatDate value="${e.ngayKeToa}" pattern="dd-MM-yyyy" /></td>
                                <td class="text-right">
                                    <div class="dropdown dropdown-action">
                                        <a href="#" class="action-icon dropdown-toggle" data-toggle="dropdown" aria-expanded="false"><i class="fa fa-ellipsis-v"></i></a>
                                        <div class="dropdown-menu dropdown-menu-right">
                                            <a class="dropdown-item" href="<c:url value="/admin/prescription/addorupdate?id=${e.id}&${lang}" />"><i class="fa fa-pencil m-r-5"></i> <spring:message code="prescription.index.table.action.edit" /></a>
                                            <a class="dropdown-item" href="#" data-toggle="modal" data-target="#delete_prescription${e.id}"><i class="fa fa-trash-o m-r-5"></i> <spring:message code="prescription.index.table.action.delete" /></a>
                                        </div>
                                    </div>
                                    <div id="delete_prescription${e.id}" class="modal fade delete-modal" role="dialog">
                                        <div class="modal-dialog modal-dialog-centered">
                                            <div class="modal-content">
                                                <div class="modal-body text-center">
                                                    <h3><spring:message code="prescription.index.table.action.delete.message" /></h3>
                                                    <div class="m-t-20"> <a href="#" class="btn btn-white" data-dismiss="modal"><spring:message code="prescription.index.table.action.close" /></a>
                                                        <form:form action="/admin/prescription/delete?id=${e.id}&${lang}" method="post">
                                                            <button type="submit" class="btn btn-danger"><spring:message code="prescription.index.table.action.delete" /></button>
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
    <div class="notification-box">
        <div class="msg-sidebar notifications msg-noti">
            <div class="topnav-dropdown-header">
                <span>Messages</span>
            </div>
            <div class="slimScrollDiv" style="position: relative; overflow: hidden; width: auto; height: 630px;"><div class="drop-scroll msg-list-scroll" id="msg_list" style="overflow: hidden; width: auto; height: 630px;">
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
            </div><div class="slimScrollBar" style="background: rgb(135, 135, 135); width: 4px; position: absolute; top: 0px; opacity: 0.4; display: none; border-radius: 0px; z-index: 99; right: 1px; height: 809px;"></div><div class="slimScrollRail" style="width: 4px; height: 100%; position: absolute; top: 0px; display: none; border-radius: 7px; background: rgb(51, 51, 51); opacity: 0.2; z-index: 90; right: 1px;"></div></div>
            <div class="topnav-dropdown-footer">
                <a href="chat.html">See all messages</a>
            </div>
        </div>
    </div>
</div>

<script>
    function searchPrescription(id) {
        let list = document.querySelectorAll("div.cal-icon > input");
        let fromm = list[0].value;
        let to = list[1].value;
        $.getJSON("/api/searchPrescriptionFromTo?id="+id+"&from="+fromm+"&to="+to).done(function (task) {
            $('#DataTables_Table_0 tbody tr').remove();
            console.log(JSON.stringify(task));
            let ds = task;
            for(let i = 0; i < ds.length; i++) {
                if (ds[i].id != null) {
                    $('#DataTables_Table_0').find('tbody').append(
                        '<tr>'+
                        '<td><a href="/admin/prescription/details?id='+ds[i].id+'&${lang}">'+ds[i].id+'</a></td>'+
                        '<td>'+
                        '<img width="28" height="28" src="'+ds[i].doctorImg+'" ' +
                        'class="rounded-circle" alt="">'+
                        '<h2>'+ds[i].doctorName+'</h2>'+
                        '</td>'+
                        '<td>'+ds[i].patientName+'</td>'+
                        '<td>'+ds[i].diseaseName+'</td>'+
                        '<td>'+formatDate(ds[i].date)+'</td>'+
                        '<td class="text-right">'+
                        '<div class="dropdown dropdown-action">'+
                        '<a href="#" class="action-icon dropdown-toggle" data-toggle="dropdown" ' +
                        'aria-expanded="false"><i class="fa fa-ellipsis-v"></i></a>'+
                        '<div class="dropdown-menu dropdown-menu-right">'+
                        '<a class="dropdown-item" href="/admin/prescription/addorupdate?id='+ds[i].id+'&${lang}">' +
                        '<i class="fa fa-pencil m-r-5"></i> '+
                        '<spring:message code="prescription.index.table.action.edit" /></a>'+
                        '<a class="dropdown-item" href="#" data-toggle="modal" ' +
                        'data-target="#delete_prescription'+ds[i].id+'"><i class="fa fa-trash-o m-r-5"></i>'+
                        '<spring:message code="prescription.index.table.action.delete" /></a>'+
                        '</div>'+
                        '</div>'+
                        '<div id="delete_prescription'+ds[i].id+'" class="modal fade delete-modal" ' +
                        'role="dialog">'+
                        '<div class="modal-dialog modal-dialog-centered">'+
                        '<div class="modal-content">'+
                        '<div class="modal-body text-center">'+
                        '<h3><spring:message code="prescription.index.table.action.delete.message" /></h3>'+
                        '<div class="m-t-20"> <a href="#" class="btn btn-white" data-dismiss="modal">'+
                        '<spring:message code="prescription.index.table.action.close" /></a>'+
                        '<form action="/admin/prescription/delete?id='+ds[i].id+'&${lang}" method="post">'+
                        '<button type="submit" class="btn btn-danger">'+
                        '<spring:message code="prescription.index.table.action.delete" /></button>'+
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
        })
    }

    function formatDate(date) {
        var d = new Date(date),
            month = '' + (d.getMonth() + 1),
            day = '' + d.getDate(),
            year = d.getFullYear();

        if (month.length < 2)
            month = '0' + month;
        if (day.length < 2)
            day = '0' + day;

        return [day, month, year].join('-');
    }
</script>
