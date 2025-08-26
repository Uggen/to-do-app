package dao;

import entity.TaskEntity;
import entity.TaskStatus;
import exceptions.DaoException;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class TaskDao implements Dao<Long, TaskEntity> {

    private static final TaskDao INSTANCE = new TaskDao();
    private static final String DELETE_SQL = """
            DELETE
            FROM tasks
            WHERE id = ?
            """;
    public static final String SAVE_SQL = """
            INSERT INTO tasks (id, title, description, status, created_at, updated_at)
            VALUES (?, ?, ?, ?, ?, ?)
            """;
    public static final String UPDATE_SQL = """
            UPDATE tasks
            SET title = ?,
                description = ?,
                status = ?,
                created_at = ?,
                updated_at = ?,
            WHERE id = ?
            """;
    public static final String FIND_ALL_SQL = """
            SELECT id,
                   title,
                   description,
                   status,
                   created_at,
                   updated_at
            FROM tasks
            """;
    public static final String FIND_BY_ID_SQL = FIND_ALL_SQL+  """
            WHERE id = ?
            """;

    @Override
    public List<TaskEntity> findAll() {
        try (var connection = ConnectionPool.get();
             var preparedStatement = connection.prepareStatement(FIND_ALL_SQL)) {
            ResultSet result = preparedStatement.executeQuery();
            List<TaskEntity> tasks = new ArrayList<>();
            while (result.next()){
                tasks.add(buildTasks(result));
            }

        } catch (SQLException e) {
            throw new DaoException(e);
        }
        return List.of();
    }

    private TaskEntity buildTasks(ResultSet result) {
        try{
            return new TaskEntity(
                    result.getLong("id"),
                    result.getString("title"),
                    result.getString("description"),
                    result.getObject("status", TaskStatus.class),
                    result.getObject("created_at", LocalDateTime.class),
                    result.getObject("updated_at", LocalDateTime.class)
            );
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }


    @Override
    public Optional<TaskEntity> findById(Long id) {
        try (var connection = ConnectionPool.get();
             var preparedStatement = connection.prepareStatement(FIND_BY_ID_SQL)) {
            preparedStatement.setLong(1, id);

            var result = preparedStatement.executeQuery();
            TaskEntity taskEntity = null;
            if (result.next()) {
                taskEntity = new TaskEntity(
                        result.getLong("id"),
                        result.getString("title"),
                        result.getString("description"),
                        result.getObject("status", TaskStatus.class),
                        result.getObject("created_at", LocalDateTime.class),
                        result.getObject("updated_at", LocalDateTime.class));
            }
            return Optional.ofNullable(taskEntity);
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public TaskEntity save(TaskEntity entity) {
        try (var connection = ConnectionPool.get();
             var preparedStatement = connection.prepareStatement(SAVE_SQL, Statement.RETURN_GENERATED_KEYS)) {

            preparedStatement.setLong(1, entity.getId());
            preparedStatement.setString(2, entity.getTitle());
            preparedStatement.setString(3, entity.getDescription());
            preparedStatement.setObject(4, entity.getStatus());
            preparedStatement.setObject(5, entity.getCreated_at());
            preparedStatement.setObject(6, entity.getUpdated_at());

            preparedStatement.executeUpdate();
            var generatedKeys = preparedStatement.getGeneratedKeys();
            if (generatedKeys.next()) {
                entity.setId(generatedKeys.getLong("id"));
            }
            return entity;
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public void update(TaskEntity entity) {
        try (var connection = ConnectionPool.get();
             var preparedStatement = connection.prepareStatement(UPDATE_SQL)) {
            preparedStatement.setString(1, entity.getTitle());
            preparedStatement.setString(2, entity.getDescription());
            preparedStatement.setObject(3, entity.getStatus());
            preparedStatement.setObject(4, entity.getCreated_at());
            preparedStatement.setObject(5, entity.getUpdated_at());
            preparedStatement.setLong(6, entity.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public void delete(Long id) {
        try (var connection = ConnectionPool.get();
             var preparedStatement = connection.prepareStatement(DELETE_SQL)) {
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }
}
