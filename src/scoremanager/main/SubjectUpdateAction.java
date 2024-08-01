package scoremanager.main;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.Subject;
import bean.Teacher;
import dao.SubjectDao;
import tool.Action;

public class SubjectUpdateAction extends Action {

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
        // ローカル変数の宣言 1
        HttpSession session = req.getSession(); // セッション
        Teacher teacher = (Teacher) session.getAttribute("user"); // ログインユーザー
        String cd = ""; // 入力された科目コード
        SubjectDao sDao = new SubjectDao(); // 科目Daoを初期化
        // リクエストパラメータ―の取得 2
         cd = req.getParameter("cd");


        // DBからデータ取得 3
        // ログインユーザーの学校コードをもとに科目の一覧を取得
        Subject subject = sDao.get(cd, teacher.getSchool());


        // レスポンス値をセット 6
        // リクエストに科目コードと科目名をセット
        req.setAttribute("subjects", subject);

        // JSPへフォワード 7
        req.getRequestDispatcher("subject_update.jsp").forward(req, res);
    }
}

