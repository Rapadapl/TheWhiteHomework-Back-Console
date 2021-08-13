package com.example.demo.repository;


import com.example.demo.entity.MathExpressions;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface MathExpressionsRepository extends JpaRepository<MathExpressions, UUID>,
                                                   QuerydslPredicateExecutor<MathExpressions> {
}
