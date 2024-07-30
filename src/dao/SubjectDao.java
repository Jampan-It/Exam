package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bean.School;
import bean.Subject;

public class SubjectDao extends Dao {
<<<<<<< HEAD
	/**
	 * getメソッド 学生番号を指定して学生インスタンスを1件取得する
	 *
	 * @param cd:String
	 *
	 * @return 学生クラスのインスタンス 存在しない場合はnull
	 * @throws Exception
	 */
	public Subject get(String cd) throws Exception {
		// 学生インスタンスを初期化
		Subject subject = new Subject();
		// データベースへのコネクションを確立
		Connection connection = getConnection();
		// プリペアードステートメント
		PreparedStatement statement = null;

		try {
			// プリペアードステートメントにSQL文をセット
			statement = connection.prepareStatement("select * from subject");
			// プリペアードステートメントに学生番号をバインド
			statement.setString(1, cd);
			// プリペアードステートメントを実行
			ResultSet rSet = statement.executeQuery();

			// 学校Daoを初期化
			SchoolDao schoolDao = new SchoolDao();

			if (rSet.next()) {
				// リザルトセットが存在する場合
				// 学生インスタンスに検索結果をセット
				subject.setCd(rSet.getString("cd"));
				subject.setName(rSet.getString("name"));

				subject.setSchool(schoolDao.get(rSet.getString("school")));
			} else {
				// リザルトセットが存在しない場合
				// 学生インスタンスにnullをセット
				subject = null;
			}
		} catch (Exception e) {
			throw e;
		} finally {
			// プリペアードステートメントを閉じる
			if (statement != null) {
				try {
					statement.close();
				} catch (SQLException sqle) {
					throw sqle;
				}
			}
			// コネクションを閉じる
			if (connection != null) {
				try {
					connection.close();
				} catch (SQLException sqle) {
					throw sqle;
				}
			}
		}

		return subject;
	}

	/**
	 * baseSql:String 共通SQL文 プライベート
	 */
	private String baseSql = "select * from student where school_cd=? ";

	/**
	 * postFilterメソッド フィルター後のリストへの格納処理 プライベート
	 *
	 * @param rSet:リザルトセット
	 * @param school:School
	 *            学校
	 * @return 学生のリスト:List<Student> 存在しない場合は0件のリスト
	 * @throws Exception
	 */
	private List<Subject> postFilter(ResultSet rSet, School school) throws Exception {
		// リストを初期化
		List<Subject> list = new ArrayList<>();
		try {
			// リザルトセットを全権走査
			while (rSet.next()) {
				// 学生インスタンスを初期化
				Subject subject = new Subject();
				// 学生インスタンスに検索結果をセット
				subject.setCd(rSet.getString("no"));
				subject.setName(rSet.getString("name"));

				subject.setSchool(school);
				// リストに追加
				list.add(subject);
			}
		} catch (SQLException | NullPointerException e) {
			e.printStackTrace();
		}

		return list;
	}

	/**
	 * filterメソッド 学校、入学年度、クラス番号、在学フラグを指定して学生の一覧を取得する
	 *
	 * @param school:School
	 *            学校
	 * @param entYear:int
	 *            入学年度
	 * @param classNum:String
	 *            クラス番号
	 * @param isAttend:boolean
	 *            在学フラグ
	 * @return 学生のリスト:List<Student> 存在しない場合は0件のリスト
	 * @throws Exception
	 */


	/**
	 * filterメソッド 学校、入学年度、在学フラグを指定して学生の一覧を取得する
	 *
	 * @param school:School
	 *            学校
	 * @param entYear:int
	 *            入学年度
	 * @param isAttend:boolean
	 *            在学フラグ
	 * @return 学生のリスト:List<Student> 存在しない場合は0件のリスト
	 * @throws Exception
	 */
	public List<Subject> filter(School school) throws Exception {
		// リストを初期化
		List<Subject> list = new ArrayList<>();
		// コネクションを確立
		Connection connection = getConnection();
		// プリペアードステートメント
		PreparedStatement statement = null;
		// リザルトセット
		ResultSet rSet = null;
		// SQL文の条件
		String condition = "and ent_year=? ";
		// SQL文のソート
		String order = " order by no asc";

		// SQL文の在学フラグ
		String conditionIsAttend = "";
		// 在学フラグがtrueだった場合


		try {
			// プリペアードステートメントにSQL文をセット
			statement = connection.prepareStatement(baseSql + condition + conditionIsAttend + order);
			// プリペアードステートメントに学校コードをバインド
			statement.setString(1, school.getCd());

			// リストへの格納処理を実行
			list = postFilter(rSet, school);
		} catch (Exception e) {
			throw e;
		} finally {
			// プリペアードステートメントを閉じる
			if (statement != null) {
				try {
					statement.close();
				} catch (SQLException sqle) {
					throw sqle;
				}
			}
			// コネクションを閉じる
			if (connection != null) {
				try {
					connection.close();
				} catch (SQLException sqle) {
					throw sqle;
				}
			}
		}

		return list;
	}

	/**
	 * filterメソッド 学校、在学フラグを指定して学生の一覧を取得する
	 *
	 * @param school:School
	 *            学校
	 * @param isAttend:boolean
	 *            在学フラグ
	 * @return 学生のリスト:List<Subject> 存在しない場合は0件のリスト
	 * @throws Exception
	 */

	/**
	 * saveメソッド 学生インスタンスをデータベースに保存する データが存在する場合は更新、存在しない場合は登録
	 *
	 * @param subject：Subject
	 *            学生
	 * @return 成功:true, 失敗:false
	 * @throws Exception
	 */
	public boolean save(Subject subject) throws Exception {
		// コネクションを確立
		Connection connection = getConnection();
		// プリペアードステートメント
		PreparedStatement statement = null;
		// 実行件数
		int count = 0;

		try {
			// データベースから学生を取得
			Subject old = get(subject.getCd());
			if (old == null) {
				// 学生が存在しなかった場合
				// プリペアードステートメンにINSERT文をセット
				statement = connection.prepareStatement(
						"insert into subject(no, name, ent_year, class_num, is_attend, school_cd) values(?, ?, ?, ?, ?, ?)");
				// プリペアードステートメントに値をバインド
				statement.setString(1, subject.getCd());
				statement.setString(2, subject.getName());

				statement.setString(6, subject.getSchool().getCd());
			} else {
				// 学生が存在した場合
				// プリペアードステートメントにUPDATE文をセット
				statement = connection
						.prepareStatement("update subject set name=?, ent_year=?, class_num=?, is_attend=? where no=?");
				// プリペアードステートメントに値をバインド
				statement.setString(1, subject.getName());


				statement.setString(5, subject.getCd());
			}

			// プリペアードステートメントを実行
			count = statement.executeUpdate();

		} catch (Exception e) {
			throw e;
		} finally {
			// プリペアードステートメントを閉じる
			if (statement != null) {
				try {
					statement.close();
				} catch (SQLException sqle) {
					throw sqle;
				}
			}
			// コネクションを閉じる
			if (connection != null) {
				try {
					connection.close();
				} catch (SQLException sqle) {
					throw sqle;
				}
			}
		}

		if (count > 0) {
			// 実行件数が1件以上ある場合
			return true;
		} else {
			// 実行件数が0件の場合
			return false;
		}
	}

	/**
	 * deleteメソッド 学生をデータベースから削除する
	 *
	 * @param student:Student
	 * @return 成功:true, 失敗:false
	 * @throws Exception
	 */
	public boolean delete(Subject subject) throws Exception {
		// コネクションを確立
		Connection connection = getConnection();
		// プリペアードステートメント
		PreparedStatement statement = null;
		// 実行件数
		int count = 0;

		try {
			// プリペアードステートメントにDELETE文をセット
			statement = connection.prepareStatement("delete from student where no=?");
			// プリペアードステートメントに学生番号をバインド
			statement.setString(1, subject.getCd());
			// プリペアードステートメントを実行
			count = statement.executeUpdate();
		} catch (Exception e) {
			throw e;
		} finally {
			// プリペアードステートメントを閉じる
			if (statement != null) {
				try {
					statement.close();
				} catch (SQLException sqle) {
					throw sqle;
				}
			}
			// コネクションを閉じる
			if (connection != null) {
				try {
					connection.close();
				} catch (SQLException sqle) {
					throw sqle;
				}
			}
		}

		if (count > 0) {
			// 実行件数が1件以上ある場合
			return true;
		} else {
			// 実行件数が0件の場合
			return false;
		}
	}
}
=======
<<<<<<< HEAD
    public Subject get(String cd, School school) throws Exception {
        Subject subject = new Subject();
        Connection connection = getConnection();
        PreparedStatement statement = null;

        try {
            statement = connection.prepareStatement("SELECT * FROM subject WHERE cd=?");
            statement.setString(1, cd);
            ResultSet rSet = statement.executeQuery();

            SchoolDao schoolDao = new SchoolDao();

            if (rSet.next()) {
                subject.setCd(rSet.getString("cd"));
                subject.setName(rSet.getString("name"));
                subject.setSchool(schoolDao.get(rSet.getString("school_cd")));
            } else {
                subject = null;
            }
        } catch (Exception e) {
            throw e;
        } finally {
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException sqle) {
                    throw sqle;
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException sqle) {
                    throw sqle;
                }
            }
        }

        return subject;
    }

    private String baseSql = "SELECT * FROM subject WHERE school_cd=? ";

    public List<Subject> filter(School school) throws Exception {
        List<Subject> list = new ArrayList<>();
        Connection connection = getConnection();
        PreparedStatement statement = null;
        ResultSet rSet = null;
        String order = " ORDER BY cd ASC";

        try {
            statement = connection.prepareStatement(baseSql + order);
            statement.setString(1, school.getCd());
            rSet = statement.executeQuery();

            while (rSet.next()) {
                Subject subject = new Subject();
                subject.setCd(rSet.getString("cd"));
                subject.setName(rSet.getString("name"));
                subject.setSchool(school);
                list.add(subject);
            }
        } catch (Exception e) {
            throw e;
        } finally {
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException sqle) {
                    throw sqle;
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException sqle) {
                    throw sqle;
                }
            }
        }

        return list;
    }

    public boolean save(Subject subject) throws Exception {
        Connection connection = getConnection();
        PreparedStatement statement = null;
        int count = 0;

        try {
            Subject old = get(subject.getCd(), null);
            if (old == null) {
                statement = connection.prepareStatement(
                        "INSERT INTO subject(school_cd, cd, name) VALUES(?, ?, ?)");
                statement.setString(1, subject.getSchool().getCd());
                statement.setString(2, subject.getCd());
                statement.setString(3, subject.getName());
            } else {
                statement = connection.prepareStatement(
                        "UPDATE subject SET name=? WHERE cd=? AND school_cd=?");
                statement.setString(1, subject.getName());
                statement.setString(2, subject.getCd());
                statement.setString(3, subject.getSchool().getCd());
            }

            count = statement.executeUpdate();

        } catch (Exception e) {
            throw e;
        } finally {
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException sqle) {
                    throw sqle;
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException sqle) {
                    throw sqle;
                }
            }
        }

        return count > 0;
    }
}
=======
	/**
	 * getメソッド 学校コードを指定して学校インスタンスを１件取得する
	 *
	 * @param cd:String    school:School
	 *            学校コード		学校
	 * @return 学校クラスのインスタンス 存在しない場合はnull
	 * @throws Exception
	 */
	public Subject get(String cd ,School school) throws Exception {
		// 学校インスタンスを初期化
		Subject subject = new Subject();
		// データベースへのコネクションを確率
		Connection connection = getConnection();
		// プリペアードステートメント
		PreparedStatement statement = null;

		try {
			// プリペアードステートメントにSQL文をセット
			statement = connection.prepareStatement("select * from subject where cd=? and school=?");
			// プリペアードステートメントに学校コードをバインド
			statement.setString(1, cd);
			statement.setObject(2, school);
			// プリペアードステートメントを実行
			ResultSet rSet = statement.executeQuery();

			if (rSet.next()) {
				// リザルトセットが存在する場合
				// 学校インスタンスに学校コードと学校名をセット
				subject.setName(rSet.getString("name"));
			} else {
				// 存在しない場合
				// 学校インスタンスにnullをセット
				subject = null;
			}
		} catch (Exception e) {
			throw e;
		} finally {
			// プリペアードステートメントを閉じる
			if (statement != null) {
				try {
					statement.close();
				} catch (SQLException sqle) {
					throw sqle;
				}
			}
			// コネクションを閉じる
			if (connection != null) {
				try {
					connection.close();
				} catch (SQLException sqle) {
					throw sqle;
				}
			}
		}
		return subject;
	}
	private String baseSql = "select * from subject where school_cd=? ";
	/**
	 * filterメソッド 学校、在学フラグを指定して科目の一覧を取得する
	 *
	 * @param school:School
	 *            学校
	 * @param isAttend:boolean
	 *            在学フラグ
	 * @return 科目のリスト:List<subject> 存在しない場合は0件のリスト
	 * @throws Exception
	 */
	public List<Subject> filter(School school) throws Exception {
		// リストを初期化
		List<Subject> list = new ArrayList<>();
		// コネクションを確立
		Connection connection = getConnection();
		// プリペアードステートメント
		PreparedStatement statement = null;
		// リザルトセット
		ResultSet rSet = null;
		// SQL文の条件
		String order = " order by cd asc";

		try {
			// プリペアードステートメントにSQL文をセット
			statement = connection.prepareStatement(baseSql + order);
			// プリペアードステートメントに学校コードをバイン
			statement.setObject(1, school.getCd());
			// プリペアードステートメントを実行
			rSet = statement.executeQuery();
			// リザルトセットを全権走査
			while (rSet.next()) {
				// 科目インスタンスを初期化
				Subject subject = new Subject();
				// 科目インスタンスに検索結果をセット
				subject.setName(rSet.getString("name"));
				subject.setCd(rSet.getString("cd"));
				subject.setSchool(school);
				// リストに追加
				list.add(subject);
			}

		} catch (Exception e) {
			throw e;
		} finally {
			// プリペアードステートメントを閉じる
			if (statement != null) {
				try {
					statement.close();
				} catch (SQLException sqle) {
					throw sqle;
				}
			}
			// コネクションを閉じる
			if (connection != null) {
				try {
					connection.close();
				} catch (SQLException sqle) {
					throw sqle;
				}
			}
		}

		return list;
	}
	/**
	 * saveメソッド 科目インスタンスをデータベースに保存する データが存在する場合は更新、存在しない場合は登録
	 *
	 * @param subject：subject
	 *            科目
	 * @return 成功:true, 失敗:false
	 * @throws Exception
	 */
	public boolean save(Subject subject) throws Exception {
		// コネクションを確立
		Connection connection = getConnection();
		// プリペアードステートメント
		PreparedStatement statement = null;
		// 実行件数
		int count = 0;

		try {
			// データベースから科目を取得
			Subject old = get(subject.getCd(),subject.getSchool());
			if (old == null) {
				// 科目が存在しなかった場合
				// プリペアードステートメンにINSERT文をセット
				statement = connection.prepareStatement(
						"insert into subject( cd, name, school_cd) values(?, ?, ?");
				// プリペアードステートメントに値をバインド
				statement.setString(1, subject.getCd());
				statement.setString(2, subject.getName());
				statement.setObject(3, subject.getSchool());
			} else {
				// 科目が存在した場合
				// プリペアードステートメントにUPDATE文をセット
				statement = connection
						.prepareStatement("update subject set cd=?, name=?, where school_cd=?");
				// プリペアードステートメントに値をバインド
				statement.setString(1, subject.getCd());
				statement.setString(2, subject.getName());
				statement.setObject(3, subject.getSchool());
			}

			// プリペアードステートメントを実行
			count = statement.executeUpdate();

		} catch (Exception e) {
			throw e;
		} finally {
			// プリペアードステートメントを閉じる
			if (statement != null) {
				try {
					statement.close();
				} catch (SQLException sqle) {
					throw sqle;
				}
			}
			// コネクションを閉じる
			if (connection != null) {
				try {
					connection.close();
				} catch (SQLException sqle) {
					throw sqle;
				}
			}
		}

		if (count > 0) {
			// 実行件数が1件以上ある場合
			return true;
		} else {
			// 実行件数が0件の場合
			return false;
		}
	}
	/**
	 * deleteメソッド 科目をデータベースから削除する
	 *
	 * @param subject:subject
	 * @return 成功:true, 失敗:false
	 * @throws Exception
	 */
	public boolean delete(Subject subject) throws Exception {
		// コネクションを確立
		Connection connection = getConnection();
		// プリペアードステートメント
		PreparedStatement statement = null;
		// 実行件数
		int count = 0;

		try {
			// プリペアードステートメントにDELETE文をセット
			statement = connection.prepareStatement("delete from subject where cd=?");
			// プリペアードステートメントに科目番号をバインド
			statement.setString(1, subject.getCd());
			// プリペアードステートメントを実行
			count = statement.executeUpdate();
		} catch (Exception e) {
			throw e;
		} finally {
			// プリペアードステートメントを閉じる
			if (statement != null) {
				try {
					statement.close();
				} catch (SQLException sqle) {
					throw sqle;
				}
			}
			// コネクションを閉じる
			if (connection != null) {
				try {
					connection.close();
				} catch (SQLException sqle) {
					throw sqle;
				}
			}
		}

		if (count > 0) {
			// 実行件数が1件以上ある場合
			return true;
		} else {
			// 実行件数が0件の場合
			return false;
		}
	}

}
>>>>>>> refs/remotes/origin/master
>>>>>>> branch 'master' of https://github.com/Jampan-It/Exam.git
