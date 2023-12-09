package com.pray2you.p2uhomepage.domain.point.dao;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.time.LocalDateTime;

@Component
public class RankDao {
    private JdbcTemplate jdbcTemplate;

    public RankDao(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public int calculatePreviousRank(LocalDateTime previousRank) {
        return jdbcTemplate.update("update `rank` p inner join (\n" +
                "  select *,\n" +
                "    rank()  over (order by week_point desc) as rn\n" +
                "  from `rank`\n" +
                "  where week_point is not null AND end_date >= ? AND start_date <= ?" +
                ") pp on pp.rank_id = p.rank_id\n" +
                "set p.ranking = pp.rn", previousRank, previousRank);
    }
}
