<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" import="java.sql.*" import="java.util.ArrayList" import="java.util.Calendar" import="model.*"%>
<!DOCTYPE html>
<html>
 <head>
  <title>Notatum Medicum</title>
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
  <style>
   input.button
   {
    background-color:#0000ff;
    border-style:none;
    color:#ffffff
   }
  </style>
 </head>
 <body>
  <table border="1" width="100%">
   <tr>
    <td colspan="2">
     <div align="center">
      <font size="5">患者一覧画面
      </font>
     </div>
    </td>
   </tr>
   <tr>
    <td colspan="2">
     <div align="right">
      <a href="signup.html">
       <input class="button" type="button" value="患者の新規登録">
      </a>
     </div>
    </td>
   </tr>
   <tr>
    <td colspan="2">
     <p>検索欄
     </p>
     <form action="./PatientSearch" method="POST">
      <p>
       <input type="text" name="keyword" size="40">
       <input class="button" type="submit" value="検索">
      </p>
      <p>
       <input type="radio" name="attribute" value="number" checked="checked">患者番号
      </p>
      <p>
       <input type="radio" name="attribute" value="name">氏名
      </p>
      <p>
       <input type="radio" name="attribute" value="symptom">症状
      </p>
     </form>
    </td>
   </tr>
   <tr>
    <td width="50%">
     <p>
      <div align="center">全患者一覧
      </div>
     </p>
     <p>
      <table border="1" width="100%">
       <tr>
        <td width="30%">
         <div align="center">患者番号
         </div>
        </td>
        <td width="30%">
         <div align="center">氏名
         </div>
        </td>
        <td width="10%">
         <div align="center">性別
         </div>
        </td>
        <td width="10%">
         <div align="center">年齢
         </div>
        </td>
        <td width="20%">
         <div align="center">受付に追加
         </div>
        </td>
       </tr>
       <%! ArrayList<Patient> patients; %>
       <% patients = (ArrayList<Patient>)request.getAttribute("patients"); %>
       <% for(Patient patient : patients){ %>
       <tr>
        <td width="30%">
         <div align="center">
          <%= patient.getPatientID() %>
         </div>
        </td>
        <td width="30%">
         <div align="center">
          <ruby>
           <rb>
            <%= patient.getFName() %>
           </rb>
           <rt>
            <%= patient.getFNameKana() %>
           </rt>
          </ruby>
          <ruby>
           <rb>
            <%= patient.getLName() %>
           </rb>
           <rt>
            <%= patient.getLNameKana() %>
           </rt>
          </ruby>
         </div>
        </td>
        <td width="10%">
         <div align="center">
          <%= patient.getSex() == Sex.male ? "男性" : "女性" %>
         </div>
        </td>
        <td width="10%">
         <div align="center">
          <%= patient.getAge() %>
         </div>
        </td>
        <td width="20%">
         <div align="center">
          <form action="./Reception" method="POST">
           <%= "<input type=\"hidden\" name=\"patientid\" value=\"" + patient.getPatientID() + "\">\n" %>
           <input class="button" type="submit" value="受付に追加">
          </form>
         </div>
        </td>
       </tr>
       <% } %>
      </table>
     </p>
    </td>
    <td>
     <p>
      <div align="center">受付一覧
      </div>
     </p>
     <p>
      <table border="1" width="100%">
       <tr>
        <td width="10%">
         <div align="center">受付番号
         </div>
        </td>
        <td width="30%">
         <div align="center">患者番号
         </div>
        </td>
        <td width="30%">
         <div align="center">氏名
         </div>
        </td>
        <td width="10%">
         <div align="center">性別
         </div>
        </td>
        <td width="10%">
         <div align="center">年齢
         </div>
        </td>
        <td width="10%">
         <div align="center">診察画面へ
         </div>
        </td>
       </tr>
       <%! ArrayList<Reception> receptions; %>
       <% receptions = (ArrayList<Reception>)request.getAttribute("receptions"); %>
       <% for(Reception reception : receptions){ %>
       <% Patient patient = Patient.idSearch(reception.getPatientID()).get(0); %>
       <tr>
        <td width="10%">
         <div align="center">
          <%= reception.getReceptID() %>
         </div>
        </td>
        <td width="30%">
         <div align="center">
          <%= reception.getPatientID() %>
         </div>
        </td>
        <td width="30%">
         <div align="center">
          <ruby>
           <rb>
            <%= patient.getFName() %>
           </rb>
           <rt>
            <%= patient.getFNameKana() %>
           </rt>
          </ruby>
          <ruby>
           <rb>
            <%= patient.getLName() %>
           </rb>
           <rt>
            <%= patient.getLNameKana() %>
           </rt>
          </ruby>
         </div>
        </td>
        <td width="10%">
         <div align="center">
          <%= patient.getSex() == Sex.male ? "男性" : "女性" %>
         </div>
        </td>
        <td width="10%">
         <div align="center">
          <%= patient.getAge() %>
         </div>
        </td>
        <td width="10%">
         <div align="center">
          <form>
           <input class="button" type="button" value="診察画面">
          </form>
         </div>
        </td>
       </tr>
       <% } %>
      </table>
     </p>
    </td>
   </tr>
  </table>
 </body>
</html>
