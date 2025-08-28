package io.github.uggen.dao;

import io.github.uggen.entity.TaskEntity;

public class DaoProvider {
    public static Dao<Long, TaskEntity> getTaskEntityDao() {
        return new TaskDao();
    }
}
