package ro.fasttrackit.curs10.homework.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;
import ro.fasttrackit.curs10.homework.entity.Train;
import ro.fasttrackit.curs10.homework.filter.TrainFilter;

import java.util.List;

import static java.util.Optional.ofNullable;
import static org.springframework.data.mongodb.core.query.Query.query;
import static org.springframework.data.support.PageableExecutionUtils.getPage;

@Repository
@RequiredArgsConstructor
public class TrainDao {
    private final MongoTemplate mongo;

    public Page<Train> findBy(TrainFilter filters, Pageable pageable) {
        Criteria criteria = new Criteria();
        ofNullable(filters.locationId())
                .ifPresent(locationId -> criteria.and("locationId").is(locationId));
        ofNullable(filters.modelName())
                .ifPresent(model -> criteria.and("model").is(model));
        ofNullable(filters.minVagoane())
                .ifPresent(minVagoane -> criteria.and("carts").gte(minVagoane));
        ofNullable(filters.maxVagoane())
                .ifPresent(maxVagoane -> criteria.and("carts").lte(maxVagoane));

        Query query = query(criteria).with(pageable);
        List<Train> content = mongo.find(query, Train.class);
        return getPage(content, pageable, () -> mongo.count(query(criteria), Train.class));
    }
}
