package site.euan.bester.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import site.euan.bester.domain.model.Blog;

@Repository
public interface BlogRepository extends MongoRepository<Blog, String> {
}
