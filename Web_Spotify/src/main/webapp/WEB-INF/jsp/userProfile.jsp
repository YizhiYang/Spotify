<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
        <title>Edit Profile</title>
          <link rel="stylesheet" href="./css/user-profile/top.css">
          <link rel="stylesheet" href="./css/user-profile/bottom.css">
          <link rel="stylesheet" href="./css/user-profile/bodyStyle.css">

          <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
        
        
        <link href="./js/profilePageJS/libs/bootstrap/css/bootstrap.css" rel="stylesheet">
        <script src="./js/profilePageJS/libs/jquery/jquery-1.8.2.min.js"></script>
        <script src="./js/profilePageJS/libs/bootstrap/js/bootstrap.min.js"></script>

        <link href="./js/profilePageJS/bootstrap-editable/css/bootstrap-editable.css" rel="stylesheet">
        <script src="./js/profilePageJS/bootstrap-editable/js/bootstrap-editable.min.js"></script>

    <link href="https://fonts.googleapis.com/css?family=Montserrat" rel="stylesheet">
</head>
<body>
    <div id=userPageMain>
        <div id=userPageWrapper>
            <div id="userProfile">
                <div id="userProfileWrapper">
                    <div id="userImage"></div>
                    <div id="editRemoveImage">
                        <button class="editRemoveImageElement">Edit Image</button>
                        <button class="editRemoveImageElement">Remove Image</button>
                        <button class="editRemoveImageElement logout" onclick='logoutUser()'>Logout</button>
                    </div>
                </div>
            </div>
            <div id=userSettings>
                <div id="userContainerOuterWrapper">
                    <div class='userContainer'>
                        <div id=userContent>
                            <div class=userContentHeader>Profile</div>
                            <div class="userContentList">
<!--
                                    <div class="userContentElementList">
                                        <div class="userContentElementType">Email</div>
                                        <div class="userContentElementContent" contenteditable="true">test@gmail.com</div>
                                        <i class="material-icons profilePageIcon" style="font-size:15px">edit</i>
                                    </div>
                                    <div class="userContentElementList">
                                        <div class="userContentElementType">Password</div>
                                        <div class="userContentElementContent" contenteditable="true">XXXXXXXXXXXX<i class="material-icons profilePageIcon" style="font-size:15px">edit</i></div>
                                    </div>
                                    <div class="userContentElementList">
                                        <div class="userContentElementType">Date of Birth</div>
                                        <div class="userContentElementContent" contenteditable="true">01 Jan 1970<i class="material-icons profilePageIcon" style="font-size:15px">edit</i></div>
                                    </div>
                                    <div class="userContentElementList">
                                        <div class="userContentElementType">Country</div>
                                        <div class="userContentElementContent" contenteditable="true">United States<i class="material-icons profilePageIcon" style="font-size:15px">edit</i></div>
                                    </div>      
-->
<!--
                                    <div class="userContentElementList">
                                        <div class="userContentElementType">Theme</div>
                                        <div class="userContentElementContent">RED<i class="material-icons profilePageIcon" style="font-size:15px">edit</i></div>
                                    </div>
-->
                                <form>
<!--                                      readonly="readonly" style='background-color:white; box-shadow: none; border: none'-->
                                  <div class="userContentElementList">
                                    <div class="userContentElementType">Email</div>
                                    <input class='userContentElementContent' type="text" value="test@gmail.com">
                                  </div>
                                  <div class="userContentElementList">
                                    <div class="userContentElementType">Password</div>
                                    <input class='userContentElementContent' type="password" value="*********">
                                  </div>
                                  <div class="userContentElementList">
                                    <div class="userContentElementType">Date of Birth</div>
                                    <input class='userContentElementContent' type="date" value="01 Jan 1970">
                                  </div>
                                  <div class="userContentElementList">
                                    <div class="userContentElementType">Country</div>
                                    <input class='userContentElementContent' type="text" value="United States">
                                  </div>
                                </form>
                                <div class="userContentElementList">
                                    <button class="btn btn-danger">Save</button>
                                </div>
                            </div>
                        </div>
                    </div>                
                </div>                                  
            </div>           
        </div>     
    </div>
</body>
</html>