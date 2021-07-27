<%--
  Created by IntelliJ IDEA.
  User: Buu
  Date: 09/04/2021
  Time: 8:21 CH
  To change this template use File | Settings | File Templates.
--%>
<%--
  Created by IntelliJ IDEA.
  User: Buu
  Date: 09/04/2021
  Time: 8:21 CH
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<c:if test="${param.lang != null && !param.lang.isEmpty()}">
    <c:set var="lang" value="lang=${param.lang}"/>
</c:if>
<div class="page-wrapper" style="min-height: 754px;">
    <div class="content">
        <h4><spring:message code="invoice.details.heading" /></h4>
        <table class="table table-responsive-sm">
            <thead>
            <tr>
                <th style="min-width:200px;"><spring:message code="invoice.details.table.thead.id" /></th>
                <th><spring:message code="invoice.details.table.thead.employee" /></th>
                <th><spring:message code="invoice.details.table.thead.patient" /></th>
                <th><spring:message code="invoice.details.table.thead.disease" /></th>
                <th style="min-width: 110px;"><spring:message code="invoice.details.table.thead.date" /></th>
                <th class="text-right"><spring:message code="invoice.details.table.thead.action" /></th>
            </tr>
            </thead>
            <tbody>
            <tr>
                <td>${invoice.id}</td>
                <td>${invoice.employee.ten}</td>
                <td>${invoice.prescription.benhNhan.ten}</td>
                <td>${invoice.prescription.loaiBenh.tenBenh}</td>
                <td>${invoice.date}</td>
                <td class="text-right">
                    <div class="dropdown dropdown-action">
                        <a href="#" class="action-icon dropdown-toggle" data-toggle="dropdown" aria-expanded="false"><i class="fa fa-ellipsis-v"></i></a>
                        <div class="dropdown-menu dropdown-menu-right">
                            <a class="dropdown-item" href="<c:url value="/admin/invoice/addorupdate?id=${invoice.id}&${lang}" />"><i class="fa fa-pencil m-r-5"></i> <spring:message code="invoice.details.table.thead.action.edit" /></a>
                            <a class="dropdown-item" href="#" data-toggle="modal" data-target="#delete_invoice${invoice.id}"><i class="fa fa-trash-o m-r-5"></i> <spring:message code="invoice.details.table.thead.action.delete" /></a>
                        </div>
                    </div>
                    <div id="delete_invoice${invoice.id}" class="modal fade delete-modal" role="dialog">
                        <div class="modal-dialog modal-dialog-centered">
                            <div class="modal-content">
                                <div class="modal-body text-center">
                                    <h3><spring:message code="invoice.details.table.thead.action.delete.msg" /></h3>
                                    <div class="m-t-20"> <a href="#" class="btn btn-white" data-dismiss="modal"><spring:message code="invoice.details.table.thead.action.close" /></a>
                                        <form:form action="/admin/invoice/delete?id=${invoice.id}&${lang}" method="post">
                                            <button type="submit" class="btn btn-danger"><spring:message code="invoice.details.table.thead.action.delete" /></button>
                                        </form:form>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </td>
            </tr>
            </tbody>
            <tfoot>
            <tr>
                <td colspan="5"><spring:message code="invoice.details.table.tfoot.total.disease" /></td>
                <td colspan="1">${invoice.prescription.loaiBenh.donGia} VND</td>
            </tr>
            </tfoot>
        </table>

        <h4><spring:message code="invoice.details.medicines.heading" /></h4>
        <table class="table table-responsive-md">
            <thead>
            <tr>
                <th><spring:message code="patient.details.modal.table.thead.number" /></th>
                <th><spring:message code="patient.details.modal.table.thead.medicine" /></th>
                <th><spring:message code="patient.details.modal.table.thead.quantity" /></th>
                <th><spring:message code="patient.details.modal.table.thead.price" /></th>
                <th><spring:message code="patient.details.modal.table.thead.unit" /></th>
                <th><spring:message code="patient.details.modal.table.thead.total" /></th>
            </tr>
            </thead>
            <tbody>
            <c:set var="i" value="0" />
            <c:forEach items="${invoice.medicines.entrySet()}" var="set">
                <tr>
                    <td>${i = i + 1}</td>
                    <td>${set.getKey().tenThuoc}</td>
                    <td>${set.getValue()}</td>
                    <td>${set.getKey().donGia}</td>
                    <td>${set.getKey().donVi}</td>
                    <td class="total"><c:out value="${set.getKey().donGia * set.getValue()}" /> VND</td>
                </tr>
            </c:forEach>
            </tbody>
            <tfoot>
            <tr>
                <td colspan="5"><spring:message code="patient.details.modal.table.thead.total.medicines" /></td>
                <td id="price" colspan="1"></td>
            </tr>
            </tfoot>
        </table>
    </div>
</div>

<script>
    let e = document.getElementsByClassName("total");
    let totalAmount = 0;
    for (let i = 0; i < e.length; i++) {
        totalAmount = totalAmount + parseInt(e[i].innerHTML);
    }
    let price = document.getElementById("price");
    price.innerHTML = totalAmount + " VND";
</script>
