<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<html>
  <head>
    <meta http-equiv="content-type" content="text/html; charset=UTF-8">
    <title>Hello App Engine</title>
    <link rel="stylesheet" type="text/css" href="stylesheets/index.css"/>
    <link rel="stylesheet" type="text/css" href="stylesheets/general.css"/>

  </head>

  <body>
    <%@include file="header1.html"%>
    <form action="/main" method="get">
        <p class = "text">Full Name:</p>
        <input type="text" name="name"><br>
        <p class="text">Year:</p>
        <input type="text" name="year"><br>
        <p class="text">Phone Number:</p>
        <input type="text" name="phone"><br>
        <input type="submit" value="Submit" id="button">
    </form>
  </body>
</html>
