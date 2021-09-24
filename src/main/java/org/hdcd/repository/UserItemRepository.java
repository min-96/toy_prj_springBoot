package org.hdcd.repository;

import org.hdcd.domain.UserItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserItemRepository extends JpaRepository<UserItem,Long> {
}
