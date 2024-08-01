package scoremanager.main;

import java.time.LocalDate;
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

public class SubjectUpdateAction extends Action {

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
        // ローカル変数の宣言 1
        HttpSession session = req.getSession(); // セッション
        Teacher teacher = (Teacher) session.getAttribute("user"); // ログインユーザー
        String cd = ""; // 入力された科目コード
        String name = ""; // 入力された科目名
        String school = ""; // 入力された学校コード
        List<Subject> subjects = null; // 科目リスト
        LocalDate todaysDate = LocalDate.now(); // LocalDateインスタンスを取得
        int year = todaysDate.getYear(); // 現在の年を取得
        SubjectDao sDao = new SubjectDao(); // 科目Daoを初期化
        Map<String, String> errors = new HashMap<>(); // エラーメッセージ

        // リクエストパラメータ―の取得 2
        String cdStr = req.getParameter("f1");
        String nameStr = req.getParameter("f2");
        String schoolStr = req.getParameter("f3");

        // DBからデータ取得 3
        // ログインユーザーの学校コードをもとに科目の一覧を取得
        List<Subject> list = sDao.filter(teacher.getSchool());

        // 科目コードが送信されていた場合
        if (cdStr != null) {
            cd = cdStr;
        }

        // 科目名が送信されていた場合
        if (nameStr != null) {
            name = nameStr;
        }

        // 学校コードが送信されていた場合
        if (schoolStr != null) {
            school = schoolStr;
        }


        // ビジネスロジック 4
        // リストを初期化
        List<Integer> entYearSet = new ArrayList<>();
        // 10年前から1年後まで年をリストに追加
        for (int i = year - 10; i < year + 1; i++) {
            entYearSet.add(i);
        }

        // DBへデータ保存 5
        // なし

        // レスポンス値をセット 6
        // リクエストに科目コードをセット
        req.setAttribute("f1", cd);
        // リクエストに科目名をセット
        req.setAttribute("f2", name);
        // リクエストに学校コードをセット
        req.setAttribute("f3", school);
        // リクエストに科目リストをセット
        req.setAttribute("subjects", subjects);
        // リクエストにデータをセット
        req.setAttribute("class_num_set", list);
        req.setAttribute("ent_year_set", entYearSet);

        // JSPへフォワード 7
        req.getRequestDispatcher("subject_list.jsp").forward(req, res);
    }
}

