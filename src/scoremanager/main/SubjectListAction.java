package scoremanager.main;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.Subject;
import bean.Teacher;
import dao.SubjectDao;
import tool.Action;

public class SubjectListAction extends Action {
    @Override
    public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
        HttpSession session = req.getSession();
        Teacher teacher = (Teacher) session.getAttribute("user");

        if (teacher != null && teacher.getSchool() != null) {
            SubjectDao subDao = new SubjectDao();
            List<Subject> subjects = subDao.filter(teacher.getSchool());

            List<Map<String, String>> subjectList = new ArrayList<>();
            for (Subject subject : subjects) {
                Map<String, String> subjectMap = new HashMap<>();
                subjectMap.put("cd", subject.getCd());
                subjectMap.put("name", subject.getName());
                subjectList.add(subjectMap);
            }
            req.setAttribute("subjects", subjectList);
        } else {
            req.setAttribute("error", "ログイン情報が正しくありません。");
        }

        req.getRequestDispatcher("subject_list.jsp").forward(req, res);
    }
}