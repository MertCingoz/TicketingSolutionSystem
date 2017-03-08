<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN"
  "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
 
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en">
    <head>
        <meta content="text/html;charset=utf-8" http-equiv="Content-Type" />
        <meta content="utf-8" http-equiv="encoding" />
 
<script type="text/javascript" src="js/jquery.js"></script>
        <script type="text/javascript">
 
        $(document).ready(function() 
        {
            $("#uploadBtn").click(function() 
            {
                $('input[name="file"]').each(function(index, value) 
                { 
                    var file = value.files[0];
                    if(file) 
                    {
                        var formData = new FormData();
                        formData.append('file', file);
                        $.ajax({
                          url : '/TicketingSolutionRest/singleUpload',
                          type : 'POST',
                          data : formData,
                          cache : false,
                          contentType : false,
                          processData : false,
                          success : function(data, textStatus, jqXHR) {
                                var message = jqXHR.responseText;
                                $("#messages").append("<li>" + message + "</li>");
                          },
                          error : function(jqXHR, textStatus, errorThrown) {
                                $("#messages").append("<li style='color: red;'>" + textStatus + "</li>");
                          }
                        });
                    }
                });
            });
        });
        </script>
</head>
<body>
    <h1>JAX-RS Multi-file Upload Example</h1>
 
    <form action="rest/upload/pdf" method="post" enctype="multipart/form-data">
 
        <p>
            Select file 1: <input type="file" name="file" size="45"  accept=".pdf" />
        </p>
        
        <p>
            <input id="uploadBtn" type="button" value="Upload PFD Files" />
        </p>
 
    </form>
     
    <ul id="messages">    
    </ul>
 
</body>
</html>