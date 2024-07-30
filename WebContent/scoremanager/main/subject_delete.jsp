<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:import url="/common/base.jsp">
    <c:param name="title">
        科目削除システム
    </c:param>

    <c:param name="scripts">
        <script type="text/javascript">
        </script>
    </c:param>

    <c:param name="content">
        <section class="me-4">
            <h2 class="h3 mb-3 fw-normal bg-secondary bg-opacity-10 py-2 px-4">科目削除</h2>
            <div class="bg-success bg-opacity-50 text-center lh-lg">
                    <label for="inputSubjectCode" class="col-sm-2 col-form-label">科目コード</label>
                    <div class="col-sm-10">
                        <input type="text" class="form-control" id="inputSubjectCode" name="code" value="${subject.code}" required>
                    </div>
                    <button type="button" class="btn btn-primary" onclick="window.location.href='subject_delete_done.jsp';">削除</button>
            </div>
            <div class="lh-lg row" style="margin-top: 8rem;">
                <div class="mx-3 col-2">
                    <a href="SubjectList.action">科目一覧へ戻る</a>
                </div>
            </div>
        </section>
    </c:param>
</c:import>
