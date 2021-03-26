package ru.javawebinar.topjava.repository.jdbc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.support.DataAccessUtils;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;
import ru.javawebinar.topjava.model.Role;
import ru.javawebinar.topjava.model.User;
import ru.javawebinar.topjava.repository.UserRepository;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.*;
import java.util.stream.Collectors;

@Repository
public class JdbcUserRepository implements UserRepository {

    private static final BeanPropertyRowMapper<User> ROW_MAPPER = BeanPropertyRowMapper.newInstance(User.class);

    private final JdbcTemplate jdbcTemplate;

    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    private final SimpleJdbcInsert insertUser;

    @Autowired
    public JdbcUserRepository(JdbcTemplate jdbcTemplate, NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.insertUser = new SimpleJdbcInsert(jdbcTemplate)
                .withTableName("users")
                .usingGeneratedKeyColumns("id");

        this.jdbcTemplate = jdbcTemplate;
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    @Override
    public User save(User user) {
        BeanPropertySqlParameterSource parameterSource = new BeanPropertySqlParameterSource(user);

        if (user.isNew()) {
            Number newKey = insertUser.executeAndReturnKey(parameterSource);
            user.setId(newKey.intValue());
            List<Role> rolesList = user.getRoles().stream().collect(Collectors.toList());
            jdbcTemplate.batchUpdate("insert into user_roles (user_id, role) values (?,?)", new BatchPreparedStatementSetter() {
                @Override
                public void setValues(PreparedStatement ps, int i) throws SQLException {
                    ps.setInt(1, user.getId());
                    ps.setString(2, rolesList.get(i).toString());
                }

                @Override
                public int getBatchSize() {
                    return user.getRoles().size();
                }
            });
        } else if (namedParameterJdbcTemplate.update("""
                   UPDATE users SET name=:name, email=:email, password=:password, 
                   registered=:registered, enabled=:enabled, calories_per_day=:caloriesPerDay WHERE id=:id
                """, parameterSource) == 0) {
            return null;
        }
        return user;
    }

    @Override
    public boolean delete(int id) {
        return jdbcTemplate.update("DELETE FROM users WHERE id=?", id) != 0;
    }

    @Override
    public User get(int id) {
        List<User> users = jdbcTemplate.query("SELECT u.*, ur.role as roles FROM users u LEFT JOIN user_roles ur ON ur.user_id=u.id WHERE id=?", ROW_MAPPER, id);
        return DataAccessUtils.singleResult(mergeUser(users));
    }

    /*
     * SELECT u.*, ur.role as role FROM users u LEFT JOIN user_roles ur ON ur.user_id = u.id;
     * SELECT u.*, ur.role FROM users u, user_roles ur WHERE u.id = ur.user_id;
     * */
    @Override
    public User getByEmail(String email) {
//        return jdbcTemplate.queryForObject("SELECT * FROM users WHERE email=?", ROW_MAPPER, email);
        List<User> users = jdbcTemplate.query("SELECT u.*, ur.role as roles FROM users u LEFT JOIN user_roles ur ON ur.user_id=u.id WHERE email=?", ROW_MAPPER, email);
        if (users.isEmpty()) return DataAccessUtils.singleResult(users);
        users = mergeUser(users);
        return DataAccessUtils.singleResult(users);
    }

    @Override
    public List<User> getAll() {
        List<User> list = jdbcTemplate.query("SELECT u.*, ur.role as roles FROM users u LEFT JOIN user_roles ur ON ur.user_id = u.id ORDER BY name, email", ROW_MAPPER);
        return mergeUser(list);
    }

    private List<User> mergeUser(List<User> users) {
        if (users.isEmpty()) return null;
        //users.sort(Comparator.comparing(User::getId));
        List<User> result = new ArrayList<>();
        Set<Role> roles = new HashSet<>();
        for (int i = 0; i < users.size(); i++) {
            User user = users.get(i);
            roles.addAll(user.getRoles());
            if (i != users.size() - 1 && (users.get(i + 1).getId().equals(user.getId()))) {
                roles.addAll(users.get(i + 1).getRoles());
            } else {
                user.setRoles(roles);
                result.add(user);
                roles.clear();
            }
        }
        return result;
    }
}
