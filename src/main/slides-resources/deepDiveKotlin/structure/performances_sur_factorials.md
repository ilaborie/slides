

> Ne croyez pas les benchmarks, faites les vous-même !

<https://github.com/MonkeyPatchIo/kotlin-perf>

Test de `10!`

|Benchmark                          | Mode  | Cnt |         Score |          Error | Units |
|-----------------------------------|-------|-----|---------------|----------------|-------|
|MyBenchmark.factorialJava          | thrpt | 200 | 274141213.561 | ± 28963758.069 | ops/s |
|MyBenchmark.factorialKotlinFor     | thrpt | 200 | 267717955.205 | ±  8457315.205 | ops/s |
|MyBenchmark.factorialKotlinRec     | thrpt | 200 |  56270660.700 | ±  2453418.383 | ops/s |
|MyBenchmark.factorialKotlinTailRec | thrpt | 200 | 341898899.761 | ± 11456349.191 | ops/s |
