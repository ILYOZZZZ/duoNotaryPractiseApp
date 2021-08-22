package uz.pdp.appduonotarypraktikaserver.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import uz.pdp.appduonotarypraktikaserver.entity.Blog;
import uz.pdp.appduonotarypraktikaserver.resModels.ResBlog;

import java.util.UUID;

public interface BlogRepository extends JpaRepository<Blog, UUID> {

    @Query(value = "select cast(b.id as varchar) as id, b.title as title , b.description as description, cast(attachment_id as varchar) as attachmentId from blog b where lower(b.title) LIKE concat('%',lower(:title),'%') order by created_at ",nativeQuery = true)
    Page<ResBlog> getAllBlogs(@Param(value = "title") String title, Pageable pageable);
}
