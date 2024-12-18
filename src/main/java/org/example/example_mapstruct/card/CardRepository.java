package org.example.example_mapstruct.card;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CardRepository extends JpaRepository<Card, Long> {

    @Query(value = "select * from map_card as mc where mc.user_id=?1",nativeQuery = true)
    List<Card>getAllByUserId(Long userId);
}
