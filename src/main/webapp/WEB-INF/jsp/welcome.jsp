<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>
<html>
<head>
<meta name="_csrf" content="${_csrf.token}"/>
<!-- default header name is X-CSRF-TOKEN -->
<meta name="_csrf_header" content="${_csrf.headerName}"/>
<script
  src="https://code.jquery.com/jquery-3.1.1.min.js"
  integrity="sha256-hVVnYaiADRTO2PzUGmuLJr8BLUSjGIZsDYGmIJLv2b8="
  crossorigin="anonymous"></script>
<script type="text/javascript">
  // Add x-csrf-token to all ajax request
  $().ready(function(){ 
	  $.ajaxSetup({
	      headers: {
	          'X-CSRF-Token': $('meta[name="csrf-token"]').attr('content')
	      }
	  })
  });
</script>
<sec:csrfMetaTags />
<meta charset="ISO-8859-1">
<title>Página Principal</title>
</head>
<body>

<p>Página principal jsp</p>
</body>
</html>