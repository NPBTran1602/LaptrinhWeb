<%@ page contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head>
  <title>Admin - ${page.title}</title>
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
  ${page.head}
</head>
<body>
  <div class="d-flex">
    <aside class="bg-dark text-white p-3" style="width:220px; min-height:100vh;">
      <%@ include file="/common/admin/left.jsp" %>
    </aside>
    <main class="flex-grow-1 p-4">
      <%@ include file="/common/admin/topbar.jsp" %>
      ${page.body}
    </main>
  </div>
  <%@ include file="/common/admin/footer.jsp" %>
</body>
</html>
