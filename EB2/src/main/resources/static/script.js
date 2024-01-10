//bar graph for annual quartly, monthly
google.charts.load("current", { packages: ["bar"] });
google.charts.setOnLoadCallback(drawAnnualChart); //annual chart
google.charts.setOnLoadCallback(drawQuartChart); //quartly chart
google.charts.setOnLoadCallback(drawMonthChart); //monthly chart

//annual chart
function drawAnnualChart() {
  var data = google.visualization.arrayToDataTable([
    ["年", "銷售額"],
    ["2019", 290270],
    ["2020", 300200],
    ["2021", 530200],
    ["2022", 636720],
  ]);

  var options = {
    chart: {
      title: "年銷售額",
      subtitle: "近四年年銷售額",
    },
  };

  var chart = new google.charts.Bar(document.getElementById("annual_chart"));
  chart.draw(data, google.charts.Bar.convertOptions(options));
}

//quartly chart
function drawQuartChart() {
  var data = google.visualization.arrayToDataTable([
    ["季", "銷售額"],
    ["1", 134800],
    ["2", 137320],
    ["3", 194350],
    ["4", 170250],
  ]);

  var options = {
    chart: {
      title: "季銷售額",
      subtitle: "四季銷售額",
    },
  };

  var chart = new google.charts.Bar(document.getElementById("quart_chart"));
  chart.draw(data, google.charts.Bar.convertOptions(options));
}

//monthly chart
function drawMonthChart() {
  var data = google.visualization.arrayToDataTable([
    ["月", "銷售額"],
    ["1", 41000],
    ["2", 54600],
    ["3", 39200],
    ["4", 40030],
    ["5", 47090],
    ["6", 50200],
    ["7", 60300],
    ["8", 70020],
    ["9", 64030],
    ["10", 50020],
    ["11", 60200],
    ["12", 60030],
  ]);

  var options = {
    chart: {
      title: "月銷售額",
      subtitle: "1~12月銷售額",
    },
  };

  var chart = new google.charts.Bar(document.getElementById("month_chart"));
  chart.draw(data, google.charts.Bar.convertOptions(options));
}

//bar chart for customer age distribution
google.charts.load("current", { packages: ["bar"] });
google.charts.setOnLoadCallback(drawAgeChart);

function drawAgeChart() {
  fetch("/customer/all")
    .then((response) => response.json())
    .then((customers) => {
      var ageCount = {}; // 用于统计每个年龄的出现次数

      // 统计每个年龄的出现次数
      customers.forEach((customer) => {
        if (ageCount[customer.cage]) {
          ageCount[customer.cage] += 1;
        } else {
          ageCount[customer.cage] = 1;
        }
      });

      // 转换为 ageStatistics 数组的格式
      var ageStatistics = [["年齡", "人數"]];
      for (var age in ageCount) {
        ageStatistics.push([age.toString(), ageCount[age]]);
      }

      console.log(ageStatistics);
      console.log("after");

      var data = google.visualization.arrayToDataTable(ageStatistics);

      var options = {
        chart: {
          title: "年齡分佈圖",
        },
        vAxis: {
          scaleType: "null",
          title: "人數",
          ticks: [0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10],
        },
        bars: "vertical", // Required for Material Bar Charts.
      };

      var chart = new google.charts.Bar(
        document.getElementById("age-barchart")
      );

      chart.draw(data, google.charts.Bar.convertOptions(options));
    });
}

//pie chart for customer location disdribution
google.charts.load("current", { packages: ["corechart"] });
google.charts.setOnLoadCallback(drawLocationChart);

function drawLocationChart() {
  fetch("/customer/all")
    .then((response) => response.json())
    .then((customers) => {
      var locationCount = {}; // 用于统计每个地区的客户数量

      // 统计每个地区的客户数量
      customers.forEach((customer) => {
        console.log(customer.address);
        if (locationCount[customer.address]) {
          locationCount[customer.address] += 1;
        } else {
          locationCount[customer.address] = 1;
        }
      });

      // 转换为 locationStatistics 数组的格式
      var locationStatistics = [["縣市", "人數"]];
      for (var location in locationCount) {
        locationStatistics.push([location.toString(), locationCount[location]]);
      }

      var data = google.visualization.arrayToDataTable(locationStatistics);

      var options = {
        title: "地區分佈圖",
      };

      var chart = new google.visualization.PieChart(
        document.getElementById("location-piechart")
      );

      chart.draw(data, options);
    })
    .catch((error) => {
      console.error("Error fetching customer data:", error);
    });
}

let menu = document.querySelector(".bx-menu");
let navbar = document.querySelector(".navbar");

//menu bar
menu.addEventListener("click", function () {
  menu.classList.toggle("bx-x");
  navbar.classList.toggle("nav-toggle");
});
//scroll bar
window.addEventListener("scroll", () => {
  menu.classList.remove("bx-x");
  navbar.classList.remove("nav-toggle");
});

const header = document.querySelector("header");
window.onscroll = function () {
  if (document.documentElement.scrollTop > 5) {
    header.style.height = "100px";
    header.style.backgroundColor = "#fff";
  } else {
    header.style.height = "100px";
    header.style.backgroundColor = "#fff";
  }
};

//search cust info in table
function searchTable() {
  var input, filter, table, tr, td, i, txtValue;
  input = document.getElementById("searchInput");
  filter = input.value.toUpperCase();
  table = document.querySelector(".box-container table");
  tr = table.getElementsByTagName("tr");

  for (i = 0; i < tr.length; i++) {
    td = tr[i].getElementsByTagName("td")[1];
    if (td) {
      txtValue = td.textContent || td.innerText;
      if (txtValue.toUpperCase().indexOf(filter) > -1) {
        tr[i].style.display = "";
      } else {
        tr[i].style.display = "none";
      }
    }
  }
}
function loadCustomerData() {
  fetch("/customer/all")
    .then((response) => response.json())
    .then((customers) => {
      const tableBody = document.querySelector("#cust-info table tbody");
      tableBody.innerHTML = ""; // 清空表格

      customers.forEach((customer) => {
        const row = document.createElement("tr");

        row.innerHTML = `
                  <td>${customer.customerID}</td>
                  <td>${customer.cName}</td>
                  <td>${customer.cage}</td>
                  <td>${customer.address}</td>
                  <td>${customer.cPhoneNum}</td>
                  <td>${customer.lastPurchaseDate}</td>
                  <td>${customer.clv}</td>
              `;

        tableBody.appendChild(row);
      });
    })
    .catch((error) => console.error("Error:", error));
}

function showEditForm(index) {
  document.getElementById("editForm-" + index).style.display = "";
}

function hideEditForm(index) {
  document.getElementById("editForm-" + index).style.display = "none";
}

function toggleEditModeok(index) {
  var editForm = document.getElementById("plantingDetailForm-" + index);
  editForm.style.display =
    editForm.style.display == "none" ? "table-row" : "none";
}

function toggleEditModeGrowthDetail(index) {
  var editForm = document.getElementById("GrowthDetailForm-" + index);
  editForm.style.display =
    editForm.style.display == "none" ? "table-row" : "none";
}

function openForm() {
  document.getElementById("myModal").style.display = "block";
  loadCustomerDataDropList(); // 在打開表單時加載客戶數據
}

function closeForm() {
  document.getElementById("myModal").style.display = "none";
}

function loadCustomerDataDropList() {
  fetch("/customer/all")
    .then((response) => {
      console.log("Response: ", response[0]); // 打印完整的響應對象
      return response.json();
    })
    .then((Customer) => {
      const customerSelect = document.getElementById("customerSelect");
      customerSelect.innerHTML = ""; // 清空下拉式選單中的既有選項
      console.log("Customers: ", Customer); // 打印解析後的 JSON
      Customer.forEach((Customer) => {
        const option = document.createElement("option");
        option.value = Customer.customerID;
        option.textContent = Customer.cName;
        customerSelect.appendChild(option);
      });
    })
    .catch((error) => console.error("Error:", error));
}

function toggleCustomerForm() {
  var formDiv = document.getElementById("customerFormDiv");
  formDiv.style.display = formDiv.style.display === "none" ? "block" : "none";
}

function clearCustomerForm() {
  document.getElementById("customerForm").reset();
}
