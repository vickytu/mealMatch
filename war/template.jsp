<%@ page import="com.google.appengine.api.datastore.DatastoreService" %>
<%@ page import="com.google.appengine.api.datastore.DatastoreServiceFactory" %>
<%@ page import="com.google.appengine.api.datastore.Entity" %>

<HTML>
<HEAD>
  <link rel="stylesheet" type="text/css" href="stylesheets/colleges.css" />
  <link rel="stylesheet" type="text/css" href="stylesheets/general.css"/>

</HEAD>
<BODY>
<!--       COLLEGE NAME AND LOGO
============================================
-->
  <%
    String[] nameList = (String []) request.getAttribute("nameList");
    String[] yearList = (String []) request.getAttribute("yearList");
    String[] phoneList= (String []) request.getAttribute("phoneList");
    String present = (String) request.getSession(true).getAttribute("Checkedin");
    String collegeName = (String) request.getSession(true).getAttribute("url");
    String college = (String) request.getSession(true).getAttribute("College");

    int total = nameList.length;
  %>
  <div id = "collegeBanner">
    <h1 class = "collegeText" id = "collegeName"><%= collegeName + " Dining Hall"%></h1>
    <h2 class = "collegeText" id = "collegeSubText">People currently eating: <%=total%></h2>
    <h2 class = "collegeText" id = "collegeSubText">Availability: </h2>
    <center>
      <%
      if(total>0){
        int i = 0;
        while(i<total){
        %>
        <div id="container">
      <%
        for(int j=0; j<3&&i<total; j++){
      %>
      <div id="separate">
        <img src="images/profile.png" alt="#" id="profile">
        <br>
        <%=nameList[i]%>
        <br>
        <%=yearList[i]%>
        <br>
        (<%=phoneList[i].substring(0,3)%>) <%=phoneList[i].substring(3,6)%>-<%=phoneList[i].substring(6,10)%>
      </div>
      <%
        i++;
        }
      %>
      </div>
      <br>
      <%
      }
    }
      %>
    </center>
  <%
    if (present.equals("Yes")) {
      if(collegeName.equals(college)){
  %>
    <p id="checkedInMessage">You are already checked in here</p>
  <%
      }else{
  %>
    <p id="checkedInMessage">You are already checked in at <%=college%></p>
  <%
      }
  %>
    <form action="/checkout" method="get">
      <input type="submit" value="Check out" id="checkout">
      <input type="hidden" name="chosen" value=<%=collegeName%>><br>
    </form>
  <%
    }else {
  %>
    <form action="/checkin" method="get">
      <input type="submit" value="Check in" id="checkin">
      <input type="hidden" name="choose" value=<%=collegeName%>><br>
    </form>
  <%
    }
  %>
  </div>
</BODY>
</HTML>