package com.samuel.urlshortener.presenter.usecase;

import com.samuel.urlshortener.core.usecase.UseCase;
import com.samuel.urlshortener.core.usecase.UseCaseExecutor;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;
import java.util.function.Function;

@Service
public class UseCaseExecutorImpl implements UseCaseExecutor {
    @Override
    public <RX, I extends UseCase.InputValues, O extends UseCase.OutputValues> CompletableFuture<RX> execute(
            UseCase<I, O> useCase, I input, Function<O, RX> outputMapper) {
        return CompletableFuture
                .supplyAsync(() -> input)
                .thenApplyAsync(useCase::execute)
                .thenApplyAsync(outputMapper);
    }
}