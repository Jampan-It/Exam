package scoremanager.main;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.StudentDao;
import dao.SubjectDao;
import tool.Action;


public class SubjectCreateExecuteAction extends Action {

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
		//ローカル変数の宣言 1
		HttpSession session = req.getSession();//セッション
		StudentDao sDao = new StudentDao();//学生Dao

		String cd = "";//学生番号
		String name = "";//氏名StudentCreateExecuteAction.java
		String school = "";//クラス番号

		Map<String, String> errors = new HashMap<>();// エラーメッセージ
		SubjectDao SubjectDao = new SubjectDao();// クラス番号Daoを初期化


		//エラーがあったかどうかで手順6~7の内容が分岐
		//レスポンス値をセット 6
		//JSPへフォワード 7

		if(!errors.isEmpty()){
			// リクエスト属性をセット

			req.setAttribute("cd", cd);
			req.setAttribute("name", name);
			req.setAttribute("school",school);
			req.getRequestDispatcher("subject_create.jsp").forward(req, res);
			return;
		}
		req.getRequestDispatcher("subject_create_done.jsp").forward(req, res);
	}
}