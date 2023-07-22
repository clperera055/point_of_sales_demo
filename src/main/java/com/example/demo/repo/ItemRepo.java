package com.example.demo.repo;

import com.example.demo.entity.Item;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
@EnableJpaRepositories
@Transactional

public interface ItemRepo extends JpaRepository<Item,Integer> {

    @Modifying
    @Query(value = "update items set balance_qty=?1, supplier_price=?2, selling_price=?3, active_state=?4 where item_id=?5",nativeQuery = true)
    void updateItem(double balanceQty, double supplierPrice, double sellingPrice, boolean activeState, int id);

    List<Item> findAllByActiveStateEquals(boolean b);

    int countAllByActiveStateEquals(boolean val);

    Page<Item> findAllByActiveStateEquals(boolean b, PageRequest pageRequest);

}
