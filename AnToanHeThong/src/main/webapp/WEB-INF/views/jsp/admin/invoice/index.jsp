<%--
  Created by IntelliJ IDEA.
  User: Buu
  Date: 09/04/2021
  Time: 8:20 CH
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<c:if test="${param.lang != null && !param.lang.isEmpty()}">
    <c:set var="lang" value="lang=${param.lang}"/>
</c:if>
<div class="page-wrapper" style="min-height: 754px;">
    <div class="content">
        <div class="row">
            <div class="col-sm-5 col-4">
                <h4 class="page-title"><spring:message code="invoice.index.page.title" /></h4>
            </div>
            <div class="col-sm-7 col-8 text-right m-b-30">
                <a href="<c:url value="/admin/invoice/addorupdate?${lang}" />" class="btn btn-primary btn-rounded"><i class="fa fa-plus"></i> <spring:message code="invoice.index.add" /></a>
            </div>
        </div>
        <div class="row filter-row">
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
                <div class="form-group form-focus select-focus focused">
                    <label class="focus-label"><spring:message code="invoice.index.table.status" /></label>
                    <select class="select floating select2-hidden-accessible" tabindex="-1" aria-hidden="true">
                        <option><spring:message code="invoice.index.table.status.default" /></option>
                        <option>Pending</option>
                        <option>Paid</option>
                        <option>Partially Paid</option>
                    </select>
                </div>
            </div>
            <div class="col-sm-6 col-md-3">
                <a href="javascript:;" onclick="searchInvoice()" class="btn btn-success btn-block"> <spring:message code="invoice.index.table.search" /> </a>
            </div>
        </div>
        <div class="row">
            <div class="col-md-12">
                <div class="table-responsive">
                    <table id="table-data" class="table table-striped custom-table mb-0">
                        <thead>
                        <tr>
                            <th>#</th>
                            <th><spring:message code="invoice.index.table.number" /></th>
                            <th><spring:message code="invoice.index.table.patient" /></th>
                            <th><spring:message code="invoice.index.table.date" /></th>
                            <th><spring:message code="invoice.index.table.employee" /></th>
                            <th><spring:message code="invoice.index.table.total" /></th>
                            <th><spring:message code="invoice.index.table.status" /></th>
                            <th class="text-right"><spring:message code="invoice.index.table.action" /></th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:set var="e" value="0" />
                        <c:forEach items="${invoices}" var="i">
                            <tr>
                                <td>${e = e + 1}</td>
                                <td><a href="<c:url value="/admin/invoice/details?id=${i.id}&${lang}" />">${i.id}</a></td>
                                <td>${i.toaThuoc.benhNhan.ten}</td>
                                <td><fmt:formatDate value="${i.ngayXuat}" pattern="dd-MM-yyyy" /></td>
                                <td>${i.nhanVien.ten}</td>
                                <td>${i.tongTien}</td>
                                <td><span class="custom-badge status-green"><spring:message code="invoice.index.table.status.paid" /></span></td>
                                <td class="text-right">
                                    <div class="dropdown dropdown-action">
                                        <a href="#" class="action-icon dropdown-toggle" data-toggle="dropdown" aria-expanded="false"><i class="fa fa-ellipsis-v"></i></a>
                                        <div class="dropdown-menu dropdown-menu-right">
                                            <a class="dropdown-item" href="<c:url value="/admin/invoice/addorupdate?id=${i.id}&${lang}" />"><i class="fa fa-pencil m-r-5"></i> <spring:message code="invoice.index.table.action.edit" /></a>
                                            <a class="dropdown-item" href="<c:url value="/admin/invoice/details?id=${i.id}&${lang}" />"><i class="fa fa-eye m-r-5"></i> <spring:message code="invoice.index.table.action.view" /></a>
                                            <a class="dropdown-item" href="#" data-toggle="modal" data-target="#delete_invoice${i.id}"><i class="fa fa-trash-o m-r-5"></i> <spring:message code="invoice.index.table.action.delete" /></a>
                                        </div>
                                    </div>
                                    <div id="delete_invoice${i.id}" class="modal fade delete-modal" role="dialog">
                                        <div class="modal-dialog modal-dialog-centered">
                                            <div class="modal-content">
                                                <div class="modal-body text-center">
                                                    <h3><spring:message code="invoice.index.table.action.delete.msg" /></h3>
                                                    <div class="m-t-20"> <a href="#" class="btn btn-white" data-dismiss="modal"><spring:message code="invoice.index.table.action.close" /></a>
                                                        <form:form action="/admin/invoice/delete?id=${i.id}&${lang}" method="post">
                                                            <button type="submit" class="btn btn-danger"><spring:message code="invoice.index.table.action.delete" /></button>
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
    function searchInvoice() {
        let list = document.querySelectorAll("div.cal-icon > input");
        let fromm = list[0].value;
        let to = list[1].value;
        $.getJSON("/admin/invoice/search?from="+fromm+"&to="+to).done(function (task) {
            $('#table-data tbody tr').remove();
            console.log(JSON.stringify(task));
            let ds = task;
            for(let i = 0; i < ds.length; i++) {
                console.log(ds[i].id);
                if (ds[i].id != null) {
                    $('#table-data').find('tbody').append(
                        '<tr>'+
                        '<td>'+ (parseInt(i) + 1) +'</td>'+
                        '<td>' +
                        '<a href="/admin/invoice/details?id='+ds[i].id+'&${lang}">'+ds[i].id+'</a>'+
                        '</td>'+
                        '<td>'+ds[i].tenBenhNhan+'</td>'+
                        '<td>'+formatDate(ds[i].ngayXuat)+
                        '</td>'+
                        '<td>'+ds[i].tenNhanVien+'</td>'+
                        '<td>'+ds[i].tongTien+'</td>'+
                        '<td>' +
                        '<span class="custom-badge status-green"><spring:message code="invoice.index.table.status.paid" /></span>' +
                        '</td>'+
                        '<td class="text-right">'+
                        '<div class="dropdown dropdown-action">'+
                        '<a href="#" class="action-icon dropdown-toggle" ' +
                        'data-toggle="dropdown" aria-expanded="false">' +
                        '<i class="fa fa-ellipsis-v"></i></a>'+
                        '<div class="dropdown-menu dropdown-menu-right">'+
                        '<a class="dropdown-item" ' +
                        'href="/admin/invoice/addorupdate?id='+ds[i].id+'&${lang}">'+
                        '<i class="fa fa-pencil m-r-5"></i> <spring:message code="invoice.index.table.action.edit"/></a>'+
                        '<a class="dropdown-item" ' +
                        'href="/admin/invoice/details?id='+ds[i].id+'&${lang}">'+
                        '<i class="fa fa-eye m-r-5"></i> <spring:message code="invoice.index.table.action.view" /></a>'+
                        '<a class="dropdown-item" ' +
                        'href="#" data-toggle="modal" data-target="#delete_invoice'+ds[i].id+'">'+
                        '<i class="fa fa-trash-o m-r-5"></i> <spring:message code="invoice.index.table.action.delete" /></a>'+
                        '</div>'+
                        '</div>'+
                        '<div id="delete_invoice'+ds[i].id+'" class="modal fade delete-modal" role="dialog">'+
                        '<div class="modal-dialog modal-dialog-centered">'+
                        '<div class="modal-content">'+
                        '<div class="modal-body text-center">'+
                        '<h3><spring:message code="invoice.index.table.action.delete.msg" /></h3>'+
                        '<div class="m-t-20"> <a href="#" class="btn btn-white" data-dismiss="modal">' +
                        '<spring:message code="invoice.index.table.action.close" /></a>'+
                        '<form action="/admin/invoice/delete?id='+ds[i].id+'&${lang}" method="post">'+
                        '<button type="submit" class="btn btn-danger"><spring:message code="invoice.index.table.action.delete" /></button>'+
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