<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<div class="page-wrapper" style="min-height: 754px;">
    <div class="content">
        <div class="row">
            <select onchange="filter(this)" name="filter" id="filter" class="form-control select">
                <option value=""><spring:message code="filter" /></option>
                <c:set var="year">
                    <spring:message code="year"/>
                </c:set>
                <c:set var="month">
                    <spring:message code="month"/>
                </c:set>
                <c:set var="present">
                    <spring:message code="present"/>
                </c:set>
                <optgroup label="${year}">
                    <option value="year-from:2019_to:2020"><spring:message code="from"/> 2019 <spring:message code="to"/> 2020</option>
                    <option value="year-from:2020_to:2021"><spring:message code="from"/> 2020 <spring:message code="to"/> 2021</option>
                    <option value="year-from:2021_to:2022"><spring:message code="from"/> 2021 <spring:message code="to"/> 2022</option>
                </optgroup>
                <optgroup label="${month} (${present})">
                    <option value="month-from:1_to:6"><spring:message code="from"/> 1 <spring:message code="to"/> 6</option>
                    <option value="month-from:6_to:12"><spring:message code="from"/> 6 <spring:message code="to"/> 12</option>
                </optgroup>
            </select>
        </div>
        <div class="row text-center" style="margin-top: 20px">
            <div class="col-sm-12 col-md-12">
                <h3><spring:message code="doctors.title" /></h3>
                <canvas id="bd3"></canvas>
            </div>
        </div>
    </div>
</div>

<script>
    drawPieChart();

    function filter(obj) {
        drawPieChart(obj.value);
    }

    function drawPieChart() {
        //Pie Chart doctors
        let dt = [];
        if (arguments[0] != null) {
            $.getJSON('/api/getTotalPrescriptionOfDoctor?filter='+arguments[0]).done(function (data) {
                dt = data;
            })
        }
        else {
            $.getJSON('/api/getTotalPrescriptionOfDoctor').done(function (data) {
                dt = data;
            })
        }

        $.getJSON("/api/getTotalDoctors").done(function (data) {
            console.log(data);
            let labels = [];
            let backgroundColors = [];
            for (let i = 0; i < data.length; i++) {
                labels.push(data[i].ten);
                backgroundColors.push(getRandomColor());
            }
            let pieChartData = {
                labels: labels,
                datasets: [{
                    backgroundColor: backgroundColors,
                    data: dt
                }]
            };
            let pie = document.getElementById("bd3").getContext("2d");
            new Chart(pie, {
                type: 'pie',
                data: pieChartData,
                options: {
                    responsive: true,
                    tooltips: {
                        mode: 'index',
                        intersect: true,
                    }
                }
            })
        })
    }
</script>