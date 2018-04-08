
> Ne croyez pas les benchmarks, faites les vous-même !

<https://github.com/MonkeyPatchIo/kotlin-perf>

```
# VM version: JDK 9, VM 9+181
# VM invoker: /Library/Java/JavaVirtualMachines/jdk-9.jdk/Contents/Home/bin/java
# VM options: <none>
# Warmup: 20 iterations, 1 s each
# Measurement: 20 iterations, 1 s each
# Timeout: 10 min per iteration
# Threads: 1 thread, will synchronize iterations
# Benchmark mode: Throughput, ops/time
# Benchmark: io.monkeypatch.talks.MyBenchmark.testJava
```

| Benchmark              |  Mode | Cnt |     Score |    Error  | Units |
|------------------------|-------|-----|-----------|-----------|-------|
| MyBenchmark.testJava   | thrpt | 200 | 66490.271 | ± 879.996 | ops/s |
| MyBenchmark.testKotlin | thrpt | 200 | 72393.914 | ± 935.962 | ops/s |

  