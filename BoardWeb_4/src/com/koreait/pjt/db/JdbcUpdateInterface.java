package com.koreait.pjt.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
					
// 추상클래스의 개념 알기.
public abstract interface JdbcUpdateInterface {
	void update(PreparedStatement ps) throws SQLException;
}
