<<!-- html>
<body>
<h2>Hello World!</h2>

<table border="1" width="500px">
	<tr><td rowspan="2" width="300px"></td> <td></td></tr>
	<tr><td></td></tr>
</table>
</body>
</html> -->
<%@ page language="java" import="java.util.*" contentType="text/html; charset=utf-8"%>
<!DOCTYPE html>  
<html>  
<head><title>  
    表格单元格合并Demo  
</title>  
    <script src="Scripts/jquery-1.7.1.min.js" type="text/javascript"></script>  
      
    <script type="text/javascript" src="Scripts/jquery.table.rowspan.js"></script>  
    <script type="text/javascript">  
  
        function initLoad() {  
          
                $("#tbListTM").rowspan(0); //第一列合并  
                $("#tbListTM").rowspan(1);//第二列合并  
            
        }  
        $(document).ready(function () {  
            initLoad();  
        });  
    </script>  
    <style>  
        .Nlist_con table {  
            border-left: 1px solid #c9c9c9;  
            border-top: 1px solid #c9c9c9;  
            border-collapse: collapse;  
        }  
  
            .Nlist_con table th {  
                border-right: 1px solid #c9c9c9;  
                border-bottom: 1px solid #c9c9c9;  
                background: #ececec;  
                padding: 10px 5px;  
                font-size: 14px;  
                color: #2586d8;  
                font-family: "宋体";  
            }  
  
            .Nlist_con table td {  
                border-right: 1px solid #c9c9c9;  
                border-bottom: 1px solid #c9c9c9;  
                background: #fff;  
                padding: 6px 3px;  
                font-size: 12px;  
                line-height: 20px;  
                color: #676767;  
            }  
  
                .Nlist_con table td a {  
                    text-decoration: none;  
                    color: #2586d8;  
                    cursor: pointer;  
                }  
  
                    .Nlist_con table td a:hover {  
                        text-decoration: underline;  
                        color: #2586d8;  
                    }  
  
    </style>  
</head>  
<body>  
    <div class="Nlist_con">  
        <table border="1" cellspacing="1" cellpadding="0" width="100%" id="tbListTM">  
            <tr align="center">  
                <th>  
                    发展领域  
                </th>  
                <th>  
                    发展要素  
                </th>  
                <th style="width:60px">  
                    年度  
                </th>  
                <th style="width:100px">  
                    评价内容  
                </th>  
                <th>  
                    分值  
                </th>  
                <th>  
                    评价办法  
                </th>  
  
            </tr>  
  
            <tr>  
                <td style="text-align: center">  
                    学校发展目标  
                </td>  
                <td>  
                    1.学校有先进办学理念，办学目标符合教育改革发展要求，符合学校实际和发展规律，体现出阶段性、递进性和自身特别。  
                </td>  
                <td>  
                    <span id="ctl00_ContentPlaceHolder1_rptListTM_ctl00_lblND">2015</span>  
                </td>  
                <td>  
                    ee  
                </td>  
                <td>  
                    2  
                </td>  
                <td>办法1</td>  
            </tr>  
  
            <tr>  
                <td style="text-align: center">  
                    学校发展目标  
                </td>  
                <td>  
                    1.学校有先进办学理念，办学目标符合教育改革发展要求，符合学校实际和发展规律，体现出阶段性、递进性和自身特别。  
                </td>  
                <td>  
                    <span id="ctl00_ContentPlaceHolder1_rptListTM_ctl01_lblND">2016</span>  
                </td>  
                <td>  
                    dd  
                </td>  
                <td>  
                    2  
                </td>  
                <td>办法2</td>  
            </tr>  
  
            <tr>  
                <td style="text-align: center">  
                    学校发展目标  
                </td>  
                <td>  
                    1.学校有先进办学理念，办学目标符合教育改革发展要求，符合学校实际和发展规律，体现出阶段性、递进性和自身特别。  
                </td>  
                <td>  
                    <span id="ctl00_ContentPlaceHolder1_rptListTM_ctl02_lblND">2017</span>  
                </td>  
                <td>  
                    ff  
                </td>  
                <td>  
                    2  
                </td>  
                <td>办法3</td>  
            </tr>  
  
            <tr>  
                <td style="text-align: center">  
                    学校发展目标  
                </td>  
                <td>  
                    2.培养目标符合教育方针。注重促进学生、教师主动发展和个性特长的发展，体现学校的个性与特色。  
                </td>  
                <td>  
                    <span id="ctl00_ContentPlaceHolder1_rptListTM_ctl03_lblND">2015</span>  
                </td>  
                <td></td>  
                <td>  
                    0  
                </td>  
                <td></td>  
            </tr>  
  
            <tr>  
                <td style="text-align: center">  
                    学校发展目标  
                </td>  
                <td>  
                    2.培养目标符合教育方针。注重促进学生、教师主动发展和个性特长的发展，体现学校的个性与特色。  
                </td>  
                <td>  
                    <span id="ctl00_ContentPlaceHolder1_rptListTM_ctl04_lblND">2016</span>  
                </td>  
                <td></td>  
                <td>  
                    0  
                </td>  
                <td></td>  
            </tr>  
  
            <tr>  
                <td style="text-align: center">  
                    学校发展目标  
                </td>  
                <td>  
                    2.培养目标符合教育方针。注重促进学生、教师主动发展和个性特长的发展，体现学校的个性与特色。  
                </td>  
                <td>  
                    <span id="ctl00_ContentPlaceHolder1_rptListTM_ctl05_lblND">2017</span>  
                </td>  
                <td></td>  
                <td>  
                    0  
                </td>  
                <td></td>  
            </tr>  
  
        </table>  
  
    </div>  
  
</body>  
</html>  