package com.example.demo.service;

import com.example.demo.entity.MathExpressions;
import com.example.demo.entity.QMathExpressions;
import com.example.demo.repository.MathExpressionsRepository;
import com.example.demo.service.arg.MathExpressionFindArgs;
import com.google.common.collect.Lists;
import com.querydsl.core.BooleanBuilder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class MathExpressionServiceImpl implements MathExpressionService {

    private final MathExpressionsRepository repository;

    @Autowired
    public MathExpressionServiceImpl(MathExpressionsRepository repository) {
        this.repository = repository;
    }

    @Transactional
    @Override
    public MathExpressions create(int number, String result) {
        MathExpressions entity = new MathExpressions();
        entity.setNumber(number);
        entity.setResult(result);

        return repository.save(entity);
    }

    @Transactional
    @Override
    public List<String> getAll(Pageable pageable) {
        return repository.findAll(pageable)
                         .stream()
                         .map(MathExpressions::getResult)
                         .collect(Collectors.toList());
    }

    @Transactional
    @Override
    public List<MathExpressions> getFiltered(MathExpressionFindArgs args, Pageable pageable) {

        QMathExpressions expression = QMathExpressions.mathExpressions;
        BooleanBuilder builder = new BooleanBuilder();

        if (args.getNumber() != null) builder.and(expression.number.eq(args.getNumber()));
        if (args.getFromDate() != null) builder.and(expression.creationDate.goe(args.getFromDate()));
        if (args.getResult() != null) builder.and(expression.result.containsIgnoreCase(args.getResult()));
        if (args.getToDate() != null) builder.and(expression.creationDate.loe(args.getToDate()));

        assert builder.getValue() != null;
        return Lists.newArrayList(repository.findAll(builder, pageable));
    }


    @Transactional(readOnly = true)
    @Override
    public List<MathExpressions> getBetweenDates(LocalDateTime fromDateTime, LocalDateTime toDateTime) {
        BooleanBuilder builder = new BooleanBuilder();

        builder.and(QMathExpressions.mathExpressions.creationDate.goe(fromDateTime));
        builder.and(QMathExpressions.mathExpressions.creationDate.loe(toDateTime));

        return Lists.newArrayList(repository.findAll(builder));
    }

}
