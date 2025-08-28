package io.github.uggen.dao;

import io.github.uggen.entity.TaskEntity;
import io.github.uggen.entity.TaskStatus;
import io.github.uggen.exceptions.DaoException;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class TaskDao implements Dao<Long, TaskEntity> {

    private static final String DELETE_SQL = """
            DELETE
            FROM tasks
            WHERE id = ?
            """;
    private static final String SAVE_SQL = """
            INSERT INTO tasks (title, description, status, created_at, updated_at)
            VALUES (?, ?, ?, ?, ?)
            """;
    private static final String UPDATE_SQL = """
            UPDATE tasks
            SET title = ?,
                description = ?,
                status = ?,
                created_at = ?,
                updated_at = ?
            WHERE id = ?
            """;
    private static final String FIND_ALL_SQL = """
            SELECT id,
                   title,
                   description,
                   status,
                   created_at,
                   updated_at
            FROM tasks
            """;
    private static final String FIND_BY_ID_SQL = FIND_ALL_SQL + """
            WHERE id = ?
            """;
    private static final String FIND_BY_STATUS_SQL = FIND_ALL_SQL + """
            WHERE status = ?
            """;

    @Override
    public List<TaskEntity> findAll() {
        try (var connection = ConnectionPool.get();
             var preparedStatement = connection.prepareStatement(FIND_ALL_SQL)) {
            ResultSet result = preparedStatement.executeQuery();
            List<TaskEntity> tasks = new ArrayList<>();
            while (result.next()) {
                tasks.add(buildTasks(result));
            }
            return tasks;
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    private TaskEntity buildTasks(ResultSet result) {
        try {
            return TaskEntity.restore(
                    result.getLong("id"),
                    result.getString("title"),
                    result.getString("description"),
                    TaskStatus.valueOf(result.getString("status")),
                    result.getObject("created_at", LocalDateTime.class),
                    result.getObject("updated_at", LocalDateTime.class)
            );
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public List<TaskEntity> filterByStatus(TaskStatus taskStatus) {
        try (var connection = ConnectionPool.get();
             var preparedStatement = connection.prepareStatement(FIND_BY_STATUS_SQL)) {
            preparedStatement.setObject(1, taskStatus.name(), Types.OTHER);
            ResultSet result = preparedStatement.executeQuery();
            List<TaskEntity> tasks = new ArrayList<>();
            while (result.next()) {
                tasks.add(buildTasks(result));
            }
            return tasks;
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
            if (result.next()) {
                TaskEntity taskEntity = buildTasks(result);
                return Optional.of(taskEntity);
            }
            return Optional.empty();
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public TaskEntity save(TaskEntity entity) {
        try {
            return entity.isNew()
                    ? insert(entity)
                    : update(entity);
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }
    private TaskEntity insert(TaskEntity entity) throws SQLException {
        try (var connection = ConnectionPool.get();
             var preparedStatement = connection.prepareStatement(SAVE_SQL, Statement.RETURN_GENERATED_KEYS)) {

            preparedStatement.setString(1, entity.getTitle());
            preparedStatement.setString(2, entity.getDescription());
            preparedStatement.setObject(3, entity.getStatus().name(), Types.OTHER);
            preparedStatement.setObject(4, entity.getCreated_at());
            preparedStatement.setObject(5, entity.getUpdated_at());

            preparedStatement.executeUpdate();
            var generatedKeys = preparedStatement.getGeneratedKeys();
            if (generatedKeys.next()) {
                return findById(generatedKeys.getLong("id"))
                        .orElseThrow(()-> new RuntimeException(String.format("Task with id #%s not found after saving", entity.getId()))
                        );
            }
            throw new RuntimeException(String.format("Saving Error. Task id didn't generated for '%s'", entity.getTitle()));
        }
    }

    private TaskEntity update (TaskEntity entity){
        try (var connection = ConnectionPool.get();
             var preparedStatement = connection.prepareStatement(UPDATE_SQL)) {
            preparedStatement.setString(1, entity.getTitle());
            preparedStatement.setString(2, entity.getDescription());
            preparedStatement.setObject(3, entity.getStatus().name(), Types.OTHER);
            preparedStatement.setObject(4, entity.getCreated_at());
            preparedStatement.setObject(5, entity.getUpdated_at());
            preparedStatement.setLong(6, entity.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException(e);
        }
        return entity;
    }

    @Override
    public void delete (Long id){
        try (var connection = ConnectionPool.get();
             var preparedStatement = connection.prepareStatement(DELETE_SQL)) {
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }
}