package com.spring.boot.follow.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.spring.boot.entity.Follow;
import com.spring.boot.entity.FollowId;

@Repository
public interface FollowRepository extends JpaRepository<Follow, FollowId> {
	public List<Follow> findByIdMid(long mid);
	public List<Follow> findByIdTid(long tid);
	public boolean existsByIdMidAndIdTid(long mid, long tid);
	@Query("SELECT "
	         + "COUNT(CASE WHEN f.id.mid = :userId THEN 1 END) AS followingCount, "
	         + "COUNT(CASE WHEN f.id.tid = :userId THEN 1 END) AS followerCount "
	         + "FROM Follow f "
	         + "WHERE f.id.mid = :userId OR f.id.tid = :userId")
	List<Object[]> countFollowerAndFollowing(@Param("userId") Long userId);
}
