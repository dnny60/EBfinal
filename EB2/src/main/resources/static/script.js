//bar graph for annual quartly, monthly
google.charts.load('current', {'packages':['bar']});
google.charts.setOnLoadCallback(drawAnnualChart); //annual chart
google.charts.setOnLoadCallback(drawQuartChart); //quartly chart
google.charts.setOnLoadCallback(drawMonthChart); //monthly chart

//annual chart 
function drawAnnualChart() {
  var data = google.visualization.arrayToDataTable([
    ['年', '銷售額'],
    ['2019', 290270],
    ['2020', 300200],
    ['2021', 530200],
    ['2022', 636720]
  ]);

  var options = {
    chart: {
      title: '年銷售額',
      subtitle: '近四年年銷售額',
    }
  };

  var chart = new google.charts.Bar(document.getElementById('annual_chart'));
  chart.draw(data, google.charts.Bar.convertOptions(options));
}

//quartly chart 
function drawQuartChart() {
  var data = google.visualization.arrayToDataTable([
    ['季', '銷售額'],
    ['1', 134800],
    ['2', 137320],
    ['3', 194350],
    ['4', 170250]
  ]);

  var options = {
    chart: {
      title: '季銷售額',
      subtitle: '四季銷售額',
     }
  };

  var chart = new google.charts.Bar(document.getElementById('quart_chart'));
  chart.draw(data, google.charts.Bar.convertOptions(options));
}

//monthly chart
function drawMonthChart() {
  var data = google.visualization.arrayToDataTable([
    ['月', '銷售額'],
    ['1', 41000],
    ['2', 54600],
    ['3', 39200],
    ['4', 40030], 
    ['5', 47090],
    ['6', 50200],
    ['7', 60300],
    ['8', 70020], 
    ['9', 64030],
    ['10', 50020],
    ['11', 60200],
    ['12', 60030], 
   
  ]);

  var options = {
    chart: {
      title: '月銷售額',
      subtitle: '1~12月銷售額',
    }
  };

  var chart = new google.charts.Bar(document.getElementById('month_chart'));
  chart.draw(data, google.charts.Bar.convertOptions(options));
}

//bar chart for customer age distribution
google.charts.load('current', {'packages':['bar']});
google.charts.setOnLoadCallback(drawAgeChart);

function drawAgeChart() {
  var data = google.visualization.arrayToDataTable([
    ['年齡', '人數'],
    ['0-9', 0],
    ['10-19', 1],
    ['20-24', 1],
    ['25-29', 0],
    ['30-34', 2],
    ['35-39', 0],
    ['40-44', 4],
    ['45-49', 3],
    ['50-54', 10],
    ['55-59', 11],
    ['60-64', 10],
    ['65-69', 13],
    ['70-74', 17],
    ['75-79', 15],
    ['80-84', 9],
    ['85-89', 7],
    ['90-94', 3],
    ['95-99', 1],
    ['100+', 0],

  ]);

  var options = {
    chart: {
      title: '年齡分佈圖',
     },
    bars: 'horizontal' // Required for Material Bar Charts.
  };

  var chart = new google.charts.Bar(document.getElementById('age-barchart'));

  chart.draw(data, google.charts.Bar.convertOptions(options));
}
//pie chart for customer location disdribution
google.charts.load('current', {'packages':['corechart']});
google.charts.setOnLoadCallback(drawLocationChart);

function drawLocationChart() {
  var data = google.visualization.arrayToDataTable([
    ['縣市', '人數'],
    ['台北', 11],
    ['新北', 2],
    ['基隆', 2],
    ['新竹', 2],
    ['桃園', 7], 
    ['台中', 8], 
    ['苗栗', 9], 
    ['彰化', 2], 
    ['南投', 3], 
    ['雲林', 5], 
    ['高雄', 2], 
    ['台南', 4], 
    ['嘉義', 17], 
    ['屏東', 8], 
    ['澎湖', 9], 
    ['花蓮', 7], 
    ['台東', 9], 

  ]);

  var options = {
    title: '地區分佈圖'
  };

  var chart = new google.visualization.PieChart(document.getElementById('location-piechart'));

  chart.draw(data, options);
}

let menu = document.querySelector('.bx-menu');
let navbar = document.querySelector('.navbar');

//menu bar
menu.addEventListener('click', function(){
    menu.classList.toggle('bx-x');
    navbar.classList.toggle('nav-toggle');
})
//scroll bar
window.addEventListener('scroll', ()=>{
    menu.classList.remove('bx-x');
    navbar.classList.remove('nav-toggle');
})

const header = document.querySelector('header');
window.onscroll = function(){
    if(document.documentElement.scrollTop > 5){
        header.style.height = '100px';
        header.style.backgroundColor = '#fff'
    }else{
        header.style.height = '100px';
        header.style.backgroundColor = '#fff'
    }
}

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


