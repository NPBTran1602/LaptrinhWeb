<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="vi">
<head>
  <meta charset="UTF-8">
  <title>${page.title}</title>
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
  ${page.head}
</head>
<body class="d-flex flex-column min-vh-100">
  <div>
    <%@ include file="/common/web/header.jsp" %>
  </div>
  <div class="container-fluid my-4 flex-grow-1">
    <div class="row">
      <div class="col-md-2">
        <%@ include file="/common/web/left.jsp" %>
      </div>
      <div class="col-md-10">
        ${page.body}
      </div>
    </div>
  </div>
  <div>
    <%@ include file="/common/web/footer.jsp" %>
  </div>
</body>
</html>
