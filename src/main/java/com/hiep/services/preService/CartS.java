package com.hiep.services.preService;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hiep.models.Cart;
import com.hiep.repositories.CartRepository;


@Service
public interface CartS extends CartRepository {

    @Query(value = "FROM Cart AS c WHERE c.user.id=:id")
    Iterable<Cart> findAllByUserId(Long id);

    @Query(value = "FROM Cart AS c WHERE c.product_id=:id and  c.user.id=:user_id")
    Cart findProduct(Long user_id, Long id);

    @Modifying
    @Transactional
    @Query(value = "UPDATE Cart SET quantity = quantity + 1, total = cast(total + price as double) WHERE user.id = :userId AND product_id = :productId")
    void updateQuantity(@Param("userId")Long userId, @Param("productId")Long productId);

    @Modifying
    @Transactional
    @Query(value = "UPDATE Cart SET quantity = :quantity, total = cast( :quantity * price as double) WHERE user.id = :userId AND product_id = :productId")
    void updateQuantity(@Param("userId")Long userId, @Param("productId")Long productId, int quantity);

    @Query(value = "FROM Cart AS c WHERE c.user.id=:userId and c.product_id=:productId")
    Cart findByUserIdAndProductId(@Param("userId")Long userId, @Param("productId") Long productId);

    @Query("SELECT SUM(c.total) FROM Cart c WHERE c.user.id = :userId")
    Double totalCartBy(Long userId);
}
