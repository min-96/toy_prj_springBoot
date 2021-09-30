package org.hdcd.repository;

import org.hdcd.domain.UserItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UserItemRepository extends JpaRepository<UserItem,Long> {

    @Query("select a.userItemNo, a.userNo, a.itemId,a.regDate,b.itemName,b.price,b.description,b.pictureUrl from UserItem a inner join Item b on a.itemId=b.itemId where a.userNo=?1 order by a.userItemNo DESC,a.regDate desc")
    List<Object[]> listUserItem(Long userNo);

    @Query("select a.userItemNo,a.userNo, a.itemId,a.regDate,b.itemName,b.price,b.description,b.pictureUrl from UserItem a inner join Item b on a.itemId=b.itemId where a.userItemNo=?1")
    List<Object[]> readUserItem(Long userItemNo);
}
