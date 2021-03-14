package cn.mallwe.course.repository;

import cn.mallwe.course.entity.Course;
import cn.mallwe.course.query.CourseQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.beans.BeanMap;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Service;

import java.text.MessageFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class CourseRepository {
    @Autowired
    private NamedParameterJdbcTemplate jdbcTemplate;

    private final String INSERT_SQL = "INSERT INTO COURSE(CID, NAME, TIME_PLAN,GRADE,SUBJECT,AF_AMOUNT,STUDENT_TOTAL,REAL_TOTAL_APPLY,TEACHER_NAME,TUTOR_NAME)" +
            " VALUES (:cid,:name,:timePlan,:grade,:subject,:afAmount,:studentTotal,:realTotalApply,:teacherName,:tutorName);";

    private final String QUERY_SQL = "SELECT * FROM COURSE WHERE 1=1";
    private final String COUNT_SQL = "SELECT count(1) FROM COURSE WHERE 1=1";
    private final String SELECT_SQL = "SELECT * FROM COURSE WHERE CID=:cid";

    public boolean addCourse(Course course) {
        Map paramMap = BeanMap.create(course);
        int rowNum = jdbcTemplate.update(INSERT_SQL, paramMap);
        return rowNum > 0;
    }

    public List<Course> query(CourseQuery query) {
        StringBuilder sb = new StringBuilder(QUERY_SQL);
        if (query.getGrade() != null) {
            sb.append(MessageFormat.format(" AND GRADE = {0} ", query.getGrade().toString()));
        }
        if (query.getSubject() != null) {
            sb.append(MessageFormat.format(" AND SUBJECT = {0} ", query.getSubject().toString()));
        }
        sb.append(MessageFormat.format(" LIMIT {0} ,{1}", (query.getPageNum() - 1) * query.getPageSize(), query.getPageSize()));
        return jdbcTemplate.query(sb.toString(), new BeanPropertyRowMapper<>(Course.class));
    }

    public int count(CourseQuery query) {
        StringBuilder sb = new StringBuilder(COUNT_SQL);
        if (query.getGrade() != null) {
            sb.append(MessageFormat.format(" AND GRADE = {0} ", query.getGrade().toString()));
        }
        if (query.getSubject() != null) {
            sb.append(MessageFormat.format(" AND SUBJECT = {0} ", query.getSubject().toString()));
        }

        return jdbcTemplate.queryForObject(sb.toString(), new HashMap(), Integer.class);
    }

    public Optional<Course> getById(Integer cid) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("cid", cid);
        Course course;
        try {
            course = jdbcTemplate.queryForObject(SELECT_SQL, map, new BeanPropertyRowMapper<>(Course.class));
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
        return Optional.ofNullable(course);
    }
}
