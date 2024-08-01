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
import bean.Test;
import dao.ClassNumDao;
import dao.SubjectDao;
import dao.TestDao;
import tool.Action;

public class TestRegistAction extends Action {

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
		//ローカル変数の宣言 1
		HttpSession session = req.getSession();//セッション
		Teacher teacher = (Teacher)session.getAttribute("user");//ログインユーザー
		String entYearStr="";// 入力された入学年度
		String classNum = "";//入力されたクラス番号
		String entSubject = "";//入力された科目
		String entNum = "";//入力された回数
		int entYear = 0;// 入学年度
		int Num = 0;//回数
		List<Test> Tests = null;// 学生リスト
		LocalDate todaysDate = LocalDate.now();// LcalDateインスタンスを取得
		int year = todaysDate.getYear();// 現在の年を取得
		TestDao tDao = new TestDao();//成績Dao
		SubjectDao sjDao = new SubjectDao();//科目Dao
		ClassNumDao cNumDao = new ClassNumDao();// クラス番号Daoを初期化
		Map<String, String> errors = new HashMap<>();// エラーメッセージ


		//リクエストパラメータ―の取得 2
		entYearStr = req.getParameter("f1");//入学年度
		classNum = req.getParameter("f2");//クラス番号
		entSubject = req.getParameter("f3");//科目
		entNum = req.getParameter("f4");//回数

		//DBからデータ取得 3
		// ログインユーザーの学校コードをもとにクラス番号の一覧を取得
		List<String> list = cNumDao.filter(teacher.getSchool());

		//DBからデータ取得 4
		// ログインユーザーの学校コードをもとに科目の一覧を取得
		List<Subject> subjects = sjDao.filter(teacher.getSchool());


		//ビジネスロジック 4
		if (entYearStr != null) {
			// 数値に変換
			entYear = Integer.parseInt(entYearStr);
		}

		if (entNum != null) {
			// 数値に変換
			Num = Integer.parseInt(entNum);
		}

		// リストを初期化
		List<Integer> entYearSet = new ArrayList<>();
		// 10年前から1年後まで年をリストに追加
		for (int i = year - 10; i < year + 1; i++) {
			entYearSet.add(i);
		}

		List<Integer> counts = new ArrayList<>();
		counts.add(1);
		counts.add(2);


		//DBへデータ保存 5
		//なし
		//レスポンス値をセット 6
		// リクエストに入学年度をセット
		req.setAttribute("f1", entYear);
		// リクエストにクラス番号をセット
		req.setAttribute("f2", classNum);

		// リクエストにデータをセット
		req.setAttribute("counts_set", counts);
		req.setAttribute("subjects_set", subjects);
		req.setAttribute("class_num_set", list);
		req.setAttribute("ent_year_set", entYearSet);

		//JSPへフォワード 7
		req.getRequestDispatcher("test_regist.jsp").forward(req, res);




		if (entYear == 0) {// 入学年度が選択されていない場合
			errors.put("ent_year", "入学年度を選択してください");
		}else if (classNum == null) {//クラスが選択されていない場合
			errors.put("class_num", "クラスを選択してください");
		}else if (entSubject == null) {//科目が選択されていない場合
			errors.put("subjects", "科目を選択してください");
		}else if (Num == 0) {
			errors.put("counts", "回数を選択してください");


		}
	}
}
