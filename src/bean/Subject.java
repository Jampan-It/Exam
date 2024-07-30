package bean;

import java.io.Serializable;

public class Subject implements Serializable {
	/**
<<<<<<< HEAD
	 *
	 */
	private String cd;

	/**
	 * 氏名:String
	 */
	private String name;

	/**
	 * 所属校:School
	 */
	private School school;

	/**
	 * ゲッター、セッター
	 */
	public String getCd() {
		return cd;
	}

	public void setCd(String cd) {
		this.cd = cd;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}


	public School getSchool() {
		return school;
	}

	public void setSchool(School school) {
		this.school = school;
	}

=======
	 * 科目コード:String
	 */
<<<<<<< HEAD
	private String cd;

	/**
	 * 科目名:String
	 */
	private String name;


	/**
	 * 所属校:School
	 */
	private School school;


	public String getCd() {
		return cd;
	}

	public void setCd(String cd) {
		this.cd = cd;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public School getSchool() {
		return school;
	}

	public void setSchool(School school) {
		this.school = school;
	}


=======
	private String cd ;

	/**
	 * 科目名:String
	 */
	private String name;

	/**
	 * 学校コード:Scool
	 */
	private School school;
	/**
	 * ゲッター、セッター
	 */
	public String getCd() {
		return cd;
	}

	public void setCd(String cd) {
		this.cd = cd;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public School getSchool() {
		return school;
	}

	public void setSchool(School school) {
		this.school = school;
	}
>>>>>>> refs/remotes/origin/master
>>>>>>> branch 'master' of https://github.com/Jampan-It/Exam.git
}