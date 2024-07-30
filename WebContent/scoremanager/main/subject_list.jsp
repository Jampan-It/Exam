<%-- 科目一覧JSP --%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:import url="/common/base.jsp">
    <c:param name="title">
        科目管理システム
    </c:param>

    <c:param name="scripts"></c:param>

    <c:param name="content">
        <section class="me-4">
            <h2 class="h3 mb-3 fw-normal bg-secondary bg-opacity-10 py-2 px-4">科目管理</h2>
            <div class="my-2 text-end px-4">
                <a href="SubjectCreate.action">新規登録</a><br>
                <a href="subject_change.jsp">科目変更</a><br>
                <a href="subject_delete.jsp">科目削除</a>

            </div>
            <form method="get">

                    <div class="col-4">
                        <label class="form-label" for="subject-f2-select">科目名</label>
                        <select class="form-select " id="subject-f2-select" name="f2">
                            <option value="0">--------</option>
                            <c:forEach var="subject" items="${subject_set}">
                                <option value="${subject}" <c:if test="${subject==f2}">selected</c:if>>${subject}</option>
                            </c:forEach>
                        </select>
                    </div>
                    <div class="col-2 form-check text-center">
                        <label class="form-check-label" for="subject-f3-check">有効
                            <input class="form-check-input" type="checkbox"
                            id="subject-f3-check" name="f3" value="t"
                            <c:if test="${!empty f3}">checked</c:if> />
                        </label>
                    </div>
                    <div class="col-2 text-center">
                        <button class="btn btn-secondary" id="filter-button">絞り込み</button>
                    </div>
                    <div class="mt-2 text-warning">${errors.get("f1")}</div>
                </div>
            </form>
            <c:choose>
                <c:when test="${subjects.size()>0}">
                    <div>検索結果：${subjects.size()}件</div>
                    <table class="table table-hover">
                        <tr>

                            <th>科目コード</th>
                            <th>科目名</th>


                            <th></th>
                            <th></th>
                        </tr>
                        <c:forEach var="subject" items="${subjects}">
                            <tr>
                                <td>${subject.openYear}</td>
                                <td>${subject.code}</td>
                                <td>${subject.name}</td>
                                <td>${subject.teacher}</td>
                                <td class="text-center">
                                    <c:choose>
                                        <c:when test="${subject.isActive()}">
                                            ○
                                        </c:when>
                                        <c:otherwise>
                                            ×
                                        </c:otherwise>
                                    </c:choose>
                                </td>
                                <td><a href="SubjectUpdate.action?code=${subject.code}">変更</a></td>
                            </tr>
                        </c:forEach>
                    </table>
                </c:when>
                <c:otherwise>
                    <div>科目情報が存在しませんでした</div>
                </c:otherwise>
            </c:choose>
        </section>
    </c:param>
</c:import>
