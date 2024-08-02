<%-- SubjectUpdate.jsp --%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:import url="/common/base.jsp">
    <c:param name="title">
        科目変更
    </c:param>

    <c:param name="scripts"></c:param>

    <c:param name="content">
        <section class="me-4">
            <h2 class="h3 mb-3 fw-normal bg-secondary bg-opacity-10 py-2 px-4">科目変更</h2>
            <form method="post" action="SubjectUpdate.action">
                <input type="hidden" name="code" value="${param.code}" />

                <div class="mb-3 row">
                    <label for="inputSubjectCode" class="col-sm-2 col-form-label">科目コード</label>
                    <div class="col-sm-10">
                        <input type="text" class="form-control" id="inputSubjectCode" name="code" value="${subject.code}" required>
                    </div>
                </div>
                <div class="mb-3 row">
                    <label for="inputSubjectName" class="col-sm-2 col-form-label">科目名</label>
                    <div class="col-sm-10">
                        <input type="text" class="form-control" id="inputSubjectName" name="name" value="${subject.name}" required>
                    </div>
                </div>

                <div class="mt-3">
                   <button type="button" class="btn btn-primary" onclick="window.location.href='subject_update_done.jsp';">変更</button>

                    <a href="subject_list.jsp" class="btn btn-secondary">キャンセル</a>
                </div>
            </form>
        </section>
    </c:param>
</c:import>
